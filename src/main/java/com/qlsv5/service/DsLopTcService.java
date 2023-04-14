package com.qlsv5.service;

import com.qlsv5.entity.DsLopTcEntity;
import com.qlsv5.entity.LopEntity;

import java.util.List;

public interface DsLopTcService {

    public List<DsLopTcEntity> getListLopTcByMaLop(String maLop);

}
