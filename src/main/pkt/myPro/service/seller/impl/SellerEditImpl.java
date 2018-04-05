package myPro.service.seller.impl;

import myPro.bean.seller.Goods;
import myPro.dao.seller.SellerEditDao;
import myPro.service.seller.service.SellerEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  21:18
 */

@Service
public class SellerEditImpl implements SellerEditService{

    @Autowired
    SellerEditDao seller;

    public boolean shelves(Goods goods) {
        boolean result = false;
        try {
            seller.shelves(goods);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
