package com.qlsv5.api;

import com.qlsv5.common.ReturnObject;
import com.qlsv5.dto.ChiTietLopTcDto;
import com.qlsv5.dto.LopDto;
import com.qlsv5.entity.ChiTietLopTcEntity;
import com.qlsv5.entity.LopEntity;
import com.qlsv5.service.ChiTietLopTcService;
import com.qlsv5.service.CommonService;
import com.qlsv5.service.LopService;
import com.qlsv5.validation.ValidatorChiTietLopTc;
import com.qlsv5.validation.ValidatorLop;
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
public class ChiTietLopTcApi {

    @Autowired
    private CommonService commonService;
    @Autowired
    private ValidatorChiTietLopTc validatorChiTietLopTc;

    /* CREATE */
    @Operation(summary = "Create ChiTietLopTc.")
    @PostMapping("/chiTietLopTc")
    public ResponseEntity<?> createChiTietLopTc(@Valid @RequestBody ChiTietLopTcDto chiTietLopTc, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();

        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Add ChiTietLopTc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorChiTietLopTc.validateAddChiTietLopTc(chiTietLopTc);
            commonService.addObject(chiTietLopTc);
            returnObject.setRetObj(chiTietLopTc);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* UPDATE */
    @PutMapping("/chiTietLopTc")
    @Operation(summary = "Update ChiTietLopTc.")
    public ResponseEntity<?> updateChiTietLopTc(@Valid @RequestBody ChiTietLopTcDto chiTietLopTc, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();
        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Update ChiTietLopTc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorChiTietLopTc.validateEditChiTietLopTc(chiTietLopTc);
            commonService.updateObject(chiTietLopTc);

            returnObject.setRetObj(chiTietLopTc);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* DELETE */
    @DeleteMapping("/chiTietLopTc")
    @Operation(summary = "Delete ChiTietLopTc by list id")
    public ResponseEntity<?> deleteChiTietLopTc(@Valid @RequestBody List<String> lstChiTietLopTcId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Delete List ChiTietLopTc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            List<String> deleteSuccess = commonService.deleteLstObject(lstChiTietLopTcId, new ChiTietLopTcDto());
            returnObject.setRetObj(deleteSuccess);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET ALL */
    @Operation(summary = "Get all ChiTietLopTc.")
    @GetMapping("/chiTietLopTc")
    public ResponseEntity<?> getAllChiTietLopTc() {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All ChiTietLopTc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            List<Object> listChiTietLopTc = commonService.findAllObject(new ChiTietLopTcDto());
            returnObject.setRetObj(listChiTietLopTc);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET BY ID */
    @Operation(summary = "Get ChiTietLopTc by id.")
    @GetMapping("/chiTietLopTc/{chiTietLopTcId}")
    public ResponseEntity<?> getChiTietLopTcById(@PathVariable String chiTietLopTcId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get ChiTietLopTc By Id!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorChiTietLopTc.validateGetChiTietLopTcById(chiTietLopTcId);
            ChiTietLopTcEntity chiTietLopTcEntity = (ChiTietLopTcEntity) commonService.getObjectById(chiTietLopTcId, new ChiTietLopTcDto());
            returnObject.setRetObj(chiTietLopTcEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

}
