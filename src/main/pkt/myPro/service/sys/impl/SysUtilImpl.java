package myPro.service.sys.impl;

import myPro.bean.seller.Goods;
import myPro.bean.seller.GoodsPo;
import myPro.service.sys.service.SysShopDetailService;
import myPro.service.sys.service.SysUtilService;
import myPro.service.sys.service.SysUserLoginAndRegisterService;
import myPro.utils.common.DataConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  22:36
 */

@Service
public class SysUtilImpl implements SysUtilService {


    @Autowired
    SysShopDetailService sys;
    @Autowired
    SysUserLoginAndRegisterService sysUserLoginAndRegisterService;
    /**
     * 对主界面商品展示的封装
     * @param jsonList
     * @param list
     * @return
     */
    public List<GoodsPo> setImgJsonInIndex(List<List<String>> jsonList, List<Goods> list) {
        List<GoodsPo> resultList = new ArrayList<GoodsPo>();
        for (int i = 0; i <list.size() ; i++) {
            jsonList.add(DataConversion.jsonToList(list.get(i).getGoods_imgs()));
        }
        for (int i = 0; i <jsonList.size() ; i++) {
            GoodsPo goods = new GoodsPo();
            goods.setGoods(list.get(i));
            if (list.get(i).getGoods_price_now()==list.get(i).getGoods_price()){
                goods.setPromotion(false);
            }else {
                goods.setPromotion(true);
            }
            if (list.get(i).getGoods_sale_count()>=100){
                goods.setHotSale(true);
            }else {
                goods.setHotSale(false);
            }
            if (jsonList.get(i)!=null){
                if (jsonList.get(i).size()>1){
                    List<String> tmpList=new ArrayList<String>();
                    tmpList.add(jsonList.get(i).get(0));
                    goods.setList(tmpList);
                    tmpList=null;
                }else {
                    goods.setList(jsonList.get(i));
                }
            }
            resultList.add(goods);
        }
        return resultList;
    }

    public Goods getGoodsInId(int id,int is_online) {
        return sys.getGoodsInId(id,is_online);
    }
}
