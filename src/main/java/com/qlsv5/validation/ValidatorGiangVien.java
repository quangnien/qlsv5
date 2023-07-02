package com.qlsv5.validation;

import com.qlsv5.common.FunctionCommon;
import com.qlsv5.constant.MasterDataExceptionConstant;
import com.qlsv5.dto.GiangVienDto;
import com.qlsv5.dto.TkbDto;
import com.qlsv5.dto.UpdatePasswordDto;
import com.qlsv5.entity.GiangVienEntity;
import com.qlsv5.entity.UserEntity;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.repository.KhoaRepository;
import com.qlsv5.repository.GiangVienRepository;
import com.qlsv5.service.CommonService;
import com.qlsv5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

/**
 * @author NienNQ
 * @created 2023 - 03 - 05 11:02 AM
 * @project qlsv
 */
@Component
public class ValidatorGiangVien implements Validator {

    @Autowired
    private GiangVienRepository giangVienRepository;
    @Autowired
    private KhoaRepository khoaRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CommonService commonService;

    @Autowired
    private FunctionCommon functionCommon;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    @Transactional
    public void validateAddGiangVien(Object target) throws BusinessException {
        GiangVienDto giangVienDto = (GiangVienDto) target;

        int countMaGiangVien = giangVienRepository.countGiangVienByMaGv(giangVienDto.getMaGv());
        int countKhoaByMaKhoa = khoaRepository.countKhoaByMaKhoa(giangVienDto.getMaKhoa());
        int countEmail = giangVienRepository.countGiangVienByEmail(giangVienDto.getEmail());

        if (countKhoaByMaKhoa == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_KHOA_NOT_FOUND_KHOA);
        }
        else if (countMaGiangVien > 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_DUPLICATE_MA_GIANGVIEN);
        }
        else if (countEmail > 0) {
            throw new BusinessException(MasterDataExceptionConstant.COMMON_EMAIL_IS_EXIST);
        }
        else if(functionCommon.isValidEmailFormat(giangVienDto.getEmail()) == false){
            throw new BusinessException(MasterDataExceptionConstant.COMMON_EMAIL_WRONG_FORMAT);
        }
    }

    @Transactional
    public void validateEditGiangVien(Object target) throws BusinessException {
        GiangVienDto giangVienDto = (GiangVienDto) target;

        if(giangVienDto.getId() == null){
            throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
        }

        Optional<GiangVienEntity> giangVienEntity = giangVienRepository.findById(giangVienDto.getId());
        int countKhoaByMaKhoa = khoaRepository.countKhoaByMaKhoa(giangVienDto.getMaKhoa());

        if (giangVienEntity.isPresent() == false) {
            throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
        }
        else if (countKhoaByMaKhoa == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_KHOA_NOT_FOUND_KHOA);
        }
        else {
            Long countMaGiangVien = giangVienRepository.countGiangVienByMaGvAndNotId(giangVienDto.getMaGv(), giangVienDto.getId());
            long countValue = countMaGiangVien != null ? countMaGiangVien : 0;

            Long countGiangVienByEmail = giangVienRepository.countGiangVienByEmailAndNotId(giangVienDto.getEmail(), giangVienDto.getId());
            long countValueByEmail = countGiangVienByEmail != null ? countGiangVienByEmail : 0;

            if (countValue > 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_DUPLICATE_MA_GIANGVIEN);
            }
            else if (countValueByEmail > 0) {
                throw new BusinessException(MasterDataExceptionConstant.COMMON_EMAIL_IS_EXIST);
            }
            else if(functionCommon.isValidEmailFormat(giangVienDto.getEmail()) == false){
                throw new BusinessException(MasterDataExceptionConstant.COMMON_EMAIL_WRONG_FORMAT);
            }
        }
    }

    @Transactional
    public void validateGetGiangVienById(String giangVienId) throws BusinessException {

        int countMaGiangVien = giangVienRepository.countGiangVienById(giangVienId);

        if (countMaGiangVien == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
        }
    }

    @Transactional
    public void validateGetListGiangVienByMaKhoa(String maKhoa) throws BusinessException {

        if(maKhoa == null || "".equals(maKhoa)){
            throw new BusinessException(MasterDataExceptionConstant.E_KHOA_NOT_FOUND_KHOA);
        }
        else {
            int countKhoaByMaKhoa = khoaRepository.countKhoaByMaKhoa(maKhoa);

            if (countKhoaByMaKhoa == 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_KHOA_NOT_FOUND_KHOA);
            }
        }

    }

    @Transactional
    public void validateUpdatePasswordGiangVien(UpdatePasswordDto updatePasswordDto) throws BusinessException {

//        if(updatePasswordDto.getId() == null){
//            throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
//        }
        if(updatePasswordDto.getUserName() == null){
            throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
        }
        else if(updatePasswordDto.getMatKhauCu() == null || updatePasswordDto.getMatKhauCu().equals("")){
            throw new BusinessException(MasterDataExceptionConstant.E_COMMON_NOT_PASSWORD_OLD);
        }
        else if(updatePasswordDto.getMatKhauMoi() == null || updatePasswordDto.getMatKhauMoi().equals("")){
            throw new BusinessException(MasterDataExceptionConstant.E_COMMON_NOT_PASSWORD_NEW);
        }
//        else if(updatePasswordDto.getMatKhau() == null || updatePasswordDto.getMatKhau().equals("")){
//            throw new BusinessException(MasterDataExceptionConstant.E_COMMON_NOT_PASSWORD);
//        }
//        else if(updatePasswordDto.getConfirmPassword() == null || updatePasswordDto.getConfirmPassword().equals("")){
//            throw new BusinessException(MasterDataExceptionConstant.E_COMMON_NOT_CONFIRM_PASSWORD);
//        }
        else {
//            UserEntity userEntity = userService.findById(updatePasswordDto.getId());
            UserEntity userEntity = userService.findByUsername(updatePasswordDto.getUserName());

            if(userEntity == null){
                throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
            }
            else {
                // maSV
                String userName = userEntity.getUsername();

                GiangVienEntity getGiangVienByDB = (GiangVienEntity) giangVienRepository.findByMaGv(userName);

                if(getGiangVienByDB == null){
                    throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
                }
                else {
                    String decodedPassword = updatePasswordDto.getMatKhauCu();
                    if (! encoder.matches(decodedPassword, userEntity.getPassword())) {
                        throw new BusinessException(MasterDataExceptionConstant.E_COMMON_NOT_MATCH_PASSWORD);
                    }
                }
            }
//            int countMaGiangVien = giangVienRepository.countGiangVienById(updatePasswordDto.getId());
//
//            GiangVienEntity getGiangVienByDB = (GiangVienEntity) commonService.getObjectById(updatePasswordDto.getId(), new GiangVienDto());
//            UserEntity userEntity = userService.findByUsername(getGiangVienByDB.getMaGv());
//
//            if (countMaGiangVien == 0) {
//                throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
//            }
////            else if(updatePasswordDto.getConfirmPassword().equals(updatePasswordDto.getMatKhau()) == false){
////                throw new BusinessException(MasterDataExceptionConstant.E_COMMON_NOT_EQUAL_CONFIRM_PASSWORD);
////            }
//            else if (userEntity == null) {
//                throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
//            }
//            else {
//                String decodedPassword = bCryptPasswordEncoder.encode(updatePasswordDto.getMatKhauCu());
//                if (! bCryptPasswordEncoder.matches(userEntity.getPassword(), decodedPassword)) {
//                    throw new BusinessException(MasterDataExceptionConstant.E_COMMON_NOT_MATCH_PASSWORD);
//                }
//            }
        }
    }

    @Transactional
    public void validateGetTKBForGiangVien(Object target) throws BusinessException {

        TkbDto tkbDto = (TkbDto) target;

        if (tkbDto.getTimeInputBegin() == null) {
            throw new BusinessException(MasterDataExceptionConstant.E_TKB_NOT_FOUND_DATE_BEGIN);
        }
        else if (tkbDto.getTimeInputEnd() == null) {
            throw new BusinessException(MasterDataExceptionConstant.E_TKB_NOT_FOUND_DATE_END);
        }
    }

}
