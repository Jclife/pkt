package myPro.controller.sys;

import myPro.bean.sys.CartPo;
import myPro.bean.seller.Goods;
import myPro.bean.seller.GoodsPo;
import myPro.bean.seller.ShopPo;
import myPro.bean.sys.HisCartPo;
import myPro.bean.sys.HistoryGoods;
import myPro.bean.sys.User;
import myPro.service.sys.service.SysCompletService;
import myPro.utils.sys.SysPageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/2/1  22:09
 */

@RequestMapping("pkt")
@Controller
public class SysGoToController implements SysPageManager {

    @Autowired
    SysCompletService service;

    @RequestMapping("index")
    public String to_index(HttpSession session, Model model){
        List<Goods> list = service.getAllRecoGoodsInIndex(8,-1); //得到8个goods列表
        model.addAttribute("goodsList",list);
        List<List<String>> jsonList = new ArrayList<List<String>>();
        List<GoodsPo> resultList = service.setImgJsonInIndex(jsonList,list);
        model.addAttribute("result",resultList);
        User user = (User) session.getAttribute("userInfo");
        Map<Integer,CartPo> sessionMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");//购物车session
        service.addShopCartMethod(user,service,sessionMap,model,session);
        return INDEX;
    }

    @RequestMapping("about")
    public String to_about_us(HttpSession session, Model model){
        User user = (User) session.getAttribute("userInfo");
        Map<Integer,CartPo> sessionMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");//购物车session
        service.addShopCartMethod(user,service,sessionMap,model,session);
        return ABOUT_US;
    }

    @RequestMapping("cart")
    public String to_cart(HttpSession session, Model model){
        User user = (User) session.getAttribute("userInfo");
        if (user==null){
            return INDEX;
        }
        Map<Integer,CartPo> sessionMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");//购物车session
        service.addShopCartMethod(user,service,sessionMap,model,session);
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
        return CART;
    }

    @RequestMapping("checkOut")
    public String to_check_out(HttpSession session, Model model){
        User user = (User) session.getAttribute("userInfo");
        Map<Integer,CartPo> sessionMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");//购物车session
        service.addShopCartMethod(user,service,sessionMap,model,session);
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
        return CHECK_OUT;
    }

    @RequestMapping("shop")
    public String to_shop(HttpSession session, Model model){
        User user = (User) session.getAttribute("userInfo");
        List<ShopPo> allList = service.getShopLists(-1,-1,0,0);
        List<ShopPo> list = service.getShopLists(6,-1,0,0);
        List<ShopPo> otherGoods = service.getShopLists(5,-1,1,0);
        Map<String,Integer> NumResult = service.numberResultShop(allList,5);
        List<Goods> allGoods=list.get(0).getAllList();
        List<List<String>> jsonList = new ArrayList<List<String>>();
        List<GoodsPo> resultList = service.setImgJsonInIndex(jsonList,allGoods);
        List<Goods> otherList=otherGoods.get(0).getAllList();
        List<List<String>> otherJsonList = new ArrayList<List<String>>();
        List<GoodsPo> otherResultList = service.setImgJsonInIndex(otherJsonList,otherList);
        model.addAttribute("result",resultList);
        model.addAttribute("otherResultList",otherResultList);
        model.addAttribute("number",NumResult);
        Map<Integer,CartPo> sessionMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");//购物车session
        service.addShopCartMethod(user,service,sessionMap,model,session);
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
        List<GoodsPo> otherResultList = service.setImgJsonInIndex(otherJsonList,otherList);
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
        List<GoodsPo> resultList = service.setImgJsonInIndex(jsonList,tmpGoods);
        model.addAttribute("result",resultList);
        model.addAttribute("otherResultList",otherResultList);
        model.addAttribute("number",NumResult);
        User user = (User) session.getAttribute("userInfo");
        Map<Integer,CartPo> sessionMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");//购物车session
        service.addShopCartMethod(user,service,sessionMap,model,session);
        return SHOP;
    }

