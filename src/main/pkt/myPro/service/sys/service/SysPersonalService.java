package myPro.service.sys.service;

import myPro.bean.sys.HistoryGoods;

import java.util.List;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  22:20
 */
public interface SysPersonalService {

    /**
     * 得到历史记录信息
     * @param user_id
     * @return
     */
    List<HistoryGoods> getHistoryGoods(int user_id);

    /**
     * 添加记录
     * @param user_id
     * @param goods_id
     * @param com_id
     * @param text
     * @return
     */
    boolean addComment(int user_id,int goods_id,int com_id,String text);

    /**
     * 修改密码
     * @param user_id
     * @param password
     * @return
     */
    boolean modifyPass(int user_id,String password);

}
