package com.qlsv5.validation;

import com.qlsv5.constant.MasterDataExceptionConstant;
import com.qlsv5.dto.GiangVienDto;
import com.qlsv5.dto.TkbDto;
import com.qlsv5.dto.UpdatePasswordDto;
import com.qlsv5.entity.GiangVienEntity;
import com.qlsv5.entity.UserEntity;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.repository.GiangVienRepository;
import com.qlsv5.repository.KhoaRepository;
import com.qlsv5.service.CommonService;
import com.qlsv5.service.UserService;
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
public class ValidatorAdmin implements Validator {

    @Autowired
    private GiangVienRepository giangVienRepository;
    @Autowired
    private KhoaRepository khoaRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CommonService commonService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    @Transactional
    public void validateUpdatePasswordAdmin(UpdatePasswordDto updatePasswordDto) throws BusinessException {

        if(updatePasswordDto.getId() == null){
            throw new BusinessException(MasterDataExceptionConstant.E_ADMIN_NOT_FOUND_ADMIN);
        }
        else if(updatePasswordDto.getMatKhau() == null || updatePasswordDto.getMatKhau().equals("")){
            throw new BusinessException(MasterDataExceptionConstant.E_COMMON_NOT_PASSWORD);
        }
        else if(updatePasswordDto.getConfirmPassword() == null || updatePasswordDto.getConfirmPassword().equals("")){
            throw new BusinessException(MasterDataExceptionConstant.E_COMMON_NOT_CONFIRM_PASSWORD);
        }
        else {
            UserEntity userEntity = userService.findById(updatePasswordDto.getId());

            if(updatePasswordDto.getConfirmPassword().equals(updatePasswordDto.getMatKhau()) == false){
                throw new BusinessException(MasterDataExceptionConstant.E_COMMON_NOT_EQUAL_CONFIRM_PASSWORD);
            }
            else if (userEntity == null) {
                throw new BusinessException(MasterDataExceptionConstant.E_ADMIN_NOT_FOUND_ADMIN);
            }
        }
    }


}
