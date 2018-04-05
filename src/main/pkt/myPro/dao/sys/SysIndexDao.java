package myPro.dao.sys;

import myPro.bean.seller.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  21:58
 */
public interface SysIndexDao {

    /**
     * 首页
     * 得到首页热卖榜
     * @param limit
     * @param last
     * @return
     */
    List<Goods> getAllGoodsInIndex(@Param("limit") int limit, @Param("last") int last);
}
