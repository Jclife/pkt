package myPro.service.sys.impl;

import myPro.bean.seller.Goods;
import myPro.bean.seller.GoodsPo;
import myPro.bean.sys.Cart;
import myPro.bean.sys.CartPo;
import myPro.bean.sys.User;
import myPro.dao.sys.SysCartDao;
import myPro.service.sys.service.SysCartService;
import myPro.service.sys.service.SysCompletService;
import myPro.service.sys.service.SysUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
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
 * @date 2018/4/5  22:35
 */
@Service
public class SysCartImpl implements SysCartService {

    @Autowired
    SysCartDao sys;


    public boolean insertCartList(Cart cart) {
        try {
            sys.insertCart(cart);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean addCartNum(Timestamp time,int count, int goods_id) {
        try {
            sys.addCartNum(time,count,goods_id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<Cart> getCartList(int user_id, int goods_id) {
        return sys.getCartLists(user_id,goods_id);
    }


    public Map<Integer, CartPo> shopCartConversion(SysUtilService service, List<Cart> list) {
        List<Integer> goods_idList = new ArrayList<Integer>();
        Map<Integer,CartPo> result = new HashMap<Integer, CartPo>();
        for (int i = 0; i < list.size() ; i++) {
            goods_idList.add(list.get(i).getGoods_id());
        }
        List<Goods> getGoods = new ArrayList<Goods>();
        for (int i = 0; i <goods_idList.size() ; i++) {
            getGoods.add(service.getGoodsInId(goods_idList.get(i),0));
        }
        List<List<String>> jsonList = new ArrayList<List<String>>();
        List<GoodsPo> imgList = service.setImgJsonInIndex(jsonList,getGoods);
        for (int i = 0; i <getGoods.size() ; i++) {
            CartPo cartPo = new CartPo();
            cartPo.setNum(list.get(i).getGoods_count());
            cartPo.setImgs(imgList.get(i).getList().get(0));
            cartPo.setName(getGoods.get(i).getGoods_name());
            cartPo.setPrice(getGoods.get(i).getGoods_price_now());
            cartPo.setId(getGoods.get(i).getGoods_id());
            result.put(list.get(i).getGoods_id(),cartPo);
        }
        return result;
    }

    public Cart shopCartConversion2(Timestamp time,CartPo cartPo,int user_id) {
        Cart cart = new Cart();
        cart.setUser_id(user_id);
        cart.setAdd_time(time);
        cart.setGoods_id(cartPo.getId());
        cart.setGoods_count(cartPo.getNum());
        return cart;
    }


    public void addShopCartMethod( User user, SysCartService service,SysUtilService utilService, Map<Integer, CartPo> sessionMap,Model model,HttpSession session) {
        if (user!=null&&user.getUser_name()!=null&&!"".equals(user.getUser_name())){
            model.addAttribute("userName",user.getUser_name());
            model.addAttribute("userEmail",user.getEmail());
            model.addAttribute("userPhone",user.getUser_phone());
            List<Cart> cartList = service.getCartList(user.getUser_id(),-1);
            Map<Integer,CartPo> dbMap = service.shopCartConversion(utilService,cartList);
            if (sessionMap==null&&dbMap!=null){
                sessionMap = dbMap;
            }else if (sessionMap==null&&dbMap==null){
                sessionMap=null;
            } else if (dbMap!=null&&sessionMap!=null){
                sessionMap.putAll(dbMap);
            }
            session.setAttribute("cartMap",sessionMap);
        }
        if (sessionMap==null){
            model.addAttribute("cartListsNum",0);
        }else {
            model.addAttribute("cartListsNum",sessionMap.size());
            model.addAttribute("cartLists",sessionMap);
            float addPrice = 0.00f;
            for (Integer i:sessionMap.keySet()) {
                float tmpPrice;
                tmpPrice = (sessionMap.get(i).getNum())*(sessionMap.get(i).getPrice());
                addPrice +=tmpPrice;
            }
            int scale = 2;
            int roundingMode = 4;
            BigDecimal bd = new BigDecimal((double)addPrice);
            bd = bd.setScale(scale,roundingMode);
            addPrice = bd.floatValue();
            model.addAttribute("addPrice",addPrice);
        }
    }

    public boolean deleteCart(int user_id, int goods_id) {
        try {
            sys.deleteCart(user_id,goods_id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateCartNum(int user_id, int goods_id, int num) {

        try {
            sys.updateCartList(user_id,goods_id,num);
            return true;
        }catch (Exception e){

            return false;
        }
    }
}
