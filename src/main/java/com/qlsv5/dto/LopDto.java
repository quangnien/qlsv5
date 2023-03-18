package com.qlsv5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LopDto {

	private String id;

	@NotBlank(message = "Vui Lòng Nhập Mã Lớp")
	@Length(min = 4 , message = "Mã lớp chứa ít nhất 4 ký tự!")
	private String maLop;

	@NotBlank(message = "Vui Lòng Nhập Mã Khoa")
	@Length(min = 4 , message = "Mã lớp chứa ít nhất 4 ký tự!")
	private String tenLop;

	/* FOREIGN KEY */
	private String makhoa;
}
