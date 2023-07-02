package com.qlsv5.strategy;

import com.qlsv5.dto.UpdatePasswordDto;
import com.qlsv5.entity.ERole;
import com.qlsv5.entity.UserEntity;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.service.UserService;
import com.qlsv5.validation.ValidatorAdmin;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdatePasswordAdmin implements StrategyUpdatePassword{
    @Autowired
    UserService userService;
    @Autowired
    ValidatorAdmin validatorAdmin;

    @Override
    public void updatePassword(UpdatePasswordDto updatePasswordDto) throws BusinessException {
        // validate
        System.out.println("Validate received data ::: Admin ::: " + updatePasswordDto.getUserName());
        validatorAdmin.validateUpdatePasswordAdmin(updatePasswordDto);
        // updatePasswordUser ...
        System.out.println("Update password ::: Admin ::: newPW: " + updatePasswordDto.getMatKhauMoi());
        /* update PW UserEntity*/
        UserEntity userEntity = userService.findByUsername(updatePasswordDto.getUserName());
//        userEntity.setPassword(encoder.encode(updatePasswordDto.getMatKhauMoi()));
        userService.updateUser(userEntity);
    }

    @Override
    public String getRoleName() {
        return ERole.ROLE_ADMIN.toString();
    }
}
