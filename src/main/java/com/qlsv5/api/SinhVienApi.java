package com.qlsv5.api;

import com.qlsv5.common.ReturnObject;
import com.qlsv5.dto.SinhVienDto;
import com.qlsv5.entity.SinhVienEntity;
import com.qlsv5.service.CommonService;
import com.qlsv5.service.SinhVienService;
import com.qlsv5.validation.ValidatorSinhVien;
import io.swagger.v3.oas.annotations.Operation;
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
//            sinhVienService.addSinhVien(sinhVien);
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
//            sinhVienService.updateSinhVien(sinhVien);
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
    public ResponseEntity<?> deleteSinhVien(@Valid @RequestBody List<String> lstSinhVienId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Delete List SinhVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            List<String> deleteSuccess = sinhVienService.deleteLstSinhVien(lstSinhVienId);
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
    public ResponseEntity<?> getAllSinhVien() {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All SinhVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            List<SinhVienEntity> listSinhVien = sinhVienService.findAllSinhVien();
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
    public ResponseEntity<?> getSinhVienById(@PathVariable String sinhVienId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get SinhVien By Id!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorSinhVien.validateGetSinhVienById(sinhVienId);
            SinhVienEntity sinhVienEntity = sinhVienService.getSinhVienById(sinhVienId);
            returnObject.setRetObj(sinhVienEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

}
