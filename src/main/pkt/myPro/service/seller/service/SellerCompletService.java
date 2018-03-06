package myPro.service.seller.service;

import myPro.bean.seller.Goods;
import myPro.bean.seller.Store;

import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/2/7  15:00
 */
public interface SellerCompletService {

    boolean completSeller(Store store);

    boolean shelves(Goods goods);

    List<Goods> getGoodsList(int store_id);

    List<Goods> findGoodsList(int store_id,String content,int type,int page,int limit);

    int  allSaleNum(List<Goods> list);

    float saleMoney(List<Goods> list);

    int commentNum(List<Goods> list);

    List<Goods> hotSaleList(int store_id);

    List<Map<String,Object>> moneySaleList(int store_id);
}
