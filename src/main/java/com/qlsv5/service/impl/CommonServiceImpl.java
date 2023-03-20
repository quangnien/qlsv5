package com.qlsv5.service.impl;

import com.qlsv5.dto.KhoaDto;
import com.qlsv5.dto.LopDto;
import com.qlsv5.dto.SinhVienDto;
import com.qlsv5.entity.KhoaEntity;
import com.qlsv5.entity.LopEntity;
import com.qlsv5.entity.SinhVienEntity;
import com.qlsv5.repository.KhoaRepository;
import com.qlsv5.repository.LopRepository;
import com.qlsv5.repository.SinhVienRepository;
import com.qlsv5.service.CommonService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CommonServiceImpl implements CommonService {
    @Autowired
    private LopRepository lopRepository;

    @Autowired
    private KhoaRepository khoaRepository;

    @Autowired
    private SinhVienRepository sinhVienRepository;

    //CRUD  CREATE , READ , UPDATE , DELETE

    @Override
    public Object updateObject(Object object) {
        ModelMapper modelMapper = new ModelMapper();

        if(object instanceof KhoaDto){
            KhoaEntity khoaEntity = modelMapper.map(object, KhoaEntity.class);
            return khoaRepository.save(khoaEntity);
        }
        else if(object instanceof LopDto){
            LopEntity lopEntity = modelMapper.map(object, LopEntity.class);
            return lopRepository.save(lopEntity);
        }
        else if(object instanceof SinhVienDto){
            SinhVienEntity sinhVienEntity = modelMapper.map(object, SinhVienEntity.class);
            return sinhVienRepository.save(sinhVienEntity);
        }

        return null;
    }

    @Override
    public Object addObject(Object object) {
        ModelMapper modelMapper = new ModelMapper();

        if(object instanceof KhoaDto){
            KhoaEntity result = new KhoaEntity();
            result = modelMapper.map(object, KhoaEntity.class);
            result.setId(UUID.randomUUID().toString().split("-")[0]);
            return khoaRepository.save(result);
        }
        else if(object instanceof LopDto){
            LopEntity result = new LopEntity();
            result = modelMapper.map(object, LopEntity.class);
            result.setId(UUID.randomUUID().toString().split("-")[0]);
            return lopRepository.save(result);
        }
        else if(object instanceof SinhVienDto){
            SinhVienEntity result = new SinhVienEntity();
            result = modelMapper.map(object, SinhVienEntity.class);
            result.setId(UUID.randomUUID().toString().split("-")[0]);
            return sinhVienRepository.save(result);
        }

        return null;
    }

}
