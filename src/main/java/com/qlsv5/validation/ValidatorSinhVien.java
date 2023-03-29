package com.qlsv5.validation;

import com.qlsv5.constant.MasterDataExceptionConstant;
import com.qlsv5.dto.SinhVienDto;
import com.qlsv5.entity.SinhVienEntity;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.repository.LopRepository;
import com.qlsv5.repository.SinhVienRepository;
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
public class ValidatorSinhVien implements Validator {

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Autowired
    private LopRepository lopRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    @Transactional
    public void validateAddSinhVien(Object target) throws BusinessException {
        SinhVienDto sinhVienDto = (SinhVienDto) target;

        int countMaSinhVien = sinhVienRepository.countSinhVienByMaSv(sinhVienDto.getMaSv());
        int countEmail = sinhVienRepository.countSinhVienByEmail(sinhVienDto.getEmail());
        int countLopByMaLop = lopRepository.countLopByMaLop(sinhVienDto.getMaLop());

        if (countLopByMaLop == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_LOP_NOT_FOUND_LOP);
        }
        else if (countMaSinhVien > 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_DUPLICATE_MA_SINHVIEN);
        }
        else if (countEmail > 0) {
            throw new BusinessException(MasterDataExceptionConstant.COMMON_EMAIL_IS_EXIST);
        }
    }

    @Transactional
    public void validateEditSinhVien(Object target) throws BusinessException {
        SinhVienDto sinhVienDto = (SinhVienDto) target;

        if(sinhVienDto.getId() == null){
            throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
        }

        Optional<SinhVienEntity> sinhVienEntity = sinhVienRepository.findById(sinhVienDto.getId());
        int countLopByMaLop = lopRepository.countLopByMaLop(sinhVienDto.getMaLop());

        if (sinhVienEntity.isPresent() == false) {
            throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
        }
        else if (countLopByMaLop == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_KHOA_NOT_FOUND_KHOA);
        }
        else {
            Long countSinhVienByMaSv = sinhVienRepository.countSinhVienByMaSvAndNotId(sinhVienDto.getMaSv(), sinhVienDto.getId());
            long countValueByMaSv = countSinhVienByMaSv != null ? countSinhVienByMaSv : 0;

            Long countSinhVienByEmail = sinhVienRepository.countSinhVienByEmailAndNotId(sinhVienDto.getEmail(), sinhVienDto.getId());
            long countValueByEmail = countSinhVienByEmail != null ? countSinhVienByEmail : 0;
            if (countValueByMaSv > 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_DUPLICATE_MA_SINHVIEN);
            }
            else if (countValueByEmail > 0) {
                throw new BusinessException(MasterDataExceptionConstant.COMMON_EMAIL_IS_EXIST);
            }
        }
    }

    @Transactional
    public void validateGetSinhVienById(String sinhVienId) throws BusinessException {

        int countMaSinhVien = sinhVienRepository.countSinhVienById(sinhVienId);

        if (countMaSinhVien == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
        }
    }

    @Transactional
    public void validateGetListSinhVienByMaLop(String maLop) throws BusinessException {

        if(maLop == null || "".equals(maLop)){
            throw new BusinessException(MasterDataExceptionConstant.E_LOP_NOT_FOUND_LOP);
        }
        else {
            int countLopByMaLop = lopRepository.countLopByMaLop(maLop);

            if (countLopByMaLop == 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_LOP_NOT_FOUND_LOP);
            }
        }

    }

}
