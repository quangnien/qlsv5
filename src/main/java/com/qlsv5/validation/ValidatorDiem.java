package com.qlsv5.validation;

import com.qlsv5.constant.MasterDataExceptionConstant;
import com.qlsv5.dto.DiemDto;
import com.qlsv5.entity.DiemEntity;
import com.qlsv5.entity.DsLopTcEntity;
import com.qlsv5.entity.MonHocEntity;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author NienNQ
 * @created 2023 - 03 - 05 11:02 AM
 * @project qlsv
 */
@Component
public class ValidatorDiem implements Validator {

    @Autowired
    private DiemRepository diemRepository;
    @Autowired
    private SinhVienRepository sinhVienRepository;
    @Autowired
    private DsLopTcRepository dsLopTcRepository;

    @Autowired
    private KeHoachNamRepository keHoachNamRepository;

    @Autowired
    private MonHocRepository monHocRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    @Transactional
    public void validateAddDiem(Object target) throws BusinessException {
        DiemDto diemDto = (DiemDto) target;

        Long countDiemByMaSvMaLopTc = diemRepository.countByMaSvAndMaLopTc(diemDto.getMaSv(), diemDto.getMaLopTc());
        int countSinhVienByMaSv = sinhVienRepository.countSinhVienByMaSv(diemDto.getMaSv());
        int countDsLopTcByMaLopTc = dsLopTcRepository.countDsLopTcByMaLopTc(diemDto.getMaLopTc());

        if (countSinhVienByMaSv == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
        }
        else if (countDsLopTcByMaLopTc == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_NOT_FOUND_DSLOPTC);
        }
        else if (countDiemByMaSvMaLopTc > 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_DIEM_DUPLICATE_MASV_MALOPTC);
        }
    }

    @Transactional
    public void validateEditDiem(Object target) throws BusinessException {
        DiemDto diemDto = (DiemDto) target;

        if(diemDto.getId() == null){
            throw new BusinessException(MasterDataExceptionConstant.E_DIEM_NOT_FOUND_DSLOPTC);
        }
        else {
            Optional<DiemEntity> diemEntity = diemRepository.findById(diemDto.getId());

            int countSinhVienByMaSv = sinhVienRepository.countSinhVienByMaSv(diemDto.getMaSv());
            int countDsLopTcByMaLopTc = dsLopTcRepository.countDsLopTcByMaLopTc(diemDto.getMaLopTc());

            if (countSinhVienByMaSv == 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
            }
            else if (countDsLopTcByMaLopTc == 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_NOT_FOUND_DSLOPTC);
            }

            if (diemEntity.isPresent() == false) {
                throw new BusinessException(MasterDataExceptionConstant.E_DIEM_NOT_FOUND_DSLOPTC);
            }
            else {
                Long countDiemByMaSvMaLopTc = diemRepository.countByMaSvAndMaLopTcExcludingId(diemDto.getId(), diemDto.getMaSv(), diemDto.getMaLopTc());
                long countValue = countDiemByMaSvMaLopTc != null ? countDiemByMaSvMaLopTc : 0;
                if (countValue > 0) {
                    throw new BusinessException(MasterDataExceptionConstant.E_DIEM_DUPLICATE_MASV_MALOPTC);
                }
            }
        }
    }

    @Transactional
    public void validateGetDiemById(String diemId) throws BusinessException {

        int countDiem = diemRepository.countDiemById(diemId);

        if (countDiem == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_DIEM_NOT_FOUND_DSLOPTC);
        }
    }

    @Transactional
    public void validateGetListDiemByMaLopTc(String maLopTc) throws BusinessException {

        if(maLopTc == null || "".equals(maLopTc)){
            throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_NOT_FOUND_DSLOPTC);
        }
        else {
            int countLopTcByMaLopTc = dsLopTcRepository.countDsLopTcByMaLopTc(maLopTc);

            if (countLopTcByMaLopTc == 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_NOT_FOUND_DSLOPTC);
            }
        }

    }

