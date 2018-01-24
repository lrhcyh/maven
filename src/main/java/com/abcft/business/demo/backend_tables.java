package com.abcft.business.demo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class backend_tables implements Serializable {

	private static final long serialVersionUID = 3963684972005082176L;
	
	private String _id;
	private Double pageIndex;
	private String title;
	private String pngFile;
	private String algorithm;
	private Double rowCount;
	private Double columnCount;
	private Double x;
	private Double y;
	private Double width;
	private Double height;
	private List<backend_tables_data> data;
	private List<String> raw_data;
	private Double fileId;
	private String fileUrl;
	private Double state;
	private Date create_time;
	private Date last_updated;
	private Double talble_version;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public Double getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Double pageIndex) {
		this.pageIndex = pageIndex;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPngFile() {
		return pngFile;
	}
	public void setPngFile(String pngFile) {
		this.pngFile = pngFile;
	}
	public String getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	public Double getRowCount() {
		return rowCount;
	}
	public void setRowCount(Double rowCount) {
		this.rowCount = rowCount;
	}
	public Double getColumnCount() {
		return columnCount;
	}
	public void setColumnCount(Double columnCount) {
		this.columnCount = columnCount;
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
	public List<backend_tables_data> getData() {
		return data;
	}
	public void setData(List<backend_tables_data> data) {
		this.data = data;
	}
	public List<String> getRaw_data() {
		return raw_data;
	}
	public void setRaw_data(List<String> raw_data) {
		this.raw_data = raw_data;
	}
	public Double getFileId() {
		return fileId;
	}
	public void setFileId(Double fileId) {
		this.fileId = fileId;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public Double getState() {
		return state;
	}
	public void setState(Double state) {
		this.state = state;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Double getTalble_version() {
		return talble_version;
	}
	public void setTalble_version(Double talble_version) {
		this.talble_version = talble_version;
	}
	public Date getLast_updated() {
		return last_updated;
	}
	public void setLast_updated(Date last_updated) {
		this.last_updated = last_updated;
	}
	
}
