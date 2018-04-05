package myPro.service.seller.impl;

import myPro.bean.seller.Goods;
import myPro.dao.seller.SellerGoodsListDao;
import myPro.service.seller.service.SellerGoodsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  21:18
 */
@Service
public class SellerGoodsListImpl implements SellerGoodsListService {

    @Autowired
    SellerGoodsListDao seller;

    public List<Goods> getGoodsList(int store_id) {
        List<Goods> goods = seller.getGoodsList(store_id);
        return goods;
    }

    public List<Goods> findGoodsList(int store_id, String content, int type,int page,int limit) {
        return seller.findGoodsList(store_id,content,type,page,limit);
    }
}
