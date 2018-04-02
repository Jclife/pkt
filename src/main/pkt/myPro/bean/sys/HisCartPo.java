package myPro.bean.sys;

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
    int com_id;

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
