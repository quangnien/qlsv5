package com.qlsv5.validation;

import com.qlsv5.common.FunctionCommon;
import com.qlsv5.constant.MasterDataExceptionConstant;
import com.qlsv5.dto.MonHocDto;
import com.qlsv5.entity.MonHocEntity;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.service.impl.repository.KhoaRepository;
import com.qlsv5.service.impl.repository.MHTQRepository;
import com.qlsv5.service.impl.repository.MonHocRepository;
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
    private KhoaRepository khoaRepository;
    @Autowired
    private FunctionCommon functionCommon;
    @Autowired
    private MHTQRepository mhtqRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    @Transactional
    public void validateAddMonHoc(Object target) throws BusinessException {
        MonHocEntity monHocEntity = (MonHocEntity) target;

        int countMaMonHoc = monHocRepository.countMonHocByMaMh(monHocEntity.getMaMh());
        int countMaKhoa = khoaRepository.countKhoaByMaKhoa(monHocEntity.getMaKhoa());

        if (countMaMonHoc > 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_MONHOC_DUPLICATE_MA_MONHOC);
        }
        else if(countMaKhoa == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_KHOA_NOT_FOUND_KHOA);
        }
    }

    @Transactional
    public void validateEditMonHoc(Object target) throws BusinessException {
        MonHocDto monHocDto = (MonHocDto) target;

        if(monHocDto.getId() == null){
            throw new BusinessException(MasterDataExceptionConstant.E_MONHOC_NOT_FOUND_MONHOC);
        }
        else if(monHocDto.getMaKhoa() == null){
            throw new BusinessException(MasterDataExceptionConstant.E_KHOA_DUPLICATE_MA_KHOA);
        }

        Optional<MonHocEntity> monHocEntity = monHocRepository.findById(monHocDto.getId());

        if (monHocEntity.isPresent() == false) {
            throw new BusinessException(MasterDataExceptionConstant.E_MONHOC_NOT_FOUND_MONHOC);
        }
        else {
            Long countMaMonHoc = monHocRepository.countMonHocByMaMhAndNotId(monHocDto.getMaMh(), monHocDto.getId());
            int countMaKhoa = khoaRepository.countKhoaByMaKhoa(monHocDto.getMaKhoa());
            long countValue = countMaMonHoc != null ? countMaMonHoc : 0;
            if (countValue > 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_MONHOC_DUPLICATE_MA_MONHOC);
            }
            else if(countMaKhoa == 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_KHOA_NOT_FOUND_KHOA);
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

    @Transactional
    public void validateGetListMonHocByMaKhoa(String maKhoa) throws BusinessException {

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
    public void validateGetMonHocByMaMh(String maMh) throws BusinessException {

        int countMaMonHoc = monHocRepository.countMonHocByMaMh(maMh);

        if (countMaMonHoc == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_MONHOC_NOT_FOUND_MONHOC);
        }
    }

    @Transactional
    public boolean validateUpdateDKMHTQPossible(String maMh, String maMHTQ) throws BusinessException {

        int countMaMonHoc = monHocRepository.countMonHocByMaMh(maMh);
        int countMaMonHocTienQuyet = monHocRepository.countMonHocByMaMh(maMHTQ);

        Long countMHMHTQ = mhtqRepository.countMHTQByMaMhAndMaMHTQ(maMh, maMHTQ);

        if (countMHMHTQ > 0) {
            return false;
        }
        else if(maMh.equals(maMHTQ)){
            return false;
        }
        else if (countMaMonHoc == 0) {
            return false;
        }
        else if (countMaMonHocTienQuyet == 0) {
            return false;
        }

        return true;
    }
}
