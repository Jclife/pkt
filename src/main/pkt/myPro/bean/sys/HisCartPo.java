package myPro.bean.sys;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description: 历史订单封装
 * @date 2018/2/24  15:15
 */
public class HisCartPo {
    String imgs;
    float price;
    int num;
    String name;
    int id;
    String content;
    String time;
    int com_id; //评论表的ID
    BigDecimal totalPrice;

    @Override
    public String toString() {
        return "HisCartPo{" +
                "imgs='" + imgs + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", content='" + content + '\'' +
                ", com_id=" + com_id +
                '}';
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int num,float price) {
        float tmpPrice = num * price;
        BigDecimal bd = new BigDecimal((double)tmpPrice);
        int scale = 2;
        int roundingMode = 4;
        bd = bd.setScale(scale,roundingMode);
        this.totalPrice = bd;
    }



    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCom_id() {
        return com_id;
    }

    public void setCom_id(int com_id) {
        this.com_id = com_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
