package com.abcft.common.base;


import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author inning
 * @DateTime 2015-7-20 上午11:33:55
 *
 */
public class BaseCondition {
	
	private List<Object> para = new ArrayList<Object>();
		
	private String prepend;
	
	private List<String> sql = new ArrayList<String>();
	
	private List<Object> obj = new ArrayList<Object>();
	
	public static BaseCondition getCondition(String prepend) {
		BaseCondition c = new BaseCondition();
		c.setPrepend(prepend);		
		return c;
	}
		
	public void setParameter(BaseQuery q) {
		for (int i = 0; i < para.size(); i++) {
			q.setParameter(para.get(i));
		}
	}

	public Object[] getParameter() {
		return para.toArray();
	}

	public String getPrepend() {
		return prepend;
	}

	public void setPrepend(String prepend) {
		this.prepend = prepend;
	}

	public String getStr() {
		List<String> sqlList = new ArrayList<String>();
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < obj.size(); i++) {
			if (obj.get(i) !=  null && !"%null%".equals(obj.get(i)) && !"@null@".equals(obj.get(i))) {
				para.add(obj.get(i));
				sqlList.add(sql.get(i));
			}else if("@null@".equals(obj.get(i))){
				sqlList.add(sql.get(i));
			}
			
		}
		
		if (sqlList.size() == 0) {
			return "";
		}
		else {
			
			for(int i=0;i<sqlList.size();i++){
				sb.append(sqlList.get(i)+" and ");

			}

			sb.delete(sb.length() - 4, sb.length());
			
			//return " "  + prepend + " " + sqlList.toString().replaceAll(",", " and ").replace("[", "").replace("]", "");
			return " "  + prepend + " " + sb.toString();
		}
	}

	public void addAnd(String s, Object o) {
		this.sql.add(s);
		//if(o!=null)
		this.obj.add(o);
	}
	

}
