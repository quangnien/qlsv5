package com.qlsv5.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qlsv5.entity.GiangVienEntity;
import com.qlsv5.entity.SinhVienEntity;
import com.qlsv5.payload.request.SignupRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author NienNQ
 * @created 2023 - 03 - 05 3:42 PM
 * @project qlsv
 */
@Component
public class FunctionCommon {

    public static boolean isValidEmailFormat(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@gmail.com";
        return email.matches(regex);
    }
    public static double roundToHalf(float num) {
        float floor = (float) Math.floor(num);
        float diff = num - floor;
        if (diff <= 0.25) {
            return floor;
        } else if (diff <= 0.75) {
            return floor + 0.5;
        } else {
            return floor + 1;
        }
    }
    public SignupRequest createUserAccountTemp(Object object){
        SignupRequest objectTemp = new SignupRequest();
        ModelMapper modelMapper = new ModelMapper();
        if(object instanceof SinhVienEntity){
            SinhVienEntity result = new SinhVienEntity();
            result = modelMapper.map(object, SinhVienEntity.class);
            objectTemp.setUsername(result.getMaSv());
            objectTemp.setPassword(result.getMatKhau());
            objectTemp.setEmail(result.getEmail());
            objectTemp.setIdLogin(result.getId());
            objectTemp.setUserFullName(result.getHo() + " " + result.getTen());

            Set<String> roles = new HashSet<>();
            roles.add("SINHVIEN");
            objectTemp.setRole(roles);
        }
        else if(object instanceof GiangVienEntity){
            GiangVienEntity result = new GiangVienEntity();
            result = modelMapper.map(object, GiangVienEntity.class);
            objectTemp.setUsername(result.getMaGv());
            objectTemp.setPassword(result.getMatKhau());
            objectTemp.setEmail(result.getEmail());
            objectTemp.setIdLogin(result.getId());
            objectTemp.setUserFullName(result.getHo() + " " + result.getTen());

            Set<String> roles = new HashSet<>();
            roles.add("GIANGVIEN");
            objectTemp.setRole(roles);
        }
        return objectTemp;
    }
    public List<String> convertListToSetToList(List<String> result){
        // Convert List to Set
        Set<String> resultSet = new HashSet<>(result);

        // Convert Set back to List
        List<String> resultList = new ArrayList<>(resultSet);

        // Return the List
        return resultList;
    }
    // Hàm chuyển đổi object thành mảng byte
    public byte[] convertObjectToBytes(ReturnObject returnObject) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsBytes(returnObject);
        } catch (Exception e) {
            // Xử lý nếu có lỗi xảy ra trong quá trình chuyển đổi
            e.printStackTrace();
            return null; // hoặc thực hiện xử lý khác tùy ý
        }
    }

}
