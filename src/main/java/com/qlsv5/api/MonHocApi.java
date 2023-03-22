package com.qlsv5.api;

import com.qlsv5.common.ReturnObject;
import com.qlsv5.dto.MonHocDto;
import com.qlsv5.entity.MonHocEntity;
import com.qlsv5.service.CommonService;
import com.qlsv5.service.MonHocService;
import com.qlsv5.validation.ValidatorMonHoc;
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
//@Api(value = "MonHocApi", description = "REST API for MonHoc", tags = { "MonHocApi" })
public class MonHocApi {
    @Autowired
    private CommonService commonService;
    @Autowired
    private MonHocService monHocService;

    @Autowired
    private ValidatorMonHoc validatorMonHoc;

    /* CREATE */
    @Operation(summary = "Create MonHoc.")
    @PostMapping("/monHoc")
    public ResponseEntity<?> createMonHoc(@Valid @RequestBody MonHocDto monHoc, BindingResult bindingResult) {

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

            validatorMonHoc.validateAddMonHoc(monHoc);
//            monHocService.addMonHoc(monHoc);
            commonService.addObject(monHoc);
            returnObject.setRetObj(monHoc);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* UPDATE */
    @Operation(summary = "Update MonHoc.")
    @PutMapping("/monHoc")
    public ResponseEntity<?> updateMonHoc(@Valid @RequestBody MonHocDto monHoc, BindingResult bindingResult) {

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

            validatorMonHoc.validateEditMonHoc(monHoc);
//            monHocService.updateMonHoc(monHoc);
            commonService.updateObject(monHoc);

            returnObject.setRetObj(monHoc);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* DELETE */
    @Operation(summary = "Delete MonHoc by list id")
    @DeleteMapping("/monHoc")
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
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET ALL */
    @Operation(summary = "Get all MonHoc.")
    @GetMapping("/monHoc")
    public ResponseEntity<?> getAllMonHoc() {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All MonHoc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

//            List<MonHocEntity> listMonHoc = monHocService.findAllMonHoc();
            List<Object> listMonHoc = commonService.findAllObject( new MonHocDto());
            returnObject.setRetObj(listMonHoc);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET BY ID */
    @Operation(summary = "Get MonHoc by id.")
    @GetMapping("/monHoc/{monHocId}")
    public ResponseEntity<?> getMonHocById(@PathVariable String monHocId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get MonHoc By Id!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorMonHoc.validateGetMonHocById(monHocId);
//            MonHocEntity monHocEntity = monHocService.getMonHocById(monHocId);
            MonHocEntity monHocEntity = (MonHocEntity) commonService.getObjectById(monHocId, new MonHocDto());
            returnObject.setRetObj(monHocEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

}
