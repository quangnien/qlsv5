package com.qlsv5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

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
	private String maSv;
	private String maLopTc;
	
}
