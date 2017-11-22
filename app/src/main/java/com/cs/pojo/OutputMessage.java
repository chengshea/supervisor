package com.cs.pojo;



import com.cs.annotation.XStreamCDATA;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class OutputMessage {

	@XStreamAlias("ToUserName")    
    @XStreamCDATA    
    private String ToUserName;    
    
    @XStreamAlias("FromUserName")    
    @XStreamCDATA 
    private String FromUserName;    
    
    @XStreamAlias("CreateTime") 
    private Long CreateTime;    
    
    @XStreamAlias("MsgType")    
    @XStreamCDATA 
    private String MsgType;    
   

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public OutputMessage(String toUserName, String fromUserName,
			Long createTime, String msgType) {
		super();
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
	}

	public OutputMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

    

}
