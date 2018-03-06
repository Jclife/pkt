package myPro.service.sys.impl;

import myPro.bean.sys.User;
import myPro.dao.sys.UserLoginAndRegisterDao;
import myPro.service.sys.service.UserLoginAndRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/2/2  16:47
 */
@Service
public class UserLoginAndRegisterImpl implements UserLoginAndRegisterService {


    @Autowired
    UserLoginAndRegisterDao userDao;

    public User getUserInfo(String user_phone) {
        return userDao.getUserInfo(user_phone);
    }

    public boolean insertNewUser(User user) {
        boolean s = false;
        try {
            userDao.insertNewUser(user);
            s = true;
        }catch (Exception e){
            System.out.println(e);
        }finally {
            return s;
        }
    }
}
