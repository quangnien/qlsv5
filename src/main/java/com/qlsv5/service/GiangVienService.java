package com.qlsv5.service;

import com.qlsv5.dto.TkbDto;
import com.qlsv5.entity.GiangVienEntity;
import com.qlsv5.entity.GiangVienEntity;

import java.util.List;

public interface GiangVienService {

    public List<String> deleteLstGiangVien(List<String> lstGiangVienId);

    public List<GiangVienEntity> findAllGiangVien();

    public GiangVienEntity getGiangVienById(String taskId);

    public GiangVienEntity getGiangVienByMaGv(String taskId);

    public List<GiangVienEntity> getListGiangVienByMaKhoa(String maKhoa);

    public List<TkbDto> getListTKBForGiangVien(String maGiangVien, TkbDto tkbDto);

    // paging
    List<GiangVienEntity> getListGiangVienPaging(int page, int size);

    public List<TkbDto> getListTKBForGV(String maGiangVien, String maKeHoach, int tuan);

    List<GiangVienEntity> getListGiangVienByMaKhoaPaging(int page, int size, String maKhoa);

}
