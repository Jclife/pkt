package myPro.utils.common;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description: session的存放和解析
 * @date 2018/2/3  20:22
 */
public class SessionUtils {


    /**
     * session的存入
     * @param session session对象
     * @param object 存入的对象
     * @param sessionName 存入对象的key
     * @return 0表示存入成功，1表示存入失败
     */
    public static int setSessionAtt(HttpSession session, Object object, String sessionName){
        try {
            session.setAttribute(sessionName,object);
            return 0;
        }catch (Exception e){
            return 1;
        }
    }

    /**
     * 得到session的值
     * @param session
     * @param attr
     * @return ND没有数据 NS不存在这个session
     */
    public static String getSessionAtt(HttpSession session,String attr,String key){
        String result;
        Object obj=session.getAttribute(attr);
        Map<String,String> map ;
        try {
            map = DataConversion.objToMapStr(obj);
            if (map!=null){
                result = map.get(key);
            }else {
                result = "ND";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "NS";
        }
        return result;
    }

}


