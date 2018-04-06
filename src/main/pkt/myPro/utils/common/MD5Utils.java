package myPro.utils.common;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/6  14:43
 */
public class MD5Utils {

    /**
     * 加密
     * @param str
     * @return
     */
    public static String EncodeMD5(String str){
      String encodeStr = DigestUtils.md5Hex(str);
      return encodeStr;
    }


    /**
     * 判断密码是否正确
     * @param password
     * @param md5Pass
     * @return
     */
    public static boolean verifyMD5(String password,String md5Pass){
        String codePass = EncodeMD5(password);
        if (codePass.equals(md5Pass)){
            return true;
        }else {
            return false;
        }
    }

}
