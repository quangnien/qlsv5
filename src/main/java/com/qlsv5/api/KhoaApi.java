package com.qlsv5.api;

import com.qlsv5.common.ReturnObject;
import com.qlsv5.dto.KhoaDto;
import com.qlsv5.entity.KhoaEntity;
import com.qlsv5.service.KhoaService;
import com.qlsv5.validation.ValidatorKhoa;
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
//@Api(value = "KhoaApi", description = "REST API for Khoa", tags = { "KhoaApi" })
public class KhoaApi {
    @Autowired
    private KhoaService khoaService;

    @Autowired
    private ValidatorKhoa validatorKhoa;

    /* CREATE */
//    @ApiOperation(value = "Add Khoa.")
    @PostMapping("/khoa")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createKhoa(@Valid @RequestBody KhoaDto khoa, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();

        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Add Khoa!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorKhoa.validateAddKhoa(khoa);
            khoaService.addKhoa(khoa);
            returnObject.setRetObj(khoa);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* UPDATE */
    @PutMapping("/khoa")
    public ResponseEntity<?> updateKhoa(@Valid @RequestBody KhoaDto khoa, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();
        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Update Khoa!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorKhoa.validateEditKhoa(khoa);
            khoaService.updateKhoa(khoa);

            returnObject.setRetObj(khoa);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* DELETE */
    @DeleteMapping("/khoa")
    public ResponseEntity<?> deleteKhoa(@Valid @RequestBody List<String> lstKhoaId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Delete List Khoa!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            List<String> deleteSuccess = khoaService.deleteLstKhoa(lstKhoaId);
            returnObject.setRetObj(deleteSuccess);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET ALL */
    @GetMapping("/khoa")
    public ResponseEntity<?> getAllKhoa() {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All Khoa!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            List<KhoaEntity> listKhoa = khoaService.findAllKhoa();
            returnObject.setRetObj(listKhoa);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET BY ID */
    @GetMapping("/khoa/{khoaId}")
    public ResponseEntity<?> getKhoaById(@PathVariable String khoaId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get Khoa By Id!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorKhoa.validateGetKhoaById(khoaId);
            KhoaEntity khoaEntity = khoaService.getKhoaById(khoaId);
            returnObject.setRetObj(khoaEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

}
