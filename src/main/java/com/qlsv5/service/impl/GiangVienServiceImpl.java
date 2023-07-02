package com.qlsv5.service.impl;

import com.qlsv5.dto.TkbDto;
import com.qlsv5.dto.WrapTkbDto;
import com.qlsv5.entity.*;
import com.qlsv5.repository.ChiTietLopTcRepository;
import com.qlsv5.repository.DsLopTcRepository;
import com.qlsv5.repository.GiangVienRepository;
import com.qlsv5.repository.MonHocRepository;
import com.qlsv5.service.GiangVienService;
import com.qlsv5.service.KeHoachNamService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    @Autowired
    private KeHoachNamService keHoachNamService;

    @Autowired
    private MonHocRepository monHocRepository;

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
    public GiangVienEntity getGiangVienByMaGv(String taskId) {
        return giangVienRepository.findByMaGv(taskId);
    }

    @Override
    public List<GiangVienEntity> getListGiangVienByMaKhoa(String maKhoa){
        return giangVienRepository.getListGiangVienByMaKhoa(maKhoa);
    }

    @Override
    public List<TkbDto> getListTKBForGiangVien(String maGiangVien, TkbDto tkbDto) {

        List<TkbDto> tkbDtos = new ArrayList<>();

//        List<DsLopTcEntity> dsLopTcEntities = dsLopTcRepository.findByMaGvAndTimeBdLessThanEqualAndTimeKtGreaterThanEqual(maGiangVien,
//                tkbDto.getTimeInputBegin(), tkbDto.getTimeInputEnd());
//
//        for (DsLopTcEntity lopTcEntity : dsLopTcEntities) {
//            List<ChiTietLopTcEntity> listChiTietLopTcEntity = chiTietLopTcRepository.getListChiTietLopTcByMaLopTc(lopTcEntity.getMaLopTc());
//            for(ChiTietLopTcEntity chiTietLopTcEntity : listChiTietLopTcEntity){
//
//                TkbDto itemTkb = new TkbDto();
//
////                itemTkb.setKy(lopTcEntity.getKy());
//                itemTkb.setIdLopTc(lopTcEntity.getId());
//                itemTkb.setMaGv(lopTcEntity.getMaGv());
//                itemTkb.setMaLop(lopTcEntity.getMaLop());
//                itemTkb.setMaLopTc(lopTcEntity.getMaLopTc());
//                itemTkb.setMaMh(lopTcEntity.getMaMh());
////                itemTkb.setNienKhoa(lopTcEntity.getNienKhoa());
//                itemTkb.setSoLuong(lopTcEntity.getSoLuong());
//
//                itemTkb.setThu(chiTietLopTcEntity.getThu());
//                itemTkb.setTiet(chiTietLopTcEntity.getTiet());
//                itemTkb.setSoTiet(chiTietLopTcEntity.getSoTiet());
//                itemTkb.setPhong(chiTietLopTcEntity.getPhong());
//
//                tkbDtos.add(itemTkb);
//            }
//        }

        return tkbDtos;
    }

    @Override
    public List<GiangVienEntity> getListGiangVienPaging(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return giangVienRepository.findAll(pageable).getContent();
    }

    @Override
    public List<WrapTkbDto> getListTKBForGV(String maGiangVien, String maKeHoach, int tuan) {
        List<TkbDto> tkbDtoList = new ArrayList<>();

        KeHoachNamEntity keHoachNamEntity = keHoachNamService.getKeHoachNamByMaKeHoach(maKeHoach);

        LocalDate timeTuanBdParam = keHoachNamEntity.getTimeStudyBegin().plusDays((tuan - 1) * 7);
        LocalDate timeTuanKtParam = timeTuanBdParam.plusDays(7);

        List<DsLopTcEntity> dsLopTcEntityList = dsLopTcRepository.findAllByMaGvAndMaKeHoach(maGiangVien, maKeHoach);
        for(DsLopTcEntity dsLopTcEntity: dsLopTcEntityList){

            Sort sort = Sort.by(
                    Sort.Order.asc("thu"),
                    Sort.Order.asc("tiet")
            );
            List<ChiTietLopTcEntity> chiTietLopTcEntityList = chiTietLopTcRepository.findAllByMaLopTc(dsLopTcEntity.getMaLopTc(), sort);
            List<ChiTietLopTcEntity> chiTietLopTcEntityListValid = new ArrayList<>();

            for (ChiTietLopTcEntity chiTietLopTcEntity : chiTietLopTcEntityList) {
                if (timeTuanBdParam.isEqual(chiTietLopTcEntity.getTimeBd()) || timeTuanBdParam.isAfter(chiTietLopTcEntity.getTimeBd())
                    && (timeTuanKtParam.isEqual(chiTietLopTcEntity.getTimeKt()) || timeTuanKtParam.isBefore(chiTietLopTcEntity.getTimeKt()))) {
                    chiTietLopTcEntityListValid.add(chiTietLopTcEntity);
                }
            }

            if(chiTietLopTcEntityListValid != null){
                for (ChiTietLopTcEntity chiTietLopTcEntity: chiTietLopTcEntityListValid){

                    ModelMapper modelMapper = new ModelMapper();
                    TkbDto tkbDto = modelMapper.map(dsLopTcEntity, TkbDto.class);

                    // bonus TEN MON HOC
                    String tenMh = "";
                    MonHocEntity monHocEntity = monHocRepository.getMonHocByMaMh(dsLopTcEntity.getMaMh());
                    tenMh = monHocEntity.getTenMh();
                    tkbDto.setTenMh(tenMh);

                    // bonus TEN GIANG VIEN
                    String tenGv = "";
                    GiangVienEntity giangVienEntity = giangVienRepository.findByMaGv(maGiangVien);
                    tenGv = giangVienEntity.getHo() + " " + giangVienEntity.getTen();
                    tkbDto.setTenGv(tenGv);

                    tkbDto.setTiet(chiTietLopTcEntity.getTiet());
                    tkbDto.setSoTiet(chiTietLopTcEntity.getSoTiet());
                    tkbDto.setThu(chiTietLopTcEntity.getThu());
                    tkbDto.setPhong(chiTietLopTcEntity.getPhong());

                    tkbDtoList.add(tkbDto);
                }
            }

        }

        List<WrapTkbDto> wrapTkbDtoList = new ArrayList<>();

        List<String> thuOfWeek = new ArrayList<>();
        String thu2 = "02";
        String thu3 = "03";
        String thu4 = "04";
        String thu5 = "05";
        String thu6 = "06";
        String thu7 = "07";
        thuOfWeek.add(thu2);
        thuOfWeek.add(thu3);
        thuOfWeek.add(thu4);
        thuOfWeek.add(thu5);
        thuOfWeek.add(thu6);
        thuOfWeek.add(thu7);

        for (String thu: thuOfWeek){
            List<TkbDto> tkbDtos = new ArrayList<>();
            WrapTkbDto wrapTkbDto = new WrapTkbDto();

            for (TkbDto tkbDto: tkbDtoList){
                wrapTkbDto.setThu(thu);
                if(tkbDto.getThu().equals(thu)){
                    tkbDtos.add(tkbDto);
                }
            }
            wrapTkbDto.setTkbDtoList(tkbDtos);

            wrapTkbDtoList.add(wrapTkbDto);
        }


//        KeHoachNamEntity keHoachNamEntity = keHoachNamService.getKeHoachNamByMaKeHoach(maKeHoach);
//
//        Date timeTuanBd =
//
//        // Tính ngày bắt đầu của tuần
//        LocalDate startDate = LocalDate.now()
//                .with(TemporalAdjusters.previousssssssssssssssssssssssssssssssssssssssssssssssssssssssOrSame(DayOfWeek.from(LocalDate.ofYearDay(LocalDate.now().getYear(), 1))))
//                .plusWeeks(tuan - 1)
//                .with(TemporalAdjusters.previousOrSame(DayOfWeek.from(LocalDate.of(1, 1, 1))));
//
//        // Tính ngày kết thúc của tuần
//        LocalDate endDate = startDate.plusDays(6);

        return wrapTkbDtoList;
    }

    @Override
    public List<GiangVienEntity> getListGiangVienByMaKhoaPaging(int page, int size, String maKhoa) {
        Pageable pageable = PageRequest.of(page, size);
//        return giangVienRepository.findAll(pageable).getContent();
        return giangVienRepository.findAllByMaKhoa(maKhoa, pageable).getContent();

    }

}