package com.guohaoyu.utils;

import org.apache.commons.beanutils.BeanUtils;
import java.util.Map;

public class WebUtils {
    public static <T> T mapCopyToUserBean(T obj, Map map){
        try {
            BeanUtils.populate(obj,map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }
    public static Integer IntegerParseInt(String str,Integer defaultValue){
        try{
            Integer i = Integer.parseInt(str);
            return i;
        }catch (Exception e){
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
