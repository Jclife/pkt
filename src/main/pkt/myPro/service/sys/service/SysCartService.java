package myPro.service.sys.service;

import myPro.bean.sys.Cart;
import myPro.bean.sys.CartPo;
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
 * @date 2018/4/5  22:20
 */
public interface SysCartService {

    /**
     * 插入购物车
     * @param cart
     * @return
     */
    boolean insertCartList(Cart cart);

    /**
     * 增加购物车内菜品数量
     * @param time
     * @param count
     * @param goods_id
     * @return
     */
    boolean addCartNum(Timestamp time, int count, int goods_id);

    /**
     * 得到购物车信息
     * @param user_id
     * @param goods_id
     * @return
     */
    List<Cart> getCartList(int user_id, int goods_id);

    /**
     * 购物车数据转换
     * @param service
     * @param list
     * @return
     */
    Map<Integer,CartPo> shopCartConversion(SysUtilService service, List<Cart> list);

    /**
     * 购物车数据转换
     * @param time
     * @param cartPo
     * @param user_id
     * @return
     */
    Cart shopCartConversion2(Timestamp time,CartPo cartPo,int user_id);

    /**
     * 增加购物车
     * @param user
     * @param service
     * @param sessionMap
     * @param model
     * @param session
     */
    void addShopCartMethod(User user, SysCartService service,SysUtilService utilService, Map<Integer,CartPo> sessionMap, Model model, HttpSession session);

    /**
     * 删除购物车
     * @param user_id
     * @param goods_id
     * @return
     */
    boolean deleteCart(int user_id,int goods_id);

    /**
     * 修改购物车菜品数量
     * @param user_id
     * @param goods_id
     * @param num
     * @return
     */
    boolean updateCartNum(int user_id,int goods_id,int num);

}
