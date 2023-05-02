package com.qlsv5.service;

import com.qlsv5.dto.DsLopTcMonHocGiangVienLopDto;
import com.qlsv5.entity.DsLopTcEntity;

import java.util.List;

public interface DsLopTcService {

    public List<DsLopTcEntity> getListLopTcByMaLop(String maLop, int page, int size);

    public List<DsLopTcEntity> getListLopTcByMaMh(String maMh);

    public List<DsLopTcEntity> findAllByMaLopAndMaKeHoach(String maLop, String maKeHoach);

}
