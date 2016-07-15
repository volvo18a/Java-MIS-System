package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DbUtil;
import bean.Dept;
import bean.Deptturn;
import bean.Discharge;
import bean.Employee;
import dao.DeptTurnDao;

public class DeptTurnDaoImpl implements DeptTurnDao {
	DbUtil db = null;

	/**
	 * 通过类型名称查询类型编号
	 * 
	 * @param typeName
	 * @return返回值大于0是查询成功
	 */
	public int getTypeidByName(String typeName) {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;
		String sql = null;
		int typeId = 0;
		sql = "select dturntp_id from t_dturntype where dturntp_name = ?";
		rs = db.exeQuery(sql, typeName);

		try {
			if (rs.next()) {
				typeId = rs.getInt("dturntp_id");
				if (rs.next()) {
					typeId = 0;// 多条也为失败
					System.out.println("多条");
				}
			} else {
				System.out.println("0条");
				typeId = 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		return typeId;
	}

	/**
	 * 更新调动信息，实际是插入调动数据，该表用调用日期和员工编号为主键 如果要查看最新在员工表查看 调转后要及时更新员工表里的部门编号
	 * 根据员工id,调转后部门id更新员工岗位信息
	 */
	public int deptTrun(Deptturn dturn) {
		db = new DbUtil();
		db.openConnection();
		int rs = 0;
		String sql = null;
		sql = "insert into t_deptturn(dturn_beforeid,dturn_afterid,"
				+ "dturn_typeid,dturn_reason,dturn_data,dturn_empid) values(?,?,?,?,?,?)";
		rs = db.exeMod(sql, dturn.getDturn_beforeid(),
				dturn.getDturn_afterid(), dturn.getDturn_typeid(),
				dturn.getDturn_reason(), dturn.getDturn_data(),
				dturn.getDturn_empid());
		 //System.out.println("rs:" + rs);
		db.closeConnection();
		return rs;
	}

	/**
	 *
	 * @return
	 */
	/*
	 * public int setInvalid(int emp_id) { int rs = 0;
	 * 
	 * String sql = null; sql =
	 * "update t_deptturn set dturn_state = 0 where dturn_empid = ? and dturn_state = 1"
	 * ; return 0; }
	 */
	public List getAllDeptTurn(String date1, String date2) {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;
		String sql = null;
		List list = new ArrayList();

		System.out.println(date1);
		System.out.println(date2);

		sql = "select * from t_deptturn where "
				+ "dturn_data>=? and dturn_data<=?";

		rs = db.exeQuery(sql, date1, date2);
		list = Try(rs);
		db.closeConnection();
		return list;
	}

	public List Try(ResultSet rs) {
		/*db = new DbUtil();
		db.openConnection();*/
		System.out.println("jinqu");
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		int i = 0, dept_beforeid = 0, dept_afterid = 0;
		List list = new ArrayList();
		try {
			while (rs.next()) {
				dept_beforeid = rs.getInt("dturn_beforeid");
				dept_afterid = rs.getInt("dturn_afterid");
				i++;
				Deptturn deptturn = new Deptturn();
				deptturn.setDturn_id(i);
				String s = "select * from t_employee where emp_id=?";
				rs1 = db.exeQuery(s, rs.getInt("dturn_empid"));
				try {
					while (rs1.next()) {
						deptturn.setEmp_name(rs1.getString("emp_name"));
						deptturn.setEmp_sex(rs1.getString("emp_sex"));
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				rs2 = db.exeQuery("select * from t_dept where dept_id="
						+ dept_beforeid);
				try {
					while (rs2.next()) {
						deptturn.setDturn_beforename(rs2.getString("dept_name"));
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				rs3 = db.exeQuery("select * from t_dept where dept_id="
						+ dept_afterid);
				try {
					while (rs3.next()) {
						deptturn.setDturn_aftername(rs3.getString("dept_name"));
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				deptturn.setDturn_data(rs.getString("dturn_data"));
				deptturn.setDturn_reason(rs.getString("dturn_reason"));
				System.out.println(deptturn.getDturn_id());
				System.out.println(deptturn.getEmp_name());
				System.out.println(deptturn.getEmp_sex());
				System.out.println(deptturn.getDturn_beforename());
				System.out.println(deptturn.getDturn_aftername());
				System.out.println(deptturn.getDturn_data());
				System.out.println(deptturn.getDturn_reason());
				System.out.println("---------------------------");
				list.add(deptturn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	//	db.closeConnection();
		return list;
	}

}
