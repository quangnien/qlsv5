package com.qlsv5.service.impl;

import com.qlsv5.dto.MHMHTQDto;
import com.qlsv5.entity.MHTQEntity;
import com.qlsv5.entity.MonHocEntity;
import com.qlsv5.repository.MHTQRepository;
import com.qlsv5.repository.MonHocRepository;
import com.qlsv5.service.MHTQService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MHTQServiceImpl implements MHTQService {

    @Autowired
    private MHTQRepository mhtqRepository;
    @Autowired
    private MonHocRepository monHocRepository;


    @Override
    public void updateExist(MHMHTQDto mhmhtqDto) {
        String maMh = mhmhtqDto.getMaMh();

        for(String maMHTQ: mhmhtqDto.getMaMHTQList()){
            MHTQEntity mhtqEntity = new MHTQEntity();
            mhtqEntity.setId(UUID.randomUUID().toString().split("-")[0]);
            mhtqEntity.setMaMHTQ(maMHTQ);
            mhtqEntity.setMaMh(maMh);

            MonHocEntity monHocEntity = monHocRepository.findByMaMh(maMh);
            MonHocEntity monHocTienQuyetEntity = monHocRepository.findByMaMh(maMHTQ);

            mhtqEntity.setTenMh(monHocEntity.getTenMh());
            mhtqEntity.setTenMHTQ(monHocTienQuyetEntity.getTenMh());

            mhtqRepository.save(mhtqEntity);
        }
    }

    @Override
    public List<MHTQEntity> findAllByMaMh(String maMh) {
        return mhtqRepository.findAllByMaMh(maMh);
    }

    @Override
    public MHTQEntity findById(String id) {
        return mhtqRepository.findById(id).get();
    }

    @Override
    public List<MHTQEntity> findAll() {
        return mhtqRepository.findAll();
    }

    @Override
    public void deleteRecord(String id) {
        mhtqRepository.deleteById(id);
    }

}
