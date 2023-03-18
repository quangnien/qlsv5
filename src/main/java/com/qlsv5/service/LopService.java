package com.qlsv5.service;

import com.qlsv5.dto.LopDto;
import com.qlsv5.entity.LopEntity;

import java.util.List;

public interface LopService {
//    public LopEntity addLop(LopDto lop);
//    public LopEntity updateLop(LopDto lop);
    public List<String> deleteLstLop(List<String> lstLopId);

    public List<LopEntity> findAllLop();

    public LopEntity getLopById(String taskId);
	
}
