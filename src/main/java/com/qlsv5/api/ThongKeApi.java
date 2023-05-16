package com.qlsv5.api;

import com.qlsv5.common.FunctionCommon;
import com.qlsv5.common.ReturnObject;
import com.qlsv5.dto.DsLopTcDto;
import com.qlsv5.dto.DsLopTcMonHocGiangVienLopDto;
import com.qlsv5.dto.ThongKeDiemDto;
import com.qlsv5.entity.*;
import com.qlsv5.enumdef.XepLoaiEnum;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.service.*;
import com.qlsv5.validation.ValidatorDiem;
import com.qlsv5.validation.ValidatorDsLopTc;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author NienNQ
 * @created 2023 - 03 - 24 6:20 PM
 * @project qlsv
 */
@Slf4j
@RestController
@RequestMapping("/api/admin")
@Tag(name = "Thong Ke", description = "Management APIs for THONG KE.")
public class ThongKeApi {

    @Autowired
    private CommonService commonService;

    @Autowired
    private ValidatorDiem validatorDiem;

    @Autowired
    private ValidatorDsLopTc validatorDsLopTc;

    @Autowired
    private DiemService diemService;

    @Autowired
    private MonHocService monHocService;

    @Autowired
    private DsLopTcService dsLopTcService;

    @Autowired
    private GiangVienService giangVienService;

    @Autowired
    private LopService lopService;

