package myPro.service.sys.impl;

import myPro.dao.sys.SysCheckDao;
import myPro.service.sys.service.SysCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  22:35
 */
@Service
public class SysCheckImpl implements SysCheckService{

    @Autowired
    SysCheckDao sys;

    public boolean insertComment(int user_id, int goods_id, int num, String userInfo, Timestamp time) {
        try {
            sys.insertCommentTab(user_id,goods_id,num,userInfo,time);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean saleGoodsCount(int goods_id,int num) {
        try {
            sys.saleGoodsCount(goods_id,num);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
