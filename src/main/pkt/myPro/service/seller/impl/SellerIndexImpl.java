package myPro.service.seller.impl;

import myPro.bean.seller.Goods;
import myPro.dao.seller.SellerIndexDao;
import myPro.service.seller.service.SellerIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  21:18
 */

@Service
public class SellerIndexImpl implements SellerIndexService{

    @Autowired
    SellerIndexDao seller;


    public int allSaleNum(List<Goods> list) {
        int num = 0;
        for (int i = 0; i <list.size() ; i++) {
            num += list.get(i).getGoods_sale_count();
        }
        return num;
    }


    public float saleMoney(List<Goods> list) {
        float num = 0.00f;
        for (int i = 0; i <list.size() ; i++) {
            float oneNum;
            oneNum = (list.get(i).getGoods_sale_count())*(list.get(i).getGoods_price_now());
            num+=oneNum;
        }
        int scale = 2;
        int roundingMode = 4;
        BigDecimal bd = new BigDecimal((double)num);
        bd = bd.setScale(scale,roundingMode);
        num = bd.floatValue();
        return num;
    }

    //TODO 得到评论数量
    public int commentNum(int store_id) {
        int num = seller.commentNum(store_id);
        return num;
    }

    public List<Goods> hotSaleList(int store_id) {
        return seller.hotSaleList(store_id);
    }


    public List<Map<String,Object>> moneySaleList(int store_id) {

        List<Goods> list = seller.moneySaleList(store_id);
        List<Map<String,Object>> bL = new ArrayList<Map<String, Object>>();
        int scale = 2;//设置位数
        int roundingMode = 4;//表示四舍五入，可以选择其他舍值方式，例如去尾，等等.
        for (int i = 0; i <list.size() ; i++) {
            float tmpNum;
            Map<String,Object> tmpMap = new HashMap<String, Object>();
            tmpNum = list.get(i).getGoods_price_now()*list.get(i).getGoods_sale_count();
            BigDecimal bd = new BigDecimal((double)tmpNum);
            bd = bd.setScale(scale,roundingMode);
            tmpNum = bd.floatValue();
            tmpMap.put("name",list.get(i).getGoods_name());
            tmpMap.put("money",tmpNum);
            bL.add(tmpMap);
        }
        return bL;
    }
}
