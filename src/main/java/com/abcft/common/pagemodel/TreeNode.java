package com.abcft.common.pagemodel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class TreeNode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4740627159855336173L;

	private Long id;  
    private String text;// 树节点名称  
    private String iconCls;// 前面的小图标样式  
    private Boolean checked = false;// 是否勾选状态  
    private Map<String, Object> attributes;// 其他参数  
    private List<TreeNode> children;// 子节点  
    private String state = "open";// 是否展开(open,closed)  
  
    /**  
     * @return the id  
     */  
    public Long getId() {  
        return id;  
    }  
  
    /**  
     * @param id the id to set  
     */  
    public void setId(Long id) {  
        this.id = id;  
    }  
  
    public String getText() {  
        return text;  
    }  
  
    public void setText(String text) {  
        this.text = text;  
    }  
  
    public Boolean getChecked() {  
        return checked;  
    }  
  
    public void setChecked(Boolean checked) {  
        this.checked = checked;  
    }  
  
    public Map<String, Object> getAttributes() {  
        return attributes;  
    }  
  
    public void setAttributes(Map<String, Object> attributes) {  
        this.attributes = attributes;  
    }  
  
    public List<TreeNode> getChildren() {  
        return children;  
    }  
  
    public void setChildren(List<TreeNode> children) {  
        this.children = children;  
    }  
  
    public String getState() {  
        return state;  
    }  
  
    public void setState(String state) {  
        this.state = state;  
    }  
  
    public String getIconCls() {  
        return iconCls;  
    }  
  
    public void setIconCls(String iconCls) {  
        this.iconCls = iconCls;  
    }  
  

}
