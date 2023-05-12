package com.qlsv5.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiangVienDto {

	private String id;

	@NotBlank(message = "Vui Lòng Nhập Mã Sinh Viên")
	@Length(min = 4 , message = "Mã sinh viên chứa ít nhất 4 ký tự!")
	private String maGv;

	@NotBlank(message = "Vui Lòng Nhập Họ")
	private String ho;

	@NotBlank(message = "Vui Lòng Nhập Tên")
	private String ten;

	@NotBlank(message = "Vui Lòng Nhập Giới Tính")
	private String phai;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private Date ngaySinh;

	private String sdt;

	@NotBlank(message = "Vui lòng nhập Email!")
	@Email(message = "Nhập đúng định dạng email!")
	private String email;

//	@NotBlank(message = "Vui Lòng Nhập Mật Khẩu")
//	@Length(min = 4 , message = "Mật khẩu chứa ít nhất 4 ký tự!")
	@JsonIgnore
	private String matKhau;

	private int vaiTro;

	private String hinhAnh;

	/* FOREIGN KEY */
	@NotBlank(message = "Vui Lòng Nhập Mã Khoa")
	@Length(min = 4 , message = "Mã khoa chứa ít nhất 4 ký tự!")
	private String maKhoa;
}
