package com.qlsv5.service;

import com.qlsv5.dto.TkbDto;
import com.qlsv5.entity.SinhVienEntity;

import java.util.List;

public interface SinhVienService {

    public List<SinhVienEntity> getListSinhVienByMaLop(String maLop, int page, int size);

    public List<TkbDto> getListTKBForSinhVien(String maSinhVien, TkbDto tkbDto);

    public SinhVienEntity findByMaSv(String maSv);

}
