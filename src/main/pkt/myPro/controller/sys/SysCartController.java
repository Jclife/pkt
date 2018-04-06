package myPro.controller.sys;

import myPro.bean.seller.Goods;
import myPro.bean.seller.GoodsPo;
import myPro.bean.sys.Cart;
import myPro.bean.sys.CartPo;
import myPro.bean.sys.User;
import myPro.service.sys.service.SysCartService;
import myPro.service.sys.service.SysUtilService;
import myPro.utils.sys.SysPageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description: 购物车模块
 * @date 2018/4/5  21:42
 */

@RequestMapping("pkt")
@Controller
public class SysCartController implements SysPageManager{

    @Autowired
    SysCartService service;
    @Autowired
    SysUtilService utilService;


    @RequestMapping("cart")
    public String to_cart(HttpSession session, Model model){
        User user = (User) session.getAttribute("userInfo");
        if (user==null){
            return INDEX;
        }
        Map<Integer,CartPo> sessionMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");//购物车session
        service.addShopCartMethod(user,service,utilService,sessionMap,model,session);
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

    @RequestMapping("shopCartList")
    @ResponseBody
    public CartPo shopCartList(HttpSession session, int id,int addNum){

        User user = (User) session.getAttribute("userInfo");

        CartPo cartPo = new CartPo();
        if (user!=null&&user.getUser_name()!=null&&!"".equals(user.getUser_name())){//已经登录的情况下
            Goods goods = utilService.getGoodsInId(id,0);//得到添加的菜品
            List<Goods> list = new ArrayList<Goods>();
            list.add(goods);
            List<List<String>> jsonList = new ArrayList<List<String>>();
            List<GoodsPo> resultList = utilService.setImgJsonInIndex(jsonList,list);//得到封装好的物品list
            Map<Integer,CartPo> sessionMap = new HashMap<Integer, CartPo>();//存放session里存在的购物车列表
            int num = addNum;
            int goods_id = resultList.get(0).getGoods().getGoods_id();
            String img = resultList.get(0).getList().get(0);
            String name = resultList.get(0).getGoods().getGoods_name();
            float price= resultList.get(0).getGoods().getGoods_price_now();
            cartPo.setImgs(img);
            cartPo.setName(name);
            cartPo.setNum(num);
            cartPo.setPrice(price);
            cartPo.setId(id);
            sessionMap.put(goods_id,cartPo);

            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(currentTime);

            if (session.getAttribute("cartMap")==null) {
                session.setAttribute("cartMap",sessionMap);
                Cart cart = new Cart();
                cart.setAdd_time(dateString);
                cart.setGoods_count(num);
                cart.setGoods_id(goods_id);
                cart.setUser_id(user.getUser_id());
                service.insertCartList(cart);
            }else {
                boolean isExit = false;
                int tmpExitId = 0;
                Map<Integer,CartPo> tmpSessionMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");
                for (Integer i:tmpSessionMap.keySet()) {
                    if (i==goods_id){
                        isExit = true;
                        tmpExitId = i;
                    }
                }
                if (isExit){
                    int n = tmpSessionMap.get(tmpExitId).getNum();
                    int tn = n+addNum;
                    tmpSessionMap.get(tmpExitId).setNum(tn) ;
                    tmpSessionMap.put(tmpExitId,tmpSessionMap.get(tmpExitId));
                    service.addCartNum(dateString,tn,tmpExitId);
                }else {
                    tmpSessionMap.put(goods_id,cartPo);
                    Cart cart = new Cart();
                    cart.setAdd_time(dateString);
                    cart.setGoods_count(num);
                    cart.setGoods_id(goods_id);
                    cart.setUser_id(user.getUser_id());
                    service.insertCartList(cart);
                }
            }
        }else {
            Goods goods = utilService.getGoodsInId(id,0);
            List<Goods> list = new ArrayList<Goods>();
            list.add(goods);
            List<List<String>> jsonList = new ArrayList<List<String>>();
            List<GoodsPo> resultList = utilService.setImgJsonInIndex(jsonList,list);
            Map<Integer,CartPo> sessionMap = new HashMap<Integer, CartPo>();
            int num = addNum;
            int goods_id = resultList.get(0).getGoods().getGoods_id();
            String img = resultList.get(0).getList().get(0);
            String name = resultList.get(0).getGoods().getGoods_name();
            float price= resultList.get(0).getGoods().getGoods_price_now();
            cartPo.setImgs(img);
            cartPo.setName(name);
            cartPo.setNum(num);
            cartPo.setPrice(price);
            cartPo.setId(id);
            sessionMap.put(goods_id,cartPo);
            if (session.getAttribute("cartMap")==null) {
                session.setAttribute("cartMap",sessionMap);
            }else {
                boolean isExit = false;
                int tmpExitId = 0;
                Map<Integer,CartPo> tmpSessionMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");
                for (Integer i:tmpSessionMap.keySet()) {
                    if (i==goods_id){
                        isExit = true;
                        tmpExitId = i;
                    }
                }
                if (isExit){
                    int n = tmpSessionMap.get(tmpExitId).getNum();
                    int tn = n+addNum;
                    tmpSessionMap.get(tmpExitId).setNum(tn) ;
                    tmpSessionMap.put(tmpExitId,tmpSessionMap.get(tmpExitId));
                }else {
                    tmpSessionMap.put(goods_id,cartPo);
                }
            }
        }
        return cartPo;
    }


    @RequestMapping("deleteCart")
    @ResponseBody
    public String shopCartList(int id, HttpSession session){
        String result;
        Map<Integer,CartPo> sessionMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");
        sessionMap.remove(id);
        result = "success";
        User user = (User) session.getAttribute("userInfo");
        if (user!=null&&user.getUser_name()!=null&&!"".equals(user.getUser_name())){
            if (service.deleteCart(user.getUser_id(), id)){
                result = "success";
            }else {
                result = "error";
            }
        }
        return result;
    }

    /**
     * 购物车页面的数字改变
     * @param id
     * @param num
     * @param session
     * @return
     */
    @RequestMapping("changeNum")
    @ResponseBody
    public String cartChange(int id,int num,HttpSession session){
        String result = "error";
        User user = (User) session.getAttribute("userInfo");
        if (service.updateCartNum(user.getUser_id(),id,num)){
            result = "success";
        }
        return result;
    }


}
