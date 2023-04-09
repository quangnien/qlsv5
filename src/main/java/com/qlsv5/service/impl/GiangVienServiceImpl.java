package com.qlsv5.service.impl;

import com.qlsv5.dto.TkbDto;
import com.qlsv5.entity.ChiTietLopTcEntity;
import com.qlsv5.entity.DsLopTcEntity;
import com.qlsv5.entity.GiangVienEntity;
import com.qlsv5.entity.SinhVienEntity;
import com.qlsv5.repository.ChiTietLopTcRepository;
import com.qlsv5.repository.DsLopTcRepository;
import com.qlsv5.repository.GiangVienRepository;
import com.qlsv5.repository.SinhVienRepository;
import com.qlsv5.service.GiangVienService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class GiangVienServiceImpl implements GiangVienService {

    @Autowired
    private GiangVienRepository giangVienRepository;

    @Autowired
    private DsLopTcRepository dsLopTcRepository;

    @Autowired
    private ChiTietLopTcRepository chiTietLopTcRepository;

    //CRUD  CREATE , READ , UPDATE , DELETE

    @Override
    public List<String> deleteLstGiangVien(List<String> lstGiangVienId) {
        List<String> lstSuccess = new ArrayList<>();
        for (String item : lstGiangVienId) {
            int countMaGiangVien = giangVienRepository.countGiangVienById(item);
            if(countMaGiangVien > 0){
                lstSuccess.add(item);
                giangVienRepository.deleteById(item);
            }
        }
        return lstSuccess;
    }

    @Override
    public List<GiangVienEntity> findAllGiangVien() {
        return giangVienRepository.findAll();
    }

    @Override
    public GiangVienEntity getGiangVienById(String giangVienId){
        return giangVienRepository.findById(giangVienId).get();
    }

    @Override
    public List<GiangVienEntity> getListGiangVienByMaKhoa(String maKhoa){
        return giangVienRepository.getListGiangVienByMaKhoa(maKhoa);
    }

    @Override
    public List<TkbDto> getListTKBForGiangVien(String maGiangVien, TkbDto tkbDto) {

        List<TkbDto> tkbDtos = new ArrayList<>();

        List<DsLopTcEntity> dsLopTcEntities = dsLopTcRepository.findByMaGvAndTimeBdLessThanEqualAndTimeKtGreaterThanEqual(maGiangVien,
                tkbDto.getTimeInputBegin(), tkbDto.getTimeInputEnd());

        for (DsLopTcEntity lopTcEntity : dsLopTcEntities) {
            List<ChiTietLopTcEntity> listChiTietLopTcEntity = chiTietLopTcRepository.getListChiTietLopTcByMaLopTc(lopTcEntity.getMaLopTc());
            for(ChiTietLopTcEntity chiTietLopTcEntity : listChiTietLopTcEntity){

                TkbDto itemTkb = new TkbDto();

                itemTkb.setKy(lopTcEntity.getKy());
                itemTkb.setIdLopTc(lopTcEntity.getId());
                itemTkb.setMaGv(lopTcEntity.getMaGv());
                itemTkb.setMaLop(lopTcEntity.getMaLop());
                itemTkb.setMaLopTc(lopTcEntity.getMaLopTc());
                itemTkb.setMaMh(lopTcEntity.getMaMh());
                itemTkb.setNienKhoa(lopTcEntity.getNienKhoa());
                itemTkb.setSoLuong(lopTcEntity.getSoLuong());

                itemTkb.setThu(chiTietLopTcEntity.getThu());
                itemTkb.setTiet(chiTietLopTcEntity.getTiet());
                itemTkb.setSoTiet(chiTietLopTcEntity.getSoTiet());
                itemTkb.setPhong(chiTietLopTcEntity.getPhong());

                tkbDtos.add(itemTkb);
            }
        }

        return tkbDtos;
    }

}