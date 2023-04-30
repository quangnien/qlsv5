package com.qlsv5.service;


import com.qlsv5.entity.KeHoachNamEntity;

import java.util.List;

public interface KeHoachNamService {

    public List<String> deleteLstKeHoachNam(List<String> lstKeHoachNamId);

    public List<KeHoachNamEntity> findAllKeHoachNam();
    
    public KeHoachNamEntity getKeHoachNamById(String id);
    
    public KeHoachNamEntity getKeHoachNamByMaKeHoachNam(String maKeHoachNam);

}
