package myPro.service.sys.impl;

import myPro.bean.seller.Goods;
import myPro.dao.sys.SysIndexDao;
import myPro.service.sys.service.SysIndexService;
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
public class SysIndexImpl implements SysIndexService{

    @Autowired
    SysIndexDao sys;

    public List<Goods> getAllGoodsInIndex(int limit, int last) {
        return sys.getAllGoodsInIndex(limit,last);
    }
}
