package com.xyt.util;


import java.io.Reader;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;


/**
 * 
 * json解析工具类
 * 
 * @author denlgei
 */
public class GsonUtil
{
    /**
     * Logger for this class
     */
    
    private static SerializeConfig mapping = new SerializeConfig();
    
    // 日期格式化
    static
    {
        mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
        mapping.put(Timestamp.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
    }
    
    public static <T> T httpRequest2Request(HttpServletRequest httpRequest, Class<T> clazz) throws Exception
    {
        Reader reader = null;
        T req = null;
        
        try
        {
            reader = httpRequest.getReader();
            String text = IOUtils.toString(reader);
            
            if (null == text || text.length() == 0)
            {
                return null;
            }
            req = fromJson(text, clazz);
        }
        catch (Exception e)
        {
        	e.getMessage();
            return null;
        }
        finally
        {
            if (reader != null)
            {
                IOUtils.closeQuietly(reader);
            }
            
        }
       
        return req;
    }
    
    /**
     * 把json串转为指定的对象
     * 
     * @param <T>
     */
    public static <T> T fromJson(String str, Class<T> clazz)
    throws Exception
    {
        T t = JSON.parseObject(str, clazz);
        return t;
    }
    
    /**
     * 把对象转为json串
     */
    public static String toJson(Object obj)
    {
        return JSON.toJSONString(obj, mapping);
    }
   
}
