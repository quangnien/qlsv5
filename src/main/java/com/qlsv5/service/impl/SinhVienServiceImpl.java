package com.qlsv5.service.impl;

import com.qlsv5.service.SinhVienService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SinhVienServiceImpl implements SinhVienService {

}
