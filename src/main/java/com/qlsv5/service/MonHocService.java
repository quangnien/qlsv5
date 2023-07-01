package com.qlsv5.service;

import com.qlsv5.dto.MonHocModifyDto;
import com.qlsv5.entity.GiangVienEntity;
import com.qlsv5.entity.MonHocEntity;
import com.qlsv5.entity.SinhVienEntity;

import java.util.List;

public interface MonHocService {
    public List<MonHocModifyDto> getListMonHocByMaKhoa(String maKhoa, int page, int size);
    List<MonHocEntity> findByTenMhContainingIgnoreCaseLike(String keySearch);
    List<MonHocEntity> getAll();
    public MonHocEntity getMonHocByMaMh(String maMh);
    public MonHocEntity addNew(MonHocEntity monHocEntity);
    public MonHocEntity updateExist(MonHocEntity monHocEntity);

//    List<MonHocEntity> findByTenMhContainingIgnoreCaseLike(String keySearch);
    public List<MonHocEntity> findAll();
    public MonHocModifyDto findById(String id);
//    public MonHocEntity findByMaMh(String maMh);
//    public List<String> deleteList(List<String> lstId);
//    public List<MonHocEntity> findAllByMaLop(String maLop);
    public List<MonHocModifyDto> findAllMonHocModify();
//    public List<MonHocEntity> findAllByMaGV(String maGV);
}
