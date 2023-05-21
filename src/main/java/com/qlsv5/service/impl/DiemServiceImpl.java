package com.qlsv5.service.impl;

import com.qlsv5.entity.DiemEntity;
import com.qlsv5.entity.DsLopTcEntity;
import com.qlsv5.entity.LopEntity;
import com.qlsv5.entity.SinhVienEntity;
import com.qlsv5.repository.DiemRepository;
import com.qlsv5.repository.DsLopTcRepository;
import com.qlsv5.service.DiemService;
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
public class DiemServiceImpl implements DiemService {

    @Autowired
    private DiemRepository diemRepository;

    @Autowired
    private DsLopTcRepository dsLopTcRepository;

    @Override
    public List<DiemEntity> getListDiemByMaLopTc(String maLopTc, int page, int size){

        Pageable pageable = PageRequest.of(page, size);
        Page<DiemEntity> resultPage = diemRepository.findAllByMaLopTc(maLopTc, pageable);
        
        return resultPage.getContent();
//        return diemRepository.getListDciemByMaLopTc(maLopTc);
    }

    @Override
    public List<DiemEntity> getListDiemByMaSv(String maSv) {
        return diemRepository.findAllByMaSv(maSv);
    }

    @Override
    public List<DiemEntity> getListDiemByMaLopTc(String maLopTc) {
        return diemRepository.findAllByMaLopTc(maLopTc);
    }

    @Override
    public DiemEntity getDiemByMaSvAndMaLopTc(String maSv, String maLopTc) {
        return diemRepository.getDiemByMaSvAndMaLopTc(maSv, maLopTc);
    }

    @Override
    public void deleteDangKyMon(DiemEntity diemEntity) {

        /*SET SOLUONGCON CUA LOPTINCHI*/
        DsLopTcEntity dsLopTcEntity = dsLopTcRepository.getDsLopTcByMaLopTc(diemEntity.getMaLopTc());
        dsLopTcEntity.setSoLuongCon(dsLopTcEntity.getSoLuongCon() + 1);
        dsLopTcRepository.save(dsLopTcEntity);

        diemRepository.delete(diemEntity);
    }

}
