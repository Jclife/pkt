package myPro.service.sys.impl;

import myPro.bean.seller.Goods;
import myPro.bean.sys.HistoryGoods;
import myPro.dao.sys.SysShopDetailDao;
import myPro.service.sys.service.SysShopDetailService;
import myPro.utils.common.DataConversion;
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
public class SysShopDetailImpl implements SysShopDetailService{

    @Autowired
    SysShopDetailDao sys;

    public Goods getGoodsInId(int id, int is_online) {
        return sys.getGoodsInId(id,is_online);
    }

    public List<String> shopDetailImgLists(Goods goods) {
        String img = goods.getGoods_imgs();
        List<String> list = DataConversion.jsonToList(img);
        return list;
    }

    public List<HistoryGoods> getShopComment(int goods_id) {
        List<HistoryGoods> result =sys.getShopComment(goods_id);
        for (int i = 0; i <result.size() ; i++) {
            if (result.get(i).getContent()==null){
                result.remove(i);
            }
        }
        return result;
    }
}
