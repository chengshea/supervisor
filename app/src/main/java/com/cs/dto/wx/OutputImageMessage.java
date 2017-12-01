package com.cs.dto.wx;

import com.cs.annotation.XStreamCDATA;
import com.thoughtworks.xstream.annotations.XStreamAlias;


public class  OutputImageMessage extends  OutputMessage {
	
	@XStreamAlias("Image") 
	private Image  image;
	
	

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}



	public static class Image  extends  OutputMessage  {  
		
		/** 接
		 <xml>
			 <ToUserName><![CDATA[toUser]]></ToUserName>
			 <FromUserName><![CDATA[fromUser]]></FromUserName>
			 <CreateTime>1348831860</CreateTime>
			 <MsgType><![CDATA[image]]></MsgType>
			 <PicUrl><![CDATA[this is a url]]></PicUrl>
			 <MediaId><![CDATA[media_id]]></MediaId>
			 <MsgId>1234567890123456</MsgId>
		 </xml>
		 回
		<xml>
			<ToUserName><![CDATA[toUser]]></ToUserName>
			<FromUserName><![CDATA[fromUser]]></FromUserName>
			<CreateTime>12345678</CreateTime>
			<MsgType><![CDATA[image]]></MsgType>
			<Image>
			<MediaId><![CDATA[media_id]]></MediaId>
			</Image>
		</xml>
		 */
		
		 
		
		
	}   


}
