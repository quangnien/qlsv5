package com.qlsv5.api;

import com.qlsv5.common.ReturnObject;
import com.qlsv5.dto.DiemDto;
import com.qlsv5.entity.DiemEntity;
import com.qlsv5.service.CommonService;
import com.qlsv5.validation.ValidatorDiem;
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
 * @created 2023 - 03 - 24 6:20 PM
 * @project qlsv
 */
@Slf4j
@RestController
@RequestMapping("/api/admin")
public class DiemApi {

    @Autowired
    private CommonService commonService;
    @Autowired
    private ValidatorDiem validatorDiem;

    /* CREATE */
    @Operation(summary = "Create Diem.")
    @PostMapping("/diem")
    public ResponseEntity<?> createDiem(@Valid @RequestBody DiemDto diem, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();

        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Add Diem!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorDiem.validateAddDiem(diem);
            commonService.addObject(diem);
            returnObject.setRetObj(diem);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* UPDATE */
    @PutMapping("/diem")
    @Operation(summary = "Update Diem.")
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
            commonService.updateObject(diem);

            returnObject.setRetObj(diem);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* DELETE */
    @DeleteMapping("/diem")
    @Operation(summary = "Delete Diem by list id")
    public ResponseEntity<?> deleteDiem(@Valid @RequestBody List<String> lstDiemId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Delete List Diem!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            List<String> deleteSuccess = commonService.deleteLstObject(lstDiemId, new DiemDto());
            returnObject.setRetObj(deleteSuccess);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET ALL */
    @Operation(summary = "Get all Diem.")
    @GetMapping("/diem")
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
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET BY ID */
    @Operation(summary = "Get Diem by id.")
    @GetMapping("/diem/{diemId}")
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
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

}
