package com.qlsv5.service.impl;

import com.qlsv5.entity.ChiTietLopTcEntity;
import com.qlsv5.service.impl.repository.ChiTietLopTcRepository;
import com.qlsv5.service.ChiTietLopTcService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ChiTietLopTcServiceImpl implements ChiTietLopTcService {

    @Autowired
    private ChiTietLopTcRepository chiTietLopTcRepository;

    @Override
    public List<ChiTietLopTcEntity> getListChiTietLopTcByMaLopTc(String maLopTc) {
        return chiTietLopTcRepository.getListChiTietLopTcByMaLopTc(maLopTc);
    }

}
