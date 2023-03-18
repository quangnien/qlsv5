package com.qlsv5.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Document(collection = "khoa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhoaEntity {
	
	@Id
	private String id;
	
	@Indexed(unique = true)
	@NotBlank(message = "Vui Lòng Nhập Mã Khoa")
	@Length(min = 4 , message = "Mã khoa chứa ít nhất 4 ký tự!")
	private String maKhoa;
	
	@NotBlank(message = "Vui Lòng Nhập Tên Khoa")
	@Length(min = 4 , message = "Mã khoa chứa ít nhất 5 ký tự!")
	private String tenKhoa;
	
	private String sdt;
	
	@NotBlank(message = "Vui lòng nhập Email!")
	@Email(message = "Nhập đúng định dạng email!")
	private String email;
	
}
