package com.cs.pojo;

import com.cs.annotation.XStreamCDATA;
import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * 
 * 名称：Item.java<br>
 * 描述：图文信息
 * 最近修改时间: 2017年11月22日 下午5:21:59 <br>
 * @since  2017年11月22日
 * @authour cs
 */
@XStreamAlias("Item")
public class Item {

	@XStreamAlias("Title")
	@XStreamCDATA
	private String Title;
	@XStreamAlias("Description")
	@XStreamCDATA
	private String Description;
	@XStreamAlias("PicUrl")
	@XStreamCDATA
	private String PicUrl;
	@XStreamAlias("Url")
	@XStreamCDATA
	private String Url;
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}

}
