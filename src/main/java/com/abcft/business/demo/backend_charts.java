package com.abcft.business.demo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class backend_charts implements Serializable {

	private static final long serialVersionUID = 8498083555750612357L;
	private String _id;
	private String title;
	private ArrayList<backend_charts_legends> legends;
	private backend_charts_area area;
	private ArrayList<String> fonts;
	private ArrayList<String> vAxisTextL;
	private ArrayList<String> vAxisTextR;
	private ArrayList<String> hAxisTextD;
	private ArrayList<String> hAxisTextU;
	private String hAxis;
	private String lvAxis;
	private String rvAxis;
	private String chartType;
	private String data;
	private int fileId;
	private String filePath;
	private String pngFile;
	private String svgFile;
	private int pageIndex;
	private String fileUrl;
	private int state;
	private Date create_time;
	private Date last_updated;
	private int chart_version;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<backend_charts_legends> getLegends() {
		return legends;
	}
	public void setLegends(ArrayList<backend_charts_legends> legends) {
		this.legends = legends;
	}
	public backend_charts_area getArea() {
		return area;
	}
	public void setArea(backend_charts_area area) {
		this.area = area;
	}
	public ArrayList<String> getFonts() {
		return fonts;
	}
	public void setFonts(ArrayList<String> fonts) {
		this.fonts = fonts;
	}
	public ArrayList<String> getvAxisTextL() {
		return vAxisTextL;
	}
	public void setvAxisTextL(ArrayList<String> vAxisTextL) {
		this.vAxisTextL = vAxisTextL;
	}
	public ArrayList<String> getvAxisTextR() {
		return vAxisTextR;
	}
	public void setvAxisTextR(ArrayList<String> vAxisTextR) {
		this.vAxisTextR = vAxisTextR;
	}
	public ArrayList<String> gethAxisTextD() {
		return hAxisTextD;
	}
	public void sethAxisTextD(ArrayList<String> hAxisTextD) {
		this.hAxisTextD = hAxisTextD;
	}
	public ArrayList<String> gethAxisTextU() {
		return hAxisTextU;
	}
	public void sethAxisTextU(ArrayList<String> hAxisTextU) {
		this.hAxisTextU = hAxisTextU;
	}
	public String gethAxis() {
		return hAxis;
	}
	public void sethAxis(String hAxis) {
		this.hAxis = hAxis;
	}
	public String getLvAxis() {
		return lvAxis;
	}
	public void setLvAxis(String lvAxis) {
		this.lvAxis = lvAxis;
	}
	public String getRvAxis() {
		return rvAxis;
	}
	public void setRvAxis(String rvAxis) {
		this.rvAxis = rvAxis;
	}
	public String getChartType() {
		return chartType;
	}
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getPngFile() {
		return pngFile;
	}
	public void setPngFile(String pngFile) {
		this.pngFile = pngFile;
	}
	public String getSvgFile() {
		return svgFile;
	}
	public void setSvgFile(String svgFile) {
		this.svgFile = svgFile;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
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
	public int getChart_version() {
		return chart_version;
	}
	public void setChart_version(int chart_version) {
		this.chart_version = chart_version;
	}
	
}
