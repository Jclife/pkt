package myPro.controller.seller;

import myPro.bean.seller.Goods;
import myPro.bean.seller.Store;
import myPro.service.seller.service.SellerCompletService;
import myPro.utils.common.SessionUtils;
import myPro.utils.seller.FastDFSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/2/6  22:19
 */
@RequestMapping("seller")
@Controller
public class SellerOperationController {


    @Autowired
    SellerCompletService service;

    @RequestMapping("upImg")
    @ResponseBody
    public Map uploadImg(@RequestParam(required = false)MultipartFile file, Model model) throws IOException {

        Map<String,String> map = new HashMap<String, String>();
        byte[] bytes = file.getBytes();
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
        FastDFSUtils utils = new FastDFSUtils();
        try {
            String url = utils.getPicUrl(bytes,ext);
            map.put("url",url);
            map.put("code","1");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("img",null);
            map.put("code","0");
        }
        return map;
    }

    @RequestMapping("subInfo")
    @ResponseBody
    public Map subInfo(Store store, HttpSession session){

        Map<String,String> map = new HashMap<String, String>();
        String store_id = SessionUtils.getSessionAtt(session,"sellerInfo","store_id");
        store.setStore_id(Integer.parseInt(store_id));
        if (service.completSeller(store)){
            map.put("status","success");
            session.setAttribute("sellerInfo",store);
        }else {
            map.put("status","error");
        }
        return map;
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

    @RequestMapping("modifyBase")
    @ResponseBody
    public String modifyBase(Goods goods){
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
