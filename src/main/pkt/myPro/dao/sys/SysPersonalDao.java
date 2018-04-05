package myPro.dao.sys;

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
public interface SysPersonalDao {

    /**
     * 个人中心
     * 得到历史订单
     * @param user_id
     * @return
     */
    List<HistoryGoods> getHistoryGoods(@Param("user_id") int user_id);


    /**
     * 个人中心
     * 添加评论
     * @param user_id
     * @param goods_id
     * @param com_id
     * @param text
     */
    void addComment(@Param("user_id") int user_id,@Param("goods_id") int goods_id,@Param("com_id") int com_id,@Param("text") String text);


    /**
     * 个人中心
     * 修改用户密码
     * @param user_id
     * @param password
     */
    void modifyUserPass(@Param("user_id") int user_id,@Param("password") String password);

}
