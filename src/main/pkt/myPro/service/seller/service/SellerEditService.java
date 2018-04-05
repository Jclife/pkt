package myPro.service.seller.service;

import myPro.bean.seller.Goods;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  17:11
 */
public interface SellerEditService {


    /**
     * 菜品上架
     * @param goods
     * @return
     */
    boolean shelves(Goods goods);
}
