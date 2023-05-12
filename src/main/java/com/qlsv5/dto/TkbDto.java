package com.qlsv5.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TkbDto {

	private String idLopTc;

	private String maLopTc;

	private String nienKhoa;
	private int ky;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private Date timeBd;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private Date timeKt;

	/* soluong */
	private int soLuong;

	private int soLuongCon;

	/* FOREIGN KEY */
	private String maMh;
	private String maGv;
	private String maLop;

	/* DATE INPUT */
	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private Date timeInputBegin;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private Date timeInputEnd;

	/* FROM CHI TIET LOP TC */
	private int tiet;
	private String thu;
	private int soTiet;
	private String phong;

	public TkbDto(Date timeInputBegin, Date timeInputEnd) {
		this.timeInputBegin = timeInputBegin;
		this.timeInputEnd = timeInputEnd;
	}
}
