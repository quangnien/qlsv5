package com.qlsv5.service.impl;

import com.qlsv5.dto.*;
import com.qlsv5.entity.*;
import com.qlsv5.repository.*;
import com.qlsv5.service.CommonService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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
            result.setId(UUID.randomUUID().toString().split("-")[0]);
            return sinhVienRepository.save(result);
        }
        else if(object instanceof GiangVienDto){
            GiangVienEntity result = new GiangVienEntity();
            result = modelMapper.map(object, GiangVienEntity.class);
            result.setId(UUID.randomUUID().toString().split("-")[0]);
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
        else if(object instanceof DiemDto){
            DiemEntity result = new DiemEntity();
            result = modelMapper.map(object, DiemEntity.class);
            result.setId(UUID.randomUUID().toString().split("-")[0]);
            return diemRepository.save(result);
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
                    lstSuccess.add(item);
                    khoaRepository.deleteById(item);
                }
            }
        }
        else if(object instanceof LopDto){
            for (String item : lstId) {
                int countMaLop = lopRepository.countLopById(item);
                if(countMaLop > 0){
                    lstSuccess.add(item);
                    lopRepository.deleteById(item);
                }
            }
        }
        else if(object instanceof SinhVienDto){
            for (String item : lstId) {
                int countMaSinhVien = sinhVienRepository.countSinhVienById(item);
                if(countMaSinhVien > 0){
                    lstSuccess.add(item);
                    sinhVienRepository.deleteById(item);
                }
            }
        }
        else if(object instanceof GiangVienDto){
            for (String item : lstId) {
                int countMaGiangVien = giangVienRepository.countGiangVienById(item);
                if(countMaGiangVien > 0){
                    lstSuccess.add(item);
                    giangVienRepository.deleteById(item);
                }
            }
        }
        else if(object instanceof MonHocDto){
            for (String item : lstId) {
                int countMaMonHoc = monHocRepository.countMonHocById(item);
                if(countMaMonHoc > 0){
                    lstSuccess.add(item);
                    monHocRepository.deleteById(item);
                }
            }
        }
        else if(object instanceof DsLopTcDto){
            for (String item : lstId) {
                int countMaDsLopTc = dsLopTcRepository.countDsLopTcById(item);
                if(countMaDsLopTc > 0){
                    lstSuccess.add(item);
                    dsLopTcRepository.deleteById(item);
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
                int countMaDiem = diemRepository.countDiemById(item);
                if(countMaDiem > 0){
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

}
