package com.qlsv5.common;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author NienNQ
 * @created 2023 - 03 - 05 3:42 PM
 * @project qlsv
 */
@Component
public class FunctionCommon {

    public static boolean isValidEmailFormat(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@gmail.com";
        return email.matches(regex);
    }

}
