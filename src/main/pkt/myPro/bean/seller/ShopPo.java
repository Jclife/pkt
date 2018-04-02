package myPro.bean.seller;

import java.util.List;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description: 分类商品列表
 * @date 2018/2/23  17:33
 */
public class ShopPo {

    List<Goods> allList;

    List<Goods> meatList;

    List<Goods> soupList;

    List<Goods> hotPotList;

    List<Goods> starchList;

    List<Goods> searchList;

    List<Goods> likeList;

    public List<Goods> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Goods> searchList) {
        this.searchList = searchList;
    }

    public List<Goods> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<Goods> likeList) {
        this.likeList = likeList;
    }

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
