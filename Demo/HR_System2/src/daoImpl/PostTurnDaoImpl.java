package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DbUtil;
import bean.Deptturn;
import bean.Postturn;
import dao.PostTurnDao;

public class PostTurnDaoImpl implements PostTurnDao {

	DbUtil db = null;

	/**
	 * 插入岗位调动信息
	 * 
	 * @param dturn
	 * @return
	 */
	public int postTrun(Postturn pturn) {
		db = new DbUtil();
		db.openConnection();
		int rs = 0;
		String sql = null;
		sql = "insert into t_postturn(pturn_beforeid,pturn_afterid,"
				+ "pturn_typeid,pturn_reason,pturn_date,pturn_empid) values(?,?,?,?,?,?)";
		rs = db.exeMod(sql, pturn.getPturn_beforeid(),
				pturn.getPturn_afterid(), pturn.getPturn_typeid(),
				pturn.getPturn_reason(), pturn.getPturn_date(),
				pturn.getPturn_empid());
		db.closeConnection();
		System.out.println("rs:" + rs);
		return rs;
	}

	/**
	 * 通过调转类型名称查找id
	 * 
	 * @param typeName
	 * @return
	 */
	public int getTypeidByName(String typeName) {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;
		String sql = null;
		int typeId = 0;
		sql = "select pturn_id from t_pturntype where pturn_name = ?";
		rs = db.exeQuery(sql, typeName);

		try {
			if (rs.next()) {
				typeId = rs.getInt("pturn_id");
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

	public List getAllPostTurn(String date1, String date2) {
		db = new DbUtil();
		db.openConnection();

		ResultSet rs = null;
		String sql = null;
		List list = new ArrayList();

		System.out.println(date1);
		System.out.println(date2);

		sql = "select * from t_postturn where "
				+ "pturn_date>=? and pturn_date<=?";

		rs = db.exeQuery(sql, date1, date2);
		list = Try(rs);
        db.closeConnection();
		return list;
	}

	public List Try(ResultSet rs) {
		System.out.println("jinqu");
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		int i = 0, post_beforeid = 0, post_afterid = 0, dept_id = 0;
		List list = new ArrayList();
		try {
			while (rs.next()) {
				post_beforeid = rs.getInt("pturn_beforeid");
				post_afterid = rs.getInt("pturn_afterid");

				i++;

				Postturn postturn = new Postturn();
				postturn.setPturn_id(i);
				String s = "select * from t_employee where emp_id=?";
				rs1 = db.exeQuery(s, rs.getInt("pturn_empid"));
				try {
					while (rs1.next()) {
						postturn.setEmp_name(rs1.getString("emp_name"));
						postturn.setEmp_sex(rs1.getString("emp_sex"));
						dept_id = (rs1.getInt("emp_deptid"));

						rs4 = db.exeQuery("select * from t_dept where dept_id="
								+ dept_id);
						while (rs4.next()) {
							try {
								System.out.println(rs4.getString("dept_name"));
								postturn.setPturn_deptname(rs4
										.getString("dept_name"));
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				rs2 = db.exeQuery("select * from t_position where post_id="
						+ post_beforeid);
				try {
					while (rs2.next()) {
						postturn.setPturn_beforename(rs2.getString("post_name"));
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				rs3 = db.exeQuery("select * from t_position where post_id="
						+ post_afterid);
				try {
					while (rs3.next()) {
						postturn.setPturn_aftername(rs3.getString("post_name"));
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				postturn.setPturn_date(rs.getString("pturn_date"));
				postturn.setPturn_reason(rs.getString("pturn_reason"));
				/*
				 * System.out.println(postturn.getPturn_id());
				 * System.out.println(postturn.getEmp_name());
				 * System.out.println(postturn.getEmp_sex());
				 * System.out.println(postturn.getPturn_deptname());
				 * System.out.println(postturn.getPturn_beforename());
				 * System.out.println(postturn.getPturn_aftername());
				 * System.out.println(postturn.getPturn_date());
				 * System.out.println(postturn.getPturn_reason());
				 * System.out.println("---------------------------");
				 */
				list.add(postturn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
