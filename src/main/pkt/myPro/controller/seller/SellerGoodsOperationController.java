package myPro.controller.seller;

import myPro.bean.seller.GoodsOp;
import myPro.bean.seller.Store;
import myPro.service.seller.service.SellerGoodsOperationService;
import myPro.utils.seller.SellerPageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/4/6  22:27
 */

@RequestMapping("seller")
@Controller
public class SellerGoodsOperationController implements SellerPageManager{

    @Autowired
    SellerGoodsOperationService service;
    static void setModel(Model model, Store store){
        model.addAttribute("sellerName",store.getStore_name());
        model.addAttribute("store_info",store);
    }

    @RequestMapping("operation")
    public String toOperation(HttpSession session, Model model){
        Store store = (Store) session.getAttribute("sellerInfo");
        if (store!=null&&store.getStore_name()!=null){
            setModel(model,store);
            List<GoodsOp> allGoods = service.getOperationGood(store.getStore_id(),0,0,999);
            List<GoodsOp> waitSendGoods = service.getOperationGood(store.getStore_id(),0,0,6);
            model.addAttribute("waitSendGoods",waitSendGoods);
            model.addAttribute("count",allGoods.size());
            model.addAttribute("status","1");
            return GOODS_OPERATION;
        }else {
            return REDIRECT_LOGIN;
        }
    }

    @RequestMapping(value = "operation/{type}/{page}/{limit}")
    public String operations(HttpSession session,@PathVariable("type")  int type,
                             @PathVariable("page") int page,
                             @PathVariable("limit") int limit,Model model){
        Store store = (Store) session.getAttribute("sellerInfo");
        if (store!=null&&store.getStore_name()!=null) {
            setModel(model, store);
            List<GoodsOp> resultGoods ;
            List<GoodsOp> allGoods;
            if (type==1){
                resultGoods = service.getOperationGood(store.getStore_id(),0,(limit*(page-1)),limit);
                allGoods = service.getOperationGood(store.getStore_id(),0,0,999);
                model.addAttribute("status","1");
            }else if (type==2){
                resultGoods = service.getOperationGood(store.getStore_id(),1,(limit*(page-1)),limit);
                allGoods = service.getOperationGood(store.getStore_id(),1,0,999);
                model.addAttribute("status","2");
            }else if (type==3){
                resultGoods = service.getOperationGood(store.getStore_id(),2,(limit*(page-1)),limit);
                allGoods = service.getOperationGood(store.getStore_id(),2,0,999);
                model.addAttribute("status","3");
            }else {
                return REDIRECT_INDEX;
            }
            model.addAttribute("waitSendGoods",resultGoods);
            model.addAttribute("count",allGoods.size());
            return GOODS_OPERATION;
        }else {
            return REDIRECT_LOGIN;
        }
    }


    @RequestMapping("confirmDelivery")
    @ResponseBody
    public String confirmDelivery(int id){
        boolean br = service.confirmDelivery(id);
        return br?"ok":"error";
    }
}
