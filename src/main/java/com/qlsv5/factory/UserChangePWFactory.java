package com.qlsv5.factory;

import com.qlsv5.enumdef.RoleEnum;
import com.qlsv5.factoryMethod.ApplicationContextProvider;
import com.qlsv5.service.GiangVienService;
import com.qlsv5.service.UserService;
import com.qlsv5.validation.ValidatorAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserChangePWFactory {
//    @Autowired
//    public UserService userService;
//    @Autowired
//    public ValidatorAdmin validatorAdmin;
//    @Autowired
//    public PasswordEncoder encoder;

    @Autowired
    private static ApplicationContext applicationContext;

    public static UserChangePW createUserToChangePW(String userRole){
        if(userRole.equals(RoleEnum.GIANGVIEN.getName())) {
            GiangVienChangePW giangVienChangePW = new GiangVienChangePW();
            return new GiangVienChangePW();
        }
        else if(userRole.equals(RoleEnum.SINHVIEN.getName())) {
            return new SinhVienChangePW();
        }
        else if(userRole.equals(RoleEnum.ADMIN.getName())) {
            AdminChangePW updatePasswordAdmin = ApplicationContextProvider.getApplicationContext().getBean(AdminChangePW.class) ;
            AdminChangePW adminChangePW = applicationContext.getBean("adminChangePW", AdminChangePW.class);

//            AdminChangePW adminChangePW = new AdminChangePW();
//            return new AdminChangePW();
            return adminChangePW;
        }
        else throw new IllegalArgumentException();
    }
}
