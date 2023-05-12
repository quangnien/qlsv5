package com.qlsv5.dto;


import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TuanDto {
	
	private String id;
	private int tuan;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private LocalDate ngaybd;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private LocalDate ngaykt;

	private String description;

	private int nam;
}
