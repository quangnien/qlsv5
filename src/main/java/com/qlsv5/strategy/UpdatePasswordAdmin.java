package com.qlsv5.strategy;

import com.qlsv5.dto.UpdatePasswordDto;
import com.qlsv5.entity.UserEntity;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.service.UserService;
import com.qlsv5.validation.ValidatorAdmin;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//@Component
//@Getter
//@Setter
//@Configuration
public class UpdatePasswordAdmin implements StrategyUpdatePassword{
    @Autowired
    public UserService userService;
    @Autowired
    public ValidatorAdmin validatorAdmin;
    @Autowired
    public PasswordEncoder encoder;

//    public UpdatePasswordAdmin updatePasswordAdmin() {
//        UpdatePasswordAdmin updatePasswordAdmin = new UpdatePasswordAdmin();
//        updatePasswordAdmin.setUserService(userService);
//        updatePasswordAdmin.setValidatorAdmin(validatorAdmin);
//        updatePasswordAdmin.setEncoder(encoder);
//        return updatePasswordAdmin;
//    }

//    public UpdatePasswordAdmin(UserService userService, ValidatorAdmin validatorAdmin, PasswordEncoder encoder) {
//        this.userService = userService;
//        this.validatorAdmin = validatorAdmin;
//        this.encoder = encoder;
//    }

    @Override
    public void updatePassword(UpdatePasswordDto updatePasswordDto) throws BusinessException {

        // validate
        System.out.println("Validate received data ::: Student ::: " + updatePasswordDto.getUserName());
        validatorAdmin.validateUpdatePasswordAdmin(updatePasswordDto);

        // updatePasswordUser ...
        System.out.println("Update password ::: Student ::: newPW: " + updatePasswordDto.getMatKhauMoi());

        /* update PW UserEntity*/
        UserEntity userEntity = userService.findByUsername(updatePasswordDto.getUserName());
//        userEntity.setPassword(encoder.encode(updatePasswordDto.getMatKhauMoi()));
        userService.updateUser(userEntity);

    }
}
