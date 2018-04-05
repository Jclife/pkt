package myPro.dao.sys;

import myPro.bean.sys.Cart;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  21:58
 */
public interface SysCartDao {

    /**
     * 购物车
     * 添加购物车
     * @param cart
     */
    void insertCart(Cart cart);

    /**
     * 购物车
     * 添加购物车数字
     * @param time
     * @param count
     * @param goods_id
     */
    void addCartNum(@Param("time") Timestamp time, @Param("count") int count, @Param("goods_id") int goods_id);


    /**
     * 购物车
     * 得到购物车列表
     * @param user_id
     * @param goods_id
     * @return
     */
    List<Cart> getCartLists(@Param("user_id") int user_id, @Param("goods_id") int goods_id);


    /**
     * 购物车
     * 删除购物车
     * @param user_id
     * @param goods_id
     */
    void deleteCart(@Param("user_id") int user_id,@Param("goods_id") int goods_id);


    /**
     * 购物车
     * 更新购物车数据
     * @param user_id
     * @param goods_id
     * @param num
     */
    void updateCartList(@Param("user_id") int user_id,@Param("goods_id") int goods_id,@Param("num") int num);
}
