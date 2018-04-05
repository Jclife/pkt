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
public interface SysSearchGoodsDao {

    /**
     * 查询
     * 查询菜品列表
     * @param goodsName
     * @return
     */
    List<Goods> searchLists(@Param("goodsName") String goodsName);


    /**
     * 查询
     * 模糊查询菜品列表
     * @param goodsName
     * @param limit
     * @param last
     * @return
     */
    List<Goods> searchLikeLists(@Param("goodsName") String goodsName,@Param("limit") int limit,@Param("last") int last);
}
