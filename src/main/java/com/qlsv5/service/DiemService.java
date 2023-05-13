package com.qlsv5.service;

import com.qlsv5.entity.DiemEntity;
import com.qlsv5.entity.LopEntity;

import java.util.List;

public interface DiemService {

    public List<DiemEntity> getListDiemByMaLopTc(String maLopTc, int page, int size);
    public List<DiemEntity> getListDiemByMaSv(String maSv);

    public List<DiemEntity> getListDiemByMaLopTc(String maLopTc);

    DiemEntity getDiemByMaSvAndMaLopTc(String maSv, String maLopTc);

    void deleteDangKyMon(DiemEntity diemEntity);

}
