package myPro.controller.sys;

import myPro.bean.seller.Goods;
import myPro.bean.seller.GoodsPo;
import myPro.bean.sys.CartPo;
import myPro.bean.sys.HisCartPo;
import myPro.bean.sys.HistoryGoods;
import myPro.bean.sys.User;
import myPro.service.sys.service.SysCartService;
import myPro.service.sys.service.SysCompletService;
import myPro.service.sys.service.SysPersonalService;
import myPro.service.sys.service.SysUtilService;
import myPro.utils.sys.SysPageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description: 个人中心模块
 * @date 2018/4/5  21:42
 */

@RequestMapping("pkt")
@Controller
public class SysPersonalController implements SysPageManager{

    @Autowired
    SysPersonalService service;
    @Autowired
    SysCartService cartService;
    @Autowired
    SysUtilService utilService;



    @RequestMapping("personal")
    public String to_personal(HttpSession session, Model model){
        User user = (User) session.getAttribute("userInfo");
        if (user==null){
            return REDIRECT_INDEX;
        }
        Map<Integer,CartPo> sessionMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");//购物车session
        cartService.addShopCartMethod(user,cartService,utilService,sessionMap,model,session);
        Map<Integer,CartPo> tmpMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");
        int scale = 2;
        int roundingMode = 4;
        List<Float> price = new ArrayList<Float>(); //封装session里的购物车列表的价格
        if (tmpMap!=null){
            for (Integer i:tmpMap.keySet()) {
                float tmpPrice = (tmpMap.get(i).getNum())*(tmpMap.get(i).getPrice());
                BigDecimal bd = new BigDecimal((double)tmpPrice);
                bd = bd.setScale(scale,roundingMode);
                price.add(bd.floatValue());
            }
        }

        List<HistoryGoods> historyGoods = service.getHistoryGoods(user.getUser_id(),2);//已经收货订单菜品列表
        List<HistoryGoods> waitGoods = service.getHistoryGoods(user.getUser_id(),1);//待收货订单菜品列表
        List<HistoryGoods> sendGoods = service.getHistoryGoods(user.getUser_id(),0);//待发货订单菜品列表
        List<HisCartPo> historyList = service.getGoodsList(historyGoods,utilService);
        List<HisCartPo> waitList = service.getGoodsList(waitGoods,utilService);
        List<HisCartPo> sendList = service.getGoodsList(sendGoods,utilService);
        model.addAttribute("priceLists",price);
        model.addAttribute("histCart",historyList);
        model.addAttribute("waitCart",waitList);
        model.addAttribute("sendCart",sendList);
        return PERSONAL;
    }

    @RequestMapping("addComment")
    @ResponseBody
    public String addComment(int goods_id,String text,int com_id,HttpSession session){
        boolean br = true;
        User user = (User) session.getAttribute("userInfo");
        int user_id = user.getUser_id();
        if (!service.addComment(user_id,goods_id,com_id,text)){
            br = false;
        }
        return br?"ok":"error";
    }

    @RequestMapping("modifyPass")
    @ResponseBody
    public String modifyPass(String oldPass,String newPass,HttpSession session){
        String result ="error";
        User user = (User) session.getAttribute("userInfo");
        if (user.getUser_password().equals(oldPass)){
            if (service.modifyPass(user.getUser_id(), newPass)){
                result ="ok";
            }
        }
        return  result;
    }

    @RequestMapping("changeGoodsStatus")
    @ResponseBody
    public String changeGoodsStatus(int com_id){
        boolean br = service.changeGoodsStatus(com_id);
        return br?"ok":"error";
    }

    @RequestMapping(value = "checkDelivery/{cid}")
    public String checkDelivery(@PathVariable("cid") int cid, HttpSession session,Model model){
        User user = (User) session.getAttribute("userInfo");
        if (user==null){
            return REDIRECT_INDEX;
        }
        List<String> list = service.returnMapPoint(cid);
        model.addAttribute("buyersLng",list.get(0));
        model.addAttribute("buyersLat",list.get(1));
        model.addAttribute("sellersLng",list.get(2));
        model.addAttribute("sellersLat",list.get(3));
        return CHECK_DELIVERY;
    }

}
