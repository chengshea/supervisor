package com.cs.util;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resources;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cs.constant.MsgType;
import com.cs.dto.wx.InputMessage;
import com.cs.dto.wx.OutputImageMessage;
import com.cs.dto.wx.OutputTextMessage;
import com.cs.dto.wx.OutputVideoMessage;
import com.cs.model.UserInfo;
import com.cs.pojo.WXUserInfo;
import com.cs.service.UserInfoService;
import com.cs.service.WXService;
import com.thoughtworks.xstream.XStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 
 * 描述：处理微信消息
 * 最近修改时间: 2017年11月22日 下午2:05:42 <br>
 * @since  2017年11月22日
 * @authour cs
 */

public class WXMessage {


	private static Logger logger = LoggerFactory.getLogger(WXMessage.class);

	 public static String getWeixinOpenId(HttpServletRequest request) {
			String openId = request.getParameter("openid");
			if(StringUtils.isEmpty(openId) ||"null".equals(openId)){
				openId = request.getSession().getAttribute("openid")+"";
			}
			if(!StringUtils.isEmpty(openId) &&!"null".equals(openId.toLowerCase())){
				setWeixinOpenId(request,openId);
			}else{
				openId = "";
			}
			return openId;
		}
	    private static void setWeixinOpenId(HttpServletRequest request,String openid) {
			if(!StringUtils.isEmpty(openid) &&!"null".equals(openid.toLowerCase())){
				request.getSession().setAttribute("openid",openid);
			}
		}
	

    public static    void   text( XStream xs,String server,String openId,Long time,HttpServletResponse response) throws IOException{
        String xml = " ";
        // 文本消息
        xs.processAnnotations(OutputTextMessage.class);
        xs.alias("xml", OutputTextMessage.class);

        OutputTextMessage  msg = new OutputTextMessage();
        msg.setFromUserName(server);
        msg.setToUserName(openId);
        msg.setCreateTime(time);
        msg.setMsgType(MsgType.Text);
        msg.setContent("回应： ");

        try{
            xml = xs.toXML(msg);
        }catch(Exception e){
            e.printStackTrace();
        }

        response.getWriter().write(xml);
    }


    public static    void   image( XStream xs,String server,String openId,Long time,HttpServletResponse response) throws IOException{
        xs.processAnnotations(OutputImageMessage.class);
        xs.alias("xml", OutputImageMessage.class);
        OutputImageMessage msg = new OutputImageMessage();
        msg.setFromUserName(server);
        msg.setToUserName(openId);
        msg.setCreateTime(time);
        msg.setMsgType(MsgType.Image);
        OutputImageMessage.Image im = new OutputImageMessage.Image();
        im.setMediaId(" =====");
        msg.setImage(im);
        String xml = " ";
        try{
            xml = xs.toXML(msg);
        }catch(Exception e){
            e.printStackTrace();
        }

        response.getWriter().write(xml);
    }



    public static    void   music( XStream xs,String server,String openId,Long time,HttpServletResponse response) throws IOException{
        response.getWriter().write(" ");
    }


    public static    void   video( XStream xs,String server,String openId,Long time,HttpServletResponse response) throws IOException{
        xs.processAnnotations(OutputVideoMessage.class);
        xs.alias("xml", OutputVideoMessage.class);
        OutputVideoMessage msg = new OutputVideoMessage();
        msg.setFromUserName(server);
        msg.setToUserName(openId);
        msg.setCreateTime(time);
        msg.setMsgType(MsgType.Video);
        OutputVideoMessage.Video  m = new OutputVideoMessage.Video();
        m.setTitle("测试");
        m.setDescription("来了测试");
        m.setMediaId("=========");
        msg.setVideo(m);
        String xml = " ";
        try{
            xml = xs.toXML(msg);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.err.println(xml);
        response.getWriter().write(xml);
    }



    public static    void   address( InputMessage inputMsg,HttpServletResponse response) throws IOException{
        String locationX = inputMsg.getLocationX();
        String locationY = inputMsg.getLocationY();

        String add = GetLocation.admName(locationY, locationX);
        String[] arr = add.split(",");
        if( arr.length > 1){
            System.out.println("省:"+arr[0]+"\n市:"+arr[1]+"\n区:"+arr[2]);
        }
        response.getWriter().write(" ");
    }



    public static void subscribe(XStream xs,String server,String openId,Long time,WXService wx,
    		UserInfoService user,SnowflakeIdWorker id,HttpServletResponse response) throws IOException{
        UserInfo bu = new UserInfo();
        WXUserInfo info = wx.getUserInfo(openId);
        logger.info("\n wx : "+info);
        bu.setUsername(info.getNickname());
        bu.setOpenId(openId);
        bu.setSex(info.getSex());
        bu.setUnionId(info.getUnionid());
        bu.setCreateDate(new Date());
        bu.setId(id.nextId());
        Long s = user.save(bu);
        if(!StringUtils.isBlank(s+"")){
            text(xs,server,openId,time,"ID:"+s,response);
        }else {
            response.getWriter().write(" ");
        }
    }



    public  static void   text(XStream xs,String server,String openId,Long time,String text, HttpServletResponse response) throws IOException{
        // 文本消息
        xs.processAnnotations(OutputTextMessage.class);
        xs.alias("xml", OutputTextMessage.class);

        OutputTextMessage  msg = new OutputTextMessage();
        msg.setFromUserName(server);
        msg.setToUserName(openId);
        msg.setCreateTime(time);
        msg.setMsgType(MsgType.Text);
        msg.setContent(text);
        String xml = null;
        try{
            xml = xs.toXML(msg);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(" xml === \n: "+xml);
        response.getWriter().write(xml);
    }
}