//    @Transactional
//    public void validateDangKyMon(Object target) throws BusinessException {
//        DiemDto diemDto = (DiemDto) target;
//
//        Long countDiemByMaSvMaLopTc = diemRepository.countByMaSvAndMaLopTc(diemDto.getMaSv(), diemDto.getMaLopTc());
//        int countSinhVienByMaSv = sinhVienRepository.countSinhVienByMaSv(diemDto.getMaSv());
//        int countDsLopTcByMaLopTc = dsLopTcRepository.countDsLopTcByMaLopTc(diemDto.getMaLopTc());
//
//        if (countSinhVienByMaSv == 0) {
//            throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
//        }
//        else if (countDsLopTcByMaLopTc == 0) {
//            throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_NOT_FOUND_DSLOPTC);
//        }
//        else if (countDiemByMaSvMaLopTc > 0) {
//            throw new BusinessException(MasterDataExceptionConstant.E_DIEM_DUPLICATE_MASV_MALOPTC);
//        }
//        else {
//            DsLopTcEntity dsLopTcEntity = dsLopTcRepository.getDsLopTcByMaLopTc(diemDto.getMaLopTc());
//            if(dsLopTcEntity.getSoLuongCon() <= 0){
//                throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_FULL_SLOT);
//            }
//        }
//
//    }

    @Transactional
    public boolean validateDangKyMon(Object target) throws BusinessException {
        DiemDto diemDto = (DiemDto) target;

        Long countDiemByMaSvMaLopTc = diemRepository.countByMaSvAndMaLopTc(diemDto.getMaSv(), diemDto.getMaLopTc());
        int countSinhVienByMaSv = sinhVienRepository.countSinhVienByMaSv(diemDto.getMaSv());
        int countDsLopTcByMaLopTc = dsLopTcRepository.countDsLopTcByMaLopTc(diemDto.getMaLopTc());

        /* DK: 1 MONHOC 1 - n LOPTINCHI -> only đk 1 LOPTINCHI thuộc về 1 môn học đó*/
//        Hiện tại không bắt đk này, vì 1 list truyền cả 2 cái đó vô thì sao kiểm soát được
        List<DiemEntity> diemEntityList = new ArrayList<>();
        if(diemDto.getMaSv() != null){
            diemEntityList = diemRepository.getListDiemByMaSv(diemDto.getMaSv());
        }

        if (countSinhVienByMaSv == 0) {
            return false;
        }
        else if (countDsLopTcByMaLopTc == 0) {
            return false;
        }
        else if (countDiemByMaSvMaLopTc > 0) {
            return false;
        }
        /* DK: 1 MONHOC 1 - n LOPTINCHI -> only đk 1 LOPTINCHI thuộc về 1 môn học đó*/
        else if(diemEntityList.size() > 0){
            DsLopTcEntity dsLopTcEntityInput = dsLopTcRepository.getDsLopTcByMaLopTc(diemDto.getMaLopTc());
            for (DiemEntity diemEntity : diemEntityList) {
                DsLopTcEntity dsLopTcEntity = dsLopTcRepository.getDsLopTcByMaLopTc(diemEntity.getMaLopTc());
                if(dsLopTcEntity != null){
                    if(dsLopTcEntityInput.getMaMh().equals(dsLopTcEntity.getMaMh())){
                        return false;
                    };
                }
            }
        }
        else {
            DsLopTcEntity dsLopTcEntity = dsLopTcRepository.getDsLopTcByMaLopTc(diemDto.getMaLopTc());
            if(dsLopTcEntity.getSoLuongCon() <= 0){
                return false;
            }
        }

        return true;
    }

    @Transactional
    public void validateThongKeDiem(String id, String col) throws BusinessException {

//        if(col == null || "".equals(col)){
//            throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_NOT_FOUND_DSLOPTC);
//        }
//        if(id == null || "".equals(id)){
//            throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_NOT_FOUND_DSLOPTC);
//        }
//        else {

            if(id != null && !id.equals("")){

                int countMaDsLopTc = dsLopTcRepository.countDsLopTcById(id);

                if (countMaDsLopTc == 0) {
                    throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_NOT_FOUND_DSLOPTC);
                }
            }
    }

    @Transactional
    public void validateGetListDiemByMaSvAndMaKeHoach(String maSv, String maKeHoach) throws BusinessException {

        if(maSv == null || "".equals(maSv)){
            throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
        }
        else if(maKeHoach == null || "".equals(maKeHoach)){
            throw new BusinessException(MasterDataExceptionConstant.E_KEHOACHNAM_NOT_FOUND_KEHOACHNAM);
        }
        else {
            int countSvByMaSv = sinhVienRepository.countSinhVienByMaSv(maSv);
            int countByMaKeHoach = keHoachNamRepository.countKeHoachNamByMaKeHoach(maKeHoach);

            if (countSvByMaSv == 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
            }
            else if (countByMaKeHoach == 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_KEHOACHNAM_NOT_FOUND_KEHOACHNAM);
            }
        }
    }

    @Transactional
    public void validateGetListDiemByMaSv(String maSv) throws BusinessException {

        if(maSv == null || "".equals(maSv)){
            throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
        }
        else {
            int countSvByMaSv = sinhVienRepository.countSinhVienByMaSv(maSv);

            if (countSvByMaSv == 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
            }
        }
    }

//    @Transactional
//    public void validateDeleteDangKyMonValid(String maSv, String maLopTc) throws BusinessException {
//
//        if(maSv == null || "".equals(maSv)){
//            throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
//        }
//        else {
//            int countSvByMaSv = sinhVienRepository.countSinhVienByMaSv(maSv);
//
//            if (countSvByMaSv == 0) {
//                throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
//            }
//        }
//    }

}
