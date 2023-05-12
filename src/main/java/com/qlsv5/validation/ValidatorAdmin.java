package com.qlsv5.validation;

import com.qlsv5.constant.MasterDataExceptionConstant;
import com.qlsv5.dto.UpdatePasswordDto;
import com.qlsv5.entity.UserEntity;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.repository.GiangVienRepository;
import com.qlsv5.repository.KhoaRepository;
import com.qlsv5.service.CommonService;
import com.qlsv5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    PasswordEncoder encoder;

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
        else if(updatePasswordDto.getMatKhauCu() == null || updatePasswordDto.getMatKhauCu().equals("")){
            throw new BusinessException(MasterDataExceptionConstant.E_COMMON_NOT_PASSWORD_OLD);
        }
        else if(updatePasswordDto.getMatKhauMoi() == null || updatePasswordDto.getMatKhauMoi().equals("")){
            throw new BusinessException(MasterDataExceptionConstant.E_COMMON_NOT_PASSWORD_NEW);
        }
//        else if(updatePasswordDto.getConfirmPassword() == null || updatePasswordDto.getConfirmPassword().equals("")){
//            throw new BusinessException(MasterDataExceptionConstant.E_COMMON_NOT_CONFIRM_PASSWORD);
//        }
        else {
            UserEntity userEntity = userService.findById(updatePasswordDto.getId());

//            if(updatePasswordDto.getConfirmPassword().equals(updatePasswordDto.getMatKhau()) == false){
//                throw new BusinessException(MasterDataExceptionConstant.E_COMMON_NOT_EQUAL_CONFIRM_PASSWORD);
//            }
            if (userEntity == null) {
                throw new BusinessException(MasterDataExceptionConstant.E_ADMIN_NOT_FOUND_ADMIN);
            }
            else {
                String decodedPassword = updatePasswordDto.getMatKhauCu();
                if (! encoder.matches(decodedPassword, userEntity.getPassword())) {
                    throw new BusinessException(MasterDataExceptionConstant.E_COMMON_NOT_MATCH_PASSWORD);
                }

            }
        }
    }


}
