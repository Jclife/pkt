package myPro.bean.seller;

import java.util.List;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/2/23  12:02
 */
public class GoodsPo {

    Goods goods;
    List<String> list;
    boolean promotion;
    boolean hotSale;

    public Goods getGoods() {
        return goods;
    }

    public boolean isHotSale() {
        return hotSale;
    }

    public void setHotSale(boolean hotSale) {
        this.hotSale = hotSale;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