    @Operation(summary = "Thong Ke.")
    @GetMapping("/search/thong-ke")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DiemEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DiemEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DiemEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DiemEntity.class)) })})
    public ResponseEntity<?> searchLopTcByKeywordAndKeHoachNamId(@RequestParam(required = true) String idKeHoachNam, @RequestParam(required = false, defaultValue = "") String keySearch) throws BusinessException {

        ReturnObject returnObject = new ReturnObject();

        try {
            log.info("Thong ke diem!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorDsLopTc.validateSearchThongKe(idKeHoachNam, keySearch);

            List<MonHocEntity> monHocEntityList = new ArrayList<>();
            if(keySearch.equals("")){
                monHocEntityList = monHocService.getAll();
            }
            else {
                monHocEntityList = monHocService.findByTenMhContainingIgnoreCaseLike(keySearch);
            }

            List<DsLopTcEntity> dsLopTcEntityList = new ArrayList<>();
            List<DsLopTcMonHocGiangVienLopDto> dsLopTcMonHocGiangVienLopDtoList = new ArrayList<>();

            for (MonHocEntity monHocEntity : monHocEntityList) {

                List<DsLopTcEntity> dsLopTcEntities = dsLopTcService.getListLopTcByMaMh(monHocEntity.getMaMh());

                for (DsLopTcEntity dsLopTcEntity : dsLopTcEntities) {
                    dsLopTcEntityList.add(dsLopTcEntity);

                    String maGv = dsLopTcEntity.getMaGv();
//                    String maMh = dsLopTcEntity.getMaMh();
                    String maLopNotParam = dsLopTcEntity.getMaLop();
                    String tenGv = "";
                    String tenMh = "";
                    String tenLop = "";

                    GiangVienEntity giangVienEntity = giangVienService.getGiangVienByMaGv(maGv);
//                    MonHocEntity monHocEntity = monHocService.getMonHocByMaMh(maMh);

                    LopEntity lopEntity = lopService.getLopByMaLop(maLopNotParam);

                    if(giangVienEntity != null){
                        tenGv = giangVienEntity.getHo() + " " + giangVienEntity.getTen();
                    }
                    if(monHocEntity != null){
                        tenMh = monHocEntity.getTenMh();
                    }
                    if(lopEntity != null){
                        tenLop = lopEntity.getTenLop();
                    }

                    ModelMapper modelMapper = new ModelMapper();
                    DsLopTcMonHocGiangVienLopDto dsLopTcMonHocGiangVienLopDto = new DsLopTcMonHocGiangVienLopDto();
                    dsLopTcMonHocGiangVienLopDto = modelMapper.map(dsLopTcEntity, DsLopTcMonHocGiangVienLopDto.class);
                    dsLopTcMonHocGiangVienLopDto.setTenGv(tenGv);
                    dsLopTcMonHocGiangVienLopDto.setTenMh(tenMh);
                    dsLopTcMonHocGiangVienLopDto.setTenLop(tenLop);

                    dsLopTcMonHocGiangVienLopDtoList.add(dsLopTcMonHocGiangVienLopDto);

                }
            }

            returnObject.setRetObj(dsLopTcMonHocGiangVienLopDtoList);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage(ex.getMessage());
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

//    /* CREATE */
    @Operation(summary = "Thong Ke.")
    @GetMapping("/diem/thong-ke")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DiemEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DiemEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DiemEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DiemEntity.class)) })})
    public ResponseEntity<?> thongKeDiem(@RequestParam(required = true) String idLopTc, @RequestParam(required = true) String col) {

        ReturnObject returnObject = new ReturnObject();

        List<ThongKeDiemDto> thongKeDiemDtos = new ArrayList<>();

        try {
            log.info("Thong ke diem!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorDiem.validateThongKeDiem(idLopTc, col);

            /* list valid */
            DsLopTcEntity dsLopTcEntity = (DsLopTcEntity) commonService.getObjectById(idLopTc, new DsLopTcDto());
            List<DiemEntity> diemDtoList = diemService.getListDiemByMaLopTc(dsLopTcEntity.getMaLopTc(), 0 , 1000);
            List<DiemEntity> diemDtoListValid = new ArrayList<>();
            for (DiemEntity diemEntity: diemDtoList) {
                if(diemEntity.getXepLoai() != null){
                    diemDtoListValid.add(diemEntity);
                }
            }

            if(col.toUpperCase().equals("CC")){
                for(float i = 0; i <= 10; i += 0.5){
                    ThongKeDiemDto thongKeDiemDto = new ThongKeDiemDto();
                    thongKeDiemDto.setType(Float.toString(i));
                    thongKeDiemDto.setSoLuong(0);

//                    Iterator<DiemEntity> iter = diemDtoListValid.iterator();
//                    while (iter.hasNext()) {
//                        DiemEntity diemEntity = iter.next();
//                        if (diemEntity.getCc() == (float) i) {
//                            thongKeDiemDto.setSoLuong(thongKeDiemDto.getSoLuong() + 1);
//                            iter.remove(); // xóa phần tử đang lặp qua
//                        }
//                    }

                    for (DiemEntity diemEntity: diemDtoListValid) {
                        if(FunctionCommon.roundToHalf(diemEntity.getCc()) == i){
                            thongKeDiemDto.setSoLuong(thongKeDiemDto.getSoLuong() + 1);
                        }
                    }

                    thongKeDiemDtos.add(thongKeDiemDto);
                }
            }
            else if(col.toUpperCase().equals("GK")){
                for(float i = 0; i <= 10; i += 0.5){
                    ThongKeDiemDto thongKeDiemDto = new ThongKeDiemDto();
                    thongKeDiemDto.setType(Float.toString(i));
                    thongKeDiemDto.setSoLuong(0);

                    for (DiemEntity diemEntity: diemDtoListValid) {
                        if(FunctionCommon.roundToHalf(diemEntity.getGk()) == i){
                            thongKeDiemDto.setSoLuong(thongKeDiemDto.getSoLuong() + 1);
                        }
                    }

                    thongKeDiemDtos.add(thongKeDiemDto);
                }
            }
            else if(col.toUpperCase().equals("CK")){
                for(float i = 0; i <= 10; i += 0.5){
                    ThongKeDiemDto thongKeDiemDto = new ThongKeDiemDto();
                    thongKeDiemDto.setType(Float.toString(i));
                    thongKeDiemDto.setSoLuong(0);

                    for (DiemEntity diemEntity: diemDtoListValid) {
                        if(FunctionCommon.roundToHalf(diemEntity.getCk()) == i){
                            thongKeDiemDto.setSoLuong(thongKeDiemDto.getSoLuong() + 1);
                        }
                    }

                    thongKeDiemDtos.add(thongKeDiemDto);
                }
            }
            else if(col.toUpperCase().equals("TB")){
                for(float i = 0; i <= 10; i += 0.5){
                    ThongKeDiemDto thongKeDiemDto = new ThongKeDiemDto();
                    thongKeDiemDto.setType(Float.toString(i));
                    thongKeDiemDto.setSoLuong(0);

                    for (DiemEntity diemEntity: diemDtoListValid) {
                        if(FunctionCommon.roundToHalf(diemEntity.getTb()) == i){
                            thongKeDiemDto.setSoLuong(thongKeDiemDto.getSoLuong() + 1);
                        }
                    }

                    thongKeDiemDtos.add(thongKeDiemDto);
                }
            }
            else if(col.toUpperCase().equals("XEPLOAI")){

                List<String> xepLoaiEnums = XepLoaiEnum.getComboList();

                for (String xepLoai: xepLoaiEnums) {
                    ThongKeDiemDto thongKeDiemDto = new ThongKeDiemDto();
                    thongKeDiemDto.setType(xepLoai);
                    thongKeDiemDto.setSoLuong(0);

                    for (DiemEntity diemEntity: diemDtoListValid) {
                        if(diemEntity.getXepLoai().equals(xepLoai)){
                            thongKeDiemDto.setSoLuong(thongKeDiemDto.getSoLuong() + 1);
                        }
                    }

                    thongKeDiemDtos.add(thongKeDiemDto);
                }
            }

            returnObject.setRetObj(thongKeDiemDtos);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage(ex.getMessage());
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

}
