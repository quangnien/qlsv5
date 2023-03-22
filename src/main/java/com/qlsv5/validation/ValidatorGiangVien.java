package com.qlsv5.validation;

import com.qlsv5.constant.MasterDataExceptionConstant;
import com.qlsv5.dto.GiangVienDto;
import com.qlsv5.entity.GiangVienEntity;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.repository.LopRepository;
import com.qlsv5.repository.GiangVienRepository;
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
    private LopRepository lopRepository;

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
        int countLopByMaLop = lopRepository.countLopByMaLop(giangVienDto.getMaKhoa());

        if (countLopByMaLop == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_KHOA_NOT_FOUND_KHOA);
        }
        else if (countMaGiangVien > 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_DUPLICATE_MA_GIANGVIEN);
        }
    }

    @Transactional
    public void validateEditGiangVien(Object target) throws BusinessException {
        GiangVienDto giangVienDto = (GiangVienDto) target;

        if(giangVienDto.getId() == null){
            throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
        }

        Optional<GiangVienEntity> giangVienEntity = giangVienRepository.findById(giangVienDto.getId());
        int countLopByMaLop = lopRepository.countLopByMaLop(giangVienDto.getMaKhoa());

        if (giangVienEntity.isPresent() == false) {
            throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
        }
        else if (countLopByMaLop == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_KHOA_NOT_FOUND_KHOA);
        }
        else {
            Long countMaGiangVien = giangVienRepository.countGiangVienByMaGvAndNotId(giangVienDto.getMaGv(), giangVienDto.getId());
            long countValue = countMaGiangVien != null ? countMaGiangVien : 0;
            if (countValue > 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_DUPLICATE_MA_GIANGVIEN);
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

}
