package myPro.utils.common;

import java.util.regex.Pattern;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description: 正则验证
 * @date 2018/4/6  12:32
 */
public class RegexUtils {

    public static boolean checkPhone(String phone){
        boolean re = false;
        try {
            String phoneReg="^((13[0-9])|(15[^4,\\D])|(18[0,3-9]))\\d{8}$"; //手机号码正则
            re = Pattern.matches(phoneReg,phone);
            System.out.println(phone+"   "+re);
        }catch (Exception e){
            re = false;
        }
        return re;
    }

    public static boolean checkEmail(String email){
        boolean re = false;
        try {
            String emailReg ="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            re = Pattern.matches(emailReg,email);
            System.out.println(email+"   "+re);
        }catch (Exception e){
            re = false;
        }
        return re;
    }



    public static boolean checkPassword(String password){
        boolean re = false;
        try {
            String passReg= "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,}$"; //不小于8位且包含大小写英文字母
            re = Pattern.matches(passReg,password);
            System.out.println(password+"   "+re);
        }catch (Exception e){
            re = false;
        }
        return re;
    }
}
