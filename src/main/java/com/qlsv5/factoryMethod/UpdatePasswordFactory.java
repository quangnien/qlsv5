package com.qlsv5.factoryMethod;

import com.qlsv5.enumdef.RoleEnum;
import com.qlsv5.service.UserService;
import com.qlsv5.strategy.StrategyUpdatePassword;
import com.qlsv5.strategy.UpdatePasswordAdmin;
import com.qlsv5.strategy.UpdatePasswordGiangVien;
import com.qlsv5.strategy.UpdatePasswordSinhVien;
import com.qlsv5.validation.ValidatorAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UpdatePasswordFactory {
    @Autowired
    private UserService userService;

    @Autowired
    private ValidatorAdmin validatorAdmin;

    @Autowired
    private PasswordEncoder encoder;
    public static StrategyUpdatePassword createStrategyUpdatePW(String userRole){
        if(userRole.equals(RoleEnum.GIANGVIEN.getName())) {
            return new UpdatePasswordGiangVien();
        }
        else if(userRole.equals(RoleEnum.SINHVIEN.getName())) {
            return new UpdatePasswordSinhVien();
        }
        else if(userRole.equals(RoleEnum.ADMIN.getName())) {
            UpdatePasswordAdmin updatePasswordAdmin = new UpdatePasswordAdmin();
//            updatePasswordAdmin.setEncoder(encoder);
//            updatePasswordAdmin.setValidatorAdmin(validatorAdmin);
            return updatePasswordAdmin;
        }
        else throw new IllegalArgumentException();
    }
}
