package com.qlsv5.strategy;

import com.qlsv5.dto.UpdatePasswordDto;
import com.qlsv5.entity.SinhVienEntity;
import com.qlsv5.entity.UserEntity;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.service.CommonService;
import com.qlsv5.service.SinhVienService;
import com.qlsv5.service.UserService;
import com.qlsv5.validation.ValidatorSinhVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

public class UpdatePasswordSinhVien implements StrategyUpdatePassword{

    @Autowired
    public ValidatorSinhVien validatorSinhVien;
    @Autowired
    public UserService userService;
    @Autowired
    public SinhVienService sinhVienService;
    @Autowired
    public CommonService commonService;
    @Autowired
    public PasswordEncoder encoder;
    @Override
    public void updatePassword(UpdatePasswordDto updatePasswordDto) throws BusinessException {

        // validate
        System.out.println("Validate received data ::: Student ::: " + updatePasswordDto.getUserName());
        validatorSinhVien.validateUpdatePasswordSinhVien(updatePasswordDto);

        // updatePasswordUser ...
        System.out.println("Update password ::: Student ::: newPW: " + updatePasswordDto.getMatKhauMoi());

        UserEntity userEntity = userService.findByUsername(updatePasswordDto.getUserName());
        SinhVienEntity getSinhVienByDB = sinhVienService.findByMaSv(updatePasswordDto.getUserName());

        /* update PW SinhVienEntity */
        getSinhVienByDB.setMatKhau(updatePasswordDto.getMatKhauMoi());

//        sinhVienService.updateExist(getSinhVienByDB);
        commonService.updateObject(getSinhVienByDB);

        /* update PW UserEntity*/
        userEntity.setPassword(encoder.encode(updatePasswordDto.getMatKhauMoi()));
        userService.updateUser(userEntity);
        
    }

}
