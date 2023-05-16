package com.qlsv5.validation;

import com.qlsv5.common.FunctionCommon;
import com.qlsv5.constant.MasterDataExceptionConstant;
import com.qlsv5.dto.DsLopTcDto;
import com.qlsv5.entity.DsLopTcEntity;
import com.qlsv5.entity.KeHoachNamEntity;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

/**
 * @author NienNQ
 * @created 2023 - 03 - 05 11:02 AM
 * @project qlsv
 */
@Component
public class ValidatorDsLopTc implements Validator {

    @Autowired
    private DsLopTcRepository dsLopTcRepository;

    @Autowired
    private KhoaRepository khoaRepository;

    @Autowired
    private GiangVienRepository giangVienRepository;

    @Autowired
    private LopRepository lopRepository;

    @Autowired
    private MonHocRepository monHocRepository;

    @Autowired
    private FunctionCommon functionCommon;

    @Autowired
    private KeHoachNamRepository keHoachNamRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    @Transactional
    public void validateAddDsLopTc(Object target) throws BusinessException {
        DsLopTcDto dsLopTcDto = (DsLopTcDto) target;

        int countMaDsLopTc = dsLopTcRepository.countDsLopTcByMaLopTc(dsLopTcDto.getMaLopTc());
        int countGvByMaGv = giangVienRepository.countGiangVienByMaGv(dsLopTcDto.getMaGv());
        int countLopByMaLop = lopRepository.countLopByMaLop(dsLopTcDto.getMaLop());
        int countMonHocByMaMh = monHocRepository.countMonHocByMaMh(dsLopTcDto.getMaMh());
        int countKeHoachNamByMaKeHoach = keHoachNamRepository.countKeHoachNamByMaKeHoach(dsLopTcDto.getMaKeHoach());

        if (countGvByMaGv == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
        }
        if (countLopByMaLop == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_LOP_NOT_FOUND_LOP);
        }
        if (countMonHocByMaMh == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_MONHOC_NOT_FOUND_MONHOC);
        }
        else if (countKeHoachNamByMaKeHoach == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_KEHOACHNAM_NOT_FOUND_KEHOACHNAM);
        }
        else if (countMaDsLopTc > 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_DUPLICATE_MA_DSLOPTC);
        }
    }

    @Transactional
    public void validateEditDsLopTc(Object target) throws BusinessException {

        DsLopTcDto dsLopTcDto = (DsLopTcDto) target;

        if(dsLopTcDto.getId() == null){
            throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_NOT_FOUND_DSLOPTC);
        }

        Optional<DsLopTcEntity> dsLopTcEntity = dsLopTcRepository.findById(dsLopTcDto.getId());
        int countGvByMaGv = giangVienRepository.countGiangVienByMaGv(dsLopTcDto.getMaGv());
        int countLopByMaLop = lopRepository.countLopByMaLop(dsLopTcDto.getMaLop());
        int countMonHocByMaMh = monHocRepository.countMonHocByMaMh(dsLopTcDto.getMaMh());
        int countKeHoachNamByMaKeHoach = keHoachNamRepository.countKeHoachNamByMaKeHoach(dsLopTcDto.getMaKeHoach());

        if (dsLopTcEntity.isPresent() == false) {
            throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_NOT_FOUND_DSLOPTC);
        }
        else if (countGvByMaGv == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
        }
        else if (countLopByMaLop == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_LOP_NOT_FOUND_LOP);
        }
        else if (countMonHocByMaMh == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_MONHOC_NOT_FOUND_MONHOC);
        }
        else if (countKeHoachNamByMaKeHoach == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_KEHOACHNAM_NOT_FOUND_KEHOACHNAM);
        }
        else {
            Long countMaDsLopTc = dsLopTcRepository.countDsLopTcByMaLopTcAndNotId(dsLopTcDto.getMaLopTc(), dsLopTcDto.getId());
            long countValue = countMaDsLopTc != null ? countMaDsLopTc : 0;
            if (countValue > 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_DUPLICATE_MA_DSLOPTC);
            }
        }
    }

    @Transactional
    public void validateGetDsLopTcById(String dsLopTcId) throws BusinessException {

        int countMaDsLopTc = dsLopTcRepository.countDsLopTcById(dsLopTcId);

        if (countMaDsLopTc == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_NOT_FOUND_DSLOPTC);
        }
    }

    @Transactional
    public void validateGetListLopTcByMaLop(String maLop) throws BusinessException {

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

    @Transactional
    public void validateSearchThongKe(String idKeHoachNam, String keySearch) throws BusinessException {

        if(idKeHoachNam == null || "".equals(idKeHoachNam)){
            throw new BusinessException(MasterDataExceptionConstant.E_KEHOACHNAM_NOT_FOUND_KEHOACHNAM);
        }
        else {
            int countKeHoachNam = keHoachNamRepository.countKeHoachNamById(idKeHoachNam);

            if (countKeHoachNam == 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_KEHOACHNAM_NOT_FOUND_KEHOACHNAM);
            }
        }
    }

    @Transactional
    public void validateGetListLopTcByMaGvAndMaKeHoach(String maGv, String maKeHoach) throws BusinessException {

        if(maGv == null || "".equals(maGv)){
            throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
        }
        else if(maKeHoach == null || "".equals(maKeHoach)){
            throw new BusinessException(MasterDataExceptionConstant.E_KEHOACHNAM_NOT_FOUND_KEHOACHNAM);
        }
        else {
            int countGvMaGv = giangVienRepository.countGiangVienByMaGv(maGv);
            int countByMaKeHoach = keHoachNamRepository.countKeHoachNamByMaKeHoach(maKeHoach);

            if (countGvMaGv == 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
            }
            else if (countByMaKeHoach == 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_KEHOACHNAM_NOT_FOUND_KEHOACHNAM);
            }
        }
    }

    @Transactional
    public void validateDeleteDsLopTc(Object target) throws BusinessException {

        DsLopTcDto dsLopTcDto = (DsLopTcDto) target;

//        Date dateNow = new Date();
        LocalDate dateNow = LocalDate.now();

        KeHoachNamEntity keHoachNamEntity = keHoachNamRepository.getKeHoachNamByMaKeHoach(dsLopTcDto.getMaKeHoach());
        if(dateNow.isAfter(keHoachNamEntity.getTimeDkMonEnd())){
            throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_NGOAI_TIME_DK);
        }
    }
}
