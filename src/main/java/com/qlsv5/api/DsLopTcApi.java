package com.qlsv5.api;

import com.qlsv5.common.ReturnObject;
import com.qlsv5.dto.DiemByMaSvAndMaKeHoachDto;
import com.qlsv5.dto.DsLopTcDto;
import com.qlsv5.dto.DsLopTcMonHocGiangVienLopDto;
import com.qlsv5.dto.KeHoachNamDto;
import com.qlsv5.entity.*;
import com.qlsv5.service.*;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

/**
 * @author NienNQ
 * @created 2023 - 03 - 18 6:20 AM
 * @project qlsv
 */
@Slf4j
@RestController
@RequestMapping("/api/admin")
@Tag(name = "Danh Sach Lop Tin Chi", description = "Management APIs for DANH SACH LOP TIN CHI.")
public class DsLopTcApi {

    @Autowired
    private CommonService commonService;

    @Autowired
    private GiangVienService giangVienService;

    @Autowired
    private MonHocService monHocService;

    @Autowired
    private DsLopTcService dsLopTcService;

    @Autowired
    private LopService lopService;

    @Autowired
    private ValidatorDsLopTc validatorDsLopTc;

    @Autowired
    private KeHoachNamService keHoachNamService;

    @Autowired
    private DiemService diemService;

