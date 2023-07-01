package com.qlsv5.validation;

import com.qlsv5.common.FunctionCommon;
import com.qlsv5.constant.MasterDataExceptionConstant;
import com.qlsv5.dto.KhoaDto;
import com.qlsv5.entity.KhoaEntity;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.service.impl.repository.KhoaRepository;
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
public class ValidatorKhoa implements Validator {

    @Autowired
    private KhoaRepository khoaRepository;

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
    public void validateAddKhoa(Object target) throws BusinessException {
        KhoaDto khoaDto = (KhoaDto) target;

        int countMaKhoa = khoaRepository.countKhoaByMaKhoa(khoaDto.getMaKhoa());
        int countEmail = khoaRepository.countKhoaByEmail(khoaDto.getEmail());

        if (countMaKhoa > 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_KHOA_DUPLICATE_MA_KHOA);
        }
        else if(functionCommon.isValidEmailFormat(khoaDto.getEmail()) == false){
            throw new BusinessException(MasterDataExceptionConstant.COMMON_EMAIL_WRONG_FORMAT);
        }
        else if (countEmail > 0) {
            throw new BusinessException(MasterDataExceptionConstant.COMMON_EMAIL_IS_EXIST);
        }
    }

    @Transactional
    public void validateEditKhoa(Object target) throws BusinessException {
        KhoaDto khoaDto = (KhoaDto) target;

        if(khoaDto.getId() == null){
            throw new BusinessException(MasterDataExceptionConstant.E_KHOA_NOT_FOUND_KHOA);
        }

        Optional<KhoaEntity> khoaEntity = khoaRepository.findById(khoaDto.getId());

        if (khoaEntity.isPresent() == false) {
            throw new BusinessException(MasterDataExceptionConstant.E_KHOA_NOT_FOUND_KHOA);
        }
        else if(functionCommon.isValidEmailFormat(khoaDto.getEmail()) == false){
            throw new BusinessException(MasterDataExceptionConstant.COMMON_EMAIL_WRONG_FORMAT);
        }
        else {
            Long countMaKhoaByMaKhoa = khoaRepository.countKhoaByMaKhoaAndNotId(khoaDto.getMaKhoa(), khoaDto.getId());
            long countValue = countMaKhoaByMaKhoa != null ? countMaKhoaByMaKhoa : 0;

            Long countKhoaByEmail = khoaRepository.countKhoaByEmailAndNotId(khoaDto.getEmail(), khoaDto.getId());
            long countValueByEmail = countKhoaByEmail != null ? countKhoaByEmail : 0;

            if (countValue > 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_KHOA_DUPLICATE_MA_KHOA);
            }
            else if (countValueByEmail > 0) {
                throw new BusinessException(MasterDataExceptionConstant.COMMON_EMAIL_IS_EXIST);
            }
        }
    }

    @Transactional
    public void validateGetKhoaById(String khoaId) throws BusinessException {

        int countMaKhoa = khoaRepository.countKhoaById(khoaId);

        if (countMaKhoa == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_KHOA_NOT_FOUND_KHOA);
        }
    }

}
