package myPro.controller.sys;

import myPro.bean.sys.CartPo;
import myPro.bean.sys.User;
import myPro.service.sys.service.SysCartService;
import myPro.service.sys.service.SysCompletService;
import myPro.service.sys.service.SysUtilService;
import myPro.utils.sys.SysPageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description: 关于我们模块
 * @date 2018/4/5  21:45
 */

@RequestMapping("pkt")
@Controller
public class SysAboutController implements SysPageManager{

    @Autowired
    SysCartService service;
    @Autowired
    SysUtilService utilService;

    @RequestMapping("about")
    public String to_about_us(HttpSession session, Model model){
        User user = (User) session.getAttribute("userInfo");
        Map<Integer,CartPo> sessionMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");//购物车session
        service.addShopCartMethod(user,service,utilService,sessionMap,model,session);
        return ABOUT_US;
    }
}
