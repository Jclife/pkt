package myPro.service.sys.service;

import myPro.bean.sys.User;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/2/1  21:26
 */
public interface UserLoginAndRegisterService {

    User getUserInfo(String user_phone);

    boolean insertNewUser(User user);

}
