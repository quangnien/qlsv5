package com.qlsv5.service.impl;

import com.qlsv5.entity.SinhVienEntity;
import com.qlsv5.repository.SinhVienRepository;
import com.qlsv5.service.SinhVienService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SinhVienServiceImpl implements SinhVienService {
    @Autowired
    private SinhVienRepository repository;

    //CRUD  CREATE , READ , UPDATE , DELETE

    @Override
    public List<String> deleteLstSinhVien(List<String> lstSinhVienId) {
        List<String> lstSuccess = new ArrayList<>();
        for (String item : lstSinhVienId) {
            int countMaSinhVien = repository.countSinhVienById(item);
            if(countMaSinhVien > 0){
                lstSuccess.add(item);
                repository.deleteById(item);
            }
        }
        return lstSuccess;
    }

    @Override
    public List<SinhVienEntity> findAllSinhVien() {
        return repository.findAll();
    }

    @Override
    public SinhVienEntity getSinhVienById(String sinhVienId){
        return repository.findById(sinhVienId).get();
    }

}
