package com.qlsv5.api;

import com.qlsv5.common.ReturnObject;
import com.qlsv5.dto.GiangVienDto;
import com.qlsv5.entity.GiangVienEntity;
import com.qlsv5.service.CommonService;
import com.qlsv5.service.GiangVienService;
import com.qlsv5.validation.ValidatorGiangVien;
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
public class GiangVienApi {
    @Autowired
    private CommonService commonService;
    @Autowired
    private GiangVienService giangVienService;

    @Autowired
    private ValidatorGiangVien validatorGiangVien;

    /* CREATE */
    @Operation(summary = "Create Giang Vien.")
    @PostMapping("/giangVien")
    public ResponseEntity<?> createGiangVien(@Valid @RequestBody GiangVienDto giangVien, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();

        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Add GiangVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorGiangVien.validateAddGiangVien(giangVien);
            commonService.addObject(giangVien);
            returnObject.setRetObj(giangVien);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* UPDATE */
    @Operation(summary = "Update Giang Vien.")
    @PutMapping("/giangVien")
    public ResponseEntity<?> updateGiangVien(@Valid @RequestBody GiangVienDto giangVien, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();
        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Update GiangVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorGiangVien.validateEditGiangVien(giangVien);
            commonService.updateObject(giangVien);

            returnObject.setRetObj(giangVien);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* DELETE */
    @Operation(summary = "Delete Giang Vien by list id")
    @DeleteMapping("/giangVien")
    public ResponseEntity<?> deleteGiangVien(@Valid @RequestBody List<String> lstGiangVienId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Delete List GiangVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

//            List<String> deleteSuccess = giangVienService.deleteLstGiangVien(lstGiangVienId);
            List<String> deleteSuccess = commonService.deleteLstObject(lstGiangVienId, new GiangVienDto());
            returnObject.setRetObj(deleteSuccess);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET ALL */
    @Operation(summary = "Get all Giang Vien.")
    @GetMapping("/giangVien")
    public ResponseEntity<?> getAllGiangVien() {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All GiangVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

//            List<GiangVienEntity> listGiangVien = giangVienService.findAllGiangVien();
            List<Object> listGiangVien = commonService.findAllObject(new GiangVienDto());
            returnObject.setRetObj(listGiangVien);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET BY ID */
    @Operation(summary = "Get Giang Vien by id.")
    @GetMapping("/giangVien/{giangVienId}")
    public ResponseEntity<?> getGiangVienById(@PathVariable String giangVienId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get GiangVien By Id!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorGiangVien.validateGetGiangVienById(giangVienId);
//            GiangVienEntity giangVienEntity = giangVienService.getGiangVienById(giangVienId);
            GiangVienEntity giangVienEntity = (GiangVienEntity) commonService.getObjectById(giangVienId, new GiangVienDto());
            returnObject.setRetObj(giangVienEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

}
