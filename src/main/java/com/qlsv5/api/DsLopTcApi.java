package com.qlsv5.api;

import com.qlsv5.common.ReturnObject;
import com.qlsv5.dto.DsLopTcDto;
import com.qlsv5.entity.DsLopTcEntity;
import com.qlsv5.entity.LopEntity;
import com.qlsv5.service.CommonService;
import com.qlsv5.service.DsLopTcService;
import com.qlsv5.validation.ValidatorDsLopTc;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@Tag(name = "Danh Sach Lop Tin Chi", description = "Management APIs for DANH SACH LOP TIN CHI.")
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
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) })})
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
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) })})
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
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) })})
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
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @GetMapping("/dsLopTc")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) })})
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
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) })})
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

    @Operation(summary = "Get danh sach lop tin chi by maLop")
    @GetMapping("/dsLopTc/lop/{maLop}")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DsLopTcEntity.class)) })})
    public ResponseEntity<?> getDsLopTcByMaLop(@PathVariable String maLop,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "4") int size) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get Ds Lop Tin Chi By maLop!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorDsLopTc.validateGetListLopTcByMaLop(maLop);
            List<DsLopTcEntity> dsLopTcEntity = dsLopTcService.getListLopTcByMaLop(maLop, page, size);
            returnObject.setRetObj(dsLopTcEntity);

            /*for paging*/
            List<DsLopTcEntity> dsLopTcEntityForPaging = dsLopTcService.getListLopTcByMaLop(maLop, 0, 100000);

            double totalPageDouble = (double) dsLopTcEntityForPaging.size() / size;
            int totalPageForPaging = (int) Math.ceil(totalPageDouble);

            returnObject.setPage(page);
            returnObject.setTotalRetObjs(dsLopTcEntityForPaging.size());
            returnObject.setTotalPages(totalPageForPaging);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        String s1 = "welcome";
        String s2 = new String("welcome");
        System.out.print("s1 == s2: " + (s1 == s2));

        return ResponseEntity.ok(returnObject);
    }

}
