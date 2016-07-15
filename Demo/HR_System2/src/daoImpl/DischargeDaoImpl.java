package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DbUtil;
import bean.Dept;
import bean.Discharge;
import bean.Employee;
import bean.Position;
import bean.Postturn;
import dao.DischargeDao;

public class DischargeDaoImpl implements DischargeDao {

	DbUtil db = null;

	// 插入离职信息
	public int dischgDeal(Discharge dischg) {
		db = new DbUtil();
		db.openConnection();
		int rs = 0;
		int i = 0;
		String sql = null;
		sql = "insert into t_discharge(dischg_empid,dischg_date,dischg_type,dischg_dinec,dischg_ifenter)"
				+ "values(?,?,?,?,?)";

		rs = db.exeMod(sql, dischg.getDischg_empid(), dischg.getDischg_date(),
				dischg.getDischg_type(), dischg.getDischg_dinec(),
				dischg.getDischg_ifenter());
		if (rs == 1) {
			String sq = "update t_employee set emp_ifdischarge=? where emp_id=?";
			i = db.exeMod(sq, 0, dischg.getDischg_empid());
		}
		db.closeConnection();
		System.out.println("i:" + i);
		return i;
	}
	
	public List getDisInfo(String date1, String date2, int deptid)
	{
		db = new DbUtil();
		db.openConnection();
		List list = new ArrayList();
		ResultSet rs = null;
		String sql = null;
		if (deptid == 0)//表示全部部门
		{
			System.out.println("全部部门");
			sql = "select * from t_discharge,t_dept,t_position,t_employee where dischg_empid = emp_id and emp_deptid = dept_id and emp_postid = post_id and dischg_date >= ? and dischg_date<= ?";
			rs = db.exeQuery(sql, date1,date2);
		}
		else//其他有的部门
		{
			System.out.println("其他部门");
			sql ="select * from t_discharge,t_dept,t_position,t_employee where dischg_empid = emp_id and emp_deptid = dept_id and emp_postid = post_id and dept_id = ? and dischg_date >= ? and dischg_date<= ?";

			rs = db.exeQuery(sql,deptid, date1,date2);
		}
		int i= 0;
		try 
		{

			while(rs.next())
			{
				i++;
				Discharge discharge = new Discharge();
				discharge.setDischg_id(i);

				discharge.setEmp_name(rs.getString("emp_name"));
				discharge.setEmp_sex(rs.getString("emp_sex"));
				discharge.setDept_name(rs.getString("dept_name"));
				discharge.setPost_name(rs.getString("post_name"));
				discharge.setDischg_date(rs.getString("dischg_date"));
				discharge.setDischg_type(rs.getString("dischg_type"));
				list.add(discharge);			
			}
				
		} catch (Exception e) {
					// TODO: handle exception
				
		}
		System.out.println(i);
		db.closeConnection();
		return list;
	}
	//新加
	public List getAllDischarge(String date1, String date2, String dep) {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;

		int emp_id = 0, dept_id;
		String sql = null;
		List list = new ArrayList();

		System.out.println(date1);
		System.out.println(date2);
		System.out.println(dep);

		if ("全部".equals(dep)) {
			sql = "select * from t_discharge where "
					+ "dischg_date>=? and dischg_date<=?";

			rs = db.exeQuery(sql, date1, date2);
			list = Try(rs);

		}

		else {
			sql = "select * from t_dept where dept_name=?";
			rs2 = db.exeQuery(sql, dep);//通过部门名称查询部门标号
			
			try {
				while(rs2.next()){
					try {
						String sql1 = "select * from t_employee where emp_deptid=?";
						dept_id = rs2.getInt("dept_id");
						rs1 = db.exeQuery(sql1, dept_id);//通过查询该部门的所有员工
						while (rs1.next()) {
							emp_id = rs1.getInt("emp_id");
							sql = "select * from t_discharge where "
									+ "dischg_date>=?and dischg_date<=?";
							rs = db.exeQuery(sql, date1, date2);//查询离职表在日期之间的
							list = Try(rs);

						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

		}
		db.closeConnection();
		return list;
	}

	public List Try(ResultSet rs) {
		System.out.println("jinqu");
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		int i = 0, emp_id = 0, dept_id = 0, post_id = 0;
		List list = new ArrayList();
		try {
			while (rs.next()) {
				i++;
				Discharge discharge = new Discharge();
				discharge.setDischg_id(i);

				String s = "select * from t_employee where emp_id=?";
				rs1 = db.exeQuery(s, rs.getInt("dischg_empid"));
				try {
					while (rs1.next()) {
						discharge.setEmp_name(rs1.getString("emp_name"));
						discharge.setEmp_sex(rs1.getString("emp_sex"));
						dept_id = rs1.getInt("emp_deptid");
						post_id = rs1.getInt("emp_postid");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				/*
				 * rs1=db.exeQuery("select * from t_employee where emp_id="
				 * +rs.getInt("dischg_empid"));
				 */
				/* System.out.println("xingming"+rs1.getString("emp_name")); */

				rs2 = db.exeQuery("select * from t_dept where dept_id="
						+ dept_id);
				try {
					while (rs2.next()) {
						discharge.setDept_name(rs2.getString("dept_name"));
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				rs3 = db.exeQuery("select * from t_position where post_id="
						+ post_id);
				try {
					while (rs3.next()) {
						discharge.setPost_name(rs3.getString("post_name"));
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				discharge.setDischg_date(rs.getString("dischg_date"));
				discharge.setDischg_type(rs.getString("dischg_type"));
				/*
				 * System.out.println(discharge.getDischg_id());
				 * System.out.println(discharge.getDept_name());
				 * System.out.println(discharge.getPost_name());
				 * System.out.println(discharge.getEmp_name());
				 * System.out.println(discharge.getEmp_sex());
				 * System.out.println(discharge.getDischg_date());
				 * System.out.println(discharge.getDischg_type());
				 * System.out.println("---------------------------");
				 */
				list.add(discharge);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
