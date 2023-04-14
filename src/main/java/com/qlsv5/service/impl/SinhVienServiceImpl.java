package com.qlsv5.service.impl;

import com.qlsv5.dto.TkbDto;
import com.qlsv5.entity.ChiTietLopTcEntity;
import com.qlsv5.entity.DiemEntity;
import com.qlsv5.entity.DsLopTcEntity;
import com.qlsv5.entity.SinhVienEntity;
import com.qlsv5.repository.ChiTietLopTcRepository;
import com.qlsv5.repository.DiemRepository;
import com.qlsv5.repository.DsLopTcRepository;
import com.qlsv5.repository.SinhVienRepository;
import com.qlsv5.service.SinhVienService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SinhVienServiceImpl implements SinhVienService {

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Autowired
    private DsLopTcRepository dsLopTcRepository;

    @Autowired
    private ChiTietLopTcRepository chiTietLopTcRepository;

    @Autowired
    private DiemRepository diemRepository;

    @Override
    public List<SinhVienEntity> getListSinhVienByMaLop(String maLop){
        return sinhVienRepository.getListSinhVienByMaLop(maLop);
    }

    @Override
    public List<TkbDto> getListTKBForSinhVien(String maSinhVien, TkbDto tkbDto) {

        List<TkbDto> tkbDtos = new ArrayList<>();

        /* get list Diem (cotain list maLopTC) */
        List<DiemEntity> listDiemEntities = diemRepository.getListDiemByMaSv(maSinhVien);

        List<DsLopTcEntity> dsLopTcEntities = new ArrayList<>();

        for(DiemEntity diemEntity : listDiemEntities){
            DsLopTcEntity lopTcEntity =  dsLopTcRepository.findByMaLopTcAndTimeBdGreaterThanEqualAndTimeKtLessThanEqual(diemEntity.getMaLopTc(),
                    tkbDto.getTimeInputBegin(), tkbDto.getTimeInputEnd());
            if(lopTcEntity != null){
                dsLopTcEntities.add(lopTcEntity);
            }
        }

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
