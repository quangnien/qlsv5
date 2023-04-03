package com.qlsv5.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiemDto {

	private String id;

	private float cc;
	private float gk;
	private float ck;
	private float tb;

	@NotBlank(message = "Vui Lòng Nhập Xếp Loại")
	private String xepLoai;

	/* FOREIGN KEY */
	@NotBlank(message = "Vui Lòng Nhập Mã Sinh Viên")
	@Length(min = 4 , message = "Mã sinh viên tín chỉ chứa ít nhất 4 ký tự!")
	private String maSv;

	@NotBlank(message = "Vui Lòng Nhập Mã Lớp Tín Chỉ")
	@Length(min = 4 , message = "Mã lớp tín chỉ chứa ít nhất 4 ký tự!")
	private String maLopTc;
	
}
