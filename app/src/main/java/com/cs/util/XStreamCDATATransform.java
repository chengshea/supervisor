package com.cs.util;

import java.io.Writer;  
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


import com.cs.dto.wx.OutputNewsMessage;
import com.cs.annotation.XStreamCDATA;
import com.thoughtworks.xstream.XStream;  
import com.thoughtworks.xstream.annotations.XStreamAlias;  
import com.thoughtworks.xstream.core.util.QuickWriter;  
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;  
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;  
import com.thoughtworks.xstream.io.xml.XppDriver;  

/**
 * 
 * 名称：SerializeXmlUtil.java<br>
 * 描述：xml 解析
 * 最近修改时间: 2017年11月22日 上午8:55:25 <br>
 * @since  2017年11月22日
 * @authour cs
 */
public class XStreamCDATATransform {

	 /**
	  * 
	 * @Description: 有bug ，带解决
	 * @param @return    
	 * @return XStream    
	 * @throws 
	 * @date  2017年11月22日 下午11:19:23
	  */
    public static XStream createXstream() {    
        return new XStream(new XppDriver() {    
            @Override    
            public HierarchicalStreamWriter createWriter(Writer out) {    
                return new PrettyPrintWriter(out) {    
                    boolean cdata = false;    
                    Class<?> targetClass = null;    
    
                    @Override    
                    public void startNode(String name, @SuppressWarnings("rawtypes") Class clazz) {    
                        super.startNode(name, clazz);    
                        // 业务处理，对于用XStreamCDATA标记的Field，需要加上CDATA标签    
                        if (!name.equals("xml")) {    
                            cdata = needCDATA(targetClass, name);    
                        } else {    
                            targetClass = clazz;    
                        }    
                    }    
    
                    @Override
                    public void setValue(String text) {
                        if(text!=null && !"".equals(text)){
                            //浮点型判断
                            Pattern patternInt = Pattern.compile("[0-9]*(\\.?)[0-9]*");
                            //整型判断
                            Pattern patternFloat = Pattern.compile("[0-9]+");
                            //如果是整数或浮点数 就不要加[CDATA[]了
                            if(patternInt.matcher(text).matches() || patternFloat.matcher(text).matches()){
                                cdata = false;
                            }else{
                                 cdata = true;
                            }
                        }
                        super.setValue(text);  
                    }
                    
                    @Override    
                    protected void writeText(QuickWriter writer, String text) {    
                        if (cdata) {    
                            writer.write("<![CDATA[");    
                            writer.write(text);    
                            writer.write("]]>");    
                        } else {    
                            writer.write(text);    
                        }    
                    }    
                };    
            }    
        });    
    }    
     
    private static boolean needCDATA(Class<?> targetClass, String fieldAlias) {    
        boolean cdata = false;    
        // first, scan self    
        cdata = existsCDATA(targetClass, fieldAlias);    
        if (cdata)    
            return cdata;    
        // if cdata is false, scan supperClass until java.lang.Object    
        Class<?> superClass = targetClass.getSuperclass();    
        while (!superClass.equals(Object.class)) {    
            cdata = existsCDATA(superClass, fieldAlias);    
            if (cdata)    
                return cdata;    
            superClass = superClass.getClass().getSuperclass();    
        }    
        return false;    
    }    
    
    private static boolean existsCDATA(Class<?> clazz, String fieldAlias) {    
        if ("MediaId".equals(fieldAlias)) {    
            return true;    
        }    
        // scan fields    
        Field[] fields = clazz.getDeclaredFields();    
        for (Field field : fields) {    
            // 1. exists XStreamCDATA    
            if (field.getAnnotation(XStreamCDATA.class) != null) {    
                XStreamAlias xStreamAlias = field.getAnnotation(XStreamAlias.class);    
                // 2. exists XStreamAlias    
                if (null != xStreamAlias) {    
                    if (fieldAlias.equals(xStreamAlias.value()))// matched    
                        return true;    
                } else {// not exists XStreamAlias    
                    if (fieldAlias.equals(field.getName()))    
                        return true;    
                }    
            }    
        }    
        return false;    
    }    
    
   

}
