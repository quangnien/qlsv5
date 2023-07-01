package com.qlsv5.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonHocModifyDto {

	private String id;

	@NotBlank(message = "Vui Lòng Nhập Mã Môn Học")
	@Length(min = 2 , message = "Mã môn học chứa ít nhất 2 ký tự!")
	private String maMh;

	@NotBlank(message = "Vui Lòng Nhập Tên Môn Học")
	@Length(min = 2 , message = "Tên môn học chứa ít nhất 2 ký tự!")
	private String tenMh;

	private List<String> maMHTQList;
	private List<String> tenMHTQList;

	private int percentCc;
	private int percentGk;
	private int percentCk;
	private int soTietLt;
	private int soTietTh;

	private int soTc;

	/* FOREIGN KEY */
	private String maKhoa;

}
