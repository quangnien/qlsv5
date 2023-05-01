package com.qlsv5.api;

import com.qlsv5.common.FunctionCommon;
import com.qlsv5.common.ReturnObject;
import com.qlsv5.dto.DsLopTcDto;
import com.qlsv5.dto.ThongKeDiemDto;
import com.qlsv5.entity.DiemEntity;
import com.qlsv5.entity.DsLopTcEntity;
import com.qlsv5.enumdef.XepLoaiEnum;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.service.CommonService;
import com.qlsv5.service.DiemService;
import com.qlsv5.service.DsLopTcService;
import com.qlsv5.validation.ValidatorDiem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    private DiemService diemService;

    @Autowired
    private DsLopTcService dsLopTcService;

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
    public ResponseEntity<?> thongKeDiem(@Valid String idLopTc, @Valid String col) throws BusinessException {

        ReturnObject returnObject = new ReturnObject();

        validatorDiem.validateThongKeDiem(idLopTc, col);

        List<ThongKeDiemDto> thongKeDiemDtos = new ArrayList<>();

        try {
            log.info("Thong ke diem!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

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
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }
//
//    /* DELETE */
//    @DeleteMapping("/dang-ky-mon")
//    @Operation(summary = "Delete Dang Ky Mon by list id")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Success",
//                    content = {
//                            @Content(mediaType = "application/json", schema = @Schema(implementation = DiemEntity.class)) }),
//            @ApiResponse(responseCode = "401", description = "Unauthorized",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DiemEntity.class)) }),
//            @ApiResponse(responseCode = "403", description = "Forbidden",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DiemEntity.class)) }),
//            @ApiResponse(responseCode = "500", description = "Internal server error",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DiemEntity.class)) })})
//    public ResponseEntity<?> deleteDangKyMon(@Valid @RequestBody List<String> lstDiemId) {
//
//        ReturnObject returnObject = new ReturnObject();
//        try {
//            log.info("Delete List Diem!");
//
//            returnObject.setStatus(ReturnObject.SUCCESS);
//            returnObject.setMessage("200");
//
//            List<String> deleteSuccess = commonService.deleteLstObject(lstDiemId, new DiemDto());
//            returnObject.setRetObj(deleteSuccess);
//        }
//        catch (Exception ex){
//            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage(ex.getMessage());
//        }
//
//        return ResponseEntity.ok(returnObject);
//    }



}
