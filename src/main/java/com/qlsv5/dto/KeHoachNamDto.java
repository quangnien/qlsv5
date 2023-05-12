package com.qlsv5.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeHoachNamDto {

	private String id;

	@NotBlank(message = "Vui Lòng Nhập Mã Kế Hoạch")
	@Length(min = 2 , message = "Mã lớp chứa ít nhất 2 ký tự!")
	private String maKeHoach;

	private int ky;

	private int nam;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private Date timeDkMonBegin;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private Date timeDkMonEnd;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private Date timeStudyBegin;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private Date timeStudyEnd;

}
