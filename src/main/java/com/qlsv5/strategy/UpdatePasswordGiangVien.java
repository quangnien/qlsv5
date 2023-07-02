package com.qlsv5.strategy;

import com.qlsv5.dto.UpdatePasswordDto;
import com.qlsv5.entity.ERole;
import com.qlsv5.entity.GiangVienEntity;
import com.qlsv5.entity.UserEntity;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.service.CommonService;
import com.qlsv5.service.GiangVienService;
import com.qlsv5.service.UserService;
import com.qlsv5.validation.ValidatorGiangVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UpdatePasswordGiangVien implements StrategyUpdatePassword{
    @Autowired
    ValidatorGiangVien validatorGiangVien;
    @Autowired
    UserService userService;
    @Autowired
    GiangVienService giangVienService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    CommonService commonService;

    public void updatePassword(UpdatePasswordDto updatePasswordDto) throws BusinessException {

        // validate
        System.out.println("Validate received data ::: Lecturer ::: " + updatePasswordDto.getUserName());
        validatorGiangVien.validateUpdatePasswordGiangVien(updatePasswordDto);

        // updatePasswordUser ...
        System.out.println("Update password ::: Lecturer ::: newPW: " + updatePasswordDto.getMatKhauMoi());

        UserEntity userEntity = userService.findByUsername(updatePasswordDto.getUserName());
        GiangVienEntity getGiangVienByDB = giangVienService.getGiangVienByMaGv(updatePasswordDto.getUserName());

        /* update PW GiangVienEntity*/
        getGiangVienByDB.setMatKhau(updatePasswordDto.getMatKhauMoi());

//        giangVienService.updateGiangVien(getGiangVienByDB);
        commonService.updateObject(getGiangVienByDB);

        /* update PW UserEntity*/
        userEntity.setPassword(encoder.encode(updatePasswordDto.getMatKhauMoi()));
        userService.updateUser(userEntity);
    }

    @Override
    public String getRoleName() {
        return ERole.ROLE_GIANGVIEN.toString();
    }
}
