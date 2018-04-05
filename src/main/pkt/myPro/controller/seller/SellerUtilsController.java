package myPro.controller.seller;

import myPro.utils.seller.FastDFSUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
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
public class SellerUtilsController {

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
}
