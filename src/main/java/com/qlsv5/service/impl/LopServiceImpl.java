package com.qlsv5.service.impl;

import com.qlsv5.entity.LopEntity;
import com.qlsv5.repository.LopRepository;
import com.qlsv5.service.LopService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class LopServiceImpl implements LopService {

    @Autowired
    private LopRepository lopRepository;

    //CRUD  CREATE , READ , UPDATE , DELETE

//    @Override
//    public LopEntity addLop(LopDto lopDto) {
//        LopEntity result = new LopEntity();
//        ModelMapper modelMapper = new ModelMapper();
//        result = modelMapper.map(lopDto, LopEntity.class);
//        result.setId(UUID.randomUUID().toString().split("-")[0]);
//        return repository.save(result);
//    }

//    @Override
//    public LopEntity updateLop(LopDto lopDto) {
//        ModelMapper modelMapper = new ModelMapper();
//        LopEntity lopEntity = modelMapper.map(lopDto, LopEntity.class);
//        return repository.save(lopEntity);
//    }

    @Override
    public List<String> deleteLstLop(List<String> lstLopId) {
        List<String> lstSuccess = new ArrayList<>();
        for (String item : lstLopId) {
            int countMaLop = lopRepository.countLopById(item);
            if(countMaLop > 0){
                lstSuccess.add(item);
                lopRepository.deleteById(item);
            }
        }
        return lstSuccess;
    }

    @Override
    public List<LopEntity> findAllLop() {
        return lopRepository.findAll();
    }

    @Override
    public LopEntity getLopById(String lopId){
        return lopRepository.findById(lopId).get();
    }

    @Override
    public List<LopEntity> getListLopByMaKhoa(String maKhoa){
        return lopRepository.getListLopByMaKhoa(maKhoa);
    }
}
