package com.qlsv5.service.impl;

import com.qlsv5.dto.LopDto;
import com.qlsv5.entity.LopEntity;
import com.qlsv5.repository.LopRepository;
import com.qlsv5.service.LopService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class LopServiceImpl implements LopService {
    @Autowired
    private LopRepository repository;

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
            int countMaLop = repository.countLopById(item);
            if(countMaLop > 0){
                lstSuccess.add(item);
                repository.deleteById(item);
            }
        }
        return lstSuccess;
    }

    @Override
    public List<LopEntity> findAllLop() {
        return repository.findAll();
    }

    @Override
    public LopEntity getLopById(String lopId){
        return repository.findById(lopId).get();
    }
}
