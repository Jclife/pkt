package myPro.service.sys.impl;

import myPro.bean.seller.Goods;
import myPro.bean.seller.GoodsPo;
import myPro.bean.seller.ShopPo;
import myPro.bean.sys.Cart;
import myPro.bean.sys.CartPo;
import myPro.bean.sys.HistoryGoods;
import myPro.bean.sys.User;
import myPro.dao.sys.SysCompleteDao;
import myPro.service.sys.service.SysCompletService;
import myPro.utils.common.DataConversion;
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
 * @date 2018/2/22  18:23
 */
@Service
public class SysCompletImpl implements SysCompletService {

    @Autowired
    SysCompleteDao completeDao;

    public List<Goods> getAllRecoGoodsInIndex(int limit,int last) {
        return completeDao.getAllRecoGoodsInIndex(limit,last);
    }

    /**
     * 对主界面商品展示的封装
     * @param jsonList
     * @param list
     * @return
     */
    public List<GoodsPo> setImgJsonInIndex(List<List<String>> jsonList, List<Goods> list) {
        List<GoodsPo> resultList = new ArrayList<GoodsPo>();
        for (int i = 0; i <list.size() ; i++) {
            jsonList.add(DataConversion.jsonToList(list.get(i).getGoods_imgs()));
        }
        for (int i = 0; i <jsonList.size() ; i++) {
            GoodsPo goods = new GoodsPo();
            goods.setGoods(list.get(i));
            if (list.get(i).getGoods_price_now()==list.get(i).getGoods_price()){
                goods.setPromotion(false);
            }else {
                goods.setPromotion(true);
            }
            if (list.get(i).getGoods_sale_count()>=100){
                goods.setHotSale(true);
            }else {
                goods.setHotSale(false);
            }
            if (jsonList.get(i)!=null){
                if (jsonList.get(i).size()>1){
                    List<String> tmpList=new ArrayList<String>();
                    tmpList.add(jsonList.get(i).get(0));
                    goods.setList(tmpList);
                    tmpList=null;
                }else {
                    goods.setList(jsonList.get(i));
                }
            }
            resultList.add(goods);
        }
        return resultList;
    }

    public List<ShopPo> getShopLists(int limit, int last,int order,int type) {

        List<ShopPo> lists = new ArrayList<ShopPo>();
        ShopPo tmpShop = new ShopPo();
        if (type==0){
            List<Goods> meatGoods = completeDao.getShopLists(limit,last,1,order);
            tmpShop.setMeatList(meatGoods);
            List<Goods> soupGoods = completeDao.getShopLists(limit,last,2,order);
            tmpShop.setSoupList(soupGoods);
            List<Goods> starchGoods = completeDao.getShopLists(limit,last,3,order);
            tmpShop.setStarchList(starchGoods);
            List<Goods> hotPotGoods = completeDao.getShopLists(limit,last,4,order);
            tmpShop.setHotPotList(hotPotGoods);
            List<Goods> allGoods = completeDao.getShopLists(limit,last,5,order);
            tmpShop.setAllList(allGoods);
        }else {
            switch (type){
                case 1:
                    List<Goods> meatGoods = completeDao.getShopLists(limit,last,1,order);
                    tmpShop.setMeatList(meatGoods);
                    break;
                case 2:
                    List<Goods> soupGoods = completeDao.getShopLists(limit,last,2,order);
                    tmpShop.setSoupList(soupGoods);
                    break;
                case 3:
                    List<Goods> starchGoods = completeDao.getShopLists(limit,last,3,order);
                    tmpShop.setStarchList(starchGoods);
                    break;
                case 4:
                    List<Goods> hotPotGoods = completeDao.getShopLists(limit,last,4,order);
                    tmpShop.setHotPotList(hotPotGoods);
                    break;
                default:
                    List<Goods> allGoods = completeDao.getShopLists(limit,last,5,order);
                    tmpShop.setAllList(allGoods);
                    break;
            }
        }
        lists.add(0,tmpShop);
        return lists;
    }

    public int meatListNum(List<ShopPo> list) {
        return list.get(0).getMeatList().size();
    }

    public int soupListNum(List<ShopPo> list) {
        return list.get(0).getSoupList().size();
    }

