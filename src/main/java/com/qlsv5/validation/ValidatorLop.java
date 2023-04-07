package com.qlsv5.validation;

import com.qlsv5.common.FunctionCommon;
import com.qlsv5.constant.MasterDataExceptionConstant;
import com.qlsv5.dto.LopDto;
import com.qlsv5.entity.KhoaEntity;
import com.qlsv5.entity.LopEntity;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.repository.KhoaRepository;
import com.qlsv5.repository.LopRepository;
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
public class ValidatorLop implements Validator {

    @Autowired
    private LopRepository lopRepository;

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
    public void validateAddLop(Object target) throws BusinessException {
        LopDto lopDto = (LopDto) target;

        int countMaLop = lopRepository.countLopByMaLop(lopDto.getMaLop());
        int countKhoaByMaKhoa = khoaRepository.countKhoaByMaKhoa(lopDto.getMaKhoa());

        if (countKhoaByMaKhoa == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_KHOA_NOT_FOUND_KHOA);
        }
        else if (countMaLop > 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_LOP_DUPLICATE_MA_LOP);
        }
    }

    @Transactional
    public void validateEditLop(Object target) throws BusinessException {
        LopDto lopDto = (LopDto) target;

        if(lopDto.getId() == null){
            throw new BusinessException(MasterDataExceptionConstant.E_LOP_NOT_FOUND_LOP);
        }

        Optional<LopEntity> lopEntity = lopRepository.findById(lopDto.getId());
        int countKhoaByMaKhoa = khoaRepository.countKhoaByMaKhoa(lopDto.getMaKhoa());

        if (lopEntity.isPresent() == false) {
            throw new BusinessException(MasterDataExceptionConstant.E_LOP_NOT_FOUND_LOP);
        }
        else if (countKhoaByMaKhoa == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_KHOA_NOT_FOUND_KHOA);
        }
        else {
            Long countMaLop = lopRepository.countLopByMaLopAndNotId(lopDto.getMaLop(), lopDto.getId());
            long countValue = countMaLop != null ? countMaLop : 0;
            if (countValue > 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_LOP_DUPLICATE_MA_LOP);
            }
        }
    }

    @Transactional
    public void validateGetLopById(String lopId) throws BusinessException {

        int countMaLop = lopRepository.countLopById(lopId);

        if (countMaLop == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_LOP_NOT_FOUND_LOP);
        }
    }

    @Transactional
    public void validateGetListLopByMaKhoa(String maKhoa) throws BusinessException {

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

}
