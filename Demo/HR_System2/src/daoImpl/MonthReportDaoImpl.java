package daoImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DbUtil;
import bean.Dept;
import bean.Employee;
import bean.Monthreport;
import dao.MonthReportDao;

public class MonthReportDaoImpl implements MonthReportDao {
	DbUtil db = null;

	public List<Monthreport> getAllReport(String date1, String date2) {
		// TODO Auto-generated method stub
		db = new DbUtil();
		db.openConnection();
		List<Dept> list = new ArrayList<Dept>();
		List<Monthreport> list1 = new ArrayList<Monthreport>();
		DeptDaoImpl dept = new DeptDaoImpl();
		list = dept.getAllDept();
		ResultSet rs = null;
		ResultSet rs1 = null;
		String sql = null, sql1 = null;
		int i = 0, j = 0, k = 0, a = 0;

		

		for (Dept deptl : list) {
			Monthreport monthreport = new Monthreport();
			monthreport.setDept_name(deptl.getDept_name());
			System.out.println(monthreport.getDept_name());

			// 月初人数
			sql = "select * from t_employee where emp_entrydate < ? and emp_deptid=?";
			rs = db.exeQuery(sql, date1, deptl.getDept_id());
			try {
				while (rs.next()) {
					i++;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			monthreport.setEmp_beforenum(i);

			// 月末人数
			i = 0;
			sql = "select * from t_employee where emp_entrydate <= ? and emp_deptid=?";
			rs = db.exeQuery(sql, date2, deptl.getDept_id());
			try {
				while (rs.next()) {
					i++;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			monthreport.setEmp_afternum(i);
		
			// 新入职人数
			i = 0;
			sql = "select * from t_employee where emp_entrydate >= ? and emp_entrydate <= ? and emp_deptid=?";
			rs = db.exeQuery(sql, date1, date2, deptl.getDept_id());
			try {
				while (rs.next()) {
					i++;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			monthreport.setEmp_newnum(i);
			
			// 离职人数
			i = 0;
			sql = "select * from t_discharge where dischg_date >= ? and dischg_date <= ?";
			rs = db.exeQuery(sql, date1, date2);
			try {
				while (rs.next()) {
					sql1 = "select * from t_employee where emp_id=? and emp_deptid=?";
					rs1 = db.exeQuery(sql1, rs.getInt("dischg_id"),
							deptl.getDept_id());
					try {
						while (rs1.next()) {
							i++;
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			monthreport.setEmp_dischargenum(i);
			
			// 学历统计
			i = 0;
			sql = "select * from t_employee where emp_deptid=?";
			rs = db.exeQuery(sql, deptl.getDept_id());
			try {
				while (rs.next()) {
					switch (rs.getString("emp_dgree")) {
					case "研究生":
						i++;
						break;
					case "本科":
						j++;
						break;
					case "大专":
						k++;
						break;
					case "高中及高中以下":
						a++;
						break;
					default:
						break;
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			monthreport.setGraduate_num(i);
			monthreport.setCollege_num(j);
			monthreport.setJunior_num(k);
			monthreport.setSenior_num(a);
		

			// 调转出的人数
			i = 0;
			sql = "select * from t_deptturn where dturn_data>=? and dturn_data<=? and dturn_beforeid=?";
			rs = db.exeQuery(sql, date1, date2, deptl.getDept_id());
			try {
				while (rs.next()) {
					i++;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			monthreport.setTurnout_num(i);
		
			// 调转入的人数
			i = 0;
			sql = "select * from t_deptturn where dturn_data>=? and dturn_data<=? and dturn_afterid=?";
			rs = db.exeQuery(sql, date1, date2, deptl.getDept_id());
			try {
				while (rs.next()) {
					i++;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			monthreport.setTurnin_num(i);
		   // System.out.println("aaaaaa...."+monthreport.getDept_name());
		   // System.out.println("aaaaaa...."+monthreport.getEmp_beforenum());
		   // System.out.println("aaaaaa...."+monthreport.getEmp_afternum());
		
			list1.add(monthreport);
			/*System.out.println(list1);*/
		}
		db.closeConnection();
		return list1;
	}

}
