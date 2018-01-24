package com.abcft.business.demo;

import java.io.Serializable;

public class backend_text_paragraphs implements Serializable {

	private static final long serialVersionUID = 1039582865224176798L;

	private String text;
	private Integer pageIndex;
	private Double x;
	private Double y;
	private String fontName;
	private Double fontSize;
	private Integer fontStyle;
	private Integer textStyle;
	private Integer contentType;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	public String getFontName() {
		return fontName;
	}
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}
	public Double getFontSize() {
		return fontSize;
	}
	public void setFontSize(Double fontSize) {
		this.fontSize = fontSize;
	}
	public Integer getFontStyle() {
		return fontStyle;
	}
	public void setFontStyle(Integer fontStyle) {
		this.fontStyle = fontStyle;
	}
	public Integer getTextStyle() {
		return textStyle;
	}
	public void setTextStyle(Integer textStyle) {
		this.textStyle = textStyle;
	}
	public Integer getContentType() {
		return contentType;
	}
	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}
	
	
}
