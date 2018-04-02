package myPro.bean.seller;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description: 店铺表
 * @date 2018/2/5  21:10
 */
public class Store {

    int store_id;
    String store_name;
    String store_tags;
    String store_account;
    String store_password;
    String store_head;
    String store_intro;
    String store_address;

    @Override
    public String toString() {
        return "Store{" +
                "store_id=" + store_id +
                ", store_name='" + store_name + '\'' +
                ", store_tags='" + store_tags + '\'' +
                ", store_account='" + store_account + '\'' +
                ", store_password='" + store_password + '\'' +
                ", store_head='" + store_head + '\'' +
                ", store_intro='" + store_intro + '\'' +
                ", store_address='" + store_address + '\'' +
                '}';
    }

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address;
    }

    public String getStore_intro() {
        return store_intro;
    }

    public void setStore_intro(String store_intro) {
        this.store_intro = store_intro;
    }

    public String getStore_head() {
        return store_head;
    }

    public void setStore_head(String store_head) {
        this.store_head = store_head;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_tags() {
        return store_tags;
    }

    public void setStore_tags(String store_tags) {
        this.store_tags = store_tags;
    }

    public String getStore_account() {
        return store_account;
    }

    public void setStore_account(String store_account) {
        this.store_account = store_account;
    }

    public String getStore_password() {
        return store_password;
    }

    public void setStore_password(String store_password) {
        this.store_password = store_password;
    }
}
