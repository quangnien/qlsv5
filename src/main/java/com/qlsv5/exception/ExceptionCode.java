package com.qlsv5.exception;

import com.qlsv5.constant.DtsConstant;
import com.qlsv5.utils.DtsStringUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author NienNQ
 * @created 2023 - 03 - 05 11:05 AM
 * @project qlsv
 */
@Getter
@Setter
public class ExceptionCode implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    private String text;
    private int value;

    public ExceptionCode(String exceptionErrorCode) {
        int indexUnderlinedFirst = DtsStringUtil.indexOf(exceptionErrorCode, DtsConstant.UNDERLINED);
        this.value = Integer.parseInt(DtsStringUtil.substring(exceptionErrorCode, 0, indexUnderlinedFirst));
        this.text = DtsStringUtil.substring(exceptionErrorCode, indexUnderlinedFirst + 1, DtsStringUtil.length(exceptionErrorCode));
    }

}
