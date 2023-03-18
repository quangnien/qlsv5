package com.qlsv5.service;


import com.qlsv5.dto.KhoaDto;
import com.qlsv5.entity.KhoaEntity;

import java.util.List;

public interface KhoaService {

//    public KhoaEntity addKhoa(KhoaDto khoa);
//    public KhoaEntity updateKhoa(KhoaDto khoa);
    public List<String> deleteLstKhoa(List<String> lstKhoaId);

    public List<KhoaEntity> findAllKhoa();
//
    public KhoaEntity getKhoaById(String taskId);
//
//    public List<KhoaEntity> getTaskBySeverity(int severity);
//
//    public List<KhoaEntity> getTaskByAssignee(String assignee);
//
//    public KhoaEntity updateTask(KhoaEntity taskRequest);
//


}
