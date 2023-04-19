package com.qlsv5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DangKyMonDto {
    List<DiemDto> diemDtoList;
}
