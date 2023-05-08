package com.qlsv5.service.impl;

import com.qlsv5.entity.KeHoachNamEntity;
import com.qlsv5.entity.KhoaEntity;
import com.qlsv5.repository.KeHoachNamRepository;
import com.qlsv5.repository.KhoaRepository;
import com.qlsv5.service.KeHoachNamService;
import com.qlsv5.service.KhoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class KeHoachNamServiceImpl implements KeHoachNamService {

    @Autowired
    private KeHoachNamRepository keHoachNamRepository;


    @Override
    public KeHoachNamEntity getKeHoachNamByMaKeHoach(String maKeHoachNam) {
        return keHoachNamRepository.getKeHoachNamByMaKeHoach(maKeHoachNam);
    }
}
