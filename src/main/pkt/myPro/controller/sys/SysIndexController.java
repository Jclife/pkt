package myPro.controller.sys;

import myPro.bean.seller.Goods;
import myPro.bean.seller.GoodsPo;
import myPro.bean.sys.CartPo;
import myPro.bean.sys.User;
import myPro.service.sys.service.SysCartService;
import myPro.service.sys.service.SysCompletService;
import myPro.service.sys.service.SysIndexService;
import myPro.service.sys.service.SysUtilService;
import myPro.utils.sys.SysPageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description: 首页模块
 * @date 2018/4/5  21:44
 */

@RequestMapping("pkt")
@Controller
public class SysIndexController implements SysPageManager{


    @Autowired
    SysIndexService service;
    @Autowired
    SysUtilService utilService;
    @Autowired
    SysCartService cartService;

    @RequestMapping("index")
    public String to_index(HttpSession session, Model model){
        List<Goods> list = service.getAllGoodsInIndex(8,-1); //得到8个goods列表
        model.addAttribute("goodsList",list);
        List<List<String>> jsonList = new ArrayList<List<String>>();
        List<GoodsPo> resultList = utilService.setImgJsonInIndex(jsonList,list);
        model.addAttribute("result",resultList);
        User user = (User) session.getAttribute("userInfo");
        Map<Integer,CartPo> sessionMap = (Map<Integer, CartPo>) session.getAttribute("cartMap");//购物车session
        cartService.addShopCartMethod(user,cartService,utilService,sessionMap,model,session);
        return INDEX;
    }
}
