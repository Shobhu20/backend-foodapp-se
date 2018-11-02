package com.windsor.foodapp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    //String regexStr = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$"

    public static final Pattern VALID_PHONE_NUMBER =
            Pattern.compile("^[1-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    public static boolean validatePhone(String phoneNum) {
        Matcher matcher = VALID_PHONE_NUMBER .matcher(phoneNum);
        return matcher.find();
    }


}
