package myPro.dao.sys;

import myPro.bean.sys.User;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:用户登录注册
 * @date 2018/2/2  16:44
 */
public interface UserLoginAndRegisterDao {

    void insertNewUser(User user);

    User getUserInfo(String user_phone);
}
