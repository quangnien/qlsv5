package com.qlsv5.entity;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "lop")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LopEntity {

	@Id
	private String id;

	@Indexed(unique = true)
	@NotBlank(message = "Vui Lòng Nhập Mã Lớp")
	@Length(min = 4 , message = "Mã lớp chứa ít nhất 4 ký tự!")
	private String maLop;

	@NotBlank(message = "Vui Lòng Nhập Mã Khoa")
	@Length(min = 4 , message = "Mã lớp chứa ít nhất 4 ký tự!")
	private String tenLop;

	/* FOREIGN KEY */
	@NotBlank(message = "Vui Lòng Nhập Mã Khoa")
	@Length(min = 4 , message = "Mã khoa chứa ít nhất 4 ký tự!")
	private String maKhoa;

}
