package com.qlsv5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiemByMaSvAndMaKeHoachDto {

	private String id;

	private float cc;
	private float gk;
	private float ck;
	private float tb;

	private String xepLoai;

	private String maMh;
	private String tenMh;
	private int percentCc;
	private int percentGk;
	private int percentCk;
	private int soTc;

	/* FOREIGN KEY */
	private String maSv;

	private String maLopTc;
}
