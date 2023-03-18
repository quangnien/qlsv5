package com.qlsv5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;

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
	private String maSv;
	
	private String maLopTc;
	
}
