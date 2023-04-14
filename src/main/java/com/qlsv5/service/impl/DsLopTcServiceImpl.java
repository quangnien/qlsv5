package com.qlsv5.service.impl;

import com.qlsv5.entity.DsLopTcEntity;
import com.qlsv5.entity.LopEntity;
import com.qlsv5.repository.DsLopTcRepository;
import com.qlsv5.service.DsLopTcService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class DsLopTcServiceImpl implements DsLopTcService {

    @Autowired
    private DsLopTcRepository dsLopTcRepository;

    @Override
    public List<DsLopTcEntity> getListLopTcByMaLop(String maLop){
        return dsLopTcRepository.getListDsLopByMaLop(maLop);
    }

}
