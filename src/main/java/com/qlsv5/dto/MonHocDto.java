package com.qlsv5.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonHocDto {

	private String id;

	@NotBlank(message = "Vui Lòng Nhập Mã Môn Học")
	@Length(min = 4 , message = "Mã môn học chứa ít nhất 4 ký tự!")
	private String maMh;

	@NotBlank(message = "Vui Lòng Nhập Tên Môn Học")
	@Length(min = 4 , message = "Tên môn học chứa ít nhất 4 ký tự!")
	private String tenMh;

	private int soTc;
}
