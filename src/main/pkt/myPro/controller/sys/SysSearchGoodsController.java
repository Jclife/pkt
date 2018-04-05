package myPro.controller.sys;

import myPro.bean.seller.Goods;
import myPro.bean.seller.GoodsPo;
import myPro.bean.seller.ShopPo;
import myPro.bean.sys.CartPo;
import myPro.bean.sys.User;
import myPro.service.sys.service.*;
import myPro.utils.sys.SysPageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description: 搜索菜品模块
 * @date 2018/4/5  21:42
 */

@RequestMapping("pkt")
@Controller
public class SysSearchGoodsController  implements SysPageManager{

    @Autowired
    SysSearchGoodsService service;
    @Autowired
    SysUtilService utilService;
    @Autowired
    SysShopService shopService;
    @Autowired
    SysCartService cartService;


    @RequestMapping(value = "search/{goodsName}")
    public String to_shop_conditions(HttpSession session, Model model,
                                     @PathVariable("goodsName") String goodsName){
        List<Goods> searchGoods;
        searchGoods = service.searchLists(goodsName);
        List<List<String>> jsonList = new ArrayList<List<String>>();
        List<GoodsPo> resultList = utilService.setImgJsonInIndex(jsonList,searchGoods);
        List<ShopPo> otherGoods = shopService.getShopLists(5,-1,1,0);
        List<Goods> otherList=otherGoods.get(0).getAllList();
        List<List<String>> otherJsonList = new ArrayList<List<String>>();
        List<GoodsPo> otherResultList = utilService.setImgJsonInIndex(otherJsonList,otherList);
        model.addAttribute("otherResultList",otherResultList);
        model.addAttribute("result",resultList);
        Map<String,Integer> numberMap = new HashMap<String, Integer>();
        numberMap.put("allNum",searchGoods.size());
        model.addAttribute("number",numberMap);
        User user = (User) session.getAttribute("userInfo");
        Map<Integer,CartPo> sessionMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");//购物车session
        cartService.addShopCartMethod(user,cartService,utilService,sessionMap,model,session);
        return SHOP;
    }

    @RequestMapping("search_exit")
    @ResponseBody
    public String searchExit(String goods){
        List<Goods> searchGoods;
        searchGoods = service.searchLists(goods);
        return searchGoods.size()>0?"success":"error";
    }
}
