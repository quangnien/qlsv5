package com.qlsv5.service.impl;

import com.qlsv5.entity.GiangVienEntity;
import com.qlsv5.entity.SinhVienEntity;
import com.qlsv5.repository.GiangVienRepository;
import com.qlsv5.repository.SinhVienRepository;
import com.qlsv5.service.GiangVienService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class GiangVienServiceImpl implements GiangVienService {
    @Autowired
    private GiangVienRepository giangVienRepository;

    //CRUD  CREATE , READ , UPDATE , DELETE

    @Override
    public List<String> deleteLstGiangVien(List<String> lstGiangVienId) {
        List<String> lstSuccess = new ArrayList<>();
        for (String item : lstGiangVienId) {
            int countMaGiangVien = giangVienRepository.countGiangVienById(item);
            if(countMaGiangVien > 0){
                lstSuccess.add(item);
                giangVienRepository.deleteById(item);
            }
        }
        return lstSuccess;
    }

    @Override
    public List<GiangVienEntity> findAllGiangVien() {
        return giangVienRepository.findAll();
    }

    @Override
    public GiangVienEntity getGiangVienById(String giangVienId){
        return giangVienRepository.findById(giangVienId).get();
    }

    @Override
    public List<GiangVienEntity> getListGiangVienByMaKhoa(String maKhoa){
        return giangVienRepository.getListGiangVienByMaKhoa(maKhoa);
    }
}
