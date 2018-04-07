package myPro.service.seller.impl;

import myPro.bean.seller.GoodsOp;
import myPro.bean.sys.HistoryGoods;
import myPro.dao.seller.SellerOperationDao;
import myPro.service.seller.service.SellerGoodsOperationService;
import myPro.utils.common.DataConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/6  22:50
 */

@Service
public class SellerGoodsOperationImpl implements SellerGoodsOperationService{

    @Autowired
    SellerOperationDao seller;

    public List<GoodsOp> getOperationGood(int store_id,int get_goods,int limit,int last) {
        System.out.println(store_id+"                "+get_goods);
        List<HistoryGoods> dbGoods = seller.getOrderGoodsToOperation(store_id,get_goods,limit,last);



        //dbGoods 转 封装的goodOp
        List<GoodsOp> goodsOpList = new ArrayList<GoodsOp>();
        for (int i = 0; i < dbGoods.size(); i++) {
            GoodsOp goodsOp = new GoodsOp();
            HistoryGoods tmpGoods = dbGoods.get(i);
            Map<String,String> userMap = new HashMap<String, String>();
            try {
                userMap = DataConversion.jsonToMap(tmpGoods.getUserInfo());
            } catch (IOException e) {
                e.printStackTrace();
            }
            goodsOp.setCom_id(dbGoods.get(i).getCom_id());
            goodsOp.setGoods_id(tmpGoods.getGoods_id());
            goodsOp.setAddress(userMap.get("address"));
            goodsOp.setUser_name(userMap.get("name"));
            goodsOp.setCount(tmpGoods.getGoods_count());
            goodsOp.setTime(tmpGoods.getShopTime());
            goodsOp.setContent(dbGoods.get(i).getContent());
            goodsOp.setGoods_name(seller.getNameFromGoodsId(tmpGoods.getGoods_id()));
            goodsOp.setPrice(seller.getPriceFromGoodsId(tmpGoods.getGoods_id()));
            goodsOp.setTotalPrice(goodsOp.getCount(),goodsOp.getPrice());
            goodsOpList.add(goodsOp);
        }
        return goodsOpList;
    }

    public boolean confirmDelivery(int com_id) {
        try {
            seller.confirmDelivery(com_id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
