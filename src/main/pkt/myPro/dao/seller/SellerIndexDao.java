package myPro.dao.seller;

import myPro.bean.seller.Goods;

import java.util.List;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  21:24
 */
public interface SellerIndexDao {

    /**
     * 首页热卖榜
     * @param store_id
     * @return
     */
    List<Goods> hotSaleList(int store_id);

    /**
     * 首页售额榜
     * @param store_id
     * @return
     */
    List<Goods> moneySaleList(int store_id);

    /**
     * 评论数量
     */
    int commentNum(int store_id);
}
