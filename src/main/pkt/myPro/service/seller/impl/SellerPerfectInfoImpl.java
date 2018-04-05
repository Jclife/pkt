package myPro.service.seller.impl;

import myPro.bean.seller.Store;
import myPro.dao.seller.SellerPerfectInfoDao;
import myPro.service.seller.service.SellerPerfectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  21:19
 */
@Service
public class SellerPerfectInfoImpl implements SellerPerfectInfoService{

    @Autowired
    SellerPerfectInfoDao seller;

    public boolean completeSeller(Store store) {
        boolean result = false;
        try {
            seller.completeInfo(store);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