    /* CREATE */
    @Operation(summary = "Create DsLopTc.")
    @PostMapping("/dsLopTc")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) })})
    public ResponseEntity<?> createDsLopTc(@Valid @RequestBody DsLopTcDto dsLopTc, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();

        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Add DsLopTc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorDsLopTc.validateAddDsLopTc(dsLopTc);
            DsLopTcEntity dsLopTcEntity = (DsLopTcEntity) commonService.addObject(dsLopTc);
            returnObject.setRetObj(dsLopTcEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage(ex.getMessage());
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* UPDATE */
    @PutMapping("/dsLopTc")
    @Operation(summary = "Update DsLopTc.")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) })})
    public ResponseEntity<?> updateDsLopTc(@Valid @RequestBody DsLopTcDto dsLopTc, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();
        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Update DsLopTc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorDsLopTc.validateEditDsLopTc(dsLopTc);
            commonService.updateObject(dsLopTc);

            returnObject.setRetObj(dsLopTc);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage(ex.getMessage());
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* DELETE */
    @DeleteMapping("/dsLopTc")
    @Operation(summary = "Delete DsLopTc by list id")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) })})
    public ResponseEntity<?> deleteDsLopTc(@Valid @RequestBody List<String> lstDsLopTcId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Delete List DsLopTc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            if(lstDsLopTcId.size() > 0){
                DsLopTcEntity dsLopTcEntity = dsLopTcService.getDsLopTcById(lstDsLopTcId.get(0));
//                validatorDsLopTc.validateDeleteDsLopTc(dsLopTcEntity);
            }

            List<String> deleteSuccess = commonService.deleteLstObject(lstDsLopTcId, new DsLopTcDto());
            returnObject.setRetObj(deleteSuccess);
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
    /* GET ALL DSLOPTC BY MALOP & MAKEHOACH*/
    /* GET ALL DSLOPTC BY MAKEHOACH*/
    @Operation(summary = "Get all DsLopTc.")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @GetMapping("/dsLopTc")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) })})
    public ResponseEntity<?> getAllDsLopTc(@RequestParam(required = false, defaultValue = "") String maLop,
                                           @RequestParam(required = false, defaultValue = "") String maKeHoach) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All DsLopTc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            List<DsLopTcMonHocGiangVienLopDto> dsLopTcMonHocGiangVienLopDtos = new ArrayList<>();

            List<Object> listDsLopTc = new ArrayList<>();
            if(maLop.equals("") && maKeHoach.equals("")){
                listDsLopTc = commonService.findAllObject(new DsLopTcDto());
                returnObject.setRetObj(listDsLopTc);
                return ResponseEntity.ok(returnObject);
            }
            else if(maLop.equals("") && !maKeHoach.equals("")){
                List<DsLopTcEntity> dsLopTcEntityList = dsLopTcService.findAllByMaKeHoach(maKeHoach);

                for (DsLopTcEntity dsLopTcEntity: dsLopTcEntityList) {
                    String maGv = dsLopTcEntity.getMaGv();
                    String maMh = dsLopTcEntity.getMaMh();
                    String maLopNotParam = dsLopTcEntity.getMaLop();
                    String tenGv = "";
                    String tenMh = "";
                    String tenLop = "";
                    int soTc = 0;

                    GiangVienEntity giangVienEntity = giangVienService.getGiangVienByMaGv(maGv);
                    MonHocEntity monHocEntity = monHocService.getMonHocByMaMh(maMh);

                    LopEntity lopEntity = lopService.getLopByMaLop(maLopNotParam);

                    if(giangVienEntity != null){
                        tenGv = giangVienEntity.getHo() + " " + giangVienEntity.getTen();
                    }
                    if(monHocEntity != null){
                        tenMh = monHocEntity.getTenMh();
                        soTc = monHocEntity.getSoTc();
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
                    dsLopTcMonHocGiangVienLopDto.setSoTc(soTc);

                    dsLopTcMonHocGiangVienLopDtos.add(dsLopTcMonHocGiangVienLopDto);
                }

//                List<DsLopTcEntity>  dsLopTcEntityList = dsLopTcService.findAllByMaKeHoach(maKeHoach);
//                returnObject.setRetObj(dsLopTcEntityList);
//                return ResponseEntity.ok(returnObject);
            }
            else if(!maLop.equals("") && maKeHoach.equals("")){

                KeHoachNamEntity keHoachNamEntity = keHoachNamService.getKeHoachNamClosest();
                maKeHoach = keHoachNamEntity.getMaKeHoach();

                List<DsLopTcEntity> dsLopTcEntityList = dsLopTcService.findAllByMaLopAndMaKeHoach(maLop, maKeHoach);
                for (DsLopTcEntity dsLopTcEntity: dsLopTcEntityList) {
                    String maGv = dsLopTcEntity.getMaGv();
                    String maMh = dsLopTcEntity.getMaMh();
                    String tenGv = "";
                    String tenMh = "";
                    int soTc = 0;

                    GiangVienEntity giangVienEntity = giangVienService.getGiangVienByMaGv(maGv);
                    MonHocEntity monHocEntity = monHocService.getMonHocByMaMh(maMh);

                    if(giangVienEntity != null){
                        tenGv = giangVienEntity.getHo() + " " + giangVienEntity.getTen();
                    }
                    if(monHocEntity != null){
                        soTc = monHocEntity.getSoTc();
                        tenMh = monHocEntity.getTenMh();
                    }

                    ModelMapper modelMapper = new ModelMapper();
                    DsLopTcMonHocGiangVienLopDto dsLopTcMonHocGiangVienLopDto = new DsLopTcMonHocGiangVienLopDto();
                    dsLopTcMonHocGiangVienLopDto = modelMapper.map(dsLopTcEntity, DsLopTcMonHocGiangVienLopDto.class);
                    dsLopTcMonHocGiangVienLopDto.setTenGv(tenGv);
                    dsLopTcMonHocGiangVienLopDto.setTenMh(tenMh);
                    dsLopTcMonHocGiangVienLopDto.setSoTc(soTc);

                    dsLopTcMonHocGiangVienLopDtos.add(dsLopTcMonHocGiangVienLopDto);
                }
            }
            else {
                List<DsLopTcEntity> dsLopTcEntityList = dsLopTcService.findAllByMaLopAndMaKeHoach(maLop, maKeHoach);
                for (DsLopTcEntity dsLopTcEntity: dsLopTcEntityList) {
                    String maGv = dsLopTcEntity.getMaGv();
                    String maMh = dsLopTcEntity.getMaMh();
                    String tenGv = "";
                    String tenMh = "";
                    int soTc = 0;

                    GiangVienEntity giangVienEntity = giangVienService.getGiangVienByMaGv(maGv);
                    MonHocEntity monHocEntity = monHocService.getMonHocByMaMh(maMh);

                    if(giangVienEntity != null){
                        tenGv = giangVienEntity.getHo() + " " + giangVienEntity.getTen();
                    }
                    if(monHocEntity != null){
                        soTc = monHocEntity.getSoTc();
                        tenMh = monHocEntity.getTenMh();
                    }

                    ModelMapper modelMapper = new ModelMapper();
                    DsLopTcMonHocGiangVienLopDto dsLopTcMonHocGiangVienLopDto = new DsLopTcMonHocGiangVienLopDto();
                    dsLopTcMonHocGiangVienLopDto = modelMapper.map(dsLopTcEntity, DsLopTcMonHocGiangVienLopDto.class);
                    dsLopTcMonHocGiangVienLopDto.setTenGv(tenGv);
                    dsLopTcMonHocGiangVienLopDto.setTenMh(tenMh);
                    dsLopTcMonHocGiangVienLopDto.setSoTc(soTc);

                    dsLopTcMonHocGiangVienLopDtos.add(dsLopTcMonHocGiangVienLopDto);
                }
            }

            returnObject.setRetObj(dsLopTcMonHocGiangVienLopDtos);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage(ex.getMessage());
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET ALL DSLOPTC BY MA MON HOC & MA KE HOACH*/
    @Operation(summary = "Get all DsLopTc By Ma Mon Hoc & Ma Ke Hoach")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @GetMapping("/dsLopTc/monHoc")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) })})
    public ResponseEntity<?> getAllDsLopTcByMaMhAndMaKeHoach(@RequestParam(required = true, defaultValue = "") String maMh,
                                           @RequestParam(required = false, defaultValue = "") String maKeHoach) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All DsLopTc By Ma Ke Hoach & Ma Mon Hoc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            if(maKeHoach.equals("")){
                KeHoachNamEntity keHoachNamEntity = keHoachNamService.getKeHoachNamClosest();
                maKeHoach = keHoachNamEntity.getMaKeHoach();
            }

            List<DsLopTcMonHocGiangVienLopDto> dsLopTcMonHocGiangVienLopDtos = new ArrayList<>();

            List<Object> listDsLopTc = new ArrayList<>();

            List<DsLopTcEntity> dsLopTcEntityList = dsLopTcService.findAllByMaMhAndMaKeHoach(maMh, maKeHoach);
            for (DsLopTcEntity dsLopTcEntity: dsLopTcEntityList) {
                String maGv = dsLopTcEntity.getMaGv();
                String tenGv = "";
                String tenMh = "";
                int soTc = 0;

                GiangVienEntity giangVienEntity = giangVienService.getGiangVienByMaGv(maGv);
                MonHocEntity monHocEntity = monHocService.getMonHocByMaMh(maMh);

                if(giangVienEntity != null){
                    tenGv = giangVienEntity.getHo() + " " + giangVienEntity.getTen();
                }
                if(monHocEntity != null){
                    soTc = monHocEntity.getSoTc();
                    tenMh = monHocEntity.getTenMh();
                }

                ModelMapper modelMapper = new ModelMapper();
                DsLopTcMonHocGiangVienLopDto dsLopTcMonHocGiangVienLopDto = new DsLopTcMonHocGiangVienLopDto();
                dsLopTcMonHocGiangVienLopDto = modelMapper.map(dsLopTcEntity, DsLopTcMonHocGiangVienLopDto.class);
                dsLopTcMonHocGiangVienLopDto.setTenGv(tenGv);
                dsLopTcMonHocGiangVienLopDto.setTenMh(tenMh);
                dsLopTcMonHocGiangVienLopDto.setSoTc(soTc);

                dsLopTcMonHocGiangVienLopDtos.add(dsLopTcMonHocGiangVienLopDto);
            }

            returnObject.setRetObj(dsLopTcMonHocGiangVienLopDtos);
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
    @Operation(summary = "Get DsLopTc by id.")
    @GetMapping("/dsLopTc/{dsLopTcId}")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) })})
    public ResponseEntity<?> getDsLopTcById(@PathVariable String dsLopTcId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get DsLopTc By Id!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorDsLopTc.validateGetDsLopTcById(dsLopTcId);
            DsLopTcEntity dsLopTcEntity = (DsLopTcEntity) commonService.getObjectById(dsLopTcId, new DsLopTcDto());
            returnObject.setRetObj(dsLopTcEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage(ex.getMessage());
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    @Operation(summary = "Get danh sach lop tin chi by maLop")
    @GetMapping("/dsLopTc/lop/{maLop}")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) })})
    public ResponseEntity<?> getDsLopTcByMaLop(@PathVariable String maLop,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "4") int size) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get Ds Lop Tin Chi By maLop!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorDsLopTc.validateGetListLopTcByMaLop(maLop);
            List<DsLopTcEntity> dsLopTcEntity = dsLopTcService.getListLopTcByMaLop(maLop, page, size);
            returnObject.setRetObj(dsLopTcEntity);

            /*for paging*/
            List<DsLopTcEntity> dsLopTcEntityForPaging = dsLopTcService.getListLopTcByMaLop(maLop, 0, 100000);

            double totalPageDouble = (double) dsLopTcEntityForPaging.size() / size;
            int totalPageForPaging = (int) Math.ceil(totalPageDouble);

            returnObject.setPage(page);
            returnObject.setTotalRetObjs(dsLopTcEntityForPaging.size());
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

    @Operation(summary = "Get danh sach lop tin chi by maGv and maKeHoach")
    @PostMapping("/dsLopTc/giangVien/{maGv}")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) })})
    public ResponseEntity<?> getDsLopTcByMaGvAndMaKeHoach(@PathVariable(required = true) String maGv,
                                                          @RequestParam(required = false, defaultValue = "") String maKeHoach) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get Ds Lop Tin Chi By maLop!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            if(maKeHoach.equals("")){
                KeHoachNamEntity keHoachNamEntity = keHoachNamService.getKeHoachNamClosest();
                maKeHoach = keHoachNamEntity.getMaKeHoach();
            }

            validatorDsLopTc.validateGetListLopTcByMaGvAndMaKeHoach(maGv, maKeHoach);

            List<DsLopTcEntity> dsLopTcEntityList = dsLopTcService.findAllByMaGvAndMaKeHoach(maGv, maKeHoach);

            List<DsLopTcMonHocGiangVienLopDto> dsLopTcMonHocGiangVienLopDtoList = new ArrayList<>();

            for (DsLopTcEntity dsLopTcEntity: dsLopTcEntityList) {

                ModelMapper modelMapper = new ModelMapper();
                DsLopTcMonHocGiangVienLopDto dsLopTcMonHocGiangVienLopDto = modelMapper.map(dsLopTcEntity, DsLopTcMonHocGiangVienLopDto.class);

                MonHocEntity monHocEntity = monHocService.getMonHocByMaMh(dsLopTcEntity.getMaMh());
                dsLopTcMonHocGiangVienLopDto.setTenMh(monHocEntity.getTenMh());

                dsLopTcMonHocGiangVienLopDtoList.add(dsLopTcMonHocGiangVienLopDto);
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

    /* GET ALL DSLOPTC BY MASINH VIEN & MA KE HOACH*/
    @Operation(summary = "Get all DsLopTc By Ma Mon Hoc & Ma Ke Hoach")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @GetMapping("/dsLopTc/sinhVien")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) })})
    public ResponseEntity<?> getAllDsLopTcByMaSvAndMaKeHoach(@RequestParam(required = true, defaultValue = "") String maSv,
                                                             @RequestParam(required = false, defaultValue = "") String maKeHoach) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All DsLopTc By Ma Sinh Vien & Ma Mon Hoc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            if(maKeHoach.equals("")){
                KeHoachNamEntity keHoachNamEntity = keHoachNamService.getKeHoachNamClosest();
                maKeHoach = keHoachNamEntity.getMaKeHoach();
            }

            List<DsLopTcMonHocGiangVienLopDto> dsLopTcMonHocGiangVienLopDtos = new ArrayList<>();

            List<Object> listDsLopTc = new ArrayList<>();

