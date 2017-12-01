package com.cs.dto.wx;


import com.thoughtworks.xstream.annotations.XStreamAlias;

public class  OutputVideoMessage extends OutputMessage  {
    
	
	@XStreamAlias("Video")    
	private  Video Video; 

	
	
	
	public Video getVideo() {
		return Video;
	}



	public void setVideo(Video video) {
		Video = video;
	}


	
	public static class Video {
	    
		@XStreamAlias("MediaId") 
		private String MediaId;
		@XStreamAlias("Title") 
		private String  Title;
		@XStreamAlias("Description") 
		private String Description;
		public String getMediaId() {
			return MediaId;
		}
		public void setMediaId(String mediaId) {
			MediaId = mediaId;
		}
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
	}
		
	
}
