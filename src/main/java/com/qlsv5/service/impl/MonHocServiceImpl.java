package com.qlsv5.service.impl;

import com.qlsv5.entity.MonHocEntity;
import com.qlsv5.repository.MonHocRepository;
import com.qlsv5.service.MonHocService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MonHocServiceImpl implements MonHocService {

    @Autowired
    private MonHocRepository monHocRepository;

    @Override
    public List<MonHocEntity> getListMonHocByMaKhoa(String maKhoa, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<MonHocEntity> resultPage = monHocRepository.findAllByMaKhoa(maKhoa, pageable);

//        return monHocRepository.getListMonHocByMaKhoa(maKhoa);
        return resultPage.getContent();
    }

    @Override
    public List<MonHocEntity> findByTenMhContainingIgnoreCaseLike(String keySearch) {
        return monHocRepository.findByTenMhLikeIgnoreCase(keySearch);
    }

    @Override
    public List<MonHocEntity> getAll() {
        return monHocRepository.findAll();
    }

}
