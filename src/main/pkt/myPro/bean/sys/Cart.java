package myPro.bean.sys;

import java.sql.Timestamp;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/2/25  11:26
 */
public class Cart {

    int cart_id;
    int user_id;
    int goods_id;
    int goods_count;
    Timestamp add_time;

    @Override
    public String toString() {
        return "Cart{" +
                "cart_id=" + cart_id +
                ", user_id=" + user_id +
                ", goods_id=" + goods_id +
                ", goods_count=" + goods_count +
                ", add_time=" + add_time +
                '}';
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
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

    public Timestamp getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Timestamp add_time) {
        this.add_time = add_time;
    }
}
