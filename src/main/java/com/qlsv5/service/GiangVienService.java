package com.qlsv5.service;

import com.qlsv5.entity.GiangVienEntity;

import java.util.List;

public interface GiangVienService {
    public List<String> deleteLstGiangVien(List<String> lstGiangVienId);

    public List<GiangVienEntity> findAllGiangVien();

    public GiangVienEntity getGiangVienById(String taskId);
}
