package com.qlsv5.entity;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "diem")
@Data
@AllArgsConstructor
@NoArgsConstructor
@CompoundIndex(def = "{'maSv': 1, 'maLopTc': 1}", unique = true)
public class DiemEntity {
	
	@Id
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
