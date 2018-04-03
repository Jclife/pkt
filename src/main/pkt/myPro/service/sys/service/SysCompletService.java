package myPro.service.sys.service;

import myPro.bean.seller.Goods;
import myPro.bean.seller.GoodsPo;
import myPro.bean.seller.ShopPo;
import myPro.bean.sys.Cart;
import myPro.bean.sys.CartPo;
import myPro.bean.sys.HistoryGoods;
import myPro.bean.sys.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/2/22  18:22
 */
public interface SysCompletService {

    List<Goods> getAllRecoGoodsInIndex(int limit,int last);

    List<GoodsPo> setImgJsonInIndex(List<List<String>> jsonList,List<Goods> list);

    List<ShopPo> getShopLists(int limit,int last,int order,int type);

    int meatListNum(List<ShopPo> list);

    int soupListNum(List<ShopPo> list);

    int starchListNum(List<ShopPo> list);

    int hotPotListNum(List<ShopPo> list);

    Map<String,Integer> numberResultShop(List<ShopPo> list,int mark);

    Goods getGoodsInId(int id,int is_online);

    boolean insertCartList(Cart cart);

    boolean addCartNum(Timestamp time,int count,int goods_id);

    List<Cart> getCartList(int user_id,int goods_id);

    Map<Integer,CartPo> shopCartConversion(SysCompletService service, List<Cart> list);

    Cart shopCartConversion2(Timestamp time,CartPo cartPo,int user_id);

    void addShopCartMethod(User user, SysCompletService service, Map<Integer,CartPo> sessionMap, Model model,HttpSession session);

    List<String> shopDetailImgLists(Goods goods);

    boolean deleteCart(int user_id,int goods_id);

    boolean updateCartNum(int user_id,int goods_id,int num);

    boolean insertComment(int user_id,int goods_id,int num,String userInfo,Timestamp time);

    List<HistoryGoods> getHistoryGoods(int user_id);

    boolean addComment(int user_id,int goods_id,int com_id,String text);

    List<HistoryGoods> getShopComment(int goods_id);

    User getUserToComment(int user_id);

    boolean saleGoodsCount(int goods_id,int num);

    boolean modifyPass(int user_id,String password);

    List<Goods> searchLists(String goodsName);
    List<Goods> searchLikeLists(String goodsName,int limit,int last);
}
