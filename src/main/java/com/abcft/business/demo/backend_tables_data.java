package com.abcft.business.demo;

import java.io.Serializable;

public class backend_tables_data implements Serializable {

	private static final long serialVersionUID = -8069547129827038169L;
	
	private Double x;
	private Double y;
	private Double width;
	private Double height;
	private Double row;
	private Double column;
	private String text;
	
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
	
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}
	
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Double getRow() {
		return row;
	}
	public void setRow(Double row) {
		this.row = row;
	}
	
	public Double getColumn() {
		return column;
	}
	public void setColumn(Double column) {
		this.column = column;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}
