package com.qlsv5.api;

import com.qlsv5.common.ReturnObject;
import com.qlsv5.dto.TkbDto;
import com.qlsv5.dto.TuanDto;
import com.qlsv5.dto.WrapTkbDto;
import com.qlsv5.entity.KeHoachNamEntity;
import com.qlsv5.entity.TuanEntity;
import com.qlsv5.service.*;
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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;

/**
 * @author NienNQ
 * @created 2023 - 03 - 05 6:20 AM
 * @project qlsv
 */
@Slf4j
@RestController
@RequestMapping("/api/admin")
@Tag(name = "TKB", description = "Management APIs for TKB.")
public class ThoiKhoaBieuApi {
    
    @Autowired
    private KeHoachNamService keHoachNamService;

    @Autowired
    private GiangVienService giangVienService;

    @Autowired
    private SinhVienService sinhVienService;

    @Operation(summary = "Get TKB For Giang Vien")
    @GetMapping("/tkb/giangVien/{maGv}")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = TuanEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TuanEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TuanEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TuanEntity.class)) })})
    public ResponseEntity<?> getTKBFlByTuanForGiangVien(@PathVariable String maGv,
                                        @RequestParam(required = false, defaultValue = "") String maKeHoach,
                                        @RequestParam(required = true, defaultValue = "") int tuan) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All TKB Fl By Tuan!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            KeHoachNamEntity keHoachNamEntity = keHoachNamService.getKeHoachNamClosest();
            if(maKeHoach.equals("")){
                maKeHoach = keHoachNamEntity.getMaKeHoach();
            }

            List<WrapTkbDto> wrapTkbDtoList = giangVienService.getListTKBForGV(maGv, maKeHoach, tuan);

            returnObject.setRetObj(wrapTkbDtoList);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    @Operation(summary = "Get TKB For Sinh Vien")
    @GetMapping("/tkb/sinhVien/{maSv}")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = TuanEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TuanEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TuanEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TuanEntity.class)) })})
    public ResponseEntity<?> getTKBFlByTuanForSinhVien(@PathVariable String maSv,
                                            @RequestParam(required = false, defaultValue = "") String maKeHoach,
                                            @RequestParam(required = true, defaultValue = "") int tuan) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All Tuan Fl By Tuan!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            KeHoachNamEntity keHoachNamEntity = keHoachNamService.getKeHoachNamClosest();
            if(maKeHoach.equals("")){
                maKeHoach = keHoachNamEntity.getMaKeHoach();
            }

            List<WrapTkbDto> wrapTkbDtoList = sinhVienService.getListTKBForSv(maSv, maKeHoach, tuan);

            returnObject.setRetObj(wrapTkbDtoList);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

}
