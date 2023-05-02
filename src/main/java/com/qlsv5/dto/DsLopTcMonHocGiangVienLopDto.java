package com.qlsv5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DsLopTcMonHocGiangVienLopDto {

	private String id;

	@NotBlank(message = "Vui Lòng Nhập Mã Lớp Tín Chỉ")
	@Length(min = 4 , message = "Mã lớp tín chỉ chứa ít nhất 4 ký tự!")
	private String maLopTc;

	/* begin more field */
	private String tenMh;
	private String tenGv;
	/* end more field */

	/* soluong */
	private int soLuong;

	private int soLuongCon;

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

	@NotBlank(message = "Vui Lòng Nhập Mã Kế Hoạch")
	@Length(min = 2 , message = "Mã lớp chứa ít nhất 2 ký tự!")
	private String maKeHoach;
}
