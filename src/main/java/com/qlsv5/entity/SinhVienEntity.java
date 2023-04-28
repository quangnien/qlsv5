package com.qlsv5.entity;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "sinh_vien")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SinhVienEntity {
	
	@Id
	private String id;
	
	@Indexed(unique = true)
	@NotBlank(message = "Vui Lòng Nhập Mã Sinh Viên")
	@Length(min = 4 , message = "Mã sinh viên chứa ít nhất 4 ký tự!")
	private String maSv;
	
	@NotBlank(message = "Vui Lòng Nhập Họ")
	private String ho;
	
	@NotBlank(message = "Vui Lòng Nhập Tên")
	private String ten;
	
	@NotBlank(message = "Vui Lòng Nhập Giới Tính")
	private String phai;
	
	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private Date ngaySinh;
	
	@NotBlank(message = "Vui Lòng Nhập Nơi Sinh")
	@Length(min = 4 , message = "Nơi sinh chứa ít nhất 4 ký tự!")
	private String noiSinh;
	
	@NotBlank(message = "Vui Lòng Nhập Địa chỉ")
	@Length(min = 4 , message = "Địa chỉ chứa ít nhất 4 ký tự!")
	private String diaChi;
	
	private int trangThai;
	
	private String hinhAnh;
	
//	@NotBlank(message = "Vui Lòng Nhập Mật Khẩu")
//	@Length(min = 4 , message = "Mật khẩu chứa ít nhất 4 ký tự!")
	@JsonIgnore
	private String matKhau;
	
	private String sdt;

	@NotBlank(message = "Vui lòng nhập Email!")
	@Email(message = "Nhập đúng định dạng email!")
	private String email;
	
	/* FOREIGN KEY */
	@NotBlank(message = "Vui Lòng Mã Lớp")
	@Length(min = 4 , message = "Mã lớp chứa ít nhất 4 ký tự!")
	private String maLop;

}
