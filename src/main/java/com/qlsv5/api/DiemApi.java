package com.qlsv5.api;

import com.qlsv5.common.ReturnObject;
import com.qlsv5.dto.DiemDto;
import com.qlsv5.entity.DiemEntity;
import com.qlsv5.entity.LopEntity;
import com.qlsv5.entity.SinhVienEntity;
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
@Tag(name = "Diem", description = "Management APIs for DIEM.")
public class DiemApi {

    @Autowired
    private CommonService commonService;

    @Autowired
    private ValidatorDiem validatorDiem;

    @Autowired
    private DiemService diemService;

    /* CREATE */
    /*@Operation(summary = "Create Diem.")
    @PostMapping("/diem")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
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
    }*/

    /* UPDATE */
    @PutMapping("/diem")
    @Operation(summary = "Update Diem.")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
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
//    @DeleteMapping("/diem")
//    @Operation(summary = "Delete Diem by list id")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Success",
//                    content = {
//                            @Content(mediaType = "application/json", schema = @Schema(implementation = DiemEntity.class)) }),
//            @ApiResponse(responseCode = "401", description = "Unauthorized",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DiemEntity.class)) }),
//            @ApiResponse(responseCode = "403", description = "Forbidden",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DiemEntity.class)) }),
//            @ApiResponse(responseCode = "500", description = "Internal server error",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DiemEntity.class)) })})
//    public ResponseEntity<?> deleteDiem(@Valid @RequestBody List<String> lstDiemId) {
//
//        ReturnObject returnObject = new ReturnObject();
//        try {
//            log.info("Delete List Diem!");
//
//            returnObject.setStatus(ReturnObject.SUCCESS);
//            returnObject.setMessage("200");
//
//            List<String> deleteSuccess = commonService.deleteLstObject(lstDiemId, new DiemDto());
//            returnObject.setRetObj(deleteSuccess);
//        }
//        catch (Exception ex){
//            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage(ex.getMessage());
//        }
//
//        return ResponseEntity.ok(returnObject);
//    }

    /* GET ALL */
    @Operation(summary = "Get all Diem.")
    @GetMapping("/diem")
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
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
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

    @Operation(summary = "Get danh sach diem by maLopTc")
    @GetMapping("/diem/lopTc/{maLopTc}")
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
    public ResponseEntity<?> getDsDiemByMaLopTc(@PathVariable String maLopTc,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "7") int size) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get danh sach diem By maLopTc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorDiem.validateGetListDiemByMaLopTc(maLopTc);
            List<DiemEntity> diemEntity = diemService.getListDiemByMaLopTc(maLopTc, page, size);
            returnObject.setRetObj(diemEntity);

            /*for paging*/
            List<DiemEntity> diemEntityForPaging = diemService.getListDiemByMaLopTc(maLopTc,  0, 100000);

            double totalPageDouble = (double) diemEntityForPaging.size() / size;
            int totalPageForPaging = (int) Math.ceil(totalPageDouble);

            returnObject.setPage(page);
            returnObject.setTotalRetObjs(diemEntityForPaging.size());
            returnObject.setTotalPages(totalPageForPaging);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

}
