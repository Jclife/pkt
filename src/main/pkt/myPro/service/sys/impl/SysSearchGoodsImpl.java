package myPro.service.sys.impl;

import myPro.bean.seller.Goods;
import myPro.dao.sys.SysSearchGoodsDao;
import myPro.service.sys.service.SysSearchGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  22:35
 */

@Service
public class SysSearchGoodsImpl implements SysSearchGoodsService{

    @Autowired
    SysSearchGoodsDao sys;

    public List<Goods> searchLists(String goodsName) {
        List<Goods> goods = sys.searchLists(goodsName);
        return goods==null?null:goods;
    }

    public List<Goods> searchLikeLists(String goodsName,int limit,int last){
        List<Goods> goods = sys.searchLikeLists(goodsName,-1,-1);
        return goods;
    }
}
