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
        Map<Integer,CartPo> sessionMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");//购物车session
        cartService.addShopCartMethod(user,cartService,utilService,sessionMap,model,session);
        Map<Integer,CartPo> tmpMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");
        int scale = 2;
        int roundingMode = 4;
        List<Float> price = new ArrayList<Float>();
        if (tmpMap!=null){
            for (Integer i:tmpMap.keySet()) {
                float tmpPrice = (tmpMap.get(i).getNum())*(tmpMap.get(i).getPrice());
                BigDecimal bd = new BigDecimal((double)tmpPrice);
                bd = bd.setScale(scale,roundingMode);
                price.add(bd.floatValue());
            }
        }
        List<HistoryGoods> historyGoods = service.getHistoryGoods(user.getUser_id());
        List<Integer> goods_idList = new ArrayList<Integer>();
        List<HisCartPo> result = new ArrayList<HisCartPo>();
        for (int i = 0; i < historyGoods.size() ; i++) {
            goods_idList.add(historyGoods.get(i).getGoods_id());
        }
        List<Goods> getGoods = new ArrayList<Goods>();
        for (int i = 0; i <goods_idList.size() ; i++) {
            getGoods.add(utilService.getGoodsInId(goods_idList.get(i),1));
        }
        List<List<String>> jsonList = new ArrayList<List<String>>();
        List<GoodsPo> imgList = utilService.setImgJsonInIndex(jsonList,getGoods);
        for (int i = getGoods.size()-1; i >=0 ; i--) {
            HisCartPo cartPo = new HisCartPo();
            cartPo.setNum(historyGoods.get(i).getGoods_count());
            cartPo.setImgs(imgList.get(i).getList().get(0));
            cartPo.setName(getGoods.get(i).getGoods_name());
            cartPo.setPrice(getGoods.get(i).getGoods_price_now());
            cartPo.setId(getGoods.get(i).getGoods_id());
            cartPo.setContent(historyGoods.get(i).getContent());
            cartPo.setCom_id(historyGoods.get(i).getCom_id());
            result.add(cartPo);
        }
        List<Float> hisPrice = new ArrayList<Float>();
        for (int i = 0; i < result.size(); i++) {
            float tmpPrice= (result.get(i).getNum())*(result.get(i).getPrice());
            BigDecimal bd = new BigDecimal((double)tmpPrice);
            bd = bd.setScale(scale,roundingMode);
            hisPrice.add(bd.floatValue());
        }
        model.addAttribute("priceLists",price);
        model.addAttribute("hisPrice",hisPrice);
        model.addAttribute("histCart",result);
        model.addAttribute("number",result.size());
        return PERSIONAL;
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

}
