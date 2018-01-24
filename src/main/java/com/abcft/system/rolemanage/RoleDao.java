package com.abcft.system.rolemanage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.abcft.common.base.BaseDao;
import com.abcft.common.base.EntityMapper;

/**
 * 角色的相关操作
 * @author huangxi
 *
 */
@Repository
@Transactional
public class RoleDao extends BaseDao {

	/**
	 * 显示所有角色并分页
	 * @param begin 开始条数
	 * @param end 结束条数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Role> getRoles(Long begin,Long end) {
		String sql = "select id,role_name,role_info,create_time from backend_role   limit ?,?";
		List<Object> list = new ArrayList<Object>();
		list.add(begin);
		list.add(end);
		return getJdbcTemplate().query(sql, new EntityMapper(Role.class), list.toArray());
	}

	//向数据库写入角色
	public Long insertRole(Role role) {
		String sql = "insert into backend_role (role_name,role_info,create_time) values(?,?,now()) ";
		List<Object> list = new ArrayList<Object>();
		list.add(role.getRole_name());
		list.add(role.getRole_info());
		
		getJdbcTemplate().update(sql.toString(), list.toArray());

		String sql2 = "SELECT ID FROM backend_role WHERE ID=@@IDENTITY";
		return getJdbcTemplate().queryForLong(sql2);
	}

	//获得总条数
	public Long getTotalRole() {
		String sql = "select count(*) from backend_role ";
		return getJdbcTemplate().queryForLong(sql);
	}
	
	//删除角色的权限
	public void deleteRoleRights(Long id) {
		String sql = "delete from backend_roleright where role_id=" + id;
		getJdbcTemplate().update(sql);
	}

	//给角色添加权限
	public void addRight(Long rid,Long fid) {
		String sql = "insert into backend_roleright (role_id,function_id,create_time) values(?,?,now())";
		List<Object> params = new ArrayList<Object>();
		params.add(rid);
		params.add(fid);
		
		getJdbcTemplate().update(sql.toString(), params.toArray());
	}
	
	//获得角色已有的权限
	public List<Long> getChekedFunctions(Integer roleid) {
		String sql = "select function_id from backend_roleright where role_id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(roleid);
		return getJdbcTemplate().queryForList(sql, Long.class, params.toArray());
	}

	//删除角色
	public void removeRole(Integer rid) {
		String sql = " DELETE FROM backend_role WHERE id = ? ";
		List<Object> params = new ArrayList<Object>();
		params.add(rid);
		getJdbcTemplate().update(sql, params.toArray());
	}

	//修改该角色下的所有用户的roleId
	public void updateRoleOfUser(Integer rid) {
		String sql = " UPDATE backend_user SET roleId = NULL WHERE roleId = ?";
		
		List<Object> params = new ArrayList<Object>();
		params.add(rid);
		
		getJdbcTemplate().update(sql, params.toArray());
		
	}

	//删除该角色下所有的菜单权限
	public void removeRoleRight(Integer rid) {
		String sql = " DELETE FROM backend_roleright WHERE role_id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(rid);
		getJdbcTemplate().update(sql,params.toArray());
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Role> findRoleById(String roleId) {
		String sql = " select * from backend_role where id=? ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(roleId);
		
		return getJdbcTemplate().query(sql.toString(), new EntityMapper(Role.class), params.toArray());
	}

	@SuppressWarnings("unchecked")
	public List<Role> getRoleByRoleName(String role_name) {
		String sql = " select id from backend_role where role_name=? ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(role_name);
		
		return getJdbcTemplate().query(sql.toString(), new EntityMapper(Role.class), params.toArray());
	}

}
