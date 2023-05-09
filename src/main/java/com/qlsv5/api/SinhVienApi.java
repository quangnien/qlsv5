package com.qlsv5.api;

import com.qlsv5.common.ReturnObject;
import com.qlsv5.dto.SinhVienDto;
import com.qlsv5.dto.TkbDto;
import com.qlsv5.dto.UpdatePasswordDto;
import com.qlsv5.entity.SinhVienEntity;
import com.qlsv5.entity.UserEntity;
import com.qlsv5.security.services.UserDetailsImpl;
import com.qlsv5.service.CommonService;
import com.qlsv5.service.SinhVienService;
import com.qlsv5.service.UserService;
import com.qlsv5.validation.ValidatorSinhVien;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@Tag(name = "Sinh Vien", description = "Management APIs for SINH VIEN.")
public class SinhVienApi {
    @Autowired
    private CommonService commonService;
    @Autowired
    private SinhVienService sinhVienService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private ValidatorSinhVien validatorSinhVien;

    /* CREATE */
    @Operation(summary = "Create Sinh Vien.")
    @PostMapping("/sinhVien")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) })})
    public ResponseEntity<?> createSinhVien(@Valid @RequestBody SinhVienDto sinhVien, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();

        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Add SinhVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorSinhVien.validateAddSinhVien(sinhVien);
            SinhVienEntity sinhVienEntity = (SinhVienEntity) commonService.addObject(sinhVien);
            returnObject.setRetObj(sinhVienEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* UPDATE */
    @Operation(summary = "Update Sinh Vien.")
    @PutMapping("/sinhVien")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) })})
    public ResponseEntity<?> updateSinhVien(@Valid @RequestBody SinhVienDto sinhVien, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();
        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Update SinhVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorSinhVien.validateEditSinhVien(sinhVien);
            commonService.updateObject(sinhVien);

            returnObject.setRetObj(sinhVien);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* DELETE */
    @Operation(summary = "Delete Sinh Vien by list id")
    @DeleteMapping("/sinhVien")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) })})
    public ResponseEntity<?> deleteSinhVien(@Valid @RequestBody List<String> lstSinhVienId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Delete List SinhVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

//            List<String> deleteSuccess = sinhVienService.deleteLstSinhVien(lstSinhVienId);
            List<String> deleteSuccess = commonService.deleteLstObject(lstSinhVienId, new SinhVienDto());
            returnObject.setRetObj(deleteSuccess);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET ALL */
    @Operation(summary = "Get all Sinh Vien.")
    @GetMapping("/sinhVien")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) })})
    public ResponseEntity<?> getAllSinhVien() {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All SinhVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

//            List<SinhVienEntity> listSinhVien = sinhVienService.findAllSinhVien();
            List<Object> listSinhVien = commonService.findAllObject(new SinhVienDto());
            returnObject.setRetObj(listSinhVien);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET BY ID */
    @Operation(summary = "Get Sinh Vien by id.")
    @GetMapping("/sinhVien/{sinhVienId}")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) })})
    public ResponseEntity<?> getSinhVienById(@PathVariable String sinhVienId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get SinhVien By Id!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorSinhVien.validateGetSinhVienById(sinhVienId);
            SinhVienEntity sinhVienEntity = (SinhVienEntity) commonService.getObjectById(sinhVienId, new SinhVienDto());
            returnObject.setRetObj(sinhVienEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    @Operation(summary = "Get Sinh Vien by maLop.")
    @GetMapping("/sinhVien/lop/{maLop}")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) })})
    public ResponseEntity<?> getSinhVienByLopId(@PathVariable String maLop,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "7") int size) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get SinhVien By maLop!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorSinhVien.validateGetListSinhVienByMaLop(maLop);
            List<SinhVienEntity> sinhVienEntity = sinhVienService.getListSinhVienByMaLop(maLop, page, size);
            returnObject.setRetObj(sinhVienEntity);

            /*for paging*/
            List<SinhVienEntity> dsLopTcEntityForPaging = sinhVienService.getListSinhVienByMaLop(maLop, 0, 100000);

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

        return ResponseEntity.ok(returnObject);
    }

//    @Operation(summary = "Update password")
//    @PostMapping("/sinhVien/updatePassword")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Success",
//                    content = {
//                            @Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) }),
//            @ApiResponse(responseCode = "401", description = "Unauthorized",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) }),
//            @ApiResponse(responseCode = "403", description = "Forbidden",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) }),
//            @ApiResponse(responseCode = "500", description = "Internal server error",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) })})
//    public ResponseEntity<?> updatePasswordSinhVien(@Valid @RequestBody UpdatePasswordDto updatePasswordDto, BindingResult bindingResult) {
//
//        ReturnObject returnObject = new ReturnObject();
//        if (bindingResult.hasErrors()) {
//            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
//            return ResponseEntity.ok(returnObject);
//        }
//        try {
//            log.info("Update password SinhVien By Id!");
//
//            returnObject.setStatus(ReturnObject.SUCCESS);
//            returnObject.setMessage("200");
//
//            validatorSinhVien.validateUpdatePasswordSinhVien(updatePasswordDto);
//
//            SinhVienEntity getSinhVienByDB = (SinhVienEntity) commonService.getObjectById(updatePasswordDto.getId(), new SinhVienDto());
//
//            /* update PW SinhVienEntity*/
//            getSinhVienByDB.setMatKhau(updatePasswordDto.getConfirmPassword());
//            commonService.updateObject(getSinhVienByDB);
//
//            /* update PW UserEntity*/
//            UserEntity userEntity = userService.findByUsername(getSinhVienByDB.getMaSv());
//            userEntity.setPassword(encoder.encode(updatePasswordDto.getConfirmPassword()));
//            userService.updateUser(userEntity);
//
//            returnObject.setRetObj(getSinhVienByDB);
//        }
//        catch (Exception ex){
//            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage(ex.getMessage());
//        }
//
//        return ResponseEntity.ok(returnObject);
//    }

    /* GET THỜI KHÓA BIỂU */
    @Operation(summary = "Get TKB For Sinh Vien.")
    @PostMapping("/sinhVien/tkb")
//    @PreAuthorize("hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TkbDto.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TkbDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TkbDto.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TkbDto.class)) }),
            @ApiResponse(responseCode = "405", description = "Method not allowed",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TkbDto.class)) })})
    public ResponseEntity<?> getTKForSinhVien(@Valid @RequestBody TkbDto tkbDto, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();
        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Get All TKB For SinhVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorSinhVien.validateGetTKBForSinhVien(tkbDto);

            /* get info user is logining*/
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = ((UserDetailsImpl)principal).getUsername();

            List<TkbDto> listTkbDto = sinhVienService.getListTKBForSinhVien(username, tkbDto);

            returnObject.setRetObj(listTkbDto);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

}
