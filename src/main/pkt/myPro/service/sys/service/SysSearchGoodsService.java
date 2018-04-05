package myPro.service.sys.service;

import myPro.bean.seller.Goods;

import java.util.List;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  22:20
 */
public interface SysSearchGoodsService {

    /**
     * 查询菜品列表
     * @param goodsName
     * @return
     */
    List<Goods> searchLists(String goodsName);

    /**
     * 模糊查询菜品列表
     * @param goodsName
     * @param limit
     * @param last
     * @return
     */
    List<Goods> searchLikeLists(String goodsName,int limit,int last);
}
