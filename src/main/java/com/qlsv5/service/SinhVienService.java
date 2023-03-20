package com.qlsv5.service;

import com.qlsv5.entity.SinhVienEntity;

import java.util.List;

public interface SinhVienService {

    public List<String> deleteLstSinhVien(List<String> lstSinhVienId);

    public List<SinhVienEntity> findAllSinhVien();

    public SinhVienEntity getSinhVienById(String taskId);

}
