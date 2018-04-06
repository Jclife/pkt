package myPro.bean.seller;


import java.math.BigDecimal;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description: seller 操作菜品类
 * @date 2018/4/6  22:44
 */
public class GoodsOp {

    int com_id;
    int goods_id;
    String goods_name;
    int count;
    float price;
    BigDecimal totalPrice;
    String time;
    String address;
    String user_name;
    String content;

    @Override
    public String toString() {
        return "GoodsOp{" +
                "goods_id=" + goods_id +
                ", goods_name='" + goods_name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", time=" + time +
                ", address='" + address + '\'' +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTotalPrice(int num, float price) {
        float tmpPrice = count * price;
        BigDecimal bd = new BigDecimal((double)tmpPrice);
        int scale = 2;
        int roundingMode = 4;
        bd = bd.setScale(scale,roundingMode);
        this.totalPrice = bd;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCom_id() {
        return com_id;
    }

    public void setCom_id(int com_id) {
        this.com_id = com_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
