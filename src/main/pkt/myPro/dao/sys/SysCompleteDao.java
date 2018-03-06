package myPro.dao.sys;

import myPro.bean.sys.HistoryGoods;
import myPro.bean.sys.Cart;
import myPro.bean.seller.Goods;
import myPro.bean.sys.User;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/2/22  14:53
 */
public interface SysCompleteDao {


    List<Goods> getAllRecoGoodsInIndex(@Param("limit") int limit,@Param("last") int last);

    List<Goods> getShopLists(@Param("limit") int limit,@Param("last") int last,@Param("type") int type,@Param("order") int order);

    Goods getGoodsInId(int id);

    void insertCart(Cart cart);

    void addCartNum(@Param("time") Timestamp time,@Param("count") int count,@Param("goods_id") int goods_id);

    List<Cart> getCartLists(@Param("user_id") int user_id,@Param("goods_id") int goods_id);

    void deleteCart(@Param("user_id") int user_id,@Param("goods_id") int goods_id);

    void updateCartList(@Param("user_id") int user_id,@Param("goods_id") int goods_id,@Param("num") int num);

    void insertCommentTab(@Param("user_id") int user_id,@Param("goods_id") int goods_id,@Param("num") int num,@Param("userInfo") String userInfo,@Param("time") Timestamp time);

    List<HistoryGoods> getHistoryGoods(@Param("user_id") int user_id);

    void addComment(@Param("user_id") int user_id,@Param("goods_id") int goods_id,@Param("com_id") int com_id,@Param("text") String text);

    List<HistoryGoods> getShopComment(@Param("goods_id") int goods_id);

    User getUserInfo(@Param("user_id") int user_id);

    void saleGoodsCount(@Param("goods_id") int goods_id,@Param("num") int num);
}
