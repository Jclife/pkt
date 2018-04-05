package myPro.dao.sys;

import myPro.bean.sys.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:用户登录注册
 * @date 2018/2/2  16:44
 */
public interface SysUserLoginAndRegisterDao {

    /**
     * 注册新用户
     * @param user
     */
    void insertNewUser(User user);

    /**
     * 得到登录用户信息
     * @param user_phone
     * @return
     */
    User getUserInfo(String user_phone);

    /**
     * 注册登录
     * 得到用户信息
     * @param user_id
     * @return
     */
    User getUserInId(@Param("user_id") int user_id);
}
