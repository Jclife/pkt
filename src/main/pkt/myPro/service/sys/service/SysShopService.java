package myPro.service.sys.service;

import myPro.bean.seller.ShopPo;

import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  22:20
 */
public interface SysShopService {

    /**
     * 分类得到菜品列表
     * @param limit
     * @param last
     * @param order
     * @param type
     * @return
     */
    List<ShopPo> getShopLists(int limit, int last, int order, int type);

    /**
     * 肉类菜品数量
     * @param list
     * @return
     */
    int meatListNum(List<ShopPo> list);

    /**
     * 汤类菜品数量
     * @param list
     * @return
     */
    int soupListNum(List<ShopPo> list);

    /**
     * 小炒菜品数量
     * @param list
     * @return
     */
    int starchListNum(List<ShopPo> list);

    /**
     * 火锅菜品数量
     * @param list
     * @return
     */
    int hotPotListNum(List<ShopPo> list);

    /**
     * 总共
     * @param list
     * @param mark
     * @return
     */
    Map<String,Integer> numberResultShop(List<ShopPo> list, int mark);

}
