package com.abcft.system.privilege;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.abcft.common.db.BaseEntity;

/**
 * 功能节点实体类
 * @author Administrator
 *
 */
@Entity
@Table(name = "sys_FUNCTIONS", schema = "MISC", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class Functions extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5095514493491533758L;
	private Long id;
	private Long parentid;
	private String functionName;
	private String description;
	private String url;
	private Integer isLeaf; // 1表示是叶子节点 0表示非叶子结点
	private Date create_time;
	private String create_user_id;
	private Date update_time;
	private String update_user_id;
	private Long sortNum;	//排序字段
	private Integer isShow; // 1可见  0不可见

	@Column(name = "isShow", unique = false, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	@Column(name = "sortnum", unique = false, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getSortNum() {
		return sortNum;
	}

	public void setSortNum(Long sortNum) {
		this.sortNum = sortNum;
	}

	@Column(name = "CREATE_TIME", unique = false, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	@Column(name = "CREATE_USER_ID", unique = false, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getCreate_user_id() {
		return create_user_id;
	}

	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}

	@Column(name = "UPDATE_TIME", unique = false, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	@Column(name = "UPDATE_USER_ID", unique = false, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getUpdate_user_id() {
		return update_user_id;
	}

	public void setUpdate_user_id(String update_user_id) {
		this.update_user_id = update_user_id;
	}

	public Functions() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PARENT_ID", unique = false, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getParentid() {
		return parentid;
	}

	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	@Column(name = "FUNCTION_NAME", unique = false, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	@Column(name = "DESCRIPTION", unique = false, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "URL", unique = false, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "ISLEAF", unique = false, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

}
