package com.qlsv5.api;

import com.qlsv5.common.ReturnObject;
import com.qlsv5.dto.UpdatePasswordDto;
import com.qlsv5.entity.SinhVienEntity;
import com.qlsv5.service.CommonService;
import com.qlsv5.service.SinhVienService;
import com.qlsv5.service.UserService;
import com.qlsv5.service.impl.UpdatePasswordServiceImpl;
import com.qlsv5.repository.GiangVienRepository;
import com.qlsv5.repository.SinhVienRepository;
import com.qlsv5.validation.ValidatorAdmin;
import com.qlsv5.validation.ValidatorGiangVien;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author NienNQ
 * @created 2023 - 03 - 05 6:20 AM
 * @project qlsv
 */
@Slf4j
@RestController
@RequestMapping("/api/admin")
@Tag(name = "DOI MAT KHAU", description = "Management APIs for DOI MAT KHAU")
public class DoiMatKhauApi {

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
    public SinhVienRepository sinhVienRepository;
    @Autowired
    public GiangVienRepository giangVienRepository;
    @Autowired
    public ValidatorSinhVien validatorSinhVien;
    @Autowired
    public ValidatorGiangVien validatorGiangVien;
    @Autowired
    public  ValidatorAdmin validatorAdmin;
    @Autowired
    private UpdatePasswordServiceImpl updatePasswordService;

    @Operation(summary = "Update password")
    @PutMapping("/updatePassword")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
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
    public ResponseEntity<?> updatePassword(@Valid @RequestBody UpdatePasswordDto updatePasswordDto, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();
        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            /* _________ STRATEGY PATTERN _________ */

            /* get info user is logining */
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<String> roleList = new ArrayList<>();
            if (principal instanceof UserDetails) {
                roleList.addAll(((UserDetails) principal).getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()));
            }

            updatePasswordService.updatePassword(updatePasswordDto, roleList.get(0));

            returnObject.setStatus(ReturnObject.SUCCESS);
            String errorMessage = "Đổi mật khẩu thành công!";
            returnObject.setMessage(errorMessage);

            /* _________ STRATEGY PATTERN _________ */
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

}
