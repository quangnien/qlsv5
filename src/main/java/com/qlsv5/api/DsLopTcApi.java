package com.qlsv5.api;

import com.qlsv5.common.ReturnObject;
import com.qlsv5.dto.DsLopTcDto;
import com.qlsv5.entity.DsLopTcEntity;
import com.qlsv5.service.CommonService;
import com.qlsv5.service.DsLopTcService;
import com.qlsv5.validation.ValidatorDsLopTc;
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
 * @created 2023 - 03 - 18 6:20 AM
 * @project qlsv
 */
@Slf4j
@RestController
@RequestMapping("/api/admin")
//@Api(value = "DsLopTcApi", description = "REST API for DsLopTc", tags = { "DsLopTcApi" })
public class DsLopTcApi {

    @Autowired
    private CommonService commonService;
    @Autowired
    private DsLopTcService dsLopTcService;

    @Autowired
    private ValidatorDsLopTc validatorDsLopTc;

    /* CREATE */
    @Operation(summary = "Create DsLopTc.")
    @PostMapping("/dsLopTc")
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
            commonService.addObject(dsLopTc);
            returnObject.setRetObj(dsLopTc);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* UPDATE */
    @PutMapping("/dsLopTc")
    @Operation(summary = "Update DsLopTc.")
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
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* DELETE */
    @DeleteMapping("/dsLopTc")
    @Operation(summary = "Delete DsLopTc by list id")
    public ResponseEntity<?> deleteDsLopTc(@Valid @RequestBody List<String> lstDsLopTcId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Delete List DsLopTc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            List<String> deleteSuccess = commonService.deleteLstObject(lstDsLopTcId, new DsLopTcDto());
            returnObject.setRetObj(deleteSuccess);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET ALL */
    @Operation(summary = "Get all DsLopTc.")
    @GetMapping("/dsLopTc")
    public ResponseEntity<?> getAllDsLopTc() {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All DsLopTc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            List<Object> listDsLopTc = commonService.findAllObject(new DsLopTcDto());
            returnObject.setRetObj(listDsLopTc);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET BY ID */
    @Operation(summary = "Get DsLopTc by id.")
    @GetMapping("/dsLopTc/{dsLopTcId}")
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
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

}
