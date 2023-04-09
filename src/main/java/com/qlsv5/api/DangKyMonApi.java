package com.qlsv5.api;

import com.qlsv5.common.ReturnObject;
import com.qlsv5.dto.DiemDto;
import com.qlsv5.entity.DiemEntity;
import com.qlsv5.service.CommonService;
import com.qlsv5.service.DiemService;
import com.qlsv5.validation.ValidatorDiem;
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
 * @created 2023 - 03 - 24 6:20 PM
 * @project qlsv
 */
@Slf4j
@RestController
@RequestMapping("/api/admin")
@Tag(name = "Dang Ky Mon", description = "Management APIs for DANG KY MON.")
public class DangKyMonApi {

    @Autowired
    private CommonService commonService;

    @Autowired
    private ValidatorDiem validatorDiem;

    @Autowired
    private DiemService diemService;

    /* CREATE */
    @Operation(summary = "Dang ky mon.")
    @PostMapping("/dang-ky-mon")
    @PreAuthorize("hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DiemEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DiemEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DiemEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DiemEntity.class)) })})
    public ResponseEntity<?> createDangKyMon(@Valid @RequestBody DiemDto diem, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();

        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Dang ky mon!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorDiem.validateDangKyMon(diem);
            commonService.addObject(diem);
            returnObject.setRetObj(diem);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* DELETE */
    @DeleteMapping("/dang-ky-mon")
    @Operation(summary = "Delete Dang Ky Mon by list id")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DiemEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DiemEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DiemEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DiemEntity.class)) })})
    public ResponseEntity<?> deleteDangKyMon(@Valid @RequestBody List<String> lstDiemId) {

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



}
