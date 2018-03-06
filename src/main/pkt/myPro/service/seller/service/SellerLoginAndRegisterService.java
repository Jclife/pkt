package myPro.service.seller.service;

import myPro.bean.seller.Store;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/2/5  22:13
 */
public interface SellerLoginAndRegisterService {

    Store getStoreInfo(String account);

    boolean insertNewStore(Store store);

}
