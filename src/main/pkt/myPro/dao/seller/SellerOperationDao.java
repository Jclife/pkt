package myPro.dao.seller;

import myPro.bean.seller.Goods;
import myPro.bean.sys.HistoryGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/6  22:46
 */
public interface SellerOperationDao {

    List<HistoryGoods> getOrderGoodsToOperation(@Param("store_id") int store_id,
                                                @Param("isGetGood") int isGetGood,
                                                @Param("limit") int limit,
                                                @Param("last") int last);

    String getNameFromGoodsId(@Param("goods_id") int goods_id);

    float getPriceFromGoodsId(@Param("goods_id") int goods_id);

    void confirmDelivery(@Param("com_id") int com_id);
}
