package myPro.controller.seller;

import myPro.bean.seller.Goods;
import myPro.bean.seller.Store;
import myPro.service.seller.service.SellerModifyGoodsService;
import myPro.utils.seller.SellerPageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/5  17:05
 */

@RequestMapping("seller")
@Controller
public class SellerModifyGoodsController implements SellerPageManager{

    @Autowired
    SellerModifyGoodsService service;

    static void setModel(Model model, Store store){
        model.addAttribute("sellerName",store.getStore_name());
        model.addAttribute("store_info",store);
    }

    @RequestMapping(value = "modify/{id}")
    public String modifyGoods(@PathVariable("id") int id, Model model, HttpSession session){

        Store store = (Store) session.getAttribute("sellerInfo");
        if (store!=null&&store.getStore_name()!=null){
            Goods modifyGood=service.getModifyGoods(id);
            System.out.println(modifyGood.toString());
            setModel(model,store);
            model.addAttribute("modifyGood",modifyGood);
            return MODIFY_GOODS;
        }else {
            return REDIRECT_LOGIN;
        }

    }

    @RequestMapping("modifyBase")
    @ResponseBody
    public String modifyBase(Goods goods){
        System.out.println(goods.toString()+"    修改");
        if (service.modifyBase(goods)){
            return "success";
        }else {
            return "error";
        }
    }

    @RequestMapping("modifyPic")
    @ResponseBody
    public String modifyPic(String imgJson,int id){
        if (service.modifyPic(id,imgJson)){
            return "success";
        }else {
            return "error";
        }
    }

    @RequestMapping("deleteGood")
    @ResponseBody
    public String deleteGood(int id){
        boolean br = false;
        if (service.deleteGood(id)){
            br = true;
        }
        return br?"success":"error";
    }

}
