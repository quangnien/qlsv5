package com.qlsv5.service.impl;

import com.qlsv5.entity.GiangVienEntity;
import com.qlsv5.repository.GiangVienRepository;
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
    private GiangVienRepository repository;

    //CRUD  CREATE , READ , UPDATE , DELETE

    @Override
    public List<String> deleteLstGiangVien(List<String> lstGiangVienId) {
        List<String> lstSuccess = new ArrayList<>();
        for (String item : lstGiangVienId) {
            int countMaGiangVien = repository.countGiangVienById(item);
            if(countMaGiangVien > 0){
                lstSuccess.add(item);
                repository.deleteById(item);
            }
        }
        return lstSuccess;
    }

    @Override
    public List<GiangVienEntity> findAllGiangVien() {
        return repository.findAll();
    }

    @Override
    public GiangVienEntity getGiangVienById(String giangVienId){
        return repository.findById(giangVienId).get();
    }
}
