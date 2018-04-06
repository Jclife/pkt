package myPro.bean.sys;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description: 历史订单评论记录表
 * @date 2018/3/4  21:45
 */
public class HistoryGoods {
    int com_id;
    int store_id;
    int user_id;
    int goods_id;
    int goods_count;
    String userInfo;
    String shopTime;
    int get_goods;
    String content;


    @Override
    public String toString() {
        return "HistoryGoods{" +
                "com_id=" + com_id +
                ", user_id=" + user_id +
                ", goods_id=" + goods_id +
                ", goods_count=" + goods_count +
                ", userInfo='" + userInfo + '\'' +
                ", shopTime=" + shopTime +
                ", content='" + content + '\'' +
                '}';
    }

    public int getGet_goods() {
        return get_goods;
    }

    public void setGet_goods(int get_goods) {
        this.get_goods = get_goods;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getCom_id() {
        return com_id;
    }

    public void setCom_id(int com_id) {
        this.com_id = com_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getGoods_count() {
        return goods_count;
    }

    public void setGoods_count(int goods_count) {
        this.goods_count = goods_count;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getShopTime() {
        return shopTime;
    }

    public void setShopTime(String shopTime) {
        this.shopTime = shopTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
