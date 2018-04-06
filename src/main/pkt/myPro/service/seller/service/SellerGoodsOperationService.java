package myPro.service.seller.service;

import myPro.bean.seller.GoodsOp;

import java.util.List;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/6  22:49
 */
public interface SellerGoodsOperationService {

    List<GoodsOp> getOperationGood(int store_id, int get_goods,int limit,int last);

    boolean confirmDelivery(int com_id);
}
