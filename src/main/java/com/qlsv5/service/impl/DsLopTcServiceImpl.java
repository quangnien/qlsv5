package com.qlsv5.service.impl;

import com.qlsv5.dto.DsLopTcMonHocGiangVienLopDto;
import com.qlsv5.entity.DsLopTcEntity;
import com.qlsv5.repository.DsLopTcRepository;
import com.qlsv5.service.DsLopTcService;
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
public class DsLopTcServiceImpl implements DsLopTcService {

    @Autowired
    private DsLopTcRepository dsLopTcRepository;

    @Override
    public List<DsLopTcEntity> getListLopTcByMaLop(String maLop, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<DsLopTcEntity> resultPage = dsLopTcRepository.findAllByMaLop(maLop, pageable);
        return resultPage.getContent();
    }

    @Override
    public List<DsLopTcEntity> getListLopTcByMaMh(String maMh) {
        return dsLopTcRepository.findAllByMaMh(maMh);
    }

    @Override
    public List<DsLopTcEntity> findAllByMaLopAndMaKeHoach(String maLop, String maKeHoach) {
        return dsLopTcRepository.findAllByMaLopAndMaKeHoach(maLop, maKeHoach);
    }

}
