package com.cs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.service.UserInfoService;
import com.cs.service.WXService;
import com.cs.constant.Event;
import com.cs.constant.MsgType;
import com.cs.dto.Message;
import com.cs.dto.wx.InputMessage;
import com.cs.model.UserInfo;
import com.cs.pojo.WXUserInfo;
import com.cs.util.XStreamCDATATransform;
import com.cs.util.Check;
import com.cs.util.OAuth;
import com.cs.util.SnowflakeIdWorker;
import com.cs.util.WXMessage;
import com.thoughtworks.xstream.XStream;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Controller
public class IndexController {
   
	private static Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private WXService  wx;
	@Autowired
	private UserInfoService userInfo;
	
	@Autowired
	private SnowflakeIdWorker id;
	
	    @RequestMapping("/")
	    public String index(Map<String,Object> map) {
	 
//	        return "index";
	    	return "/user/user-list";
	    }
	    
	    @RequestMapping("/api-ui")
	    public String api() {
	        return "forward:/swagger-ui.html";
	    }
	    
	    @GetMapping(value="/api/redirect")
	    public void redirect(HttpServletRequest request,HttpServletResponse response){
		        // 微信加密签名
		        String signature = request.getParameter("signature");
		        // 时间戳
		        String timestamp = request.getParameter("timestamp");
		        // 随机数
		        String nonce = request.getParameter("nonce");
		        // 随机字符串
		        String echostr = request.getParameter("echostr");
		        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		        if (signature != null && Check.checkSignature(signature, timestamp, nonce)) {
		                   //应答服务器信息
		                try {
							response.getWriter().write(echostr);
						} catch (IOException e) {
							e.printStackTrace();
						}
		             
		        }
	       
	       
	    } 
	    
       @PostMapping(value="/api/redirect")
       public void postRedirect(HttpServletRequest request,HttpServletResponse response){
    	   try {    
               // 接收post消息并返回消息
              acceptMessage(request, response);
           } catch (IOException e) {    
               e.printStackTrace();    
           }    
       }
	    
	    /**
		  * 
		  * 描述： 链接进入
		  * @param  String 
		  * @变更记录 2017年11月23日 下午4:28:05 cs创建
		  */
		 @RequestMapping(value="/register",method=RequestMethod.GET)
		 public void register(HttpServletRequest request) throws IOException {
			 String code = request.getParameter("code");
			 if(code!=null && !"".equals(code)){
		        	logger.info("\n code : "+code);
		        	String openID = OAuth.getOpenIdByCode(code);
		            if(!"".equals(openID)){
		                logger.info("获取微信用户openID失败");
		                
		            }else{
			            WXUserInfo map = wx.getUserInfo(openID);
			    		Integer status = OAuth.getInfo(map);
			    		if(status==1){
			                 //保存用户信息
			    		}
			            
			            logger.info(" \n openID : "+openID +" map :"+map);
		            }
		      }else{
		    	    OAuth.getCode(0);//默认1静默授权
		      }
		
		 }
	    
	    
		   /**
		     *  判断 消息 类型
		     * @param request
		     * @param response
		     * @throws IOException
		     */
		   private  void acceptMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		         // 处理接收消息
		         ServletInputStream in = request.getInputStream();
		         // 将POST流转换为XStream对象
		         XStream xs = XStreamCDATATransform.createXstream();
		         xs.processAnnotations(InputMessage.class);

		         // 将指定节点下的xml节点数据映射为对象
		         xs.alias("xml", InputMessage.class);

		         response.setContentType("text/html;charset=utf-8");

		         // 将流转换为字符串
		         StringBuilder xmlMsg = new StringBuilder();
		         byte[] b = new byte[4096];
		         for (int n; (n = in.read(b)) != -1;) {
		             xmlMsg.append(new String(b, 0, n, "UTF-8"));
		         }
		         // 将xml内容转换为InputMessage对象
		         InputMessage inputMsg = (InputMessage) xs.fromXML(xmlMsg.toString());

		       String servername = inputMsg.getToUserName();// 服务端
		       String openId = inputMsg.getFromUserName();// 客户端

		       Long returnTime = Calendar.getInstance().getTimeInMillis() / 1000;// 返回时间
		       // 取得消息类型
		       String msgType = inputMsg.getMsgType();
		       //事件类型
		       String event = inputMsg.getEvent();

		       if (MsgType.Text.equals(msgType)) {
		    	   WXMessage.text(xs,servername,openId,returnTime,"欢迎咨询",response);
		       }else if (MsgType.EVENT.equals(msgType)) {

		           if(Event.Location.equals(event)){
		               //位置
		               WXMessage.address(inputMsg,response);
		           }else if(Event.Subscribe.equals(event)){
		               //关注 保存用户信息
		               WXMessage.subscribe(xs,servername,openId,returnTime,wx,userInfo,id,response);
		           }else if(Event.Unsubscribe.equals(event)){
		               //取消关注

		               response.getWriter().write(" ");
		           }

		       }else if(MsgType.Video.equals(msgType)){

		       }else if(MsgType.Music.equals(msgType)){


		       }else if(MsgType.Image.equals(msgType)){

		       }

		   }
	    
	    
	    
}
