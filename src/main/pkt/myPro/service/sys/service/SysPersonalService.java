package myPro.service.sys.service;

import myPro.bean.sys.HisCartPo;
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
     * @param isGetGoods 是否已经收货 已经收货的传入1 待收货传入2
     * @return
     */
    List<HistoryGoods> getHistoryGoods(int user_id,int isGetGoods);

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

    /**
     * 得到传入前台的待收货列表和历史订单列表
     * @return
     */
    List<HisCartPo> getGoodsList(List<HistoryGoods> goodsList,SysUtilService utilService);

    /**
     * 确认收货service
     * @param com_id
     * @return
     */
    boolean changeGoodsStatus(int com_id);
}
