package com.qlsv5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private String tenSv;
}
