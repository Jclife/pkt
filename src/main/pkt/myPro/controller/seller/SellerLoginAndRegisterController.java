package myPro.controller.seller;

import myPro.bean.seller.Store;
import myPro.service.seller.service.SellerLoginAndRegisterService;
import myPro.utils.common.SessionUtils;
import myPro.utils.seller.SellerPageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/2/5  22:01
 */
@RequestMapping("seller")
@Controller
public class SellerLoginAndRegisterController implements SellerPageManager{

    @Autowired
    SellerLoginAndRegisterService seller;

    @RequestMapping("login")
    public String login(){
        return LOGIN;
    }


    @RequestMapping("register")
    public String register(){
        return REGISTER;
    }

    @RequestMapping("login_check")
    @ResponseBody
    public Map login_check(String account, String password,HttpSession session){
        Map<String,String> result = new HashMap<String, String>();
        Store store = seller.getStoreInfo(account);
        if (store!=null){
            if (password.equals(store.getStore_password())){
                result.put("status","success");
                if (session.getAttribute("userInfo")!=null){
                    session.removeAttribute("userInfo");
                }
                SessionUtils.setSessionAtt(session,store,"sellerInfo");
            }else {
                result.put("status","error");
            }
        }else {
            result.put("status","error");
        }
        return result;
    }

    @RequestMapping("to_register")
    @ResponseBody
    public Map register(Store store){
        Map<String,String> result = new HashMap<String, String>();
        if (seller.insertNewStore(store)){
            result.put("status","success");
        }else {
            result.put("status","error");
        }
        return result;
    }

    @RequestMapping("sellerExit")
    @ResponseBody
    public String sellerExit(HttpSession session,String storeName){
        String result;
        if (storeName.equals(SessionUtils.getSessionAtt(session,"sellerInfo","store_name"))){
            session.removeAttribute("sellerInfo");
            result =  "LogOut";
        }else {
            result = "noUser";
        }
        return result;
    }

    @RequestMapping("forget_pass")
    public String forgetPass(){
        return FORGET_PASS;
    }

}