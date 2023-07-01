package com.qlsv5.factory;

import com.qlsv5.dto.UpdatePasswordDto;
import com.qlsv5.exception.BusinessException;

/**
 * qlsv5
 *
 * @author Smartee
 * @date 7/1/2023 4:10 PM
 */
public interface UserChangePW {
    void updatePassword(UpdatePasswordDto updatePasswordDto) throws BusinessException;
}
