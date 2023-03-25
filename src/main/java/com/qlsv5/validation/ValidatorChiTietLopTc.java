package com.qlsv5.validation;

import com.qlsv5.common.FunctionCommon;
import com.qlsv5.constant.MasterDataExceptionConstant;
import com.qlsv5.dto.ChiTietLopTcDto;
import com.qlsv5.entity.ChiTietLopTcEntity;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.repository.ChiTietLopTcRepository;
import com.qlsv5.repository.DsLopTcRepository;
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
public class ValidatorChiTietLopTc implements Validator {

    @Autowired
    private ChiTietLopTcRepository chiTietLopTcRepository;

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
    public void validateAddChiTietLopTc(Object target) throws BusinessException {
        ChiTietLopTcDto chiTietLopTcDto = (ChiTietLopTcDto) target;

        Long countChiTietLopTcByMaLopTcTietThu = chiTietLopTcRepository.countByMaLopTcAndTietAndThu(chiTietLopTcDto.getMaLopTc(), chiTietLopTcDto.getTiet(), chiTietLopTcDto.getThu());
        int countDsLopTcByMaLopTc = dsLopTcRepository.countDsLopTcByMaLopTc(chiTietLopTcDto.getMaLopTc());

        if (countDsLopTcByMaLopTc == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_CHITIETLOPTC_NOT_FOUND_DSLOPTC);
        }
        else if (countChiTietLopTcByMaLopTcTietThu > 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_CHITIETLOPTC_DUPLICATE_MA_LOPTC_TIET_THU);
        }
    }

    @Transactional
    public void validateEditChiTietLopTc(Object target) throws BusinessException {
        ChiTietLopTcDto chiTietLopTcDto = (ChiTietLopTcDto) target;

        if(chiTietLopTcDto.getId() == null){
            throw new BusinessException(MasterDataExceptionConstant.E_CHITIETLOPTC_NOT_FOUND_DSLOPTC);
        }
        else {
            Optional<ChiTietLopTcEntity> chiTietLopTcEntity = chiTietLopTcRepository.findById(chiTietLopTcDto.getId());
            int countDsLopTcByMaLopTc = dsLopTcRepository.countDsLopTcByMaLopTc(chiTietLopTcDto.getMaLopTc());

            if (chiTietLopTcEntity.isPresent() == false) {
                throw new BusinessException(MasterDataExceptionConstant.E_CHITIETLOPTC_NOT_FOUND_DSLOPTC);
            }
            else if (countDsLopTcByMaLopTc == 0) {
                    throw new BusinessException(MasterDataExceptionConstant.E_CHITIETLOPTC_NOT_FOUND_DSLOPTC);
            }
            else {
                Long countKhoaByMaLopTcTietThu = chiTietLopTcRepository.countByMaLopTcAndTietAndThuExcludingId(chiTietLopTcDto.getId(), chiTietLopTcDto.getMaLopTc(), chiTietLopTcDto.getTiet(), chiTietLopTcDto.getThu());
                long countValue = countKhoaByMaLopTcTietThu != null ? countKhoaByMaLopTcTietThu : 0;
                if (countValue > 0) {
                    throw new BusinessException(MasterDataExceptionConstant.E_CHITIETLOPTC_DUPLICATE_MA_LOPTC_TIET_THU);
                }
            }
        }
    }

    @Transactional
    public void validateGetChiTietLopTcById(String chiTietLopTcId) throws BusinessException {

        int countChiTietLopTc = chiTietLopTcRepository.countChiTietLopTcById(chiTietLopTcId);

        if (countChiTietLopTc == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_CHITIETLOPTC_NOT_FOUND_DSLOPTC);
        }
    }

}
