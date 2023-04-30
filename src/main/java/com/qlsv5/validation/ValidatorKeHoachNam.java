package com.qlsv5.validation;

import com.qlsv5.common.FunctionCommon;
import com.qlsv5.constant.MasterDataExceptionConstant;
import com.qlsv5.dto.KeHoachNamDto;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.repository.KeHoachNamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author NienNQ
 * @created 2023 - 04 - 30 02:02 PM
 * @project qlsv
 */
@Component
public class ValidatorKeHoachNam implements Validator {

    @Autowired
    private KeHoachNamRepository keHoachNamRepository;

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
    public void validateAddKeHoachNam(Object target) throws BusinessException {
        KeHoachNamDto keHoachNamDto = (KeHoachNamDto) target;

        int countMaKeHoachNam = keHoachNamRepository.countKeHoachNamByMaKeHoach(keHoachNamDto.getMaKeHoach());

        if (countMaKeHoachNam > 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_KEHOACHNAM_DUPLICATE_MA_KEHOACHNAM);
        }
    }

    @Transactional
    public void validateGetKeHoachNamById(String keHoachNamId) throws BusinessException {

        int countMaKeHoachNam = keHoachNamRepository.countKeHoachNamById(keHoachNamId);

        if (countMaKeHoachNam == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_KEHOACHNAM_NOT_FOUND_KEHOACHNAM);
        }
    }

    @Transactional
    public void validateGetKeHoachNamByMaKeHoach(String maKeHoach) throws BusinessException {

        int countMaKeHoachNam = keHoachNamRepository.countKeHoachNamByMaKeHoach(maKeHoach);

        if (countMaKeHoachNam == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_KEHOACHNAM_NOT_FOUND_KEHOACHNAM);
        }
    }

}
