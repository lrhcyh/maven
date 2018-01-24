package com.abcft.business.demo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class backend_files  implements Serializable {

	private static final long serialVersionUID = 3059627580713436274L;

	private Long _id;
	private Long id;
	private String uploaduser;
	private String rating;
	private String typetitle;
	private String stockname;
	private String sharecontent;
	private String filetype;
	private String shareurl;
	private String sharetitle;
	private Long file_size;
	private int publish_power;
	private ArrayList<String> author;
	private String publish;
	private int file_pages;
	private String stockcode;
	private String shareimg;
	private String file_url;
	private String summary;
	private Date time;
	private String industryname;
	private Date update_time;
	private Boolean download_failed;
	private String oss_path;
	private Boolean export_flag;
	private Boolean downloaded;
	private String process_error;
	private Boolean has_chart;
	private ArrayList<String> chart_errors;
	private int chart_count;
	private Object detail_chart_count;
	private int chart_version;
	private Boolean has_table;
	private ArrayList<String> table_errors;
	private int table_count;
	private int table_version;
	private int text_version;
	private ArrayList<String> content_errors;
	private int pdf_pages;
	private int characterCount;
	private int paragraphCount;
	private String process_error_detail;
	private Date last_process_time;
	
	public Long get_id() {
		return _id;
	}
	public void set_id(Long _id) {
		this._id = _id;
	}
	public String getUploaduser() {
		return uploaduser;
	}
	public void setUploaduser(String uploaduser) {
		this.uploaduser = uploaduser;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getTypetitle() {
		return typetitle;
	}
	public void setTypetitle(String typetitle) {
		this.typetitle = typetitle;
	}
	public String getStockname() {
		return stockname;
	}
	public void setStockname(String stockname) {
		this.stockname = stockname;
	}
	public String getSharecontent() {
		return sharecontent;
	}
	public void setSharecontent(String sharecontent) {
		this.sharecontent = sharecontent;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public String getShareurl() {
		return shareurl;
	}
	public void setShareurl(String shareurl) {
		this.shareurl = shareurl;
	}
	public String getSharetitle() {
		return sharetitle;
	}
	public void setSharetitle(String sharetitle) {
		this.sharetitle = sharetitle;
	}
	public Long getFile_size() {
		return file_size;
	}
	public void setFile_size(Long file_size) {
		this.file_size = file_size;
	}
	public int getPublish_power() {
		return publish_power;
	}
	public void setPublish_power(int publish_power) {
		this.publish_power = publish_power;
	}
	public ArrayList<String> getAuthor() {
		return author;
	}
	public void setAuthor(ArrayList<String> author) {
		this.author = author;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public int getFile_pages() {
		return file_pages;
	}
	public void setFile_pages(int file_pages) {
		this.file_pages = file_pages;
	}
	public String getStockcode() {
		return stockcode;
	}
	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}
	public String getShareimg() {
		return shareimg;
	}
	public void setShareimg(String shareimg) {
		this.shareimg = shareimg;
	}
	public String getFile_url() {
		return file_url;
	}
	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getIndustryname() {
		return industryname;
	}
	public void setIndustryname(String industryname) {
		this.industryname = industryname;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Boolean getDownload_failed() {
		return download_failed;
	}
	public void setDownload_failed(Boolean download_failed) {
		this.download_failed = download_failed;
	}
	public String getOss_path() {
		return oss_path;
	}
	public void setOss_path(String oss_path) {
		this.oss_path = oss_path;
	}
	public Boolean getExport_flag() {
		return export_flag;
	}
	public void setExport_flag(Boolean export_flag) {
		this.export_flag = export_flag;
	}
	public Boolean getDownloaded() {
		return downloaded;
	}
	public void setDownloaded(Boolean downloaded) {
		this.downloaded = downloaded;
	}
	public String getProcess_error() {
		return process_error;
	}
	public void setProcess_error(String process_error) {
		this.process_error = process_error;
	}
	public Boolean getHas_chart() {
		return has_chart;
	}
	public void setHas_chart(Boolean has_chart) {
		this.has_chart = has_chart;
	}
	public ArrayList<String> getChart_errors() {
		return chart_errors;
	}
	public void setChart_errors(ArrayList<String> chart_errors) {
		this.chart_errors = chart_errors;
	}
	public int getChart_count() {
		return chart_count;
	}
	public void setChart_count(int chart_count) {
		this.chart_count = chart_count;
	}
	public Object getDetail_chart_count() {
		return detail_chart_count;
	}
	public void setDetail_chart_count(Object detail_chart_count) {
		this.detail_chart_count = detail_chart_count;
	}
	public int getChart_version() {
		return chart_version;
	}
	public void setChart_version(int chart_version) {
		this.chart_version = chart_version;
	}
	public Boolean getHas_table() {
		return has_table;
	}
	public void setHas_table(Boolean has_table) {
		this.has_table = has_table;
	}
	public ArrayList<String> getTable_errors() {
		return table_errors;
	}
	public void setTable_errors(ArrayList<String> table_errors) {
		this.table_errors = table_errors;
	}
	public int getTable_count() {
		return table_count;
	}
	public void setTable_count(int table_count) {
		this.table_count = table_count;
	}
	public int getTable_version() {
		return table_version;
	}
	public void setTable_version(int table_version) {
		this.table_version = table_version;
	}
	public int getText_version() {
		return text_version;
	}
	public void setText_version(int text_version) {
		this.text_version = text_version;
	}
	public ArrayList<String> getContent_errors() {
		return content_errors;
	}
	public void setContent_errors(ArrayList<String> content_errors) {
		this.content_errors = content_errors;
	}
	public int getPdf_pages() {
		return pdf_pages;
	}
	public void setPdf_pages(int pdf_pages) {
		this.pdf_pages = pdf_pages;
	}
	public int getCharacterCount() {
		return characterCount;
	}
	public void setCharacterCount(int characterCount) {
		this.characterCount = characterCount;
	}
	public int getParagraphCount() {
		return paragraphCount;
	}
	public void setParagraphCount(int paragraphCount) {
		this.paragraphCount = paragraphCount;
	}
	public String getProcess_error_detail() {
		return process_error_detail;
	}
	public void setProcess_error_detail(String process_error_detail) {
		this.process_error_detail = process_error_detail;
	}
	public Date getLast_process_time() {
		return last_process_time;
	}
	public void setLast_process_time(Date last_process_time) {
		this.last_process_time = last_process_time;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
