package com.qlsv5.factory;

import com.qlsv5.dto.UpdatePasswordDto;
import com.qlsv5.entity.UserEntity;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.service.UserService;
import com.qlsv5.validation.ValidatorAdmin;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * qlsv5
 *
 * @author Smartee
 * @date 7/1/2023 4:11 PM
 */
@Component
@Getter
@Setter
public class AdminChangePW implements UserChangePW {
//    @Autowired
    public UserService userService;
//    @Autowired
    public ValidatorAdmin validatorAdmin;
//    @Autowired
    public PasswordEncoder encoder;
//    public AdminChangePW(UserService userService, ValidatorAdmin validatorAdmin, PasswordEncoder encoder) {
//        this.userService = userService;
//        this.validatorAdmin = validatorAdmin;
//        this.encoder = encoder;
//    }

//    public final  UserService userService;
//    public final  ValidatorAdmin validatorAdmin;
//    public final  PasswordEncoder encoder;


    @Override
    public void updatePassword(UpdatePasswordDto updatePasswordDto) throws BusinessException {
        // validate
        System.out.println("Validate received data ::: Student ::: " + updatePasswordDto.getUserName());
        validatorAdmin.validateUpdatePasswordAdmin(updatePasswordDto);

        // updatePasswordUser ...
        System.out.println("Update password ::: Student ::: newPW: " + updatePasswordDto.getMatKhauMoi());

        /* update PW UserEntity*/
        UserEntity userEntity = userService.findByUsername(updatePasswordDto.getUserName());
        userEntity.setPassword(encoder.encode(updatePasswordDto.getMatKhauMoi()));
        userService.updateUser(userEntity);
    }
}
