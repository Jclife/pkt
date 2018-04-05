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
public interface SysIndexService {

    /**
     * 首页得到热卖数据
     * @param limit
     * @param last
     * @return
     */
    List<Goods> getAllGoodsInIndex(int limit, int last);
}
