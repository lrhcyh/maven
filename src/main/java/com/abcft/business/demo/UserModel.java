package com.abcft.business.demo;

import java.io.Serializable;  

public class UserModel implements Serializable {  
  
    private static final long serialVersionUID = 1L;  
    private String source;  
    private String src_id;
    private String title;
    private String industry;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSrc_id() {
		return src_id;
	}
	public void setSrc_id(String src_id) {
		this.src_id = src_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
  
    
  
  
}