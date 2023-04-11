package com.qlsv5.api;

import com.qlsv5.common.ReturnObject;
import com.qlsv5.dto.GiangVienDto;
import com.qlsv5.dto.SinhVienDto;
import com.qlsv5.dto.TkbDto;
import com.qlsv5.dto.UpdatePasswordDto;
import com.qlsv5.entity.GiangVienEntity;
import com.qlsv5.entity.KhoaEntity;
import com.qlsv5.entity.SinhVienEntity;
import com.qlsv5.entity.UserEntity;
import com.qlsv5.security.services.UserDetailsImpl;
import com.qlsv5.service.CommonService;
import com.qlsv5.service.GiangVienService;
import com.qlsv5.service.UserService;
import com.qlsv5.validation.ValidatorGiangVien;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
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
@Tag(name = "Giang Vien", description = "Management APIs for GIANG VIEN.")
public class GiangVienApi {
    @Autowired
    private CommonService commonService;
    @Autowired
    private GiangVienService giangVienService;
    @Autowired
    private ValidatorGiangVien validatorGiangVien;
    @Autowired
    private UserService userService;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;


    /* CREATE */
    @Operation(summary = "Create Giang Vien.")
    @PostMapping("/giangVien")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) })})
    public ResponseEntity<?> createGiangVien(@Valid @RequestBody GiangVienDto giangVien, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();

        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Add GiangVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorGiangVien.validateAddGiangVien(giangVien);
            commonService.addObject(giangVien);
            returnObject.setRetObj(giangVien);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* UPDATE */
    @Operation(summary = "Update Giang Vien.")
    @PutMapping("/giangVien")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) })})
    public ResponseEntity<?> updateGiangVien(@Valid @RequestBody GiangVienDto giangVien, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();
        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Update GiangVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorGiangVien.validateEditGiangVien(giangVien);
            commonService.updateObject(giangVien);

            returnObject.setRetObj(giangVien);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* DELETE */
    @Operation(summary = "Delete Giang Vien by list id")
    @DeleteMapping("/giangVien")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) })})
    public ResponseEntity<?> deleteGiangVien(@Valid @RequestBody List<String> lstGiangVienId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Delete List GiangVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

//            List<String> deleteSuccess = giangVienService.deleteLstGiangVien(lstGiangVienId);
            List<String> deleteSuccess = commonService.deleteLstObject(lstGiangVienId, new GiangVienDto());
            returnObject.setRetObj(deleteSuccess);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET ALL */
    @Operation(summary = "Get all Giang Vien.")
    @GetMapping("/giangVien")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) })})
    public ResponseEntity<?> getAllGiangVien() {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All GiangVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

//            List<GiangVienEntity> listGiangVien = giangVienService.findAllGiangVien();
            List<Object> listGiangVien = commonService.findAllObject(new GiangVienDto());
            returnObject.setRetObj(listGiangVien);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET BY ID */
    @Operation(summary = "Get Giang Vien by id.")
    @GetMapping("/giangVien/{giangVienId}")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) })})
    public ResponseEntity<?> getGiangVienById(@PathVariable String giangVienId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get GiangVien By Id!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorGiangVien.validateGetGiangVienById(giangVienId);
//            GiangVienEntity giangVienEntity = giangVienService.getGiangVienById(giangVienId);
            GiangVienEntity giangVienEntity = (GiangVienEntity) commonService.getObjectById(giangVienId, new GiangVienDto());
            returnObject.setRetObj(giangVienEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    @Operation(summary = "Get Giang Vien by maKhoa.")
    @GetMapping("/giangVien/khoa/{maKhoa}")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) })})
    public ResponseEntity<?> getGiangVienByKhoaId(@PathVariable String maKhoa) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get GiangVien By maKhoa!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorGiangVien.validateGetListGiangVienByMaKhoa(maKhoa);
            List<GiangVienEntity> giangVienEntity = giangVienService.getListGiangVienByMaKhoa(maKhoa);
            returnObject.setRetObj(giangVienEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    @Operation(summary = "Update password")
    @PostMapping("/giangVien/updatePassword")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_GIANGVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) })})
    public ResponseEntity<?> updatePasswordGiangVien(@Valid @RequestBody UpdatePasswordDto updatePasswordDto, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();
        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Update password GiangVien By Id!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorGiangVien.validateUpdatePasswordGiangVien(updatePasswordDto);

            GiangVienEntity getGiangVienByDB = (GiangVienEntity) commonService.getObjectById(updatePasswordDto.getId(), new GiangVienDto());

            /* update PW GiangVienEntity*/
            getGiangVienByDB.setMatKhau(updatePasswordDto.getConfirmPassword());
            commonService.updateObject(getGiangVienByDB);

            /* update PW UserEntity*/
            UserEntity userEntity = userService.findByUsername(getGiangVienByDB.getMaGv());
            userEntity.setPassword(encoder.encode(updatePasswordDto.getConfirmPassword()));
            userService.updateUser(userEntity);

            returnObject.setRetObj(getGiangVienByDB);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET THỜI KHÓA BIỂU */
    @Operation(summary = "Get TKB For Giang Vien.")
    @PostMapping("/giangVien/tkb")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
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
    public ResponseEntity<?> getTKB(@Valid @RequestBody TkbDto tkbDto, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();
        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Get All TKB For GiangVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorGiangVien.validateGetTKBForGiangVien(tkbDto);

            /* get info user is logining*/
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = ((UserDetailsImpl)principal).getUsername();

            List<TkbDto> listTkbDto = giangVienService.getListTKBForGiangVien(username, tkbDto);

            returnObject.setRetObj(listTkbDto);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }
}