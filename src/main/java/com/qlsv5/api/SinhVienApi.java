package com.qlsv5.api;

import com.qlsv5.common.ReturnObject;
import com.qlsv5.dto.SinhVienDto;
import com.qlsv5.entity.KhoaEntity;
import com.qlsv5.entity.SinhVienEntity;
import com.qlsv5.service.CommonService;
import com.qlsv5.service.SinhVienService;
import com.qlsv5.validation.ValidatorSinhVien;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author NienNQ
 * @created 2023 - 03 - 05 6:20 AM
 * @project qlsv
 */
@Slf4j
@RestController
@RequestMapping("/api/admin")
@Tag(name = "Sinh Vien", description = "Management APIs for SINH VIEN.")
public class SinhVienApi {
    @Autowired
    private CommonService commonService;
    @Autowired
    private SinhVienService sinhVienService;

    @Autowired
    private ValidatorSinhVien validatorSinhVien;

    /* CREATE */
    @Operation(summary = "Create Sinh Vien.")
    @PostMapping("/sinhVien")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = KhoaEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KhoaEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KhoaEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KhoaEntity.class)) })})
    public ResponseEntity<?> createSinhVien(@Valid @RequestBody SinhVienDto sinhVien, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();

        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Add SinhVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorSinhVien.validateAddSinhVien(sinhVien);
            commonService.addObject(sinhVien);
            returnObject.setRetObj(sinhVien);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* UPDATE */
    @Operation(summary = "Update Sinh Vien.")
    @PutMapping("/sinhVien")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = KhoaEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KhoaEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KhoaEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KhoaEntity.class)) })})
    public ResponseEntity<?> updateSinhVien(@Valid @RequestBody SinhVienDto sinhVien, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();
        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Update SinhVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorSinhVien.validateEditSinhVien(sinhVien);
            commonService.updateObject(sinhVien);

            returnObject.setRetObj(sinhVien);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* DELETE */
    @Operation(summary = "Delete Sinh Vien by list id")
    @DeleteMapping("/sinhVien")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = KhoaEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KhoaEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KhoaEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KhoaEntity.class)) })})
    public ResponseEntity<?> deleteSinhVien(@Valid @RequestBody List<String> lstSinhVienId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Delete List SinhVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

//            List<String> deleteSuccess = sinhVienService.deleteLstSinhVien(lstSinhVienId);
            List<String> deleteSuccess = commonService.deleteLstObject(lstSinhVienId, new SinhVienDto());
            returnObject.setRetObj(deleteSuccess);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET ALL */
    @Operation(summary = "Get all Sinh Vien.")
    @GetMapping("/sinhVien")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = KhoaEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KhoaEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KhoaEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KhoaEntity.class)) })})
    public ResponseEntity<?> getAllSinhVien() {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All SinhVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

//            List<SinhVienEntity> listSinhVien = sinhVienService.findAllSinhVien();
            List<Object> listSinhVien = commonService.findAllObject(new SinhVienDto());
            returnObject.setRetObj(listSinhVien);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET BY ID */
    @Operation(summary = "Get Sinh Vien by id.")
    @GetMapping("/sinhVien/{sinhVienId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = KhoaEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KhoaEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KhoaEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KhoaEntity.class)) })})
    public ResponseEntity<?> getSinhVienById(@PathVariable String sinhVienId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get SinhVien By Id!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorSinhVien.validateGetSinhVienById(sinhVienId);
            SinhVienEntity sinhVienEntity = (SinhVienEntity) commonService.getObjectById(sinhVienId, new SinhVienDto());
            returnObject.setRetObj(sinhVienEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    @Operation(summary = "Get Sinh Vien by maLop.")
    @GetMapping("/sinhVien/lop/{maLop}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = KhoaEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KhoaEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KhoaEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KhoaEntity.class)) })})
    public ResponseEntity<?> getSinhVienByLopId(@PathVariable String maLop) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get SinhVien By maLop!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorSinhVien.validateGetListSinhVienByMaLop(maLop);
            List<SinhVienEntity> sinhVienEntity = sinhVienService.getListSinhVienByMaLop(maLop);
            returnObject.setRetObj(sinhVienEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

}
