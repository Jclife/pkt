package myPro.dao.seller;

import myPro.bean.seller.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  21:24
 */
public interface SellerGoodsListDao {

    /**
     * 得到店铺菜品列表
     * @param store_id
     * @return
     */
    List<Goods> getGoodsList(int store_id);

    /**
     * 条件查询菜品列表
     * @param store_id
     * @param content
     * @param type
     * @param page
     * @param limit
     * @return
     */
    List<Goods> findGoodsList(@Param("store_id") int store_id,
                              @Param("content") String content,
                              @Param("type") int type,
                              @Param("page") int page,
                              @Param("limit") int limit);
}
