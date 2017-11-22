package com.cs.util;

import java.io.Writer;  
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.cs.annotation.XStreamCDATA;
import com.cs.pojo.Item;
import com.cs.pojo.OutputNewsMessage;
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
public class SerializeXmlUtil {

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
    
    
    public static void main(String[] args) {
		 XStream xs = SerializeXmlUtil. createXstream(); 
		 xs.processAnnotations(OutputNewsMessage.class);
   	 xs.alias("xml", OutputNewsMessage.class); 
   	 //xs.autodetectAnnotations(true);
   	 OutputNewsMessage nm = new OutputNewsMessage();
   	 nm.setToUserName("toUserName");
   	 nm.setFromUserName("fromUserName");
   	 nm.setMsgType("news");
   	 nm.setCreateTime(System.nanoTime());
   	 nm.setArticleCount(3);
   	 List<Item>  li = new ArrayList<Item>();
   	 for (int i = 0; i < 3; i++) {
			Item item = new Item();
			item.setTitle("文档"+1);
			item.setPicUrl(i+"pu");
			item.setUrl("url");
			item.setDescription("desc");
			li.add(item);
		}
   	  nm.setArticles(li);
   
   	 String xml = " ";
        try{
           xml = xs.toXML(nm);
        }catch(Exception e){
      	  e.printStackTrace();
        }
        System.err.println(xml);
	}

}
