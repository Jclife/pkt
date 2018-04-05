package myPro.service.sys.service;

import myPro.bean.seller.Goods;
import myPro.bean.sys.HistoryGoods;

import java.util.List;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  22:20
 */
public interface SysShopDetailService {

    /**
     * 通过id得到菜品数据
     * @param id
     * @param is_online
     * @return
     */
    Goods getGoodsInId(int id, int is_online);

    /**
     * 菜品详情的图片列表
     * @param goods
     * @return
     */
    List<String> shopDetailImgLists(Goods goods);

    /**
     * 得到所有评论
     * @param goods_id
     * @return
     */
    List<HistoryGoods> getShopComment(int goods_id);



}
