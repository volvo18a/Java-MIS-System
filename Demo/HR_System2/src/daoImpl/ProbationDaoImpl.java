package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DbUtil;
import bean.Employee;
import bean.Probation;
import dao.ProbationDao;

public class ProbationDaoImpl implements ProbationDao {

	DbUtil db = null;

	public int getStateIdByName(String stateName) {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;
		String sql = "select state_id from t_prostate where state_name like ?";
		rs = db.exeQuery(sql, stateName);
		// System.out.println(sql);
		int stateId = 0;

		try {
			if (rs.next()) {
				stateId = rs.getInt("state_id");
				if (rs.next()) {
					stateId = 0;// 多条也为失败
					System.out.println("多条");
				}
			} else {
				stateId = 0;
				System.out.println("0条");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		return stateId;
	}

	/**
	 * 更新状态为延期的方法 返回更新数据的行数，0为失败
	 */

	public int updateDelayState(Probation probation) {
		db = new DbUtil();
		db.openConnection();
		int rs = 0;
		System.out.println(probation.getEmp_id());
		String sql = "update t_probation set proba_stateid = ?,proba_dealdate = ?"
				+ ",proba_comment = ?, proba_enddate = ? where proba_empid = ?";

		rs = db.exeMod(sql, probation.getPrroba_stateid(),
				probation.getProba_dealdate(), probation.getProba_comment(),
				probation.getProba_enddate(), probation.getEmp_id());
		System.out.println("rs:" + rs);
		db.closeConnection();
		return rs;
	}

	/**
	 * 更新状态为不是延期的方法 返回更新数据的行数，0为失败
	 */
	public int updateState(Probation probation) {
		db = new DbUtil();
		db.openConnection();
		int rs = 0;
		System.out.println(probation.getEmp_id());
		String sql = "update t_probation set proba_stateid = ?,proba_dealdate = ?,"
				+ "proba_comment = ? where proba_empid = ?";

		rs = db.exeMod(sql, probation.getPrroba_stateid(),
				probation.getProba_dealdate(), probation.getProba_comment(),
				probation.getEmp_id());
		if (rs == 1) {

			System.out.println(" 试用期:" + probation.getEmp_id());
			String sq = "update t_employee set emp_ifprobation = ? where emp_id=?";
			rs += db.exeMod(sq, 0, probation.getEmp_id());

			// System.out.println("ggg:i有"+i);
		}
		db.closeConnection();
		return rs;
	}

	@Override
	public String getStatenameByid(int state_id) {
		db = new DbUtil();
		db.openConnection();
		String sql = "select state_name from t_prostate where state_id=?";
		ResultSet rs = db.exeQuery(sql, state_id);
		String state_name = null;
		try {
			if (rs.next()) {
				state_name = rs.getString("state_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		return state_name;
	}
}
