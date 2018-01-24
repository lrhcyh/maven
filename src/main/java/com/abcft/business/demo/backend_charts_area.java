package com.abcft.business.demo;

import java.io.Serializable;

public class backend_charts_area implements Serializable {
	private static final long serialVersionUID = -5778379020338553160L;
	
	private double x;
	private double y;
	private double w;
	private double h;
	
	public backend_charts_area(double x, double y, double w, double h) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	
}
