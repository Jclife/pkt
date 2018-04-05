package myPro.service.sys.impl;

import myPro.bean.seller.Goods;
import myPro.bean.seller.ShopPo;
import myPro.dao.sys.SysShopDao;
import myPro.service.sys.service.SysShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  22:35
 */

@Service
public class SysShopImpl implements SysShopService{

    @Autowired
    SysShopDao sys;

    public List<ShopPo> getShopLists(int limit, int last,int order,int type) {

        List<ShopPo> lists = new ArrayList<ShopPo>();
        ShopPo tmpShop = new ShopPo();
        if (type==0){
            List<Goods> meatGoods = sys.getShopLists(limit,last,1,order);
            tmpShop.setMeatList(meatGoods);
            List<Goods> soupGoods = sys.getShopLists(limit,last,2,order);
            tmpShop.setSoupList(soupGoods);
            List<Goods> starchGoods = sys.getShopLists(limit,last,3,order);
            tmpShop.setStarchList(starchGoods);
            List<Goods> hotPotGoods = sys.getShopLists(limit,last,4,order);
            tmpShop.setHotPotList(hotPotGoods);
            List<Goods> allGoods = sys.getShopLists(limit,last,5,order);
            tmpShop.setAllList(allGoods);
        }else {
            switch (type){
                case 1:
                    List<Goods> meatGoods = sys.getShopLists(limit,last,1,order);
                    tmpShop.setMeatList(meatGoods);
                    break;
                case 2:
                    List<Goods> soupGoods = sys.getShopLists(limit,last,2,order);
                    tmpShop.setSoupList(soupGoods);
                    break;
                case 3:
                    List<Goods> starchGoods = sys.getShopLists(limit,last,3,order);
                    tmpShop.setStarchList(starchGoods);
                    break;
                case 4:
                    List<Goods> hotPotGoods = sys.getShopLists(limit,last,4,order);
                    tmpShop.setHotPotList(hotPotGoods);
                    break;
                default:
                    List<Goods> allGoods = sys.getShopLists(limit,last,5,order);
                    tmpShop.setAllList(allGoods);
                    break;
            }
        }
        lists.add(0,tmpShop);
        return lists;
    }

    public int meatListNum(List<ShopPo> list) {
        return list.get(0).getMeatList().size();
    }

    public int soupListNum(List<ShopPo> list) {
        return list.get(0).getSoupList().size();
    }

    public int starchListNum(List<ShopPo> list) {
        return list.get(0).getStarchList().size();
    }

    public int hotPotListNum(List<ShopPo> list) {
        return list.get(0).getHotPotList().size();
    }

    public Map<String, Integer> numberResultShop(List<ShopPo> list,int mark) {
        int meatNum = meatListNum(list);
        int soupNum = soupListNum(list);
        int starchNum = starchListNum(list);
        int hotPotNum = hotPotListNum(list);
        Map<String,Integer> NumResult = new HashMap<String, Integer>();
        NumResult.put("meatNum",meatNum);
        NumResult.put("soupNum",soupNum);
        NumResult.put("starchNum",starchNum);
        NumResult.put("hotPotNum",hotPotNum);
        switch (mark){
            case 1:
                NumResult.put("allNum",meatNum);
                break;
            case 2:
                NumResult.put("allNum",soupNum);
                break;
            case 3:
                NumResult.put("allNum",starchNum);
                break;
            case 4:
                NumResult.put("allNum",hotPotNum);
                break;
            default:
                NumResult.put("allNum",(meatNum+soupNum+starchNum+hotPotNum));
                break;
        }
        return NumResult;
    }

}
