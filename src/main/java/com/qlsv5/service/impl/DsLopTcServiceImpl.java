package com.qlsv5.service.impl;

import com.qlsv5.entity.DsLopTcEntity;
import com.qlsv5.service.impl.repository.DsLopTcRepository;
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

    @Override
    public List<DsLopTcEntity> findAllByMaMhAndMaKeHoach(String maMh, String maKeHoach) {
        return dsLopTcRepository.findAllByMaMhAndMaKeHoach(maMh, maKeHoach);
    }

    @Override
    public DsLopTcEntity getDsLopTcByMaLopTcAndMaKeHoach(String maLopTc, String maKeHoach) {
        return dsLopTcRepository.getDsLopTcByMaLopTcAndMaKeHoach(maLopTc, maKeHoach);
    }

    @Override
    public List<DsLopTcEntity> findAllByMaGvAndMaKeHoach(String maGv, String maKeHoach) {
        return dsLopTcRepository.findAllByMaGvAndMaKeHoach(maGv, maKeHoach);
    }

    @Override
    public DsLopTcEntity getDsLopTcByMaLopTc(String maLopTc) {
        return dsLopTcRepository.getDsLopTcByMaLopTc(maLopTc);
    }

    @Override
    public List<DsLopTcEntity> findAllByMaKeHoach(String maKeHoach) {
        return dsLopTcRepository.findAllByMaKeHoach(maKeHoach);
    }

    @Override
    public DsLopTcEntity getDsLopTcById(String id) {
        return dsLopTcRepository.getDsLopTcById(id);
    }

}
