package myPro.service.sys.service;

import java.sql.Timestamp;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  22:20
 */
public interface SysCheckService {

    /**
     * 订单完成后插入历史订单表
     * @param user_id
     * @param goods_id
     * @param num
     * @param userInfo
     * @param time
     * @return
     */
    boolean insertComment(int store_id,int user_id,int goods_id,int num,String userInfo,Timestamp time);

    /**
     * 订单完成后修改售出数量
     * @param goods_id
     * @param num
     * @return
     */
    boolean saleGoodsCount(int goods_id,int num);

    /**
     * 得到店铺id
     * @param goods_id
     * @return
     */
    int getStoreId(int goods_id);
}
