package com.cs.annotation;


import java.lang.annotation.ElementType;    
import java.lang.annotation.Retention;    
import java.lang.annotation.RetentionPolicy;    
import java.lang.annotation.Target; 

/**
 * 
 * 名称：XStreamCDATA.java<br>
 * 描述：返回  <![CDATA[*]]>
 * 最近修改时间: 2017年11月22日 下午1:49:40 <br>
 * @since  2017年11月22日
 * @authour cs
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface XStreamCDATA {

}
