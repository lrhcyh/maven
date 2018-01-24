package com.abcft.system.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.abcft.common.base.EntityMapper;
import com.abcft.common.base.RsglBaseDao;
import com.abcft.common.util.StringUtils;
import com.abcft.system.rolemanage.Role;

/**
 * 系统用户   	数据库操作
 * @author Administrator
 *
 */
@Repository
@Transactional
public class UserDao extends RsglBaseDao {

	private static final Logger logger = LogManager.getLogger(UserDao.class);

	/**
	 * 查询用户列表
	 * @param currentPage
	 * @param pageSize
	 * @param userName
	 * @param fullName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserInfo> findEmployees(long start, long end, String userName, String fullName, String roleId) {
		StringBuffer sql = new StringBuffer(" select a.id,a.userName,a.fullName,a.email,a.status,a.roleId,b.role_name from  backend_user as a left join backend_role as b on b.id=a.roleId  where 1=1");
		
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(userName)) {
			sql.append("  AND userName like '%" + userName + "%' ");
		}
		if(!StringUtils.isEmpty(fullName)) {
			sql.append(" AND fullName like '%" + fullName + "%'  ");
		}
		if(!StringUtils.isEmpty(fullName)) {
			sql.append(" AND roleId = '" + roleId + "'  ");
		}
		
		sql.append(" limit ?,? ");  
		params.add(start);
		params.add(end);
		logger.info(sql);
		logger.info(params);
		return getJdbcTemplate().query(sql.toString(), new EntityMapper(UserInfo.class), params.toArray());
	}

	/**
	 * 查询所有的角色
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Role> findRoles() {
		String sql = "select * from backend_role ";
		logger.info(sql);
		return getJdbcTemplate().query(sql, new EntityMapper(Role.class));
	}


	/**
	 * 修改员工信息
	 * @param emp
	 */
	public void updateUser(UserInfo userInfo) {
		String sql = " update backend_user set fullName=?,email=?,status=? where id=? ";
		
		List<Object> params = new ArrayList<>();
		params.add(userInfo.getFullName());
		params.add(userInfo.getEmail());
		params.add(userInfo.getStatus());
		params.add(userInfo.getId());
		
		getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	/**
	 * 删除用户信息
	 * @param id
	 */
	public void removeUser(String id) {
		String sql = " delete from backend_user where id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		logger.info(sql);
		logger.info(params);
		getJdbcTemplate().update(sql, params.toArray());

	}

	/**
	 * 更新用户状态
	 * @param id
	 * @param status
	 */
	public void updateUserStatus(String id, String status) {
		StringBuffer sql = new StringBuffer(" UPDATE backend_user SET status=? WHERE id=? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(status);
		params.add(id);
		
		getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	/**
	 * 获取用户总数
	 * @param userName
	 * @param fullName
	 * @return
	 */
	public Long getTotalRole(String userName, String fullName, String roleId) {
		StringBuffer sql = new StringBuffer(" select count(*) from  backend_user  where 1=1 ");
		
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(userName)) {
			sql.append("  AND userName like '%" + userName + "%' ");
		}
		if(!StringUtils.isEmpty(fullName)) {
			sql.append(" AND fullName like '%" + fullName + "%'  ");
		}
		if(!StringUtils.isEmpty(fullName)) {
			sql.append(" AND roleId = '" + roleId + "'  ");
		}
		
		return getJdbcTemplate().queryForLong(sql.toString(), params.toArray());
	}

	/**
	 * 修改用户所属角色
	 * 
	 * @param id
	 * @param roleId
	 */
	public void updateUserRole(String id, String roleId) {
		String sql = " update backend_user set roleId=? where id=? ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(roleId);
		params.add(id);
		
		getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	/**
	 * 通过用户Id获取用户信息
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public UserInfo queryUserById(String id) {
		String sql = " select userName,fullName,email,status,roleId from backend_user where id=? ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		
		return (UserInfo) getJdbcTemplate().query(sql.toString(), new EntityMapper(UserInfo.class), params.toArray()).get(0);
	}

	public Long insertUser(UserInfo userInfo) {
		String sql = " insert into backend_user (userName,passWord,fullName,email,status,roleId) "
				+ " values (?,?,?,?,?,?)";
		
		List<Object> list = parseUser(userInfo);
		
		getJdbcTemplate().update(sql.toString(), list.toArray());
		
		String sql2 = "SELECT ID FROM backend_user WHERE ID=@@IDENTITY";
		
		return getJdbcTemplate().queryForLong(sql2);
	}

	private List<Object> parseUser(UserInfo userInfo) {
		List<Object> list = new ArrayList<>();
		list.add(userInfo.getUserName());
		list.add(userInfo.getPassWord());
		list.add(userInfo.getFullName());
		list.add(userInfo.getEmail());
		list.add(userInfo.getStatus());
		list.add(userInfo.getRoleId());
		
		return list;
	}

	public Long getUserInfoByUserName(String userName) {
		String sql = " select id from backend_user where userName=? ";
		
		List<Object> list = new ArrayList<>();
		list.add(userName);
		
		return getJdbcTemplate().queryForLong(sql, list.toArray());
	}

}
