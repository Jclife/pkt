package myPro.service.seller.service;

import myPro.bean.seller.Goods;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  17:11
 */
public interface SellerModifyGoodsService {

    /**
     * 要修改的菜品信息
     * @param goods_id
     * @return
     */
    Goods getModifyGoods(int goods_id);

    /**
     * 修改基本信息
     * @param goods
     * @return
     */
    boolean modifyBase(Goods goods);

    /**
     * 修改图片
     * @param goods_id
     * @param imgJson
     * @return
     */
    boolean modifyPic(int goods_id,String imgJson);

    /**
     * 下架菜品
     * @param goods_id
     * @return
     */
    boolean deleteGood(int goods_id);

}