    public int starchListNum(List<ShopPo> list) {
        return list.get(0).getStarchList().size();
    }

    public int hotPotListNum(List<ShopPo> list) {
        return list.get(0).getHotPotList().size();
    }

    public Map<String, Integer> numberResultShop(List<ShopPo> list,int mark) {
        int meatNum = meatListNum(list);
        int soupNum = soupListNum(list);
        int starchNum = starchListNum(list);
        int hotPotNum = hotPotListNum(list);
        Map<String,Integer> NumResult = new HashMap<String, Integer>();
        NumResult.put("meatNum",meatNum);
        NumResult.put("soupNum",soupNum);
        NumResult.put("starchNum",starchNum);
        NumResult.put("hotPotNum",hotPotNum);
        switch (mark){
            case 1:
                NumResult.put("allNum",meatNum);
                break;
            case 2:
                NumResult.put("allNum",soupNum);
                break;
            case 3:
                NumResult.put("allNum",starchNum);
                break;
            case 4:
                NumResult.put("allNum",hotPotNum);
                break;
            default:
                NumResult.put("allNum",(meatNum+soupNum+starchNum+hotPotNum));
                break;
        }
        return NumResult;
    }

    public Goods getGoodsInId(int id) {
        return completeDao.getGoodsInId(id);
    }

    public boolean insertCartList(Cart cart) {
        try {
            completeDao.insertCart(cart);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean addCartNum(Timestamp time,int count, int goods_id) {
        try {
            completeDao.addCartNum(time,count,goods_id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<Cart> getCartList(int user_id, int goods_id) {
        return completeDao.getCartLists(user_id,goods_id);
    }

    public Map<Integer, CartPo> shopCartConversion(SysCompletService service, List<Cart> list) {
        List<Integer> goods_idList = new ArrayList<Integer>();
        Map<Integer,CartPo> result = new HashMap<Integer, CartPo>();
        for (int i = 0; i < list.size() ; i++) {
            goods_idList.add(list.get(i).getGoods_id());
        }

        List<Goods> getGoods = new ArrayList<Goods>();
        for (int i = 0; i <goods_idList.size() ; i++) {
            getGoods.add(service.getGoodsInId(goods_idList.get(i)));
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

    public void addShopCartMethod( User user, SysCompletService service, Map<Integer, CartPo> sessionMap,Model model,HttpSession session) {
        if (user!=null&&user.getUser_name()!=null&&!"".equals(user.getUser_name())){
            model.addAttribute("userName",user.getUser_name());
            model.addAttribute("userEmail",user.getEmail());
            model.addAttribute("userPhone",user.getUser_phone());
            List<Cart> cartList = service.getCartList(user.getUser_id(),-1);
            Map<Integer,CartPo> dbMap = service.shopCartConversion(service,cartList);
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

    public List<String> shopDetailImgLists(Goods goods) {

        String img = goods.getGoods_imgs();
        List<String> list = DataConversion.jsonToList(img);
        return list;


    }

    public boolean deleteCart(int user_id, int goods_id) {
        try {
            completeDao.deleteCart(user_id,goods_id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateCartNum(int user_id, int goods_id, int num) {

        try {
            completeDao.updateCartList(user_id,goods_id,num);
            return true;
        }catch (Exception e){

            return false;
        }
    }

    public boolean insertComment(int user_id, int goods_id, int num, String userInfo, Timestamp time) {
        try {
            completeDao.insertCommentTab(user_id,goods_id,num,userInfo,time);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<HistoryGoods> getHistoryGoods(int user_id) {
        return completeDao.getHistoryGoods(user_id);
    }

    public boolean addComment(int user_id, int goods_id, int com_id,String text) {
        try {
            completeDao.addComment(user_id,goods_id,com_id,text);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<HistoryGoods> getShopComment(int goods_id) {
        List<HistoryGoods> result =completeDao.getShopComment(goods_id);
        for (int i = 0; i <result.size() ; i++) {
            if (result.get(i).getContent()==null){
                result.remove(i);
            }
        }
        return result;
    }

    public User getUserToComment(int user_id) {
        return completeDao.getUserInfo(user_id);
    }


    public boolean saleGoodsCount(int goods_id,int num) {
        try {
            completeDao.saleGoodsCount(goods_id,num);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
