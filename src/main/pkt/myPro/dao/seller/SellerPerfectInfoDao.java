package myPro.dao.seller;

import myPro.bean.seller.Store;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  21:24
 */
public interface SellerPerfectInfoDao {

    /**
     * 完善商家信息
     * @param store
     */
    void completeInfo(Store store);

}
