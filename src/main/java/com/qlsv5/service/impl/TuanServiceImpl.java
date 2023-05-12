package com.qlsv5.service.impl;

import com.qlsv5.entity.TuanEntity;
import com.qlsv5.repository.TuanRepository;
import com.qlsv5.service.TuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class TuanServiceImpl implements TuanService {

    @Autowired
    private TuanRepository tuanRepository;

    @Override
    public TuanEntity findByTuanAndNam(int weekNumber, int localDate) {
        return tuanRepository.findByTuanAndNam(weekNumber, localDate);
    }
}
