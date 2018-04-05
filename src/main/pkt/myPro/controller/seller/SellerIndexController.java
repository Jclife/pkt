package myPro.controller.seller;

import myPro.bean.seller.Goods;
import myPro.bean.seller.Store;
import myPro.service.seller.service.SellerIndexService;
import myPro.utils.seller.SellerPageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/2/5  17:16
 */

@RequestMapping("seller")
@Controller
public class SellerIndexController implements SellerPageManager{

    @Autowired
    SellerIndexService service;

    static void setModel(Model model,Store store){
        model.addAttribute("sellerName",store.getStore_name());
        model.addAttribute("store_info",store);
    }

    @RequestMapping("index")
    public String index(HttpSession session, Model model){
        Store store = (Store) session.getAttribute("sellerInfo");
        if (store!=null&&store.getStore_name()!=null){
            List<Goods> hotList = service.hotSaleList(store.getStore_id());
            if (hotList!=null){
                model.addAttribute("hotList",hotList);
                float money = service.saleMoney(hotList);
                int number = service.allSaleNum(hotList);
                int commentNum = service.commentNum(hotList);
                model.addAttribute("money",money);
                model.addAttribute("allNum",number);
                model.addAttribute("commentNum",commentNum);
            }
            List<Map<String,Object>> moneyList = service.moneySaleList(store.getStore_id());
            if (moneyList!=null){
                model.addAttribute("moneyList",moneyList);
            }
            setModel(model,store);
            return INDEX;
        }else {
            return REDIRECT_LOGIN;
        }
    }










}
