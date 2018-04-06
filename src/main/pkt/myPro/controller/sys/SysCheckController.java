package myPro.controller.sys;

import myPro.bean.sys.CartPo;
import myPro.bean.sys.User;
import myPro.service.sys.service.SysCartService;
import myPro.service.sys.service.SysCheckService;
import myPro.service.sys.service.SysCompletService;
import myPro.service.sys.service.SysUtilService;
import myPro.utils.sys.SysPageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description: 订单下单模块
 * @date 2018/4/5  21:43
 */

@RequestMapping("pkt")
@Controller
public class SysCheckController implements SysPageManager{

    @Autowired
    SysCheckService service;
    @Autowired
    SysCartService cartService;
    @Autowired
    SysUtilService utilService;


    @RequestMapping("checkOut")
    public String to_check_out(HttpSession session, Model model){
        User user = (User) session.getAttribute("userInfo");
        if (user==null){
            return REDIRECT_INDEX;
        }
        Map<Integer,CartPo> sessionMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");//购物车session
        cartService.addShopCartMethod(user,cartService,utilService,sessionMap,model,session);
        Map<Integer,CartPo> tmpMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");
        int scale = 2;
        int roundingMode = 4;
        List<Float> price = new ArrayList<Float>();
        for (Integer i:tmpMap.keySet()) {
            float tmpPrice = (tmpMap.get(i).getNum())*(tmpMap.get(i).getPrice());
            BigDecimal bd = new BigDecimal((double)tmpPrice);
            bd = bd.setScale(scale,roundingMode);
            price.add(bd.floatValue());
        }
        model.addAttribute("priceLists",price);
        model.addAttribute("user",user);
        return CHECK_OUT;
    }

    @RequestMapping("payForCart")
    @ResponseBody
    public String payCart(String userInfo,HttpSession session){
        boolean br = true;
        User user = (User) session.getAttribute("userInfo");
        int user_id = user.getUser_id();
        Map<Integer,CartPo> sessionMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");
        Timestamp d = new Timestamp(System.currentTimeMillis());
        for (Integer i:sessionMap.keySet()) {
            int num = sessionMap.get(i).getNum();
            int goods_id = sessionMap.get(i).getId();
            int store_id = service.getStoreId(goods_id);
            br = service.insertComment(store_id,user_id,goods_id,num,userInfo,d);
            if (br){

                br = cartService.deleteCart(user_id,goods_id);
                br = service.saleGoodsCount(goods_id,num);
            }
        }
        if (br){
            session.removeAttribute("cartMap");
        }
        return br?"ok":"error";
    }


}