    @RequestMapping(value = "search/{goodsName}")
    public String to_shop_conditions(HttpSession session, Model model,
                                     @PathVariable("goodsName") String goodsName){
        List<Goods> searchGoods;
        searchGoods = service.searchLists(goodsName);
        List<List<String>> jsonList = new ArrayList<List<String>>();
        List<GoodsPo> resultList = service.setImgJsonInIndex(jsonList,searchGoods);
        List<ShopPo> otherGoods = service.getShopLists(5,-1,1,0);
        List<Goods> otherList=otherGoods.get(0).getAllList();
        List<List<String>> otherJsonList = new ArrayList<List<String>>();
        List<GoodsPo> otherResultList = service.setImgJsonInIndex(otherJsonList,otherList);
        model.addAttribute("otherResultList",otherResultList);
        model.addAttribute("result",resultList);
        Map<String,Integer> numberMap = new HashMap<String, Integer>();
        numberMap.put("allNum",searchGoods.size());
        model.addAttribute("number",numberMap);
        User user = (User) session.getAttribute("userInfo");
        Map<Integer,CartPo> sessionMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");//购物车session
        service.addShopCartMethod(user,service,sessionMap,model,session);
        return SHOP;
    }


    @RequestMapping(value = "shopDetail/{goods_id}")
    public String to_shop_detail(HttpSession session, Model model,@PathVariable("goods_id") int goods_id){
        List<ShopPo> allList = service.getShopLists(-1,-1,0,0);
        Goods goods = service.getGoodsInId(goods_id,0);
        List<String> list = service.shopDetailImgLists(goods);
        List<ShopPo> otherGoods = service.getShopLists(5,-1,1,0);
        List<Goods> otherList=otherGoods.get(0).getAllList();
        int type = goods.getGoods_classify();
        List<ShopPo> aboutGoods = service.getShopLists(4,-1,1,type);
        List<Goods> aboutList = new ArrayList<Goods>();
        switch (type){
            case 1:
                aboutList = aboutGoods.get(0).getMeatList();
                break;
            case 2:
                aboutList = aboutGoods.get(0).getSoupList();
                break;
            case 3:
                aboutList = aboutGoods.get(0).getStarchList();
                break;
            case 4:
                aboutList = aboutGoods.get(0).getHotPotList();
                break;
        }

        List<List<String>> otherImg = new ArrayList<List<String>>();
        List<GoodsPo> otherResult = service.setImgJsonInIndex(otherImg,otherList);
        Map<String,Integer> NumResult = service.numberResultShop(allList,5);
        List<List<String>> aboutImg = new ArrayList<List<String>>();
        List<GoodsPo> aboutResult = service.setImgJsonInIndex(aboutImg,aboutList);
        List<HistoryGoods> shopComment = service.getShopComment(goods_id);
        List<User> userComment = new ArrayList<User>();
        for (int i = 0; i < shopComment.size(); i++) {
            userComment.add(i,service.getUserToComment(shopComment.get(i).getUser_id()));
        }
        model.addAttribute("shopComment",shopComment);
        model.addAttribute("userComment",userComment);
        model.addAttribute("commentNum",userComment.size());
        model.addAttribute("number",NumResult);
        model.addAttribute("otherResult",otherResult); // 其他
        model.addAttribute("aboutResult",aboutResult); //相关
        model.addAttribute("img",list);
        model.addAttribute("goods",goods);
        User user = (User) session.getAttribute("userInfo");
        Map<Integer,CartPo> sessionMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");//购物车session
        service.addShopCartMethod(user,service,sessionMap,model,session);
        return SHOP_DETAIL;
    }

    @RequestMapping("login")
    public String to_login(){
        return LOGIN;
    }


    @RequestMapping("personal")
    public String to_personal(HttpSession session,Model model){
        User user = (User) session.getAttribute("userInfo");
        Map<Integer,CartPo> sessionMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");//购物车session
        service.addShopCartMethod(user,service,sessionMap,model,session);
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
            getGoods.add(service.getGoodsInId(goods_idList.get(i),1));
        }
        List<List<String>> jsonList = new ArrayList<List<String>>();
        List<GoodsPo> imgList = service.setImgJsonInIndex(jsonList,getGoods);
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

}
