package com.qlsv5.entity;


import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "tuan")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TuanEntity {
	
	@Id
	private String id;
	private int tuan;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private LocalDate ngaybd;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private LocalDate ngaykt;

	private String description;

	private int nam;
}
