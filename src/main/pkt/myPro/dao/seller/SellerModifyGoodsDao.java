package myPro.dao.seller;

import myPro.bean.seller.Goods;
import org.apache.ibatis.annotations.Param;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  21:24
 */
public interface SellerModifyGoodsDao {

    /**
     * 得到修改菜品信息
     * @param goods_id
     * @return
     */
    Goods modifyGoods(int goods_id);

    /**
     * 修改基本信息，图片除外
     * @param goods
     */
    void modifyBaseGood(Goods goods);

    /**
     * 修改图片信息
     * @param goods_id
     * @param imgJson
     */
    void modifyPicGood(@Param("goods_id") int goods_id, @Param("imgJson") String imgJson);

    /**
     * 下架菜品
     * @param goods_id
     */
    void deleteGood(int goods_id);
}
