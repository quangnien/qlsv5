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
        int countLopByMaLop = lopRepository.countLopByMaLop(sinhVienDto.getMaLop());

        if (countLopByMaLop == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_KHOA_NOT_FOUND_KHOA);
        }
        else if (countMaSinhVien > 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_DUPLICATE_MA_SINHVIEN);
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
            Long countMaSinhVien = sinhVienRepository.countSinhVienByMaSvAndNotId(sinhVienDto.getMaSv(), sinhVienDto.getId());
            long countValue = countMaSinhVien != null ? countMaSinhVien : 0;
            if (countValue > 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_DUPLICATE_MA_SINHVIEN);
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

}
