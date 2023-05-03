package com.qlsv5.service;


import com.qlsv5.entity.TuanEntity;

public interface TuanService {
    TuanEntity findByTuanAndNam(int weekNumber, int localDate);
}
