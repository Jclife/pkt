package myPro.bean.seller;

import java.util.List;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/2/23  17:33
 */
public class ShopPo {

    List<Goods> allList;

    List<Goods> meatList;

    List<Goods> soupList;

    List<Goods> hotPotList;

    List<Goods> starchList;


    public List<Goods> getAllList() {
        return allList;
    }

    public void setAllList(List<Goods> allList) {
        this.allList = allList;
    }

    public List<Goods> getMeatList() {
        return meatList;
    }

    public void setMeatList(List<Goods> meatList) {
        this.meatList = meatList;
    }

    public List<Goods> getSoupList() {
        return soupList;
    }

    public void setSoupList(List<Goods> soupList) {
        this.soupList = soupList;
    }

    public List<Goods> getHotPotList() {
        return hotPotList;
    }

    public void setHotPotList(List<Goods> hotPotList) {
        this.hotPotList = hotPotList;
    }

    public List<Goods> getStarchList() {
        return starchList;
    }

    public void setStarchList(List<Goods> starchList) {
        this.starchList = starchList;
    }
}
