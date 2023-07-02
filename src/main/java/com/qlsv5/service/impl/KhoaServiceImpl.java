package com.qlsv5.service.impl;

import com.qlsv5.entity.KhoaEntity;
import com.qlsv5.repository.KhoaRepository;
import com.qlsv5.service.KhoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class KhoaServiceImpl implements KhoaService {
    @Autowired
    private KhoaRepository repository;

    //CRUD  CREATE , READ , UPDATE , DELETE

//    @Override
//    public KhoaEntity addKhoa(KhoaDto khoa) {
//        KhoaEntity result = new KhoaEntity();
//        result.setId(UUID.randomUUID().toString().split("-")[0]);
//        result.setMaKhoa(khoa.getMaKhoa());
//        result.setTenKhoa(khoa.getTenKhoa());
//        result.setSdt(khoa.getSdt());
//        result.setEmail(khoa.getEmail());
//        return repository.save(result);
//    }
//
//    @Override
//    public KhoaEntity updateKhoa(KhoaDto khoaDto) {
//        ModelMapper modelMapper = new ModelMapper();
//        KhoaEntity khoaEntity = modelMapper.map(khoaDto, KhoaEntity.class);
//        return repository.save(khoaEntity);
//    }

    @Override
    public List<String> deleteLstKhoa(List<String> lstKhoaId) {
        List<String> lstSuccess = new ArrayList<>();
        for (String item : lstKhoaId) {
            int countMaKhoa = repository.countKhoaById(item);
            if(countMaKhoa > 0){
                lstSuccess.add(item);
                repository.deleteById(item);
            }
        }
        return lstSuccess;
    }

    @Override
    public List<KhoaEntity> findAllKhoa() {
        return repository.findAll();
    }

    @Override
    public KhoaEntity getKhoaById(String khoaId){
        return repository.findById(khoaId).get();
    }
//
//    public List<KhoaEntity> getTaskBySeverity(int severity){
//        return  repository.findBySeverity(severity);
//    }
//
//    public List<KhoaEntity> getTaskByAssignee(String assignee){
//        return repository.getTasksByAssignee(assignee);
//    }
//
//    public KhoaEntity updateTask(KhoaEntity taskRequest){
//        //get the existing document from DB
//        // populate new value from request to existing object/entity/document
//        KhoaEntity existingKhoa = repository.findById(taskRequest.getTaskId()).get();
//        existingKhoa.setDescription(taskRequest.getDescription());
//        existingKhoa.setSeverity(taskRequest.getSeverity());
//        existingKhoa.setAssignee(taskRequest.getAssignee());
//        existingKhoa.setStoryPoint(taskRequest.getStoryPoint());
//        return repository.save(existingKhoa);
//    }
//
//    public String deleteTask(String taskId){
//        repository.deleteById(taskId);
//        return taskId+" task deleted from dashboard ";
//    }
}
