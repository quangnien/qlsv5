package com.qlsv5.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DangKyMonDto {
//    List<DiemDto> diemDtoList;
    String maSv;
    List<String> maLopTcList;
}
