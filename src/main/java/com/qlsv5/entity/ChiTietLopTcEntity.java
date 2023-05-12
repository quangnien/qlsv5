package com.qlsv5.entity;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "chi_tiet_lop_tc")
@Data
@AllArgsConstructor
@NoArgsConstructor
@CompoundIndex(def = "{'maLopTc': 1, 'tiet': 1, 'thu': 1}", unique = true)
public class ChiTietLopTcEntity {
	
	@Id
	private String id;

	private int tiet;
	
	@NotBlank(message = "Vui Lòng Nhập Thứ")
	@Length(min = 2 , message = "Thứ chỉ chứa ít nhất 2 ký tự!")
	private String thu;
	
	private int soTiet;
	
	@NotBlank(message = "Vui Lòng Nhập Phòng")
	@Length(min = 2 , message = "Phòng chỉ chứa ít nhất 2 ký tự!")
	private String Phong;
	
	/* FOREIGN KEY */
	@NotBlank(message = "Vui Lòng Nhập Mã Lớp Tín Chỉ")
	@Length(min = 4 , message = "Mã lớp tín chỉ chứa ít nhất 4 ký tự!")
	private String maLopTc;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private Date timeBd;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private Date timeKt;

}
