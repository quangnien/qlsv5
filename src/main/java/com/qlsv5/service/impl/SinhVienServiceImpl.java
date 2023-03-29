package com.qlsv5.service.impl;

import com.qlsv5.entity.SinhVienEntity;
import com.qlsv5.repository.SinhVienRepository;
import com.qlsv5.service.SinhVienService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SinhVienServiceImpl implements SinhVienService {
    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Override
    public List<SinhVienEntity> getListSinhVienByMaLop(String maLop){
        return sinhVienRepository.getListSinhVienByMaLop(maLop);
    }

}
