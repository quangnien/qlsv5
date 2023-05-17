package com.qlsv5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TkbDto {

	private String idLopTc;

	private String maLopTc;

	private String nienKhoa;
	private int ky;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private LocalDate timeBd;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private LocalDate timeKt;

	/* soluong */
	private int soLuong;

	private int soLuongCon;

	/* FOREIGN KEY */
	private String maMh;
	private String maGv;
	private String maLop;

	private String tenMh;
	private String tenGv;

	/* DATE INPUT */
	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private LocalDate timeInputBegin;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private LocalDate timeInputEnd;

	/* FROM CHI TIET LOP TC */
	private int tiet;
	private String thu;
	private int soTiet;
	private String phong;

	public TkbDto(LocalDate timeInputBegin, LocalDate timeInputEnd) {
		this.timeInputBegin = timeInputBegin;
		this.timeInputEnd = timeInputEnd;
	}
}
