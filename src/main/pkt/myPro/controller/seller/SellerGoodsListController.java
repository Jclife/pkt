package myPro.controller.seller;

import myPro.bean.seller.Goods;
import myPro.bean.seller.Store;
import myPro.service.seller.service.SellerGoodsListService;
import myPro.utils.seller.SellerPageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  17:04
 */

@RequestMapping("seller")
@Controller
public class SellerGoodsListController implements SellerPageManager{
    @Autowired
    SellerGoodsListService service;

    static void setModel(Model model,Store store){
        model.addAttribute("sellerName",store.getStore_name());
        model.addAttribute("store_info",store);
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




}
