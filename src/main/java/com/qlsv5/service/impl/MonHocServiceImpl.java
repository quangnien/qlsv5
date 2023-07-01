package com.qlsv5.service.impl;

import com.qlsv5.dto.MonHocModifyDto;
import com.qlsv5.entity.MHTQEntity;
import com.qlsv5.entity.MonHocEntity;
import com.qlsv5.repository.MHTQRepository;
import com.qlsv5.repository.MonHocRepository;
import com.qlsv5.service.MonHocService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MonHocServiceImpl implements MonHocService {

    @Autowired
    private MonHocRepository monHocRepository;
    @Autowired
    private MHTQRepository mhtqRepository;

    @Override
    public List<MonHocModifyDto> getListMonHocByMaKhoa(String maKhoa, int page, int size){
//        Page<MonHocEntity> resultPage = monHocRepository.findAllByMaKhoa(maKhoa, pageable);

        List<MonHocModifyDto> monHocModifyDtoList = new ArrayList<>();
        List<MonHocEntity> monHocEntityList = monHocRepository.findAllByMaKhoa(maKhoa);
        for(MonHocEntity monHocEntity : monHocEntityList){
            List<MHTQEntity> mhtqEntityList = mhtqRepository.findAllByMaMh(monHocEntity.getMaMh());
            List<String> maMHTQList = new ArrayList<>();
            List<String> tenMHTQList = new ArrayList<>();
            for(MHTQEntity mhtqEntity : mhtqEntityList){
                maMHTQList.add(mhtqEntity.getMaMHTQ());
                tenMHTQList.add(mhtqEntity.getTenMHTQ());
            }

            MonHocModifyDto monHocModifyDto = new MonHocModifyDto();
            monHocModifyDto.setId(monHocEntity.getId());
            monHocModifyDto.setMaMh(monHocEntity.getMaMh());
            monHocModifyDto.setTenMh(monHocEntity.getTenMh());
            monHocModifyDto.setMaMHTQList(maMHTQList);
            monHocModifyDto.setTenMHTQList(tenMHTQList);

            monHocModifyDto.setMaKhoa(monHocEntity.getMaKhoa());
            monHocModifyDto.setPercentCc(monHocEntity.getPercentCc());
            monHocModifyDto.setPercentGk(monHocEntity.getPercentGk());
            monHocModifyDto.setPercentCk(monHocEntity.getPercentCk());
            monHocModifyDto.setSoTietLt(monHocEntity.getSoTietLt());
            monHocModifyDto.setSoTietTh(monHocEntity.getSoTietTh());
            monHocModifyDto.setSoTc(monHocEntity.getSoTc());

            monHocModifyDtoList.add(monHocModifyDto);
        }
        return monHocModifyDtoList;
    }

    @Override
    public List<MonHocEntity> findByTenMhContainingIgnoreCaseLike(String keySearch) {
        return monHocRepository.findByTenMhLikeIgnoreCase(keySearch);
    }

    @Override
    public List<MonHocEntity> getAll() {
        return monHocRepository.findAll();
    }

    @Override
    public MonHocEntity getMonHocByMaMh(String maMh) {
        return monHocRepository.getMonHocByMaMh(maMh);
    }

    @Override
    public MonHocEntity addNew(MonHocEntity monHocEntity) {
        monHocEntity.setId(UUID.randomUUID().toString().split("-")[0]);
        return monHocRepository.save(monHocEntity);
    }

    @Override
    public MonHocEntity updateExist(MonHocEntity monHocEntity) {
        return monHocRepository.save(monHocEntity);
    }

//    @Override
//    public List<MonHocEntity> findByTenMhContainingIgnoreCaseLike(String keySearch) {
//        return monHocRepository.findByTenMhLikeIgnoreCase(keySearch);
//    }

    public List<MonHocEntity> findAll() {
        return monHocRepository.findAll();
    }

    @Override
    public MonHocModifyDto findById(String id) {

        MonHocModifyDto monHocModifyDto = new MonHocModifyDto();

        MonHocEntity monHocEntity = monHocRepository.findById(id).get();
        List<MHTQEntity> mhtqEntityList = mhtqRepository.findAllByMaMh(monHocEntity.getMaMh());

        List<String> maMHTQList = new ArrayList<>();
        List<String> tenMHTQList = new ArrayList<>();
        for(MHTQEntity mhtqEntity : mhtqEntityList){
            maMHTQList.add(mhtqEntity.getMaMHTQ());
            tenMHTQList.add(mhtqEntity.getTenMHTQ());
        }

        monHocModifyDto.setId(monHocEntity.getId());
        monHocModifyDto.setMaMh(monHocEntity.getMaMh());
        monHocModifyDto.setTenMh(monHocEntity.getTenMh());
        monHocModifyDto.setMaMHTQList(maMHTQList);
        monHocModifyDto.setTenMHTQList(tenMHTQList);

        monHocModifyDto.setMaKhoa(monHocEntity.getMaKhoa());
        monHocModifyDto.setPercentCc(monHocEntity.getPercentCc());
        monHocModifyDto.setPercentGk(monHocEntity.getPercentGk());
        monHocModifyDto.setPercentCk(monHocEntity.getPercentCk());
        monHocModifyDto.setSoTietLt(monHocEntity.getSoTietLt());
        monHocModifyDto.setSoTietTh(monHocEntity.getSoTietTh());
        monHocModifyDto.setSoTc(monHocEntity.getSoTc());

        return monHocModifyDto;
    }

//    @Override
//    public MonHocEntity findByMaMh(String maMh) {
//        return monHocRepository.findByMaMh(maMh);
//    }
//
//    @Override
//    public List<String> deleteList(List<String> lstId) {
//
//        List<String> lstSuccess = new ArrayList<>();
//
//        for (String itemId : lstId) {
//            int countMaMonHoc = monHocRepository.countMonHocById(itemId);
//            if(countMaMonHoc > 0){
//
//                /* important: validator */
////                MonHocEntity monHocEntity = monHocRepository.findById(itemId).get();
////                int countGiangVienOnDsLopTcTable = dsLopTcRepository.countDsLopTcByMaMh(monHocEntity.getMaMh());
////
////                if(countGiangVienOnDsLopTcTable > 0){
////                    continue;
////                }
////                else {
////                    lstSuccess.add(itemId);
////                    monHocRepository.deleteById(itemId);
////                }
//
//            }
//        }
//        return null;
//    }

//    @Override
//    public List<MonHocEntity> findAllByMaLop(String maLop){
//
//        /* LOP -> CTDT -> TICH_LUY -> MON_HOC list */
//        LopEntity lopEntity = lopRepository.findByMaLop(maLop);
////        CTDTEntity ctdtEntity = ctdtRepository.findByMaCTDT(lopEntity.getMaCTDT());
//        List<TichLuyEntity> tichLuyEntityList = tichLuyRepository.findAllByMaCTDT(lopEntity.getMaCTDT());
//        List<MonHocEntity> monHocEntityList = new ArrayList<>();
//        for(TichLuyEntity tichLuyEntity : tichLuyEntityList){
//            MonHocEntity monHocEntity = monHocRepository.findByMaMh(tichLuyEntity.getMaMh());
//            if(monHocEntity != null){
//                monHocEntityList.add(monHocEntity);
//            }
//        }
//
//        return monHocEntityList;
//    }

    @Override
    public List<MonHocModifyDto> findAllMonHocModify() {
        List<MonHocModifyDto> monHocModifyDtoList = new ArrayList<>();
        List<MonHocEntity> monHocEntityList = monHocRepository.findAll();
        for(MonHocEntity monHocEntity : monHocEntityList){
            List<MHTQEntity> mhtqEntityList = mhtqRepository.findAllByMaMh(monHocEntity.getMaMh());
            List<String> maMHTQList = new ArrayList<>();
            List<String> tenMHTQList = new ArrayList<>();
            for(MHTQEntity mhtqEntity : mhtqEntityList){
                maMHTQList.add(mhtqEntity.getMaMHTQ());
                tenMHTQList.add(mhtqEntity.getTenMHTQ());
            }

            MonHocModifyDto monHocModifyDto = new MonHocModifyDto();
            monHocModifyDto.setId(monHocEntity.getId());
            monHocModifyDto.setMaMh(monHocEntity.getMaMh());
            monHocModifyDto.setTenMh(monHocEntity.getTenMh());
            monHocModifyDto.setMaMHTQList(maMHTQList);
            monHocModifyDto.setTenMHTQList(tenMHTQList);

            monHocModifyDto.setMaKhoa(monHocEntity.getMaKhoa());
            monHocModifyDto.setPercentCc(monHocEntity.getPercentCc());
            monHocModifyDto.setPercentGk(monHocEntity.getPercentGk());
            monHocModifyDto.setPercentCk(monHocEntity.getPercentCk());
            monHocModifyDto.setSoTietLt(monHocEntity.getSoTietLt());
            monHocModifyDto.setSoTietTh(monHocEntity.getSoTietTh());
            monHocModifyDto.setSoTc(monHocEntity.getSoTc());

            monHocModifyDtoList.add(monHocModifyDto);
        }
        return monHocModifyDtoList;
    }

//    @Override
//    public List<MonHocEntity> findAllByMaGV(String maGV) {
//        List<MonHocEntity> monHocEntityList = new ArrayList<>();
//        List<GiangDayEntity> giangDayEntityList = giangDayRepository.findAllByMaGV(maGV);
//        for(GiangDayEntity giangDayEntity : giangDayEntityList){
//            MonHocEntity monHocEntity = monHocRepository.findByMaMh(giangDayEntity.getMaMh());
//            monHocEntityList.add(monHocEntity);
//        }
//        return monHocEntityList;
//    }
}
