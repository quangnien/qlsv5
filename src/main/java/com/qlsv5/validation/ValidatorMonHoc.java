package com.qlsv5.validation;

import com.qlsv5.common.FunctionCommon;
import com.qlsv5.constant.MasterDataExceptionConstant;
import com.qlsv5.dto.MonHocDto;
import com.qlsv5.entity.MonHocEntity;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.repository.MonHocRepository;
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
public class ValidatorMonHoc implements Validator {

    @Autowired
    private MonHocRepository monHocRepository;

    @Autowired
    private FunctionCommon functionCommon;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    @Transactional
    public void validateAddMonHoc(Object target) throws BusinessException {
        MonHocDto monHocDto = (MonHocDto) target;

        int countMaMonHoc = monHocRepository.countMonHocByMaMh(monHocDto.getMaMh());

        if (countMaMonHoc > 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_MONHOC_DUPLICATE_MA_MONHOC);
        }
    }

    @Transactional
    public void validateEditMonHoc(Object target) throws BusinessException {
        MonHocDto monHocDto = (MonHocDto) target;

        if(monHocDto.getId() == null){
            throw new BusinessException(MasterDataExceptionConstant.E_MONHOC_NOT_FOUND_MONHOC);
        }

        Optional<MonHocEntity> monHocEntity = monHocRepository.findById(monHocDto.getId());

        if (monHocEntity.isPresent() == false) {
            throw new BusinessException(MasterDataExceptionConstant.E_MONHOC_NOT_FOUND_MONHOC);
        }
        else {
            Long countMaMonHoc = monHocRepository.countMonHocByMaMhAndNotId(monHocDto.getMaMh(), monHocDto.getId());
            long countValue = countMaMonHoc != null ? countMaMonHoc : 0;
            if (countValue > 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_MONHOC_DUPLICATE_MA_MONHOC);
            }
        }
    }

    @Transactional
    public void validateGetMonHocById(String monHocId) throws BusinessException {

        int countMaMonHoc = monHocRepository.countMonHocById(monHocId);

        if (countMaMonHoc == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_MONHOC_NOT_FOUND_MONHOC);
        }
    }

}
