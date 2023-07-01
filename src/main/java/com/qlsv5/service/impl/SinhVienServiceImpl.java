package com.qlsv5.service.impl;

import com.qlsv5.dto.TkbDto;
import com.qlsv5.dto.WrapTkbDto;
import com.qlsv5.entity.*;
import com.qlsv5.service.KeHoachNamService;
import com.qlsv5.service.SinhVienService;
import com.qlsv5.service.impl.repository.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public class SinhVienServiceImpl implements SinhVienService {

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Autowired
    private DsLopTcRepository dsLopTcRepository;

    @Autowired
    private ChiTietLopTcRepository chiTietLopTcRepository;

    @Autowired
    private DiemRepository diemRepository;

    @Autowired
    private KeHoachNamService keHoachNamService;

    @Autowired
    private MonHocRepository monHocRepository;

    @Autowired
    private GiangVienRepository giangVienRepository;

    @Override
    public List<SinhVienEntity> getListSinhVienByMaLop(String maLop, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<SinhVienEntity> resultPage = sinhVienRepository.findAllByMaLop(maLop, pageable);

//        return sinhVienRepository.getListSinhVienByMaLop(maLop);
        return resultPage.getContent();
    }

    @Override
    public SinhVienEntity findByMaSv(String maSv) {
        return sinhVienRepository.findByMaSv(maSv);
    }

    @Override
    public List<WrapTkbDto> getListTKBForSv(String maSinhVien, String maKeHoach, int tuan) {
        List<TkbDto> tkbDtoList = new ArrayList<>();

        // từ maSinhVien -> tìm maGiangVien -> chuyển về bài toán getTkbByMaGiangVien

        // Ở dưới xong ta sẽ có 1 list các maLopTc
        List<DiemEntity> diemEntityList = diemRepository.getListDiemByMaSv(maSinhVien);

        List<DsLopTcEntity> dsLopTcEntityListBanDau = new ArrayList<>();
        for (DiemEntity diemEntity : diemEntityList) {
            dsLopTcEntityListBanDau.add(dsLopTcRepository.getDsLopTcByMaLopTc(diemEntity.getMaLopTc()));
        }

        KeHoachNamEntity keHoachNamEntity = keHoachNamService.getKeHoachNamByMaKeHoach(maKeHoach);

        LocalDate timeTuanBdParam = keHoachNamEntity.getTimeStudyBegin().plusDays((tuan - 1) * 7);
        LocalDate timeTuanKtParam = timeTuanBdParam.plusDays(7);

        List<DsLopTcEntity> dsLopTcEntityList = new ArrayList<>();
        for (DsLopTcEntity dsLopTcEntity : dsLopTcEntityListBanDau) {
            if (dsLopTcEntity.getMaKeHoach().equals(maKeHoach)) {
                dsLopTcEntityList.add(dsLopTcEntity);
//            dsLopTcRepository.findAllByMaGvAndMaKeHoach(maGiangVien, maKeHoach);
            }
        }

        for (DsLopTcEntity dsLopTcEntity : dsLopTcEntityList) {

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

            if (chiTietLopTcEntityListValid != null) {
                for (ChiTietLopTcEntity chiTietLopTcEntity : chiTietLopTcEntityListValid) {

                    ModelMapper modelMapper = new ModelMapper();
                    TkbDto tkbDto = modelMapper.map(dsLopTcEntity, TkbDto.class);

                    // bonus TEN MON HOC
                    String tenMh = "";
                    MonHocEntity monHocEntity = monHocRepository.getMonHocByMaMh(dsLopTcEntity.getMaMh());
                    tenMh = monHocEntity.getTenMh();
                    tkbDto.setTenMh(tenMh);

                    // bonus TEN GIANG VIEN
                    String tenGv = "";
                    GiangVienEntity giangVienEntity = giangVienRepository.findByMaGv(dsLopTcEntity.getMaGv());
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

        for (String thu : thuOfWeek) {
            List<TkbDto> tkbDtos = new ArrayList<>();
            WrapTkbDto wrapTkbDto = new WrapTkbDto();

            for (TkbDto tkbDto : tkbDtoList) {
                wrapTkbDto.setThu(thu);
                if (tkbDto.getThu().equals(thu)) {
                    tkbDtos.add(tkbDto);
                }
            }
            wrapTkbDto.setTkbDtoList(tkbDtos);

            wrapTkbDtoList.add(wrapTkbDto);
        }

        return wrapTkbDtoList;
    }
}
