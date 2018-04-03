package myPro.bean.seller;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:商品表
 * @date 2018/2/3  22:20
 */
public class Goods {

    int goods_id;
    int store_id;
    String goods_name;
    String goods_origin;
    float goods_price;
    float goods_price_now;
    int goods_count;
    int goods_sale_count;
    String goods_tags;
    int goods_classify;
    String goods_imgs;
    String goods_desc;
    String goods_comment;
    int goods_wait_sale;
    String goods_sale_time;
    int is_online;

    @Override
    public String toString() {
        return "Goods{" +
                "goods_id=" + goods_id +
                ", store_id=" + store_id +
                ", goods_name='" + goods_name + '\'' +
                ", goods_origin='" + goods_origin + '\'' +
                ", goods_price=" + goods_price +
                ", goods_price_now=" + goods_price_now +
                ", goods_count=" + goods_count +
                ", goods_sale_count=" + goods_sale_count +
                ", goods_tags='" + goods_tags + '\'' +
                ", goods_classify=" + goods_classify +
                ", goods_imgs='" + goods_imgs + '\'' +
                ", goods_desc='" + goods_desc + '\'' +
                ", goods_comment='" + goods_comment + '\'' +
                ", goods_wait_sale=" + goods_wait_sale +
                ", goods_sale_time='" + goods_sale_time + '\'' +
                ", is_online=" + is_online +
                '}';
    }

    public int getIs_online() {
        return is_online;
    }

    public void setIs_online(int is_online) {
        this.is_online = is_online;
    }

    public String getGoods_sale_time() {
        return goods_sale_time;
    }

    public void setGoods_sale_time(String goods_sale_time) {
        this.goods_sale_time = goods_sale_time;
    }

    public int getGoods_wait_sale() {
        return goods_wait_sale;
    }

    public void setGoods_wait_sale(int goods_wait_sale) {
        this.goods_wait_sale = goods_wait_sale;
    }

    public int getGoods_sale_count() {
        return goods_sale_count;
    }

    public void setGoods_sale_count(int goods_sale_count) {
        this.goods_sale_count = goods_sale_count;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
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

    public String getGoods_origin() {
        return goods_origin;
    }

    public void setGoods_origin(String goods_origin) {
        this.goods_origin = goods_origin;
    }

    public float getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(float goods_price) {
        this.goods_price = goods_price;
    }

    public float getGoods_price_now() {
        return goods_price_now;
    }

    public void setGoods_price_now(float goods_price_now) {
        this.goods_price_now = goods_price_now;
    }

    public int getGoods_count() {
        return goods_count;
    }

    public void setGoods_count(int goods_count) {
        this.goods_count = goods_count;
    }

    public String getGoods_tags() {
        return goods_tags;
    }

    public void setGoods_tags(String goods_tags) {
        this.goods_tags = goods_tags;
    }

    public int getGoods_classify() {
        return goods_classify;
    }

    public void setGoods_classify(int goods_classify) {
        this.goods_classify = goods_classify;
    }

    public String getGoods_imgs() {
        return goods_imgs;
    }

    public void setGoods_imgs(String goods_imgs) {
        this.goods_imgs = goods_imgs;
    }

    public String getGoods_desc() {
        return goods_desc;
    }

    public void setGoods_desc(String goods_desc) {
        this.goods_desc = goods_desc;
    }

    public String getGoods_comment() {
        return goods_comment;
    }

    public void setGoods_comment(String goods_comment) {
        this.goods_comment = goods_comment;
    }
}
