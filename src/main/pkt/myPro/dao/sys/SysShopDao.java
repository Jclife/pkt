package myPro.dao.sys;

import myPro.bean.seller.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  21:58
 */
public interface SysShopDao {

    /**
     * 所有菜品
     * 分类得到菜品列表
     * @param limit
     * @param last
     * @param type
     * @param order
     * @return
     */
    List<Goods> getShopLists(@Param("limit") int limit, @Param("last") int last, @Param("type") int type, @Param("order") int order);

}
