package myPro.service.seller.service;

import myPro.bean.seller.Goods;

import java.util.List;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  17:11
 */
public interface SellerGoodsListService {

    /**
     * 得到菜品列表
     * @param store_id
     * @return
     */
    List<Goods> getGoodsList(int store_id);

    /**
     * 条件查询菜品列表
     * @param store_id
     * @param content 菜品名称
     * @param type 菜品类型
     * @param page 分页起始页
     * @param limit 分页结束页
     * @return
     */
    List<Goods> findGoodsList(int store_id,String content,int type,int page,int limit);
}
