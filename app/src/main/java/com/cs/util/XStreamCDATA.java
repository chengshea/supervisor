package com.cs.util;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.cs.pojo.Item;
import com.cs.pojo.OutputNewsMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class XStreamCDATA {

	
	 public static XStream createXstream() {    
	        return new XStream(new XppDriver() {  
	     @Override    
        public HierarchicalStreamWriter createWriter(Writer out) {  
            return new PrettyPrintWriter(out) {  
                // 对那些xml节点的转换增加CDATA标记   true增加  false反之
                boolean cdata = false;  
  
                @Override
                public void startNode(String name, Class clazz) {  
		            if(!name.equals("xml")){
		                char[] arr = name.toCharArray();        
		                if (arr[0] >= 'a' && arr[0] <= 'z') {
		                   //arr[0] -= 'a' - 'A';
		                //ASCII码，大写字母和小写字符之间数值上差32
		                arr[0] = (char) ((int) arr[0] - 32);
		                }
		                name = new String(arr);
		                }
		          super.startNode(name, clazz);  
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
  
	 
	 public static void main(String[] args) {
		 XStream xs = XStreamCDATA. createXstream(); 
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
