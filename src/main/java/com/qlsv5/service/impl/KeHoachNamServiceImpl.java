package com.qlsv5.service.impl;

import com.qlsv5.entity.KeHoachNamEntity;
import com.qlsv5.service.impl.repository.KeHoachNamRepository;
import com.qlsv5.service.KeHoachNamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public KeHoachNamEntity getKeHoachNamClosest() {
        Sort sort = Sort.by(
                Sort.Order.desc("nam"),
                Sort.Order.desc("ky")
        );
        List<KeHoachNamEntity> keHoachNamEntityList = keHoachNamRepository.findAll(sort);
        return keHoachNamEntityList.get(0);
    }
}
