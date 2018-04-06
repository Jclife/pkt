package myPro.dao.seller;

import myPro.bean.seller.Store;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/2/5  22:12
 */
public interface SellerLoginAndRegisterDao {

    Store getStoreInfo(String account);

    void insertNewStore(Store store);

}
