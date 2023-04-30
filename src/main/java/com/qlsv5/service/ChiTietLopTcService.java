package com.qlsv5.service;

import com.qlsv5.entity.ChiTietLopTcEntity;
import com.qlsv5.entity.LopEntity;

import java.util.List;

public interface ChiTietLopTcService {

    public List<ChiTietLopTcEntity> getListChiTietLopTcByMaLopTc(String maLopTc);

}
