package myPro.controller.sys;

import myPro.bean.seller.Goods;
import myPro.bean.seller.GoodsPo;
import myPro.bean.seller.ShopPo;
import myPro.bean.sys.CartPo;
import myPro.bean.sys.User;
import myPro.service.sys.service.SysCartService;
import myPro.service.sys.service.SysCompletService;
import myPro.service.sys.service.SysShopService;
import myPro.service.sys.service.SysUtilService;
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
 * @Description: 所有菜品购物页面模块
 * @date 2018/4/5  21:42
 */

@RequestMapping("pkt")
@Controller
public class SysShopController implements SysPageManager{

    @Autowired
    SysShopService service;
    @Autowired
    SysUtilService utilService;
    @Autowired
    SysCartService cartService;


    @RequestMapping("shop")
    public String to_shop(HttpSession session, Model model){
        User user = (User) session.getAttribute("userInfo");
        List<ShopPo> allList = service.getShopLists(-1,-1,0,0);
        List<ShopPo> list = service.getShopLists(6,-1,0,0);
        List<ShopPo> otherGoods = service.getShopLists(5,-1,1,0);
        Map<String,Integer> NumResult = service.numberResultShop(allList,5);
        List<Goods> allGoods=list.get(0).getAllList();
        List<List<String>> jsonList = new ArrayList<List<String>>();
        List<GoodsPo> resultList = utilService.setImgJsonInIndex(jsonList,allGoods);
        List<Goods> otherList=otherGoods.get(0).getAllList();
        List<List<String>> otherJsonList = new ArrayList<List<String>>();
        List<GoodsPo> otherResultList = utilService.setImgJsonInIndex(otherJsonList,otherList);
        model.addAttribute("result",resultList);
        model.addAttribute("otherResultList",otherResultList);
        model.addAttribute("number",NumResult);
        Map<Integer,CartPo> sessionMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");//购物车session
        cartService.addShopCartMethod(user,cartService,utilService,sessionMap,model,session);
        return SHOP;
    }

    /**
     * 商品展示页面的查询controller
     * @param session
     * @param model
     * @param type
     * @param page
     * @return
     */
    @RequestMapping(value = "shop/{type}/{page}/{limit}")
    public String to_shop_conditions(HttpSession session, Model model,
                                     @PathVariable("type") int type,
                                     @PathVariable("page") int page,
                                     @PathVariable("limit") int limit){
        //得到所有的商品信息
        List<ShopPo> allList = service.getShopLists(-1,-1,0,0);
        List<ShopPo> typeList = service.getShopLists((limit*(page-1)),limit,0,0);
        List<ShopPo> otherGoods = service.getShopLists(5,-1,1,0);
        Map<String,Integer> NumResult = service.numberResultShop(allList,type);
        List<Goods> otherList=otherGoods.get(0).getAllList();
        List<List<String>> otherJsonList = new ArrayList<List<String>>();
        List<GoodsPo> otherResultList = utilService.setImgJsonInIndex(otherJsonList,otherList);
        List<Goods> tmpGoods;
        switch (type){
            case 1:
                tmpGoods = typeList.get(0).getMeatList();
                break;
            case 2:
                tmpGoods = typeList.get(0).getSoupList();
                break;
            case 3:
                tmpGoods = typeList.get(0).getStarchList();
                break;
            case 4:
                tmpGoods = typeList.get(0).getHotPotList();
                break;
            default:
                tmpGoods = typeList.get(0).getAllList();
                break;
        }
        List<List<String>> jsonList = new ArrayList<List<String>>();
        List<GoodsPo> resultList = utilService.setImgJsonInIndex(jsonList,tmpGoods);
        model.addAttribute("result",resultList);
        model.addAttribute("otherResultList",otherResultList);
        model.addAttribute("number",NumResult);
        User user = (User) session.getAttribute("userInfo");
        Map<Integer,CartPo> sessionMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");//购物车session
        cartService.addShopCartMethod(user,cartService,utilService,sessionMap,model,session);
        return SHOP;
    }


}
