package com.qlsv5.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietLopTcDto {

	private String id;
	private int tiet;

	@NotBlank(message = "Vui Lòng Nhập Thứ")
	@Length(min = 2 , message = "Thứ chỉ chứa ít nhất 2 ký tự!")
	private String thu;

	private int soTiet;

	@NotBlank(message = "Vui Lòng Nhập Phòng")
	@Length(min = 2 , message = "Phòng chỉ chứa ít nhất 2 ký tự!")
	private String phong;

	/* FOREIGN KEY */
	@NotBlank(message = "Vui Lòng Nhập Mã Lớp Tín Chỉ")
	@Length(min = 4 , message = "Mã lớp tín chỉ chứa ít nhất 4 ký tự!")
	private String maLopTc;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private LocalDate timeBd;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private LocalDate timeKt;
	
}
