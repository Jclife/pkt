package myPro.bean.seller;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:商品表
 * @date 2018/2/3  22:20
 */
public class Goods {

    int goods_id; //菜品ID
    int store_id; // 店铺ID
    String goods_name; //菜品名称
    String goods_cuisine; //菜系
    float goods_price; // 菜品价格
    float goods_price_now; //菜品促销价格
    String goods_simp_desc; //菜品简单描述
    int goods_sale_count; //菜品售出数量
    int goods_classify; //菜品类型
    String goods_imgs; // 菜品图片
    String goods_desc; // 菜品描述
    String goods_sale_time; // 最近售出时间
    int is_online; // 是否在售

    @Override
    public String toString() {
        return "Goods{" +
                "goods_id=" + goods_id +
                ", store_id=" + store_id +
                ", goods_name='" + goods_name + '\'' +
                ", goods_cuisine='" + goods_cuisine + '\'' +
                ", goods_price=" + goods_price +
                ", goods_price_now=" + goods_price_now +
                ", goods_simp_desc='" + goods_simp_desc + '\'' +
                ", goods_sale_count=" + goods_sale_count +
                ", goods_classify=" + goods_classify +
                ", goods_imgs='" + goods_imgs + '\'' +
                ", goods_desc='" + goods_desc + '\'' +
                ", goods_sale_time='" + goods_sale_time + '\'' +
                ", is_online=" + is_online +
                '}';
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_cuisine() {
        return goods_cuisine;
    }

    public void setGoods_cuisine(String goods_cuisine) {
        this.goods_cuisine = goods_cuisine;
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

    public String getGoods_simp_desc() {
        return goods_simp_desc;
    }

    public void setGoods_simp_desc(String goods_simp_desc) {
        this.goods_simp_desc = goods_simp_desc;
    }

    public int getGoods_sale_count() {
        return goods_sale_count;
    }

    public void setGoods_sale_count(int goods_sale_count) {
        this.goods_sale_count = goods_sale_count;
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

    public String getGoods_sale_time() {
        return goods_sale_time;
    }

    public void setGoods_sale_time(String goods_sale_time) {
        this.goods_sale_time = goods_sale_time;
    }

    public int getIs_online() {
        return is_online;
    }

    public void setIs_online(int is_online) {
        this.is_online = is_online;
    }
}
