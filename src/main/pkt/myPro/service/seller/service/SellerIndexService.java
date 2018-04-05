package myPro.service.seller.service;

import myPro.bean.seller.Goods;

import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  17:11
 */
public interface SellerIndexService {


    /**
     * 首页得到所有菜品售出量
     * @param list
     * @return
     */
    int  allSaleNum(List<Goods> list);

    /**
     * 首页计算所有售出金额
     * @param list
     * @return
     */
    float saleMoney(List<Goods> list);

    /**
     * 首页得到评论数量
     * @param list
     * @return
     */
    int commentNum(List<Goods> list);

    /**
     * 首页热卖榜，菜品售出量排行
     * @param store_id
     * @return
     */
    List<Goods> hotSaleList(int store_id);

    /**
     * 首页金额榜
     * @param store_id
     * @return
     */
    List<Map<String,Object>> moneySaleList(int store_id);
}
