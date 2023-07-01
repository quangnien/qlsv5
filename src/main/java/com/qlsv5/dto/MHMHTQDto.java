package com.qlsv5.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MHMHTQDto {

	@NotBlank
	private String maMh;

	private List<String> maMHTQList;

}
