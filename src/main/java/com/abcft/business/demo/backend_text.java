package com.abcft.business.demo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class backend_text implements Serializable {

	private static final long serialVersionUID = -2692308980255488545L;

	private Long _id;
	private String author;
	private String keywords;
	private String title;
	private ArrayList<backend_text_paragraphs> paragraphs;
	private String fulltext;
	private Integer pageCount;
	private Integer paragraphCount;
	private Integer characterCount;
	private Integer fileId;
	private String fileUrl;
	private Integer text_version;
	private Integer state;
	private Date create_time;
	private Date last_updated;
	
	public Long get_id() {
		return _id;
	}
	public void set_id(Long _id) {
		this._id = _id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<backend_text_paragraphs> getParagraphs() {
		return paragraphs;
	}
	public void setParagraphs(ArrayList<backend_text_paragraphs> paragraphs) {
		this.paragraphs = paragraphs;
	}
	public String getFulltext() {
		return fulltext;
	}
	public void setFulltext(String fulltext) {
		this.fulltext = fulltext;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getParagraphCount() {
		return paragraphCount;
	}
	public void setParagraphCount(Integer paragraphCount) {
		this.paragraphCount = paragraphCount;
	}
	public Integer getCharacterCount() {
		return characterCount;
	}
	public void setCharacterCount(Integer characterCount) {
		this.characterCount = characterCount;
	}
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public Integer getText_version() {
		return text_version;
	}
	public void setText_version(Integer text_version) {
		this.text_version = text_version;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getLast_updated() {
		return last_updated;
	}
	public void setLast_updated(Date last_updated) {
		this.last_updated = last_updated;
	}
}
