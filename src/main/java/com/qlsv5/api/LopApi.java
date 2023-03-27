package com.qlsv5.api;

import com.qlsv5.common.ReturnObject;
import com.qlsv5.constant.ApiPath;
import com.qlsv5.dto.LopDto;
import com.qlsv5.entity.LopEntity;
import com.qlsv5.service.CommonService;
import com.qlsv5.service.LopService;
import com.qlsv5.validation.ValidatorLop;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
@SecurityRequirement(name = "basicAuth")
@RequestMapping(value = ApiPath.API_ADMIN, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//@Api(value = "LopApi", description = "REST API for Lop", tags = { "LopApi" })
public class LopApi {

    @Autowired
    private CommonService commonService;
    @Autowired
    private LopService lopService;

    @Autowired
    private ValidatorLop validatorLop;

    /* CREATE */
    @Operation(summary = "Create Lop.")
    @PostMapping("/lop")
    public ResponseEntity<?> createLop(@Valid @RequestBody LopDto lop, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();

        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Add Lop!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorLop.validateAddLop(lop);
//            lopService.addLop(lop);
            commonService.addObject(lop);
            returnObject.setRetObj(lop);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* UPDATE */
    @PutMapping("/lop")
    @Operation(summary = "Update Lop.")
    public ResponseEntity<?> updateLop(@Valid @RequestBody LopDto lop, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();
        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Update Lop!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorLop.validateEditLop(lop);
//            lopService.updateLop(lop);
            commonService.updateObject(lop);

            returnObject.setRetObj(lop);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* DELETE */
    @DeleteMapping("/lop")
    @Operation(summary = "Delete Lop by list id")
    public ResponseEntity<?> deleteLop(@Valid @RequestBody List<String> lstLopId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Delete List Lop!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");


//            List<String> deleteSuccess = lopService.deleteLstLop(lstLopId);
            List<String> deleteSuccess = commonService.deleteLstObject(lstLopId, new LopDto());
            returnObject.setRetObj(deleteSuccess);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET ALL */
    @Operation(summary = "Get all Lop.")
    @GetMapping("/lop")
    public ResponseEntity<?> getAllLop() {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All Lop!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

//            List<LopEntity> listLop = lopService.findAllLop();
            List<Object> listLop = commonService.findAllObject(new LopDto());
            returnObject.setRetObj(listLop);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET BY ID */
    @Operation(summary = "Get Lop by id.")
    @GetMapping("/lop/{lopId}")
    public ResponseEntity<?> getLopById(@PathVariable String lopId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get Lop By Id!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorLop.validateGetLopById(lopId);
//            LopEntity lopEntity = lopService.getLopById(lopId);
            LopEntity lopEntity = (LopEntity) commonService.getObjectById(lopId, new LopDto());
            returnObject.setRetObj(lopEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

}
