package com.qlsv5.service;

import com.qlsv5.entity.GiangVienEntity;
import com.qlsv5.entity.MonHocEntity;
import com.qlsv5.entity.SinhVienEntity;

import java.util.List;

public interface MonHocService {
    public List<MonHocEntity> getListMonHocByMaKhoa(String maKhoa, int page, int size);

    List<MonHocEntity> findByTenMhContainingIgnoreCaseLike(String keySearch);

    List<MonHocEntity> getAll();

    public MonHocEntity getMonHocByMaMh(String maMh);

}
