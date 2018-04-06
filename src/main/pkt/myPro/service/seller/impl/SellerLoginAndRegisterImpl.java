package myPro.service.seller.impl;

import myPro.bean.seller.Store;
import myPro.dao.seller.SellerLoginAndRegisterDao;
import myPro.service.seller.service.SellerLoginAndRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/2/5  22:14
 */

@Service
public class SellerLoginAndRegisterImpl implements SellerLoginAndRegisterService{

    @Autowired
    SellerLoginAndRegisterDao seller;

    public Store getStoreInfo(String account) {
        return seller.getStoreInfo(account);
    }

    public boolean insertNewStore(Store store) {
        try {
            seller.insertNewStore(store);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
