package com.qlsv5.api;

import com.qlsv5.common.FunctionCommon;
import com.qlsv5.common.ReturnObject;
import com.qlsv5.dto.DiemByMaSvAndMaKeHoachDto;
import com.qlsv5.dto.MHMHTQDto;
import com.qlsv5.dto.MonHocDto;
import com.qlsv5.dto.MonHocModifyDto;
import com.qlsv5.entity.DsLopTcEntity;
import com.qlsv5.entity.MHTQEntity;
import com.qlsv5.entity.MonHocEntity;
import com.qlsv5.service.CommonService;
import com.qlsv5.service.MHTQService;
import com.qlsv5.service.MonHocService;
import com.qlsv5.validation.ValidatorMonHoc;
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
 * @created 2023 - 03 - 05 6:20 AM
 * @project qlsv
 */
@Slf4j
@RestController
@RequestMapping("/api/admin")
@Tag(name = "Mon Hoc", description = "Management APIs for MON HOC.")
public class MonHocApi {
    @Autowired
    private CommonService commonService;
    @Autowired
    private MonHocService monHocService;
    @Autowired
    private ValidatorMonHoc validatorMonHoc;
    @Autowired
    private MHTQService mhtqService;
    @Autowired
    private FunctionCommon functionCommon;

//    /* CREATE */
//    @Operation(summary = "Create MonHoc.")
//    @PostMapping("/monHoc")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Success",
//                    content = {
//                            @Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
//            @ApiResponse(responseCode = "401", description = "Unauthorized",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
//            @ApiResponse(responseCode = "403", description = "Forbidden",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
//            @ApiResponse(responseCode = "500", description = "Internal server error",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) })})
//    public ResponseEntity<?> createMonHoc(@Valid @RequestBody MonHocDto monHoc, BindingResult bindingResult) {
//
//        ReturnObject returnObject = new ReturnObject();
//
//        if (bindingResult.hasErrors()) {
//            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
//            return ResponseEntity.ok(returnObject);
//        }
//        try {
//            log.info("Add MonHoc!");
//
//            returnObject.setStatus(ReturnObject.SUCCESS);
//            returnObject.setMessage("200");
//
//            validatorMonHoc.validateAddMonHoc(monHoc);
////            monHocService.addMonHoc(monHoc);
//            MonHocEntity monHocEntity = (MonHocEntity) commonService.addObject(monHoc);
//            returnObject.setRetObj(monHocEntity);
//        }
//        catch (Exception ex){
//            returnObject.setStatus(ReturnObject.ERROR);
//            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
//            returnObject.setMessage(errorMessage);
//        }
//
//        return ResponseEntity.ok(returnObject);
//    }
//
//    /* UPDATE */
//    @Operation(summary = "Update MonHoc.")
//    @PutMapping("/monHoc")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Success",
//                    content = {
//                            @Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
//            @ApiResponse(responseCode = "401", description = "Unauthorized",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
//            @ApiResponse(responseCode = "403", description = "Forbidden",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
//            @ApiResponse(responseCode = "500", description = "Internal server error",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) })})
//    public ResponseEntity<?> updateMonHoc(@Valid @RequestBody MonHocDto monHoc, BindingResult bindingResult) {
//
//        ReturnObject returnObject = new ReturnObject();
//        if (bindingResult.hasErrors()) {
//            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
//            return ResponseEntity.ok(returnObject);
//        }
//        try {
//            log.info("Update MonHoc!");
//
//            returnObject.setStatus(ReturnObject.SUCCESS);
//            returnObject.setMessage("200");
//
//            validatorMonHoc.validateEditMonHoc(monHoc);
////            monHocService.updateMonHoc(monHoc);
//            commonService.updateObject(monHoc);
//
//            returnObject.setRetObj(monHoc);
//        }
//        catch (Exception ex){
//            returnObject.setStatus(ReturnObject.ERROR);
////            returnObject.setMessage(ex.getMessage());
//            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
//            returnObject.setMessage(errorMessage);
//        }
//
//        return ResponseEntity.ok(returnObject);
//    }

