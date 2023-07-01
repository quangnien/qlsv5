package com.qlsv5.strategy;

import com.qlsv5.dto.UpdatePasswordDto;
import com.qlsv5.exception.BusinessException;

public interface StrategyUpdatePassword {
    void updatePassword(UpdatePasswordDto updatePasswordDto) throws BusinessException;
}