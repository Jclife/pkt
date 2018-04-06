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

    /**
     * 得到店家信息
     * @param account
     * @return
     */
    Store getStoreInfo(String account);

    /**
     * 增加新账号
     * @param store
     * @return
     */
    boolean insertNewStore(Store store);

}
