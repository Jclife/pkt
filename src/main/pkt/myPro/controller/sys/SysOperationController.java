package myPro.controller.sys;

import myPro.bean.seller.ShopPo;
import myPro.bean.sys.Cart;
import myPro.bean.sys.CartPo;
import myPro.bean.seller.Goods;
import myPro.bean.seller.GoodsPo;
import myPro.bean.sys.User;
import myPro.service.sys.service.SysCompletService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/2/24  15:13
 */
@RequestMapping("pkt")
@Controller
public class SysOperationController {


    @Autowired
    SysCompletService service;

    @RequestMapping("shopCartList")
    @ResponseBody
    public CartPo shopCartList(HttpSession session, int id,int addNum){

        User user = (User) session.getAttribute("userInfo");
        CartPo cartPo = new CartPo();
        if (user!=null&&user.getUser_name()!=null&&!"".equals(user.getUser_name())){
            Goods goods = service.getGoodsInId(id);
            List<Goods> list = new ArrayList<Goods>();
            list.add(goods);
            List<List<String>> jsonList = new ArrayList<List<String>>();
            List<GoodsPo> resultList = service.setImgJsonInIndex(jsonList,list);
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
            Timestamp d = new Timestamp(System.currentTimeMillis());
            if (session.getAttribute("cartMap")==null) {
                session.setAttribute("cartMap",sessionMap);
                Cart cart = new Cart();
                cart.setAdd_time(d);
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
                    service.addCartNum(d,tn,tmpExitId);
                }else {
                    tmpSessionMap.put(goods_id,cartPo);
                    Cart cart = new Cart();
                    cart.setAdd_time(d);
                    cart.setGoods_count(num);
                    cart.setGoods_id(goods_id);
                    cart.setUser_id(user.getUser_id());
                    service.insertCartList(cart);
                }
            }
        }else {
            Goods goods = service.getGoodsInId(id);
            List<Goods> list = new ArrayList<Goods>();
            list.add(goods);
            List<List<String>> jsonList = new ArrayList<List<String>>();
            List<GoodsPo> resultList = service.setImgJsonInIndex(jsonList,list);
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
            br = service.insertComment(user_id,goods_id,num,userInfo,d);
            if (br){
                br = service.deleteCart(user_id,goods_id);
                br = service.saleGoodsCount(goods_id,sessionMap.get(i).getNum());
            }
        }
        if (br){
            session.removeAttribute("cartMap");
        }
        return br?"ok":"error";
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
