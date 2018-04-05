package myPro.controller.seller;

import myPro.bean.seller.Goods;
import myPro.bean.seller.Store;
import myPro.service.seller.service.SellerEditService;
import myPro.utils.common.SessionUtils;
import myPro.utils.seller.SellerPageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  17:01
 */
@RequestMapping("seller")
@Controller
public class SellerEditController implements SellerPageManager{

    @Autowired
    SellerEditService service;

    static void setModel(Model model,Store store){
        model.addAttribute("sellerName",store.getStore_name());
        model.addAttribute("store_info",store);
    }

    @RequestMapping("edit")
    public String edit(HttpSession session, Model model){
        Store store = (Store) session.getAttribute("sellerInfo");
        if (store!=null&&store.getStore_name()!=null){
            setModel(model,store);
            return EDIT;
        }else {
            return REDIRECT_LOGIN;
        }
    }

    @RequestMapping("shelves")
    @ResponseBody
    public Map shelves(Goods goods, HttpSession session){
        Map<String,String> map = new HashMap<String, String>();
        String store_id = SessionUtils.getSessionAtt(session,"sellerInfo","store_id");
        goods.setStore_id(Integer.parseInt(store_id));
        if (service.shelves(goods)){
            map.put("status","success");
        }else {
            map.put("status","error");
        }
        return map;
    }
}
