package com.qlsv5.service.impl;

import com.qlsv5.dto.UpdatePasswordDto;
import com.qlsv5.exception.BusinessException;
import com.qlsv5.strategy.StrategyUpdatePassword;
import com.qlsv5.strategy.UpdatePasswordFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Exception.class)
public class UpdatePasswordServiceImpl {
    @Autowired
    UpdatePasswordFactory updatePasswordFactory;

    public void updatePassword(UpdatePasswordDto updatePasswordDto, String role) throws BusinessException {
        StrategyUpdatePassword strategy = updatePasswordFactory.createStrategyUpdatePW(role);
        strategy.updatePassword(updatePasswordDto);
    }
}