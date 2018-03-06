package myPro.utils.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xerces.internal.xs.StringList;
import myPro.bean.seller.Goods;
import myPro.bean.seller.GoodsPo;
import myPro.bean.sys.Cart;
import myPro.bean.sys.CartPo;
import myPro.service.sys.service.SysCompletService;
import org.apache.commons.beanutils.BeanUtils;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description: 数据转换工具类
 * @date 2018/2/3  20:36
 */
public class DataConversion {



    /**
     * object 转 map
     * @param object
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String,Object> objToMap(Object object) throws IllegalAccessException {
        Map<String,Object> map = new HashMap<String,Object>();
        Class<?> clazz = object.getClass();
        for (Field field:clazz.getDeclaredFields()){
            field.setAccessible(true);
            String fileName = field.getName();
            Object value = field.get(object);
            map.put(fileName,value);
        }
        return map;
    }

    /**
     * map转 object
     * @param map
     * @param bean
     * @return
     * @throws Exception
     */
    public static Object mapToObj(Map<String,Object> map,Class<?> bean) throws Exception{
        if (map==null){
            return null;
        }
        Object obj = bean.newInstance();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] property = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pro:property){
            Method setter = pro.getWriteMethod();
            if (setter!=null){
                setter.invoke(obj,map.get(pro.getName()));
            }
        }
        return obj;
    }

    /**
     * map转string
     * @param map
     * @return
     * @throws JsonProcessingException
     */
    public static String mapToJson(Map<String,String> map) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json =mapper.writeValueAsString(map);
        return json;
    }

    /**
     * json 转 map
     * @param json
     * @return
     * @throws IOException
     */
    public static Map<String,String> jsonToMap(String json) throws IOException {

        Map<String,String> map ;
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(json, new TypeReference<HashMap<String,String>>(){});
        return map;
    }

    /**
     * Bean 转 map<String,String>
     * @param object
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public static Map<String,String> objToMapStr(Object object) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (object==null){
            return null;
        }else {
            Map<String,String> map = BeanUtils.describe(object);
            return map;
        }
    }

    /**
     * 图片json的转换
     * @param json
     * @return
     */
    public static List<String> jsonToList(String json){
        List<String> result = new ArrayList<String>();
        JSONArray jsonList = JSON.parseArray(json);
        for(int i=0;i<jsonList.size();i++){
            result.add(i,(String) jsonList.get(i));
        }
        return result;
    }

    /**
     * map<String,String> 转 bean
     * @param map
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T  mapStrToObj(Map<String,String> map,Class<T> clazz) throws Exception {
        T bean = null;
        bean=clazz.newInstance();
        BeanUtils.populate(bean,map);
        return bean;
    }


}
