package myPro.service.sys.impl;

import myPro.bean.sys.HistoryGoods;
import myPro.dao.sys.SysPersonalDao;
import myPro.service.sys.service.SysPersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  22:35
 */
@Service
public class SysPersonalImpl implements SysPersonalService{

    @Autowired
    SysPersonalDao sys;

    public List<HistoryGoods> getHistoryGoods(int user_id) {
        return sys.getHistoryGoods(user_id);
    }

    public boolean addComment(int user_id, int goods_id, int com_id,String text) {
        try {
            sys.addComment(user_id,goods_id,com_id,text);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean modifyPass(int user_id, String password) {
        try {
            sys.modifyUserPass(user_id,password);
            return true;
        }catch (Exception e){
            return  false;
        }
    }
}
