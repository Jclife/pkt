package myPro.controller.seller;

import myPro.bean.seller.Goods;
import myPro.bean.seller.Store;
import myPro.service.seller.service.SellerCompletService;
import myPro.utils.common.SessionUtils;
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
public class SellerGoToController implements SellerPageManager{

    @Autowired
    SellerCompletService service;

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

    @RequestMapping("forget_pass")
    public String forgetPass(){
        return FORGET_PASS;
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

    @RequestMapping("list")
    public String list(HttpSession session, Model model){
        Store store = (Store) session.getAttribute("sellerInfo");
        if (store!=null&&store.getStore_name()!=null){
            setModel(model,store);
            List<Goods> list = service.getGoodsList(store.getStore_id());
            if (list!=null){
                model.addAttribute("count",list.size());
                model.addAttribute("list",list);
            }
            return LIST;
        }else {
            return REDIRECT_LOGIN;
        }
    }

    @RequestMapping(value = "find/{content}/{type}/{page}/{limit}")
    public String listFind(HttpSession session, Model model,
                           @PathVariable("content")  String content,
                           @PathVariable("type") String type,
                           @PathVariable("page") int page,
                           @PathVariable("limit") int limit){

        Store store = (Store) session.getAttribute("sellerInfo");
        if (store!=null&&store.getStore_name()!=null){
            setModel(model,store);
            if ("all".equals(content)){
                content="";
            }
            List<Goods> list = service.findGoodsList(store.getStore_id(),content,Integer.parseInt(type),(limit*(page-1)),limit);
            List<Goods> allList = service.findGoodsList(store.getStore_id(),content,Integer.parseInt(type),-1,-1);
            if (list!=null){
                model.addAttribute("count",allList.size());
                model.addAttribute("list",list);
                return LIST;
            }else {
                return REDIRECT_LIST;
            }
        }else {
            return REDIRECT_LOGIN;
        }
    }

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
            setModel(model,store);
            return PERFECT;
        }else {
            return REDIRECT_LOGIN;
        }
    }

    @RequestMapping("login")
    public String login(){
        return LOGIN;
    }

    @RequestMapping("register")
    public String register(){
        return REGISTER;
    }



}
