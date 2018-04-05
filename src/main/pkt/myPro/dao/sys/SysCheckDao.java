package myPro.dao.sys;

import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  21:58
 */
public interface SysCheckDao {

    /**
     * 订单
     * 订单下单成功
     * @param user_id
     * @param goods_id
     * @param num
     * @param userInfo
     * @param time
     */
    void insertCommentTab(@Param("user_id") int user_id, @Param("goods_id") int goods_id, @Param("num") int num, @Param("userInfo") String userInfo, @Param("time") Timestamp time);

    /**
     * 菜品详情
     * 得到菜品售出数量
     * @param goods_id
     * @param num
     */
    void saleGoodsCount(@Param("goods_id") int goods_id,@Param("num") int num);
}
