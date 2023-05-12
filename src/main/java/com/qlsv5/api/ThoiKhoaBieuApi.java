package com.qlsv5.api;

import com.qlsv5.common.ReturnObject;
import com.qlsv5.dto.TkbDto;
import com.qlsv5.dto.TuanDto;
import com.qlsv5.entity.TuanEntity;
import com.qlsv5.service.CommonService;
import com.qlsv5.service.GiangVienService;
import com.qlsv5.service.TuanService;
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
    private CommonService commonService;

    @Autowired
    private TuanService tuanService;

    @Autowired
    private GiangVienService giangVienService;

    @Operation(summary = "Get TKB.")
    @GetMapping("/tkb")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
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
    public ResponseEntity<?> getTKBFlByTuan(@PathVariable String maGv,
                                        @RequestParam(required = true, defaultValue = "") String maKeHoach,
                                        @RequestParam(required = true, defaultValue = "") int tuan) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All Tuan!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            TkbDto tkbDto = new TkbDto();

            List<TkbDto> listTkbDto = giangVienService.getListTKBForGV(maGv, maKeHoach, tuan);

            List<Object> listTuan = commonService.findAllObject( new TuanDto());
            returnObject.setRetObj(listTuan);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage(ex.getMessage());
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }
    
    /* GET ALL */
//    @Operation(summary = "Get TKB.")
//    @GetMapping("/tkb")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Success",
//                    content = {
//                            @Content(mediaType = "application/json", schema = @Schema(implementation = TuanEntity.class)) }),
//            @ApiResponse(responseCode = "401", description = "Unauthorized",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TuanEntity.class)) }),
//            @ApiResponse(responseCode = "403", description = "Forbidden",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TuanEntity.class)) }),
//            @ApiResponse(responseCode = "500", description = "Internal server error",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TuanEntity.class)) })})
//    public ResponseEntity<?> getTKBFlByTuan(@RequestParam(required = false, defaultValue = "") String tuan,
//                                        @RequestParam(required = false, defaultValue = "") String tuanHienTai) {
//
//        ReturnObject returnObject = new ReturnObject();
//        try {
//            log.info("Get All Tuan!");
//
//            returnObject.setStatus(ReturnObject.SUCCESS);
//            returnObject.setMessage("200");
//
//            if(tuanHienTai.toUpperCase().equals("TRUE")){
//                // Get the current date
//                LocalDate currentDate = LocalDate.now();
//
//                // Get the week number for the current date
//                int weekNumber = currentDate.get(WeekFields.ISO.weekOfWeekBasedYear());
//
//                // Retrieve the record from the "tuan" table that corresponds to the current week
//                TuanEntity tuanEntity = tuanService.findByTuanAndNam(weekNumber, currentDate.getYear());
//
//            }
//
//            List<Object> listTuan = commonService.findAllObject( new TuanDto());
//            returnObject.setRetObj(listTuan);
//        }
//        catch (Exception ex){
//            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage(ex.getMessage());
//        }
//
//        return ResponseEntity.ok(returnObject);
//    }

}
