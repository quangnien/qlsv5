package com.qlsv5.service;

import com.qlsv5.dto.MHMHTQDto;
import com.qlsv5.entity.MHTQEntity;

import java.util.List;

public interface MHTQService {

    public void updateExist(MHMHTQDto gvdowDto);
    public List<MHTQEntity> findAllByMaMh(String maMh);
    public MHTQEntity findById(String id);
    public List<MHTQEntity> findAll();
    public void deleteRecord(String id);

}