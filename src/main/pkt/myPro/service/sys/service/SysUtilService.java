package myPro.service.sys.service;

import myPro.bean.seller.Goods;
import myPro.bean.seller.GoodsPo;

import java.util.List;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  22:23
 */
public interface SysUtilService {

    /**
     * 对主界面商品展示的封装
     * @param jsonList
     * @param list
     * @return
     */
    List<GoodsPo> setImgJsonInIndex(List<List<String>> jsonList, List<Goods> list);

    /**
     * 通过id得到菜品数据
     * @param id
     * @param is_online
     * @return
     */
    Goods getGoodsInId(int id, int is_online);
}
