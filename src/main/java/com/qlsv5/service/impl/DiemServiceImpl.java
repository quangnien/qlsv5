package com.qlsv5.service.impl;

import com.qlsv5.entity.DiemEntity;
import com.qlsv5.entity.LopEntity;
import com.qlsv5.repository.DiemRepository;
import com.qlsv5.service.DiemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class DiemServiceImpl implements DiemService {

    @Autowired
    private DiemRepository diemRepository;

    @Override
    public List<DiemEntity> getListDiemByMaLopTc(String maLopTc){
        return diemRepository.getListDiemByMaLopTc(maLopTc);
    }

}
