package com.cs.dto.wx;


import java.util.List;

import com.cs.annotation.XStreamCDATA;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 * 名称：OutputNewsMessage.java<br>
 * 描述：回复图文消息
 * 最近修改时间: 2017年11月22日 下午5:22:30 <br>
 * @since  2017年11月22日
 * @authour cs
 */
public class OutputNewsMessage extends OutputMessage {

	@XStreamAlias("ArticleCount")
	private Integer articleCount;
	@XStreamAlias("Articles")
	private List<Item>  articles;
	
	
	/**
	
	
	
	
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[fromUser]]></FromUserName>
		<CreateTime>12345678</CreateTime>
		<MsgType><![CDATA[news]]></MsgType>
		<ArticleCount>2</ArticleCount>
		<Articles>
		<item>
		<Title><![CDATA[title1]]></Title> 
		<Description><![CDATA[description1]]></Description>
		<PicUrl><![CDATA[picurl]]></PicUrl>
		<Url><![CDATA[url]]></Url>
		</item>
		<item>
		<Title><![CDATA[title]]></Title>
		<Description><![CDATA[description]]></Description>
		<PicUrl><![CDATA[picurl]]></PicUrl>
		<Url><![CDATA[url]]></Url>
		</item>
		</Articles>
		</xml>
	
	 */
	
	
	
	public Integer getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}

	public List<Item> getArticles() {
		return articles;
	}

	public void setArticles(List<Item> articles) {
		this.articles = articles;
	}

    
	@XStreamAlias("Item")
	public static class Item {

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
	
}



