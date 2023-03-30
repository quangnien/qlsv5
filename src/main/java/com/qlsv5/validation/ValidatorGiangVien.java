package com.qlsv5.validation;

import com.qlsv5.constant.MasterDataExceptionConstant;
import com.qlsv5.dto.GiangVienDto;
import com.qlsv5.dto.SinhVienDto;
import com.qlsv5.dto.UpdatePasswordDto;
import com.qlsv5.entity.GiangVienEntity;
import com.qlsv5.entity.SinhVienEntity;
import com.qlsv5.entity.UserEntity;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.repository.KhoaRepository;
import com.qlsv5.repository.LopRepository;
import com.qlsv5.repository.GiangVienRepository;
import com.qlsv5.service.CommonService;
import com.qlsv5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

        if(updatePasswordDto.getId() == null){
            throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
        }
        else if(updatePasswordDto.getMatKhau() == null || updatePasswordDto.getMatKhau().equals("")){
            throw new BusinessException(MasterDataExceptionConstant.E_COMMON_NOT_PASSWORD);
        }
        else if(updatePasswordDto.getConfirmPassword() == null || updatePasswordDto.getConfirmPassword().equals("")){
            throw new BusinessException(MasterDataExceptionConstant.E_COMMON_NOT_CONFIRM_PASSWORD);
        }
        else {
            int countMaGiangVien = giangVienRepository.countGiangVienById(updatePasswordDto.getId());

            GiangVienEntity getGiangVienByDB = (GiangVienEntity) commonService.getObjectById(updatePasswordDto.getId(), new GiangVienDto());
            UserEntity userEntity = userService.findByUsername(getGiangVienByDB.getMaGv());

            if (countMaGiangVien == 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
            }
            else if(updatePasswordDto.getConfirmPassword().equals(updatePasswordDto.getMatKhau()) == false){
                throw new BusinessException(MasterDataExceptionConstant.E_COMMON_NOT_EQUAL_CONFIRM_PASSWORD);
            }
            else if (userEntity == null) {
                throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
            }
        }
    }

}
