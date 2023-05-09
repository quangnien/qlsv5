package com.qlsv5.api;

import com.qlsv5.common.ReturnObject;
import com.qlsv5.dto.KeHoachNamDto;
import com.qlsv5.entity.KeHoachNamEntity;
import com.qlsv5.service.CommonService;
import com.qlsv5.validation.ValidatorKeHoachNam;
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
 * @created 2023 - 04 - 30 02:00 PM
 * @project qlsv
 */
@Slf4j
@RestController
@RequestMapping("/api/admin")
@Tag(name = "Ke Hoach Nam", description = "Management APIs for KE HOACH NAM.")
public class KeHoachNamApi {

    @Autowired
    private CommonService commonService;

    @Autowired
    private ValidatorKeHoachNam validatorKeHoachNam;

    /* CREATE */
    @Operation(summary = "Create KeHoachNam.")
    @PostMapping("/keHoachNam")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = KeHoachNamEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KeHoachNamEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KeHoachNamEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KeHoachNamEntity.class)) })})
    public ResponseEntity<?> createKeHoachNam(@Valid @RequestBody KeHoachNamDto keHoachNam, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();

        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Add KeHoachNam!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            /* validator Date không làm vì không đây không phải api chính và làm giao diện*/
            validatorKeHoachNam.validateAddKeHoachNam(keHoachNam);
            commonService.addObject(keHoachNam);
            returnObject.setRetObj(keHoachNam);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET ALL */
    @Operation(summary = "Get all KeHoachNam.")
    @GetMapping("/keHoachNam")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = KeHoachNamEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KeHoachNamEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KeHoachNamEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KeHoachNamEntity.class)) })})
    public ResponseEntity<?> getAllKeHoachNam() {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All KeHoachNam!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            List<Object> listKeHoachNam = commonService.findAllObject( new KeHoachNamDto());
            returnObject.setRetObj(listKeHoachNam);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET BY ID */
    @Operation(summary = "Get KeHoachNam by id.")
    @GetMapping("/keHoachNam/{keHoachNamId}")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = KeHoachNamEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KeHoachNamEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KeHoachNamEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = KeHoachNamEntity.class)) })})
    public ResponseEntity<?> getKeHoachNamById(@PathVariable String keHoachNamId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get KeHoachNam By Id!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorKeHoachNam.validateGetKeHoachNamById(keHoachNamId);
            KeHoachNamEntity keHoachNamEntity = (KeHoachNamEntity) commonService.getObjectById(keHoachNamId, new KeHoachNamDto());
            returnObject.setRetObj(keHoachNamEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

}
