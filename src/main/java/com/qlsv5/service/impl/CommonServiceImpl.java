package com.qlsv5.service.impl;

import com.qlsv5.constant.MasterDataExceptionConstant;
import com.qlsv5.dto.*;
import com.qlsv5.entity.*;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.payload.request.SignupRequest;
import com.qlsv5.payload.response.MessageResponse;
import com.qlsv5.repository.*;
import com.qlsv5.service.CommonService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CommonServiceImpl implements CommonService {
    @Autowired
    private LopRepository lopRepository;
    @Autowired
    private KhoaRepository khoaRepository;
    @Autowired
    private SinhVienRepository sinhVienRepository;
    @Autowired
    private GiangVienRepository giangVienRepository;
    @Autowired
    private MonHocRepository monHocRepository;
    @Autowired
    private DsLopTcRepository dsLopTcRepository;
    @Autowired
    private ChiTietLopTcRepository chiTietLopTcRepository;
    @Autowired
    private DiemRepository diemRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    RoleRepository roleRepository;

    //CRUD  CREATE , READ , UPDATE , DELETE

    @Override
    public Object updateObject(Object object) {
        ModelMapper modelMapper = new ModelMapper();

        if(object instanceof KhoaDto){
            KhoaEntity khoaEntity = modelMapper.map(object, KhoaEntity.class);
            return khoaRepository.save(khoaEntity);
        }
        else if(object instanceof LopDto){
            LopEntity lopEntity = modelMapper.map(object, LopEntity.class);
            return lopRepository.save(lopEntity);
        }
        else if(object instanceof SinhVienDto){
            SinhVienEntity sinhVienEntity = modelMapper.map(object, SinhVienEntity.class);
            return sinhVienRepository.save(sinhVienEntity);
        }
        else if(object instanceof GiangVienDto){
            GiangVienEntity giangVienEntity = modelMapper.map(object, GiangVienEntity.class);
            return giangVienRepository.save(giangVienEntity);
        }
        else if(object instanceof MonHocDto){
            MonHocEntity monHocEntity = modelMapper.map(object, MonHocEntity.class);
            return monHocRepository.save(monHocEntity);
        }
        else if(object instanceof DsLopTcDto){
            DsLopTcEntity dsLopTcEntity = modelMapper.map(object, DsLopTcEntity.class);
            return dsLopTcRepository.save(dsLopTcEntity);
        }
        else if(object instanceof ChiTietLopTcDto){
            ChiTietLopTcEntity chiTietLopTcEntity = modelMapper.map(object, ChiTietLopTcEntity.class);
            return chiTietLopTcRepository.save(chiTietLopTcEntity);
        }
        else if(object instanceof DiemDto){
            DiemEntity diemEntity = modelMapper.map(object, DiemEntity.class);
            return diemRepository.save(diemEntity);
        }

        return null;
    }

    @Override
    public Object addObject(Object object) {
        ModelMapper modelMapper = new ModelMapper();

        if(object instanceof KhoaDto){
            KhoaEntity result = new KhoaEntity();
            result = modelMapper.map(object, KhoaEntity.class);
            result.setId(UUID.randomUUID().toString().split("-")[0]);
            return khoaRepository.save(result);
        }
        else if(object instanceof LopDto){
            LopEntity result = new LopEntity();
            result = modelMapper.map(object, LopEntity.class);
            result.setId(UUID.randomUUID().toString().split("-")[0]);
            return lopRepository.save(result);
        }
        else if(object instanceof SinhVienDto){
            SinhVienEntity result = new SinhVienEntity();
            result = modelMapper.map(object, SinhVienEntity.class);

            Date currentDate = result.getNgaySinh();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String dateString = dateFormat.format(currentDate);
            result.setMatKhau(dateString);

            /*_____________________________________________*/
            /* BEGIN CREATE ACCOUNT USER WITH ROLE_SINHVIEN*/
            /*_____________________________________________*/

            SignupRequest signUpRequest = createUserAccountTemp(result);

            // Create new user's account
            UserEntity user = new UserEntity(signUpRequest.getUsername(),
                    signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()), signUpRequest.getIdLogin());

            Set<String> strRoles = signUpRequest.getRoles();
            Set<RoleEntity> roles = new HashSet<>();

            if (strRoles == null) {
                RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "ADMIN":
                            RoleEntity adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminRole);
                            break;

                        case "SINHVIEN":
                            RoleEntity sinhvienRole = roleRepository.findByName(ERole.ROLE_SINHVIEN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(sinhvienRole);
                            break;

                        case "GIANGVIEN":
                            RoleEntity giangvienRole = roleRepository.findByName(ERole.ROLE_GIANGVIEN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(giangvienRole);
                            break;
//
//                        default:
//                            RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
//                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                            roles.add(userRole);
                    }
                });
            }

            result.setId(UUID.randomUUID().toString().split("-")[0]);

            user.setRoles(roles);
            user.setIdLogin(result.getId());
            userRepository.save(user);
            /*____________________________________________*/
            /* END CREATE ACCOUNT USER WITH ROLE_SINHVIEN */
            /*____________________________________________*/

            return sinhVienRepository.save(result);
        }
        else if(object instanceof GiangVienDto){
            GiangVienEntity result = new GiangVienEntity();
            result = modelMapper.map(object, GiangVienEntity.class);

            Date currentDate = result.getNgaySinh();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String dateString = dateFormat.format(currentDate);
            result.setMatKhau(dateString);

            /*_____________________________________________*/
            /* BEGIN CREATE ACCOUNT USER WITH ROLE_SINHVIEN*/
            /*_____________________________________________*/
            SignupRequest signUpRequest = createUserAccountTemp(result);

            // Create new user's account

            UserEntity user = new UserEntity(signUpRequest.getUsername(),
                    signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()), signUpRequest.getIdLogin());

            Set<String> strRoles = signUpRequest.getRoles();
            Set<RoleEntity> roles = new HashSet<>();

            if (strRoles == null) {
                RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "ADMIN":
                            RoleEntity adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminRole);
                            break;

                        case "SINHVIEN":
                            RoleEntity sinhvienRole = roleRepository.findByName(ERole.ROLE_SINHVIEN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(sinhvienRole);
                            break;

                        case "GIANGVIEN":
                            RoleEntity giangvienRole = roleRepository.findByName(ERole.ROLE_GIANGVIEN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(giangvienRole);
                            break;
                    }
                });
            }
            result.setId(UUID.randomUUID().toString().split("-")[0]);

            user.setRoles(roles);
            user.setIdLogin(result.getId());
            userRepository.save(user);
            /*____________________________________________*/
            /* END CREATE ACCOUNT USER WITH ROLE_SINHVIEN */
            /*____________________________________________*/

            return giangVienRepository.save(result);
        }
        else if(object instanceof MonHocDto){
            MonHocEntity result = new MonHocEntity();
            result = modelMapper.map(object, MonHocEntity.class);
            result.setId(UUID.randomUUID().toString().split("-")[0]);
            return monHocRepository.save(result);
        }
        else if(object instanceof DsLopTcDto){
            DsLopTcEntity result = new DsLopTcEntity();
            result = modelMapper.map(object, DsLopTcEntity.class);
            result.setId(UUID.randomUUID().toString().split("-")[0]);
            return dsLopTcRepository.save(result);
        }
        else if(object instanceof ChiTietLopTcDto){
            ChiTietLopTcEntity result = new ChiTietLopTcEntity();
            result = modelMapper.map(object, ChiTietLopTcEntity.class);
            result.setId(UUID.randomUUID().toString().split("-")[0]);
            return chiTietLopTcRepository.save(result);
        }
        /* fact : not use */
        else if(object instanceof DiemDto){

            /* TRUTH: DANG KY MON */
            DiemEntity result = new DiemEntity();
            result = modelMapper.map(object, DiemEntity.class);
            result.setId(UUID.randomUUID().toString().split("-")[0]);

            /*SET SOLUONGCON CUA LOPTINCHI*/
            DsLopTcEntity dsLopTcEntity = dsLopTcRepository.getDsLopTcByMaLopTc(result.getMaLopTc());
            dsLopTcEntity.setSoLuongCon(dsLopTcEntity.getSoLuongCon() - 1);

            dsLopTcRepository.save(dsLopTcEntity);

            return diemRepository.save(result);
        }
        else if(object instanceof DangKyMonDto){

            /* TRUTH: DANG KY MON */
            DangKyMonDto dangKyMonDto = modelMapper.map(object, DangKyMonDto.class);

            List<DiemDto> listDiem = new ArrayList<>();
            for(int i = 0 ; i < dangKyMonDto.getDiemDtoList().size() ; i++){
                listDiem.add(dangKyMonDto.getDiemDtoList().get(i));
            }

            for(int i = 0 ; i < listDiem.size() ; i++){
                DiemEntity result = new DiemEntity();
                result = modelMapper.map(listDiem.get(i), DiemEntity.class);
                result.setId(UUID.randomUUID().toString().split("-")[0]);

                /*SET SOLUONGCON CUA LOPTINCHI*/
                DsLopTcEntity dsLopTcEntity = dsLopTcRepository.getDsLopTcByMaLopTc(result.getMaLopTc());
                dsLopTcEntity.setSoLuongCon(dsLopTcEntity.getSoLuongCon() - 1);

                dsLopTcRepository.save(dsLopTcEntity);

                diemRepository.save(result);
            }
            return null;
        }

        return null;
    }

    @Override
    public List<String> deleteLstObject(List<String> lstId, Object object) {

        List<String> lstSuccess = new ArrayList<>();

        if(object instanceof KhoaDto){
            for (String item : lstId) {

                int countMaKhoa = khoaRepository.countKhoaById(item);

                if(countMaKhoa > 0){
                    /* important: validator */
                    KhoaEntity khoaEntity = khoaRepository.findById(item).get();
                    int countMaKhoaOnGiangVienTable = giangVienRepository.countGiangVienByMaKhoa(khoaEntity.getMaKhoa());
                    int countMaKhoaOnLopTable = lopRepository.countLopByMaKhoa(khoaEntity.getMaKhoa());

                    if(countMaKhoaOnLopTable > 0 || countMaKhoaOnGiangVienTable > 0){
                        continue;
                    }
                    else {
                        lstSuccess.add(item);
                        khoaRepository.deleteById(item);
                    }
                }
            }
        }
        else if(object instanceof LopDto){
            for (String item : lstId) {
                int countMaLop = lopRepository.countLopById(item);
                if(countMaLop > 0){
                    /* important: validator */
                    LopEntity lopEntity = lopRepository.findById(item).get();
                    int countMaLopOnSinhVienTable = sinhVienRepository.countSinhVienByMaLop(lopEntity.getMaLop());
                    int countMaLopOnDsLopTcTable = dsLopTcRepository.countDsLopTcByMaLop(lopEntity.getMaLop());

                    if(countMaLopOnSinhVienTable > 0 || countMaLopOnDsLopTcTable > 0){
                        continue;
                    }
                    else {
                        lstSuccess.add(item);
                        lopRepository.deleteById(item);
                    }
                }
            }
        }
        else if(object instanceof SinhVienDto){
            for (String item : lstId) {
                int countMaSinhVien = sinhVienRepository.countSinhVienById(item);
                if(countMaSinhVien > 0){

                    /* important: validator */
                    SinhVienEntity sinhVienEntity = sinhVienRepository.findById(item).get();
                    int countSinhVienOnDiemTable = diemRepository.countDiemByMaSv(sinhVienEntity.getMaSv());

                    if(countSinhVienOnDiemTable > 0){
                        continue;
                    }
                    else {
                        lstSuccess.add(item);
                        sinhVienRepository.deleteById(item);
                    }

                }
            }
        }
        else if(object instanceof GiangVienDto){
            for (String item : lstId) {
                int countMaGiangVien = giangVienRepository.countGiangVienById(item);
                if(countMaGiangVien > 0){

                    /* important: validator */
                    GiangVienEntity giangVienEntity = giangVienRepository.findById(item).get();
                    int countGiangVienOnDsLopTcTable = dsLopTcRepository.countDsLopTcByMaGv(giangVienEntity.getMaGv());

                    if(countGiangVienOnDsLopTcTable > 0){
                        continue;
                    }
                    else {
                        lstSuccess.add(item);
                        giangVienRepository.deleteById(item);
                    }

                }
            }
        }
        else if(object instanceof MonHocDto){
            for (String item : lstId) {
                int countMaMonHoc = monHocRepository.countMonHocById(item);
                if(countMaMonHoc > 0){

                    /* important: validator */
                    MonHocEntity monHocEntity = monHocRepository.findById(item).get();
                    int countGiangVienOnDsLopTcTable = dsLopTcRepository.countDsLopTcByMaMh(monHocEntity.getMaMh());

                    if(countGiangVienOnDsLopTcTable > 0){
                        continue;
                    }
                    else {
                        lstSuccess.add(item);
                        monHocRepository.deleteById(item);
                    }

                }
            }
        }
        else if(object instanceof DsLopTcDto){
            for (String item : lstId) {
                int countMaDsLopTc = dsLopTcRepository.countDsLopTcById(item);
                if(countMaDsLopTc > 0){

                    /* important: validator */
                    DsLopTcEntity dsLopTcEntity = dsLopTcRepository.findById(item).get();
                    int countDslopTcOnDiemTable = diemRepository.countDiemByMaLopTc(dsLopTcEntity.getMaMh());
                    int countDslopTcOnChiTietLopTcTable = chiTietLopTcRepository.countChiTietLopTcByMaLopTc(dsLopTcEntity.getMaMh());

                    if(countDslopTcOnDiemTable > 0 || countDslopTcOnChiTietLopTcTable > 0){
                        continue;
                    }
                    else {

                        /*delete Loptc -> delte all ChiTietLopTc that relative with LopTc*/
                        List<ChiTietLopTcEntity> chiTietLopTcEntityList = chiTietLopTcRepository.getListChiTietLopTcByMaLopTc(dsLopTcEntity.getMaLopTc());

                        for (ChiTietLopTcEntity chiTietLopTcEntity: chiTietLopTcEntityList) {
                            chiTietLopTcRepository.deleteById(chiTietLopTcEntity.getId());
                        }
                        /* end */

                        lstSuccess.add(item);
                        dsLopTcRepository.deleteById(item);
                    }

                }
            }
        }
        else if(object instanceof ChiTietLopTcDto){
            for (String item : lstId) {
                int countMaChiTietLopTc = chiTietLopTcRepository.countChiTietLopTcById(item);
                if(countMaChiTietLopTc > 0){
                    lstSuccess.add(item);
                    chiTietLopTcRepository.deleteById(item);
                }
            }
        }
        else if(object instanceof DiemDto){
            for (String item : lstId) {

                /* TRUTH: DANG KY MON */
                int countMaDiem = diemRepository.countDiemById(item);
                if(countMaDiem > 0){

                    /*SET SOLUONGCON CUA LOPTINCHI*/

                    DiemEntity diemEntity = diemRepository.getDiemById(item);
                    DsLopTcEntity dsLopTcEntity = dsLopTcRepository.getDsLopTcByMaLopTc(diemEntity.getMaLopTc());

                    dsLopTcEntity.setSoLuongCon(dsLopTcEntity.getSoLuongCon() + 1);

                    dsLopTcRepository.save(dsLopTcEntity);

                    lstSuccess.add(item);
                    diemRepository.deleteById(item);
                }
            }
        }

        return lstSuccess;
    }

    @Override
    public List<Object> findAllObject(Object object) {
        if(object instanceof KhoaDto){
            return Collections.singletonList(khoaRepository.findAll());
        }
        else if(object instanceof LopDto){
            return Collections.singletonList(lopRepository.findAll());
        }
        else if(object instanceof SinhVienDto){
            return Collections.singletonList(sinhVienRepository.findAll());
        }
        else if(object instanceof GiangVienDto){
            return Collections.singletonList(giangVienRepository.findAll());
        }
        else if(object instanceof MonHocDto){
            return Collections.singletonList(monHocRepository.findAll());
        }
        else if(object instanceof DsLopTcDto){
            return Collections.singletonList(dsLopTcRepository.findAll());
        }
        else if(object instanceof ChiTietLopTcDto){
            return Collections.singletonList(chiTietLopTcRepository.findAll());
        }
        else if(object instanceof DiemDto){
            return Collections.singletonList(diemRepository.findAll());
        }
        return null;
    }

    @Override
    public Object getObjectById(String taskId, Object object) {
        if(object instanceof KhoaDto){
            return khoaRepository.findById(taskId).get();
        }
        else if(object instanceof LopDto){
            return lopRepository.findById(taskId).get();
        }
        else if(object instanceof SinhVienDto){
            return sinhVienRepository.findById(taskId).get();
        }
        else if(object instanceof GiangVienDto){
            return giangVienRepository.findById(taskId).get();
        }
        else if(object instanceof MonHocDto){
            return monHocRepository.findById(taskId).get();
        }
        else if(object instanceof DsLopTcDto){
            return dsLopTcRepository.findById(taskId).get();
        }
        else if(object instanceof ChiTietLopTcDto){
            return chiTietLopTcRepository.findById(taskId).get();
        }
        else if(object instanceof DiemDto){
            return diemRepository.findById(taskId).get();
        }
        return null;
    }

    /* common */
    private SignupRequest createUserAccountTemp(Object object){
        SignupRequest objectTemp = new SignupRequest();
        ModelMapper modelMapper = new ModelMapper();
        if(object instanceof SinhVienEntity){
            SinhVienEntity result = new SinhVienEntity();
            result = modelMapper.map(object, SinhVienEntity.class);
            objectTemp.setUsername(result.getMaSv());
            objectTemp.setPassword(result.getMatKhau());
            objectTemp.setEmail(result.getEmail());
            objectTemp.setIdLogin(result.getId());

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

            Set<String> roles = new HashSet<>();
            roles.add("GIANGVIEN");
            objectTemp.setRole(roles);
        }
        return objectTemp;
    }

}
