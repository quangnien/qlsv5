package com.qlsv5.validation;

import com.qlsv5.constant.MasterDataExceptionConstant;
import com.qlsv5.dto.DiemDto;
import com.qlsv5.entity.DiemEntity;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.repository.DiemRepository;
import com.qlsv5.repository.DsLopTcRepository;
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
public class ValidatorDiem implements Validator {

    @Autowired
    private DiemRepository diemRepository;
    @Autowired
    private SinhVienRepository sinhVienRepository;
    @Autowired
    private DsLopTcRepository dsLopTcRepository;

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

}