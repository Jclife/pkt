package myPro.dao.seller;

import myPro.bean.seller.Goods;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  21:24
 */
public interface SellerEditDao {

    /**
     * 上架菜品
     * @param goods
     */
    void shelves(Goods goods);
}
