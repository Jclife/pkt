package myPro.bean.seller;

import java.sql.Timestamp;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description: 订单表
 * @date 2018/4/2  15:34
 */
public class Order {

    int order_id;
    String order_name;
    String order_content;
    Timestamp order_time;
    String order_person;
    String order_message;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getOrder_content() {
        return order_content;
    }

    public void setOrder_content(String order_content) {
        this.order_content = order_content;
    }

    public Timestamp getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Timestamp order_time) {
        this.order_time = order_time;
    }

    public String getOrder_person() {
        return order_person;
    }

    public void setOrder_person(String order_person) {
        this.order_person = order_person;
    }

    public String getOrder_message() {
        return order_message;
    }

    public void setOrder_message(String order_message) {
        this.order_message = order_message;
    }
}
