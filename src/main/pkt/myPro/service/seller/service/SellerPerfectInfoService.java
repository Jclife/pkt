package myPro.service.seller.service;

import myPro.bean.seller.Store;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  17:11
 */
public interface SellerPerfectInfoService {

    /**
     * 完善商家信息
     * @param store
     * @return
     */
    boolean completeSeller(Store store);
}
