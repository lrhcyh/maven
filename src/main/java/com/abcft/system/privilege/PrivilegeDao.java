package com.abcft.system.privilege;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.abcft.common.base.BaseDao;
import com.abcft.common.base.EntityMapper;

/**
 * 权限操作
 * 
 * @author Administrator
 *
 */
@Repository
public class PrivilegeDao extends BaseDao {

	/**
	 * 查询菜单
	 * 
	 * @return
	 */
	public List<Functions> getFunctions() {
		String sql = "SELECT Id,Parent_Id,Create_Time,Function_Name,Description,Url,IsLeaf,sortnum,isShow from backend_functions order by sortnum";
		return createQuery(sql, Functions.class).getResultList();
	}

	/**
	 * 插入节点
	 * 
	 * @param function
	 */
	public Long insertFunction(Functions function) {
		String sql = " insert into backend_functions (parent_id,function_name,description,url,isLeaf,create_time,isShow)  values (?,?,?,?,?,now(),?);"
				+ "  ";

		List<Object> list = new ArrayList<Object>();
		list.add(function.getParentid());
		list.add(function.getFunctionName());
		list.add(null);
		list.add(function.getUrl());
		list.add("1");

		list.add("1");
		getJdbcTemplate().update(sql.toString(), list.toArray());
		
		String sql4 = "UPDATE backend_functions SET sortnum=@@IDENTITY WHERE ID=@@IDENTITY;";
		getJdbcTemplate().update(sql4.toString());
		// 将父节点的isLeaf设置为0
		String sql2 = "update backend_functions set isLeaf=0 where id=" + function.getParentid();
		getJdbcTemplate().update(sql2.toString());

		// 返回id
		String sql3 = "SELECT ID FROM backend_functions WHERE ID=@@IDENTITY";
		return getJdbcTemplate().queryForLong(sql3);
	}

	/**
	 * 修改节点
	 * 
	 * @param function
	 */
	public void updateFunction(Functions function) {
		String sql = " update backend_functions set  function_name=?,description=?,url=?,isLeaf=?,create_time=now() where id=?  ";
		List<Object> list = new ArrayList<Object>();
		list.add(function.getFunctionName());
		list.add(null);
		list.add(function.getUrl());
		list.add("1");
		list.add(function.getId());
		getJdbcTemplate().update(sql.toString(), list.toArray());

	}

	/**
	 * 根据父节点找到孩子节点
	 * 
	 * @return
	 */
	public List<Functions> getFunctionsParent(Integer roleId, Integer id) {
		String sql = "SELECT distinct tf.id,tf.parent_id,tf.create_time,tf.function_name,tf.description,tf.url,tf.isLeaf,sortnum from backend_functions tf join backend_roleright tr on tf.id=tr.function_id  where tf.isShow=1  ";
		List<Object> params = new ArrayList<Object>();
		if (id != null) {
			sql += " and tf.parent_id=?";
			params.add(id);
		}
		if (roleId != null && roleId!= 0 ) {
			sql += " and tr.role_id= ?";
			params.add(roleId);
		}
		sql += " order by sortnum";
		return getJdbcTemplate().query(sql, new EntityMapper(Functions.class), params.toArray());
	}

	/**
	 * 根据父节点找到孩子节点
	 * 
	 * @return
	 */
	public List<Functions> getFunctionsParent(int parentId) {
		String sql = "SELECT t.id,t.parent_id,t.create_time,t.function_name,t.description,t.url,t.isLeaf from backend_functions t where parent_id=" + parentId + " and isShow=1 order by sortnum";
		return createQuery(sql, Functions.class).getResultList();
	}

	/**
	 * 拖拽菜单节点 根据id交换两个节点的排序位置
	 * 
	 * @param sourceId
	 * @param targetId
	 * @param sourceNum
	 * @param targetNum
	 */
	public void dragNode(Long sourceId, Long targetId, Long sourceNum, Long targetNum) {
		String sql = " update backend_functions set  sortnum=? where id=?  ";
		List<Object> list = new ArrayList<Object>();
		list.add(targetNum);
		list.add(sourceId);
		getJdbcTemplate().update(sql.toString(), list.toArray());
		String sql2 = " update backend_functions set  sortnum=? where id=?  ";
		List<Object> list2 = new ArrayList<Object>();
		list2.add(sourceNum);
		list2.add(targetId);
		getJdbcTemplate().update(sql2.toString(), list2.toArray());
	}

	public void deleteFunction(Long id) {
		String sql1 = "select isLeaf from backend_functions where id=" + id;
		// 非叶子节点，不进行任何操作
		if (getJdbcTemplate().queryForInt(sql1) == 0) {
			return;
		}
		// 删除节点以前判断其父节点是否还有子节点
		Integer pid = getJdbcTemplate().queryForInt("SELECT PARENT_ID FROM backend_functions WHERE ID=" + id);
		String sql2 = " SELECT COUNT(*) FROM backend_functions WHERE PARENT_ID=" + pid;
		Integer count = getJdbcTemplate().queryForInt(sql2);
		if (count == null || count == 1) {
			String sql3 = "UPDATE backend_functions SET ISLEAF=1 WHERE ID = " + pid;
			getJdbcTemplate().update(sql3);
		}
		String sql = "delete from backend_functions where id= " + id;
		getJdbcTemplate().update(sql);
	}

	/**
	 * 修改节点是否显示
	 * 
	 * @param id
	 * @param isShow
	 */
	public void updateIsShow(Long id, Integer isShow) {
		String sql = "update backend_functions set isShow=" + isShow + " where id=" + id;
		getJdbcTemplate().update(sql);
	}

	/**
	 * 判断节点能否隐藏
	 * 
	 * @param id
	 * @return
	 */
	public int nodeCanHide(Long id) {
		String sql = "select count(*) from backend_functions where parent_id=" + id + "  and isShow=1";
		return getJdbcTemplate().queryForInt(sql);
	}

	/**
	 * 根据角色id获得对应的url
	 * @param integer
	 * @return
	 */
	public List<String> getRoleUrls(Integer roleid) {
		String sql  = "select URL from backend_functions f join backend_roleright r on f.id = r.function_id where r.role_id =  ? ";
		List<Object> params = new ArrayList<Object>();
		params.add(roleid);
		return getJdbcTemplate().queryForList(sql,params.toArray(), String.class);
	}

	public List<String> getModulesByRoleId(Integer roleid) {
		String sql  = "select function_id from backend_functions f join backend_roleright r on f.id = r.function_id where r.role_id = ? ";
		List<Object> params = new ArrayList<Object>();
		params.add(roleid);
		return getJdbcTemplate().queryForList(sql,params.toArray(), String.class);
	}

}
