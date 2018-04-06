package myPro.controller.sys;

import myPro.bean.sys.Cart;
import myPro.bean.sys.CartPo;
import myPro.bean.sys.User;
import myPro.service.sys.service.SysCartService;
import myPro.service.sys.service.SysUserLoginAndRegisterService;
import myPro.utils.common.MD5Utils;
import myPro.utils.common.RegexUtils;
import myPro.utils.common.SessionUtils;
import myPro.utils.sys.SysPageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description: 登录注册模块
 * @date 2018/2/2  15:44
 */
@RequestMapping("pkt")
@Controller
public class SysLoginAndRegisterController implements SysPageManager {

    @Autowired
    SysUserLoginAndRegisterService service;
    @Autowired
    SysCartService cartService;

    @RequestMapping("login")
    public String to_login(){
        return LOGIN;
    }

    @RequestMapping("login_check")
    @ResponseBody
    public Map login_check(String phone, String password, HttpSession session){
        Map<String,String> result = new HashMap<String, String>();
        User user = service.getUserInfo(phone);
        if(user!=null){
            if (phone.equals(user.getUser_phone())&&MD5Utils.verifyMD5(password,user.getUser_password())){
                if (SessionUtils.setSessionAtt(session,user,"userInfo")==0){
                    result.put("status","ok");
                    result.put("name",user.getUser_name());
                }else {
                    result.put("status","error");
                }

            }else {
                result.put("status","fail");
            }
        }else {
            result.put("status","error");
        }

        return result;
    }

    @RequestMapping("register_check")
    @ResponseBody
    public Map register_check(String phone,String name,String password,String email){

        Map<String,String> result = new HashMap<String, String>();
        if (service.getUserInfo(phone)!=null){
            result.put("status","isExit");
        }else {
            boolean phoneTrue = RegexUtils.checkPhone(phone);
            boolean passwordTrue = RegexUtils.checkPassword(password);
            boolean emailTrue = RegexUtils.checkEmail(email);
            if (phoneTrue&&passwordTrue&&emailTrue){
                User user = new User();
                user.setEmail(email);
                user.setUser_name(name);
                user.setUser_phone(phone);
                user.setUser_password(MD5Utils.EncodeMD5(password));
                if (service.insertNewUser(user)){
                    result.put("status","ok");
                    result.put("name",name);
                }else {
                    result.put("status","fail");
                }
            }else {
                result.put("status","fail");
            }
        }
        return result;
    }

    @RequestMapping("exit_login")
    @ResponseBody
    public String exit_login(String userName,HttpSession session){
        String result;
        User user = (User) session.getAttribute("userInfo");
        if (userName.equals(user.getUser_name())){
            session.removeAttribute("userInfo");
            session.removeAttribute("cartMap");
            result = "LogOut";
        }
        else {
            result = "noUser";
        }
        return result;
    }

    @RequestMapping("syncSession")
    public String synchronous(HttpSession session){
        User user = (User) session.getAttribute("userInfo");
        //得到当前session里的购物车列表存入数据库
        Map<Integer,CartPo> tmpSessionMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");
        List<Cart> dbCarts = cartService.getCartList(user.getUser_id(),-1);
        if (tmpSessionMap!=null){
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(currentTime);
            for (Integer i:tmpSessionMap.keySet()) {
                Cart cart = cartService.shopCartConversion2(dateString,tmpSessionMap.get(i),user.getUser_id());
                int tValue = 0;
                for (int j = 0; j <dbCarts.size() ; j++) {
                    if (cart.getGoods_id()==dbCarts.get(j).getGoods_id()){
                        tValue =  dbCarts.get(j).getGoods_count()+1;
                    }
                }
                if (tValue>0){
                    int tn = tValue;
                    cartService.addCartNum(dateString,tn,cart.getGoods_id());
                }else {
                    cartService.insertCartList(cart);
                }
            }
        }
        return REDIRECT_INDEX;
    }


}
