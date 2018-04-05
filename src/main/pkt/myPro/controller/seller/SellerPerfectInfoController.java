package myPro.controller.seller;

import myPro.bean.seller.Store;
import myPro.service.seller.service.SellerPerfectInfoService;
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
 * @date 2018/4/5  16:58
 */
@RequestMapping("seller")
@Controller
public class SellerPerfectInfoController implements SellerPageManager{

    @Autowired
    SellerPerfectInfoService service;

    @RequestMapping("perfect")
    public String perfect(HttpSession session, Model model){
        Store store = (Store) session.getAttribute("sellerInfo");
        if (store!=null&&store.getStore_name()!=null){
            String tags = SessionUtils.getSessionAtt(session,"sellerInfo","store_tags");
            if (tags!=null) {
                String[] tmpTags = tags.split("/");
                String[] strTags = {"家常", "高定", "私人厨房", "精致"};
                String[] eTags = {"home", "high_set", "PK", "delicate"};
                String stags, store_tags = "";
                for (int i = 0; i < tmpTags.length; i++) {
                    for (int j = 0; j < eTags.length; j++) {
                        if (eTags[j].equals(tmpTags[i])) {
                            stags = strTags[j] + "、";
                            store_tags += stags;
                        }
                    }
                }
                if (store_tags.length() > 1) {
                    store_tags = store_tags.substring(0, store_tags.length() - 1);
                }
                model.addAttribute("tags",store_tags);
            }
            model.addAttribute("sellerName",store.getStore_name());
            model.addAttribute("store_info",store);
            return PERFECT;
        }else {
            return REDIRECT_LOGIN;
        }
    }

    @RequestMapping("subInfo")
    @ResponseBody
    public Map subInfo(Store store, HttpSession session){
        Map<String,String> map = new HashMap<String, String>();
        String store_id = SessionUtils.getSessionAtt(session,"sellerInfo","store_id");
        store.setStore_id(Integer.parseInt(store_id));
        if (service.completeSeller(store)){
            map.put("status","success");
            session.setAttribute("sellerInfo",store);
        }else {
            map.put("status","error");
        }
        return map;
    }
}
