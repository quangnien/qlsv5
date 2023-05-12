package com.qlsv5.api;

import com.qlsv5.common.ReturnObject;
import com.qlsv5.dto.DiemByMaSvAndMaKeHoachDto;
import com.qlsv5.dto.DiemDto;
import com.qlsv5.entity.*;
import com.qlsv5.service.*;
import com.qlsv5.validation.ValidatorDiem;
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
import org.springframework.validation.BindingResult;
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
@Tag(name = "Diem", description = "Management APIs for DIEM.")
public class DiemApi {

    @Autowired
    private CommonService commonService;

    @Autowired
    private ValidatorDiem validatorDiem;

    @Autowired
    private DiemService diemService;

    @Autowired
    private DsLopTcService dsLopTcService;

    @Autowired
    private MonHocService monHocService;

    @Autowired
    private SinhVienService sinhVienService;

    @Autowired
    private KeHoachNamService keHoachNamService;

    /* UPDATE */
    @PutMapping("/diem")
    @Operation(summary = "Update Diem.")
//     @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
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
    public ResponseEntity<?> updateDiem(@Valid @RequestBody DiemDto diem, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();
        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Update Diem!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorDiem.validateEditDiem(diem);

            DiemEntity diemEntity = (DiemEntity) commonService.updateObject(diem);

            returnObject.setRetObj(diemEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage(ex.getMessage());
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET ALL */
    @Operation(summary = "Get all Diem.")
    @GetMapping("/diem")
//     @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
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
    public ResponseEntity<?> getAllDiem() {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All Diem!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            List<Object> listDiem = commonService.findAllObject(new DiemDto());
            returnObject.setRetObj(listDiem);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage(ex.getMessage());
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET BY ID */
    @Operation(summary = "Get Diem by id.")
    @GetMapping("/diem/{diemId}")
//     @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
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
    public ResponseEntity<?> getDiemById(@PathVariable String diemId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get Diem By Id!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorDiem.validateGetDiemById(diemId);
            DiemEntity diemEntity = (DiemEntity) commonService.getObjectById(diemId, new DiemDto());
            returnObject.setRetObj(diemEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage(ex.getMessage());
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    @Operation(summary = "Get danh sach diem by maLopTc")
    @GetMapping("/diem/lopTc/{maLopTc}")
//     @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
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
    public ResponseEntity<?> getDsDiemByMaLopTc(@PathVariable String maLopTc,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "7") int size) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get danh sach diem By maLopTc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorDiem.validateGetListDiemByMaLopTc(maLopTc);
            List<DiemEntity> diemEntity = diemService.getListDiemByMaLopTc(maLopTc, page, size);
            returnObject.setRetObj(diemEntity);

            /*for paging*/
            List<DiemEntity> diemEntityForPaging = diemService.getListDiemByMaLopTc(maLopTc,  0, 100000);

            double totalPageDouble = (double) diemEntityForPaging.size() / size;
            int totalPageForPaging = (int) Math.ceil(totalPageDouble);

            returnObject.setPage(page);
            returnObject.setTotalRetObjs(diemEntityForPaging.size());
            returnObject.setTotalPages(totalPageForPaging);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage(ex.getMessage());
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    @Operation(summary = "Get danh sach diem sinh vien by maSinhVien & maKeHoach")
    @PostMapping("/diem/{maSv}")
//     @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
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
    public ResponseEntity<?> getDsDiemByMaSvAndMaKeHoach(@PathVariable(required = true) String maSv,
                                                @RequestParam(required = false, defaultValue = "") String maKeHoach,
                                                @RequestParam(required = false, defaultValue = "False") String mkh) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get danh sach diem By maLopTc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            if(mkh.equals("False")){
                if(maKeHoach.equals("")){
                    validatorDiem.validateGetListDiemByMaSv(maSv);

                    List<DiemByMaSvAndMaKeHoachDto> diemByMaSvDtoListDto = new ArrayList<>();

                    List<DiemEntity> diemEntityList = diemService.getListDiemByMaSv(maSv);
                    for (DiemEntity diemEntity: diemEntityList) {
                        ModelMapper modelMapper = new ModelMapper();

                        DsLopTcEntity dsLopTcEntity = dsLopTcService.getDsLopTcByMaLopTc(diemEntity.getMaLopTc());
                        if(dsLopTcEntity == null){
                            continue;
                        }
                        else {
                            MonHocEntity monHocEntity = monHocService.getMonHocByMaMh(dsLopTcEntity.getMaMh());

                            DiemByMaSvAndMaKeHoachDto diemByMaSvAndMaKeHoachDto = modelMapper.map(diemEntity, DiemByMaSvAndMaKeHoachDto.class);

                            diemByMaSvAndMaKeHoachDto.setPercentCc(monHocEntity.getPercentCc());
                            diemByMaSvAndMaKeHoachDto.setPercentGk(monHocEntity.getPercentGk());
                            diemByMaSvAndMaKeHoachDto.setPercentCk(monHocEntity.getPercentCk());
                            diemByMaSvAndMaKeHoachDto.setTenMh(monHocEntity.getTenMh());
                            diemByMaSvAndMaKeHoachDto.setMaMh(monHocEntity.getMaMh());
                            diemByMaSvAndMaKeHoachDto.setSoTc(monHocEntity.getSoTc());

                            diemByMaSvDtoListDto.add(diemByMaSvAndMaKeHoachDto);
                        }
                    }

                    returnObject.setRetObj(diemByMaSvDtoListDto);
                }
                else {
                    validatorDiem.validateGetListDiemByMaSvAndMaKeHoach(maSv, maKeHoach);

                    List<DiemByMaSvAndMaKeHoachDto> diemByMaSvAndMaKeHoachDtoListDto = new ArrayList<>();

                    List<DiemEntity> diemEntityList = diemService.getListDiemByMaSv(maSv);
                    for (DiemEntity diemEntity: diemEntityList) {
                        ModelMapper modelMapper = new ModelMapper();

                        DsLopTcEntity dsLopTcEntity = dsLopTcService.getDsLopTcByMaLopTcAndMaKeHoach(diemEntity.getMaLopTc(), maKeHoach);
                        if(dsLopTcEntity == null){
                            continue;
                        }
                        else {
                            MonHocEntity monHocEntity = monHocService.getMonHocByMaMh(dsLopTcEntity.getMaMh());

                            DiemByMaSvAndMaKeHoachDto diemByMaSvAndMaKeHoachDto = modelMapper.map(diemEntity, DiemByMaSvAndMaKeHoachDto.class);

                            diemByMaSvAndMaKeHoachDto.setPercentCc(monHocEntity.getPercentCc());
                            diemByMaSvAndMaKeHoachDto.setPercentGk(monHocEntity.getPercentGk());
                            diemByMaSvAndMaKeHoachDto.setPercentCk(monHocEntity.getPercentCk());
                            diemByMaSvAndMaKeHoachDto.setTenMh(monHocEntity.getTenMh());
                            diemByMaSvAndMaKeHoachDto.setMaMh(monHocEntity.getMaMh());
                            diemByMaSvAndMaKeHoachDto.setSoTc(monHocEntity.getSoTc());

                            diemByMaSvAndMaKeHoachDtoListDto.add(diemByMaSvAndMaKeHoachDto);
                        }
                    }

                    returnObject.setRetObj(diemByMaSvAndMaKeHoachDtoListDto);
                }
            }
            else if(mkh.equals("True")){
                KeHoachNamEntity keHoachNamEntity = keHoachNamService.getKeHoachNamClosest();
                maKeHoach = keHoachNamEntity.getMaKeHoach();

                validatorDiem.validateGetListDiemByMaSvAndMaKeHoach(maSv, maKeHoach);

                List<DiemByMaSvAndMaKeHoachDto> diemByMaSvAndMaKeHoachDtoListDto = new ArrayList<>();

                List<DiemEntity> diemEntityList = diemService.getListDiemByMaSv(maSv);
                for (DiemEntity diemEntity: diemEntityList) {
                    ModelMapper modelMapper = new ModelMapper();

                    DsLopTcEntity dsLopTcEntity = dsLopTcService.getDsLopTcByMaLopTcAndMaKeHoach(diemEntity.getMaLopTc(), maKeHoach);
                    if(dsLopTcEntity == null){
                        continue;
                    }
                    else {
                        MonHocEntity monHocEntity = monHocService.getMonHocByMaMh(dsLopTcEntity.getMaMh());

                        DiemByMaSvAndMaKeHoachDto diemByMaSvAndMaKeHoachDto = modelMapper.map(diemEntity, DiemByMaSvAndMaKeHoachDto.class);

                        diemByMaSvAndMaKeHoachDto.setPercentCc(monHocEntity.getPercentCc());
                        diemByMaSvAndMaKeHoachDto.setPercentGk(monHocEntity.getPercentGk());
                        diemByMaSvAndMaKeHoachDto.setPercentCk(monHocEntity.getPercentCk());
                        diemByMaSvAndMaKeHoachDto.setTenMh(monHocEntity.getTenMh());
                        diemByMaSvAndMaKeHoachDto.setMaMh(monHocEntity.getMaMh());
                        diemByMaSvAndMaKeHoachDto.setSoTc(monHocEntity.getSoTc());

                        diemByMaSvAndMaKeHoachDtoListDto.add(diemByMaSvAndMaKeHoachDto);
                    }
                }

                returnObject.setRetObj(diemByMaSvAndMaKeHoachDtoListDto);
            }
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage(ex.getMessage());
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    @Operation(summary = "Get danh sach diem (Detail) by maLopTc")
    @GetMapping("/diem/lopTc/detail/{maLopTc}")
//     @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
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
    public ResponseEntity<?> getListDiemDetailByMaLopTc(@PathVariable(required = true) String maLopTc) {

        ReturnObject returnObject = new ReturnObject();

        try {
            log.info("Get danh sach diem By maLopTc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");
            validatorDiem.validateGetListDiemByMaLopTc(maLopTc);


            List<DiemByMaSvAndMaKeHoachDto> diemByMaSvAndMaKeHoachDtoListDto = new ArrayList<>();
            List<DiemEntity> diemEntityList = diemService.getListDiemByMaLopTc(maLopTc);

            for (DiemEntity diemEntity: diemEntityList) {
                ModelMapper modelMapper = new ModelMapper();
                DiemByMaSvAndMaKeHoachDto diemByMaSvAndMaKeHoachDto = modelMapper.map(diemEntity, DiemByMaSvAndMaKeHoachDto.class);

                SinhVienEntity sinhVienEntity = sinhVienService.findByMaSv(diemEntity.getMaSv());
                diemByMaSvAndMaKeHoachDto.setTenSv(sinhVienEntity.getHo() + " " + sinhVienEntity.getTen());

                DsLopTcEntity dsLopTcEntity = dsLopTcService.getDsLopTcByMaLopTc(maLopTc);
                MonHocEntity monHocEntity = monHocService.getMonHocByMaMh(dsLopTcEntity.getMaMh());

                diemByMaSvAndMaKeHoachDto.setPercentCc(monHocEntity.getPercentCc());
                diemByMaSvAndMaKeHoachDto.setPercentGk(monHocEntity.getPercentGk());
                diemByMaSvAndMaKeHoachDto.setPercentCk(monHocEntity.getPercentCk());
                diemByMaSvAndMaKeHoachDto.setTenMh(monHocEntity.getTenMh());
                diemByMaSvAndMaKeHoachDto.setMaMh(monHocEntity.getMaMh());
                diemByMaSvAndMaKeHoachDto.setSoTc(monHocEntity.getSoTc());

                diemByMaSvAndMaKeHoachDtoListDto.add(diemByMaSvAndMaKeHoachDto);
            }

            returnObject.setRetObj(diemByMaSvAndMaKeHoachDtoListDto);

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