    /* DELETE */
    @Operation(summary = "Delete MonHoc by list id")
    @DeleteMapping("/monHoc")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) })})
    public ResponseEntity<?> deleteMonHoc(@Valid @RequestBody List<String> lstMonHocId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Delete List MonHoc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

//            List<String> deleteSuccess = monHocService.deleteLstMonHoc(lstMonHocId);
            List<String> deleteSuccess = commonService.deleteLstObject(lstMonHocId, new MonHocDto());
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

//    /* GET ALL */
//    @Operation(summary = "Get all MonHoc.")
//    @GetMapping("/monHoc")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Success",
//                    content = {
//                            @Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
//            @ApiResponse(responseCode = "401", description = "Unauthorized",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
//            @ApiResponse(responseCode = "403", description = "Forbidden",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
//            @ApiResponse(responseCode = "500", description = "Internal server error",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) })})
//    public ResponseEntity<?> getAllMonHoc() {
//
//        ReturnObject returnObject = new ReturnObject();
//        try {
//            log.info("Get All MonHoc!");
//
//            returnObject.setStatus(ReturnObject.SUCCESS);
//            returnObject.setMessage("200");
//
////            List<MonHocEntity> listMonHoc = monHocService.findAllMonHoc();
//            List<Object> listMonHoc = commonService.findAllObject( new MonHocDto());
//            returnObject.setRetObj(listMonHoc);
//        }
//        catch (Exception ex){
//            returnObject.setStatus(ReturnObject.ERROR);
////            returnObject.setMessage(ex.getMessage());
//            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
//            returnObject.setMessage(errorMessage);
//        }
//
//        return ResponseEntity.ok(returnObject);
//    }

    /* GET BY ID */
    @Operation(summary = "Get MonHoc by id.")
    @GetMapping("/monHoc/{monHocId}")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) })})
    public ResponseEntity<?> getMonHocById(@PathVariable String monHocId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get MonHoc By Id!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorMonHoc.validateGetMonHocById(monHocId);
            validatorMonHoc.validateGetMonHocById(monHocId);
            MonHocModifyDto monHocModifyDto = monHocService.findById(monHocId);
            returnObject.setRetObj(monHocModifyDto);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    @Operation(summary = "Get List Mon Hoc by maKhoa.")
    @GetMapping("/monHoc/khoa/{maKhoa}")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) })})
    public ResponseEntity<?> getListMonHocByMaKhoa(@PathVariable String maKhoa,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "7") int size) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get List Mon Hoc By maKhoa!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorMonHoc.validateGetListMonHocByMaKhoa(maKhoa);
            List<MonHocModifyDto> monHocModifyDtoList = monHocService.getListMonHocByMaKhoa(maKhoa, page, size);
            returnObject.setRetObj(monHocModifyDtoList);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage(ex.getMessage());
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* ___________________________________________________ */
    /* CREATE */
    @Operation(summary = "Create MonHoc.")
    @PostMapping("/monHoc")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) })})
    public ResponseEntity<?> createMonHoc(@Valid @RequestBody MonHocModifyDto monHocModifyDto, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();

        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Add MonHoc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            ModelMapper modelMapper = new ModelMapper();
            MonHocEntity monHocEntity = modelMapper.map(monHocModifyDto, MonHocEntity.class);

//            monHocEntity.setMaMh(monHocModifyDto.getMaMh());
//            monHocEntity.setTenMh(monHocModifyDto.getTenMh());

            /* STEP1: ADD NEW */
            validatorMonHoc.validateAddMonHoc(monHocEntity);
            MonHocEntity monHocEntityResult = monHocService.addNew(monHocEntity);

            MonHocModifyDto monHocModifyDtoResult = new MonHocModifyDto();
            /* STEP2: IF STEP1 SUCCESS -> ADD TO TABLE MHTQ */
            List<String> tenMHTQ = new ArrayList<>();
            if(monHocModifyDto.getMaMHTQList() != null ){
                MHMHTQDto mhmhtqDtoValid = new MHMHTQDto();
                List<String> maMHTQList = new ArrayList<>();

                String maMh = monHocModifyDto.getMaMh();
                for(String maMHTQ: functionCommon.convertListToSetToList(monHocModifyDto.getMaMHTQList())){

                    boolean isValid = validatorMonHoc.validateUpdateDKMHTQPossible(maMh, maMHTQ);
                    if(isValid == true){
                        tenMHTQ.add(monHocService.getMonHocByMaMh(maMHTQ).getTenMh());
                        maMHTQList.add(maMHTQ);
                    }
                }

                mhmhtqDtoValid.setMaMh(monHocModifyDto.getMaMh());
                mhmhtqDtoValid.setMaMHTQList(maMHTQList);

                mhtqService.updateExist(mhmhtqDtoValid);

                monHocModifyDtoResult.setMaMHTQList(maMHTQList);
            }
            else {
                monHocModifyDtoResult.setMaMHTQList(null);
            }
            /* END STEP2 */

            monHocModifyDtoResult.setTenMh(monHocModifyDto.getTenMh());
            monHocModifyDtoResult.setMaMh(monHocModifyDto.getMaMh());
            monHocModifyDtoResult.setId(monHocEntityResult.getId());
            monHocModifyDtoResult.setMaKhoa(monHocModifyDto.getMaKhoa());
            monHocModifyDtoResult.setPercentCc(monHocModifyDto.getPercentCc());
            monHocModifyDtoResult.setPercentGk(monHocModifyDto.getPercentGk());
            monHocModifyDtoResult.setPercentCk(monHocModifyDto.getPercentCk());
            monHocModifyDtoResult.setSoTietLt(monHocModifyDto.getSoTietLt());
            monHocModifyDtoResult.setSoTietTh(monHocModifyDto.getSoTietTh());
            monHocModifyDtoResult.setSoTc(monHocModifyDto.getSoTc());
            monHocModifyDtoResult.setTenMHTQList(tenMHTQ);

            returnObject.setRetObj(monHocModifyDtoResult);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* UPDATE */
    @Operation(summary = "Update MonHoc.")
    @PutMapping("/monHoc")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) })})
    public ResponseEntity<?> updateMonHoc(@Valid @RequestBody MonHocModifyDto monHocModifyDto, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();
        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Update MonHoc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            ModelMapper modelMapper = new ModelMapper();
            MonHocDto monHocDto = modelMapper.map(monHocModifyDto, MonHocDto.class);
            MonHocEntity monHocEntity = modelMapper.map(monHocModifyDto, MonHocEntity.class);

//            MonHocEntity monHocEntity = new MonHocEntity();
//            monHocEntity.setMaMh(monHocModifyDto.getMaMh());
//            monHocEntity.setTenMh(monHocModifyDto.getTenMh());
//            monHocEntity.setId(monHocModifyDto.getId());

            /* STEP1: EDIT EXIST */
            validatorMonHoc.validateEditMonHoc(monHocDto);
            MonHocEntity monHocEntityUpdate = monHocService.updateExist(monHocEntity);

            MonHocModifyDto monHocModifyDtoResult = new MonHocModifyDto();
            /* STEP2: IF STEP1 SUCCESS -> ADD TO TABLE MHTQ */
            List<String> tenMHTQ = new ArrayList<>();
            if(monHocModifyDto.getMaMHTQList() != null ){

                /* REMOVE ALL -> MAMH */
                List<MHTQEntity> mhtqEntityList = mhtqService.findAllByMaMh(monHocModifyDto.getMaMh());
                if(mhtqEntityList != null){
                    for(MHTQEntity item : mhtqEntityList){
                        mhtqService.deleteRecord(item.getId());
                    }
                }
                /* END REMOVE ALL -> MAMH */

                MHMHTQDto mhmhtqDtoValid = new MHMHTQDto();
                List<String> maMHTQList = new ArrayList<>();

                String maMh = monHocModifyDto.getMaMh();
                for(String maMHTQ: functionCommon.convertListToSetToList(monHocModifyDto.getMaMHTQList())){

                    boolean isValid = validatorMonHoc.validateUpdateDKMHTQPossible(maMh, maMHTQ);
                    if(isValid == true){
                        tenMHTQ.add(monHocService.getMonHocByMaMh(maMHTQ).getTenMh());
                        maMHTQList.add(maMHTQ);
                    }
                }

                mhmhtqDtoValid.setMaMh(monHocModifyDto.getMaMh());
                mhmhtqDtoValid.setMaMHTQList(maMHTQList);

                mhtqService.updateExist(mhmhtqDtoValid);

                monHocModifyDtoResult.setMaMHTQList(maMHTQList);
            }
            else {
                monHocModifyDtoResult.setMaMHTQList(null);
            }
            /* END STEP2 */

            monHocModifyDtoResult.setMaMh(monHocModifyDto.getMaMh());
            monHocModifyDtoResult.setId(monHocEntityUpdate.getId());
            monHocModifyDtoResult.setTenMh(monHocModifyDto.getTenMh());
            monHocModifyDtoResult.setMaKhoa(monHocModifyDto.getMaKhoa());
            monHocModifyDtoResult.setPercentCc(monHocModifyDto.getPercentCc());
            monHocModifyDtoResult.setPercentGk(monHocModifyDto.getPercentGk());
            monHocModifyDtoResult.setPercentCk(monHocModifyDto.getPercentCk());
            monHocModifyDtoResult.setSoTietLt(monHocModifyDto.getSoTietLt());
            monHocModifyDtoResult.setSoTietTh(monHocModifyDto.getSoTietTh());
            monHocModifyDtoResult.setSoTc(monHocModifyDto.getSoTc());
            monHocModifyDtoResult.setTenMHTQList(tenMHTQ);

            returnObject.setRetObj(monHocModifyDtoResult);

        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET ALL */
    @Operation(summary = "Get all MonHoc.")
    @GetMapping("/monHoc")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) })})
    public ResponseEntity<?> getAllMonHoc() {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All MonHoc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            List<MonHocModifyDto> monHocModifyDtoList = monHocService.findAllMonHocModify();
            returnObject.setRetObj(monHocModifyDtoList);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }
}
