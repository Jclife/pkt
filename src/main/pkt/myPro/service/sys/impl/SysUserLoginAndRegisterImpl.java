package myPro.service.sys.impl;

import myPro.bean.sys.User;
import myPro.dao.sys.SysUserLoginAndRegisterDao;
import myPro.service.sys.service.SysUserLoginAndRegisterService;
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
public class SysUserLoginAndRegisterImpl implements SysUserLoginAndRegisterService {


    @Autowired
    SysUserLoginAndRegisterDao userDao;

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

    public User getUserInId(int user_id) {
        return userDao.getUserInId(user_id);
    }
}