//            List<DsLopTcEntity> dsLopTcEntityList = dsLopTcService.findAllByMaMhAndMaKeHoach(maMh, maKeHoach);
            List<DsLopTcEntity> dsLopTcEntityList = new ArrayList<>();
            List<DiemEntity> diemEntityList = diemService.getListDiemByMaSv(maSv);
            for(DiemEntity diemEntity: diemEntityList){
                DsLopTcEntity dsLopTcEntity = dsLopTcService.getDsLopTcByMaLopTcAndMaKeHoach(diemEntity.getMaLopTc(), maKeHoach);
                if(dsLopTcEntity != null){
                    dsLopTcEntityList.add(dsLopTcEntity);
                }
            }

            for (DsLopTcEntity dsLopTcEntity: dsLopTcEntityList) {
                String maGv = dsLopTcEntity.getMaGv();
                String maMh = dsLopTcEntity.getMaMh();
                String tenGv = "";
                String tenMh = "";
                int soTc = 0;

                GiangVienEntity giangVienEntity = giangVienService.getGiangVienByMaGv(maGv);
                MonHocEntity monHocEntity = monHocService.getMonHocByMaMh(maMh);

                if(giangVienEntity != null){
                    tenGv = giangVienEntity.getHo() + " " + giangVienEntity.getTen();
                }
                if(monHocEntity != null){
                    soTc = monHocEntity.getSoTc();
                    tenMh = monHocEntity.getTenMh();
                }

                ModelMapper modelMapper = new ModelMapper();
                DsLopTcMonHocGiangVienLopDto dsLopTcMonHocGiangVienLopDto = new DsLopTcMonHocGiangVienLopDto();
                dsLopTcMonHocGiangVienLopDto = modelMapper.map(dsLopTcEntity, DsLopTcMonHocGiangVienLopDto.class);
                dsLopTcMonHocGiangVienLopDto.setTenGv(tenGv);
                dsLopTcMonHocGiangVienLopDto.setTenMh(tenMh);
                dsLopTcMonHocGiangVienLopDto.setSoTc(soTc);

                dsLopTcMonHocGiangVienLopDtos.add(dsLopTcMonHocGiangVienLopDto);
            }

            returnObject.setRetObj(dsLopTcMonHocGiangVienLopDtos);
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
