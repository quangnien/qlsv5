package com.qlsv5.service.impl;

import com.qlsv5.dto.UpdatePasswordDto;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.strategy.StrategyUpdatePassword;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UpdatePasswordServiceImpl {

    StrategyUpdatePassword strategy;

    public void setStrategyUpdatePassword(StrategyUpdatePassword strategyUpdatePassword) {
        this.strategy= strategyUpdatePassword;
    }

    public void updatePassword(UpdatePasswordDto updatePasswordDto) throws BusinessException {
        strategy.updatePassword(updatePasswordDto);
    }

}