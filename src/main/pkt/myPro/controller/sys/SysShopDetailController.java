package myPro.controller.sys;

import myPro.bean.seller.Goods;
import myPro.bean.seller.GoodsPo;
import myPro.bean.seller.ShopPo;
import myPro.bean.sys.CartPo;
import myPro.bean.sys.HistoryGoods;
import myPro.bean.sys.User;
import myPro.service.sys.service.*;
import myPro.utils.sys.SysPageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description: 菜品详情页面模块
 * @date 2018/4/5  21:42
 */

@RequestMapping("pkt")
@Controller
public class SysShopDetailController implements SysPageManager{

    @Autowired
    SysShopDetailService service;
    @Autowired
    SysCartService cartService;
    @Autowired
    SysUtilService utilService;
    @Autowired
    SysShopService shopService;
    @Autowired
    SysUserLoginAndRegisterService lrService;

    @RequestMapping(value = "shopDetail/{goods_id}")
    public String to_shop_detail(HttpSession session, Model model, @PathVariable("goods_id") int goods_id){
        List<ShopPo> allList = shopService.getShopLists(-1,-1,0,0);
        Goods goods = utilService.getGoodsInId(goods_id,0);
        List<String> list = service.shopDetailImgLists(goods);
        List<ShopPo> otherGoods = shopService.getShopLists(5,-1,1,0);
        List<Goods> otherList=otherGoods.get(0).getAllList();
        int type = goods.getGoods_classify();
        List<ShopPo> aboutGoods = shopService.getShopLists(4,-1,1,type);
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
        List<GoodsPo> otherResult = utilService.setImgJsonInIndex(otherImg,otherList);
        Map<String,Integer> NumResult = shopService.numberResultShop(allList,5);
        List<List<String>> aboutImg = new ArrayList<List<String>>();
        List<GoodsPo> aboutResult = utilService.setImgJsonInIndex(aboutImg,aboutList);
        List<HistoryGoods> shopComment = service.getShopComment(goods_id);
        List<User> userComment = new ArrayList<User>();
        for (int i = 0; i < shopComment.size(); i++) {
            userComment.add(i,lrService.getUserInId(shopComment.get(i).getUser_id()));
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
        cartService.addShopCartMethod(user,cartService,utilService,sessionMap,model,session);
        return SHOP_DETAIL;
    }

}
