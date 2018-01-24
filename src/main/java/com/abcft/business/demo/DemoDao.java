package com.abcft.business.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.abcft.business.demo.Demo;
import com.abcft.common.base.BaseDao;
import com.abcft.common.base.BaseQuery;
/**
 * Demo Dao(数据库操作层)
 * @author inning
 * @DateTime 2015-7-20 上午11:32:23
 *
 */
@Repository
public class DemoDao extends BaseDao {
	/**根据ID查询*/ 
	public UserInfo queryDemoByid(String id) {
		UserInfo userInfo = super.find(UserInfo.class, id);
		return userInfo;
	}
	
	/**
	 * 查询结果集，根据传入的demo的get方法不为空匹配查询条件
	 * 
	 * */ 
     public List<UserInfo> queryDemoList(UserInfo userInfo) {
		
		
		StringBuffer sql = new StringBuffer("SELECT                  UserId as userId,UserName as userName,CanUseAmnout as canUseAmnout,FreezeAmount as freezeAmount,PhoneNumber as phoneNumber,FullName as fullName,IDNumber as idNumber   FROM UserInfo  ");
		//拼AND用
		sql.append("WHERE  1=1 "); 
		if (userInfo.getPageRows() > 0 && userInfo.getFirstRow() != null) {
			  sql.append(" and UserId not in ( select top ").append( userInfo.getFirstRow().toString() ).append(" UserId from UserInfo  )  ");
			  
			}
		
		BaseQuery q = createQuery(sql.toString(), UserInfo.class);
		//翻页用
		q.setFirst(userInfo.getPageRows());
		
		//获取总记录数
		userInfo.setTotalRows(q.getTotalRows());
		return q.getResultList();
	}
	
	/**
	 * 新增
	 * @return 返回插入的主键，DEMO必须添加主键注解以及自增长注解
	 * 
	 * */ 
	public Long insertDemo(Demo demo) {
		return (Long) super.insert(demo);
	}

	/**
	 * 更新
	 * @return 更新的记录数
	 * 
	 * */ 
	public int updateDemo(UserInfo demo) {
		return super.update(demo);
	}
	
	/**
	 * 删除
	 * @return 删除的记录数
	 * 
	 * */ 
	public int deleteDemo(Long id) {
		return super.delete(Demo.class, id);
	}

	public int updateFreeUserAmouont(UserInfo userInfo) {
		String sql=" UPDATE  UserInfo set FreezeAmount=(FreezeAmount+?),CanUseAmnout=(CanUseAmnout-?) where  UserId=? ";
		List<Object> list=new ArrayList<Object>();
		list.add(userInfo.getPrice());
		list.add(userInfo.getPrice());
		list.add(userInfo.getUserId());
		return getJdbcTemplate().update(sql.toString(), list.toArray());
		
	}
}
