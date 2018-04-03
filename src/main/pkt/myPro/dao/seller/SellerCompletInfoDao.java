package myPro.dao.seller;

import myPro.bean.seller.Goods;
import myPro.bean.seller.Store;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description: 完善信息
 * @date 2018/2/7  15:00
 */
public interface SellerCompletInfoDao {

    void completInfo(Store store);

    void shelves(Goods goods);

    List<Goods> getGoodsList(int store_id);

    List<Goods> findGoodsList(@Param("store_id") int store_id,
                              @Param("content") String content,
                              @Param("type") int type,
                              @Param("page") int page,
                              @Param("limit") int limit);

    List<Goods> hotSaleList(int store_id);

    List<Goods> moneySaleList(int store_id);

    Goods modifyGoods(int goods_id);

    void modifyBaseGood(Goods goods);

    void modifyPicGood(@Param("goods_id") int goods_id,@Param("imgJson") String imgJson);

    void deleteGood(int goods_id);
}
