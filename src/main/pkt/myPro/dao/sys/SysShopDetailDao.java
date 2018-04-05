package myPro.dao.sys;

import myPro.bean.seller.Goods;
import myPro.bean.sys.HistoryGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  21:58
 */
public interface SysShopDetailDao {

    /**
     * 菜品详情
     * 得到所有评论
     * @param goods_id
     * @return
     */
    List<HistoryGoods> getShopComment(@Param("goods_id") int goods_id);



    /**
     * 菜品详情
     * 通过id得到菜品信息
     * @param id
     * @param is_online
     * @return
     */
    Goods getGoodsInId(@Param("id") int id, @Param("is_online") int is_online);
}
