package myPro.bean.sys;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/2/24  15:15
 */
public class CartPo {
    String imgs;
    float price;
    int num;
    String name;
    int id;

    @Override
    public String toString() {
        return "CartPo{" +
                "imgs='" + imgs + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", name='" + name + '\'' +
                '}';
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
