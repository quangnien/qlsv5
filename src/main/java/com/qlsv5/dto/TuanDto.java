package com.qlsv5.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

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
