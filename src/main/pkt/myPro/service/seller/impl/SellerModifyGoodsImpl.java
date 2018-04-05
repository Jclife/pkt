package myPro.service.seller.impl;

import myPro.bean.seller.Goods;
import myPro.dao.seller.SellerModifyGoodsDao;
import myPro.service.seller.service.SellerModifyGoodsService;
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
public class SellerModifyGoodsImpl implements SellerModifyGoodsService {
    @Autowired
    SellerModifyGoodsDao seller;

    public Goods getModifyGoods(int goods_id) {
        return seller.modifyGoods(goods_id);
    }


    public boolean modifyBase(Goods goods) {
        try {
            seller.modifyBaseGood(goods);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean modifyPic(int goods_id, String imgJson) {
        try {
            seller.modifyPicGood(goods_id,imgJson);
            return true;
        }catch (Exception e){
            return  false;
        }
    }

    public boolean deleteGood(int goods_id) {
        try {
            seller.deleteGood(goods_id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
