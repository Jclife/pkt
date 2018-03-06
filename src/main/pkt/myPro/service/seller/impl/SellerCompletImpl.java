package myPro.service.seller.impl;

import myPro.bean.seller.Goods;
import myPro.bean.seller.Store;
import myPro.dao.seller.SellerCompletInfoDao;
import myPro.service.seller.service.SellerCompletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/2/7  15:01
 */

@Service
public class SellerCompletImpl implements SellerCompletService{


    @Autowired
    SellerCompletInfoDao seller;

    public boolean completSeller(Store store) {
        boolean result = false;
        try {
            seller.completInfo(store);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

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

    public List<Goods> getGoodsList(int store_id) {
        List<Goods> goods = seller.getGoodsList(store_id);
        return goods;
    }

    public List<Goods> findGoodsList(int store_id, String content, int type,int page,int limit) {
        return seller.findGoodsList(store_id,content,type,page,limit);
    }

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

    public int commentNum(List<Goods> list) {
        int num = 0;
        for (int i = 0; i <list.size() ; i++) {
            if (list.get(i).getGoods_comment()!=null){
                num++;
            }
        }
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
