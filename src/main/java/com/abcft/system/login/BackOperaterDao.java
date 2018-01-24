package com.abcft.system.login;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.abcft.common.base.BaseCondition;
import com.abcft.common.base.BaseDao;
import com.abcft.common.base.BaseQuery;
import com.abcft.common.base.EntityMapper;
import com.abcft.common.base.MysqlQuery;
import com.abcft.common.db.Employee;

/**
 * 系统用户 Dao(数据库操作)
 * 
 * @author inning
 * @DateTime 2015-7-20 上午11:39:35
 *
 */
@Repository
public class BackOperaterDao extends BaseDao {
	
	   
	
	
	
	
	/*public Employee findUserLogin(String username) {
		String sql = "SELECT UserName,PassWord,Id,FullName,PhoneNumber,Email,EmployeeStatus,RoleId FROM Employee   WHERE  UserName=?  and status=0     ";
		Employee employee = createQuery(sql, Employee.class).setParameter(
				username).getSingleResult();
		return employee;
	}*/

	/*// 查询
	public BackOperator findBackOperatorById(Long id) {
		BackOperator backOperator = super.find(BackOperator.class, id);
		return backOperator;
	}

	// 查询用户
	public List<BackOperator> queryBackOperatorList(BackOperator operator) {
		BaseCondition bc = BaseCondition.getCondition("and");
		if (operator != null) {
			bc.addAnd(" OPERATOR_ID = ?", operator.getOperatorid());
			bc.addAnd(" OPERATOR_NAME = ?", operator.getOperatorname());
			bc.addAnd(" OPERATOR_REALNAME like ?",
					"%" + operator.getOperatorrealname() + "%");
		}
		StringBuffer sql = new StringBuffer(
				"SELECT A.OPERATOR_ID,A.OPERATOR_NAME,A.OPERATOR_REALNAME,A.ROLELIST FROM SYS_ADMIN A ");
		sql.append("WHERE  1=1 ");

		sql.append(bc.getStr());
		BaseQuery q = createQuery(sql.toString(), BackOperator.class);
		q.setFirst(operator.getFirstRow()).setMax(operator.getPageRows());
		bc.setParameter(q);
		operator.setTotalRows(q.getTotalRows());
		return q.getResultList();
	}

	// 新增
	public BackOperator insertBackOperator(BackOperator backOperator) {
		return (BackOperator) super.insert(backOperator);
	}

	// 更新
	public int updateBackOperator(BackOperator backOperator) {
		return super.update(backOperator);
	}

	// 删除
	public int deleteBackOperator(Long id) {
		return super.delete(BackOperator.class, id);
	}


	public int updateEmployee(Employee a) {
		return super.update(a);
	}*/
}
