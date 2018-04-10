package com.huafan.huafano2omanger.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by rxy on 17/7/21.
 */

public class ParamMatchUtils {

    public static boolean isPhoneAvailable(String phone) {
        String regex = "^1[3,4,5,7,8]{1}\\d{9}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static boolean isCodeAvailable(String code){
        String regex = "^\\d{6}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(code);
        return matcher.matches();
    }
}
