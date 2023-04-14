package com.qlsv5.entity;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "ds_lop_tc")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DsLopTcEntity {
	
	@Id
	private String id;
	
	@NotBlank(message = "Vui Lòng Nhập Mã Lớp Tín Chỉ")
	@Length(min = 4 , message = "Mã lớp tín chỉ chứa ít nhất 4 ký tự!")
	@Indexed(unique = true)
	private String maLopTc;
	
	private int nienKhoa;
	private int ky;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private Date timeBd;
	
	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private Date timeKt;
	
	/* FOREIGN KEY */
	@NotBlank(message = "Vui Lòng Nhập Mã Môn Học")
	@Length(min = 4 , message = "Mã môn học chỉ chứa ít nhất 4 ký tự!")
	private String maMh;

	@NotBlank(message = "Vui Lòng Nhập Mã Giáo Viên")
	@Length(min = 4 , message = "Mã giáo viên chỉ chứa ít nhất 4 ký tự!")
	private String maGv;

	@NotBlank(message = "Vui Lòng Nhập Mã Lớp")
	@Length(min = 4 , message = "Mã lớp chứa ít nhất 4 ký tự!")
	private String maLop;
	
}
