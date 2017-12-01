package com.cs.dto.wx;

import com.cs.annotation.XStreamCDATA;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class OutputTextMessage extends OutputMessage {

	 
	@XStreamAlias("Content")    
    @XStreamCDATA 
    private String content;

	

	
	public String getContent() {
		return content;
	}




	public void setContent(String content) {
		this.content = content;
	}


    
}
