package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Deptturn;
import bean.Discharge;
import bean.Employee;
import bean.Postturn;
import bean.Probation;
import service.ProbationService;
import util.DbUtil;
import dao.DeptDao;
import dao.EmployeeDao;
import dao.PositionDao;
import dao.ProbationDao;

public class EmployeeDaoImpl implements EmployeeDao {

	DbUtil db = null;
	DeptDao deptDao = new DeptDaoImpl();
	PositionDao positionDao = new PositionDaoImpl();
	ProbationDao probationDao = new ProbationDaoImpl();

	// 新加
	public List getNewEmpInfo(String date1, String date2, int deptid) {
		db = new DbUtil();
		db.openConnection();
		List list = new ArrayList();
		ResultSet rs = null;
		String sql = null;
		if (deptid == 0)// 表示全部部门
		{
			System.out.println("全部部门");
			sql = "select * from t_dept,t_position,t_employee where emp_deptid = dept_id and emp_postid = post_id and emp_entrydate >= ? and emp_entrydate <= ? and emp_ifdischarge = 1";
			rs = db.exeQuery(sql, date1, date2);
		} else// 其他有的部门
		{
			System.out.println("其他部门");
			sql = "select * from t_dept,t_position,t_employee where emp_deptid = dept_id and emp_postid = post_id and dept_id = ? and emp_entrydate >= ? and emp_entrydate <= ? and emp_ifdischarge = 1";

			rs = db.exeQuery(sql, deptid, date1, date2);
		}
		int i = 0;
		try {

			while (rs.next()) {
				i++;
				Employee employee = new Employee();
				employee.setEmp_id(i);
				employee.setEmp_name(rs.getString("emp_name"));
				employee.setEmp_sex(rs.getString("emp_sex"));
				employee.setEmp_entrydate(rs.getString("emp_entrydate"));
				employee.setEmp_dgree(rs.getString("emp_dgree"));
				employee.setEmp_deptname(rs.getString("dept_name"));
				employee.setEmp_postname(rs.getString("post_name"));
				list.add(employee);
			}

		} catch (Exception e) {
			// TODO: handle exception

		}
		// System.out.println(i);
		db.closeConnection();
		return list;
	}

	public int modDeptByEmpid(int empId, int deptId) {
		db = new DbUtil();
		db.openConnection();
		int rs = 0;
		String sql = null;
		sql = "update t_employee set emp_deptid = ? where emp_id = ?";
		rs = db.exeMod(sql, deptId, empId);
		db.closeConnection();
		return rs;

	}

	public int modPostByEmpid(int empId, int postId) {
		db = new DbUtil();
		db.openConnection();
		int rs = 0;
		String sql = null;
		sql = "update t_employee set emp_postid = ? where emp_id = ?";
		rs = db.exeMod(sql, postId, empId);
		db.closeConnection();
		return rs;

	}

	public int getDeptIdByempid(int empId) {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;
		String sql = null;
		sql = "select emp_deptid from t_employee where emp_id = ?";
		rs = db.exeQuery(sql, empId);
		int deptId = 0;

		try {
			if (rs.next()) {
				deptId = rs.getInt("emp_deptid");
				if (rs.next()) {
					deptId = 0;// 多条也为失败
					// System.out.println("多条");
				}
			} else {
				deptId = 0;
				// System.out.println("0条");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		return deptId;
	}

	public int getPostIdByempid(int empId) {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;
		String sql = null;
		sql = "select emp_postid from t_employee where emp_id = ?";
		rs = db.exeQuery(sql, empId);
		int PostId = 0;

		try {
			if (rs.next()) {
				PostId = rs.getInt("emp_postid");
				if (rs.next()) {
					PostId = 0;// 多条也为失败
					// System.out.println("多条");
				}
			} else {
				PostId = 0;
				// System.out.println("0条");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		return PostId;
		// Employee emp = getEmployeeByempid(empId);
		// return emp.getEmp_deptid();

	}

	// 获取所有员工信息，结果多条
	@Override
	public List<Employee> getAllEmployee() {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;
		List<Employee> list = new ArrayList<Employee>();

		String sql = "select * from t_employee";
		rs = db.exeQuery(sql);
		list = getMessage(rs);
		db.closeConnection();

		return list;
	}

	// getMessage可以考虑用参数选择一些显示
	public List<Employee> getMessage(ResultSet rs) {
		List<Employee> ls = new ArrayList<Employee>();
		try {
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setEmp_id(rs.getInt("emp_id"));
				employee.setEmp_name(rs.getString("emp_name"));
				int dept_id = rs.getInt("emp_deptid");
				String dept_name = deptDao.getDeptnameByid(dept_id);
				employee.setDept_name(dept_name);
				int post_id = rs.getInt("emp_postid");
				String post_name = positionDao.getPostnameByid(post_id);
				employee.setPost_name(post_name);
				employee.setEmp_birth(rs.getString("emp_birth"));
				employee.setEmp_entrydate(rs.getString("emp_entrydate"));
				employee.setEmp_idcard(rs.getString("emp_idcard"));
				ls.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	// 根据员工编号查询员工信息，结果唯一
	// 如果employee为空，则表明没查到
	@Override
	public Employee getEmployeeByempid(int emp_id) {
		db = new DbUtil();
		db.openConnection();

		ResultSet rs = null;

		String sql = "select * from t_employee where emp_id=?";
		rs = db.exeQuery(sql, emp_id);
		List<Employee> list = getMessage(rs);
		db.closeConnection();
		// 判断list里是否有内容
		Employee employee = null;

		if (list.size() == 0) {
			employee = null;
		} else if (list.size() == 1) {
			employee = list.get(0);
		}

		return employee;
	}

	// 根据部门id获取员工信息，结果多条
	@Override
	public List<Employee> getEmpdeptid(int dept_id) {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;

		String sql = "select * from t_employee where emp_deptid=?";
		rs = db.exeQuery(sql, dept_id);
		List<Employee> list = getMessage(rs);
		db.closeConnection();
		return list;
	}

	// 根据岗位id获取员工信息，结果多条
	@Override
	public List<Employee> getEmppostid(int post_id) {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;

		String sql = "select * from t_employee where emp_postid=?";
		rs = db.exeQuery(sql, post_id);
		List<Employee> list = getMessage(rs);
		db.closeConnection();
		return list;
	}

	// 按照姓名模糊查询员工信息，结果多条
	@Override
	public List<Employee> getEmpByname(String name) {
		db = new DbUtil();
		db.openConnection();
		String sql = "select * from t_employee where emp_name like '%" + name
				+ "%'";
		ResultSet rs = db.exeQuery(sql);
		List<Employee> list = getMessage(rs);
		db.closeConnection();
		return list;
	}

	// 试用期管理，按照给定的条件查询处于试用期员工信息，及已转正员工信息
	// 试用期管理显示的信息是编号，姓名，岗位，试用期状态等信息的显示
	@Override
	public List<Probation> getEmppbByTerm(Employee employee, Probation probation) {
		db = new DbUtil();
		db.openConnection();
		StringBuffer buffer = null;
		ResultSet rs = null;
		List<Probation> list = new ArrayList<Probation>();
		List param = new ArrayList();
		if (probation.getPrroba_stateid() == 2) {
			buffer = new StringBuffer(
					"select * from t_employee,t_probation,t_dept,t_position where emp_id=proba_empid and emp_ifprobation=0 and emp_ifdischarge=1 and dept_id=emp_deptid and post_id=emp_postid");
		} else {
			buffer = new StringBuffer(
					"select * from t_employee,t_probation,t_dept,t_position where emp_id=proba_empid and emp_ifprobation=1 and emp_ifdischarge=1 and dept_id=emp_deptid and post_id=emp_postid");
		}
		// System.out.println("emp_id:"+employee.getEmp_id());
		empOptioninTerm(employee, buffer, param);
		empOptiondpTerm(employee, buffer, param);

		if (probation.getPrroba_stateid() != 0) {
			buffer.append(" and proba_stateid=?");
			param.add(probation.getPrroba_stateid());
		}

		if (option(probation.getProba_begindate())) {
			buffer.append(" and proba_begindate=?");
			param.add(probation.getProba_begindate());
		}

		if (option(probation.getProba_enddate())) {
			buffer.append(" and proba_enddate=?");
			param.add(probation.getProba_enddate());
		}

		if (option(probation.getBegintime())) {
			buffer.append(" and proba_dealdate >=?");
			param.add(probation.getBegintime());
		}
		if (option(probation.getEndtime())) {
			buffer.append(" and proba_dealdate <=?");
			param.add(probation.getEndtime());
		}

		String sql = buffer.toString();
		// System.out.println(sql);
		rs = db.exeQuery(sql, param.toArray());
		list = getprobaMesByTerm(rs);
		db.closeConnection();
		// System.out.println("size:" + list.size());
		return list;
	}

	public List<Probation> getprobaMesByTerm(ResultSet rs) {
		List<Probation> list = new ArrayList<Probation>();
		try {
			while (rs.next()) {
				Probation emproba = new Probation();
				emproba.setEmp_id(rs.getInt("emp_id"));
				emproba.setEmp_name(rs.getString("emp_name"));
				emproba.setDept_name(rs.getString("dept_name"));
				// System.out.println(rs.getString("dept_name"));
				emproba.setPost_name(rs.getString("post_name"));
				int state_id = rs.getInt("proba_stateid");
				String state_name = probationDao.getStatenameByid(state_id);
				emproba.setState_name(state_name);
				emproba.setProba_begindate(rs.getString("proba_begindate"));
				emproba.setProba_enddate(rs.getString("proba_enddate"));
				emproba.setProba_dealdate(rs.getString("proba_dealdate"));
				emproba.setProba_comment(rs.getString("proba_comment"));
				list.add(emproba);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 按照，部门编号，岗位编号查询员工信息
	public void empOptiondpTerm(Employee employee, StringBuffer buffer,
			List param) {
		if (employee.getEmp_deptid() != 0) {
			buffer.append(" and emp_deptid=?");
			param.add(employee.getEmp_deptid());
		}

		if (employee.getEmp_postid() != 0) {
			buffer.append(" and emp_postid=?");
			param.add(employee.getEmp_postid());
		}
	}

	// 按照员工编号，员工姓名，查询员工信息
	public void empOptioninTerm(Employee employee, StringBuffer buffer,
			List param) {
		if (employee.getEmp_id() != 0) {
			buffer.append(" and emp_id=?");
			param.add(employee.getEmp_id());
		}

		if (option(employee.getEmp_name())) {
			buffer.append(" and emp_name like '%" + employee.getEmp_name()
					+ "%'");
			// System.out.println(buffer.toString());
		}
	}

	// 条件判断函数，用于判断输入的字符串类型是否为空
	public boolean option(String string) {
		if (string instanceof String) {
			if (string != null && !string.equals("")) {
				return true;
			}
		}
		return false;
	}

	// 员工列表查询 ,查询条件为部门编号，岗位，员工编号，员工姓名
	@Override
	public List<Employee> getEmpByFouritem(Employee employee) {
		db = new DbUtil();
		db.openConnection();
		List<Employee> list = new ArrayList<Employee>();
		ResultSet rs = null;
		StringBuffer buffer = new StringBuffer(
				"select * from t_employee where 1=1 and emp_ifdischarge=1");
		List param = new ArrayList();
		empOptioninTerm(employee, buffer, param);
		empOptiondpTerm(employee, buffer, param);
		String sql = buffer.toString();
		rs = db.exeQuery(sql, param.toArray());
		list = getMessage(rs);
		db.closeConnection();
		return list;
	}

	// 部门调动管理--开始日期，结束日期，员工编号，员工姓名，调动方式。
	@Override
	public List<Deptturn> getDeptturnByitem(Employee employee, Deptturn deptturn) {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;
		StringBuffer buffer = new StringBuffer(
				"select * from t_employee,t_deptturn,t_dturntype where emp_id=dturn_empid and dturn_typeid=dturntp_id and emp_ifdischarge=1");
		List<Deptturn> list = new ArrayList<Deptturn>();
		List param = new ArrayList();
		empOptioninTerm(employee, buffer, param);
		if (option(deptturn.getBegintime())) {
			buffer.append(" and dturn_data >=?");
			param.add(deptturn.getBegintime());
		}
		if (option(deptturn.getEndtime())) {
			buffer.append(" and dturn_data <=?");
			param.add(deptturn.getEndtime());
		}

		if (deptturn.getDturn_typeid() != 0) {
			buffer.append(" and dturn_typeid =?");
			param.add(deptturn.getDturn_typeid());
		}
		String sql = buffer.toString();
		rs = db.exeQuery(sql, param.toArray());
		list = getDeptturnMesByTerm(rs);
		db.closeConnection();
		return list;
	}

	public List<Deptturn> getDeptturnMesByTerm(ResultSet rs) {
		List<Deptturn> list = new ArrayList<Deptturn>();
		try {
			while (rs.next()) {
				Deptturn deptturn = new Deptturn();
				deptturn.setDturn_empid(rs.getInt("emp_id"));
				deptturn.setEmp_name(rs.getString("emp_name"));
				// System.out.println("name:"+rs.getString("emp_name"));
				int beforeid = rs.getInt("dturn_beforeid");
				// System.out.println("beforeid:"+beforeid);
				String beforename = deptDao.getDeptnameByid(beforeid);
				// System.out.println("beforename:"+beforename);
				deptturn.setDturn_beforename(beforename);
				int afterid = rs.getInt("dturn_afterid");
				// System.out.println("afterid:"+afterid);
				String aftername = deptDao.getDeptnameByid(afterid);
				// System.out.println("aftername:"+aftername);
				deptturn.setDturn_aftername(aftername);
				deptturn.setDturn_typeName(rs.getString("dturntp_name"));
				// System.out.println("调动名" + rs.getString("dturntp_name"));
				deptturn.setDturn_data(rs.getString("dturn_data"));
				deptturn.setDturn_reason(rs.getString("dturn_reason"));
				list.add(deptturn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 岗位调动管理--开始日期，结束日期，员工编号，员工姓名，调动方式。
	@Override
	public List<Postturn> getPostturnByitem(Employee employee, Postturn postturn) {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;
		StringBuffer buffer = new StringBuffer(
				"select * from t_employee,t_postturn,t_pturntype where emp_id=pturn_empid and pturn_typeid=t_pturntype.pturn_id and emp_ifdischarge=1");
		List<Postturn> list = new ArrayList<Postturn>();
		List param = new ArrayList();
		empOptioninTerm(employee, buffer, param);
		if (option(postturn.getBegintime())) {
			buffer.append(" and pturn_date >=?");
			param.add(postturn.getBegintime());
		}
		if (option(postturn.getEndtime())) {
			buffer.append(" and pturn_date <=?");
			param.add(postturn.getEndtime());
		}

		if (postturn.getPturn_typeid() != 0) {
			buffer.append(" and pturn_typeid =?");
			param.add(postturn.getPturn_typeid());
		}
		String sql = buffer.toString();
		rs = db.exeQuery(sql, param.toArray());
		list = getPostturnMesByTerm(rs);
		db.closeConnection();
		return list;
	}

	// 岗位调动返回信息
	public List<Postturn> getPostturnMesByTerm(ResultSet rs) {
		List<Postturn> list = new ArrayList<Postturn>();
		try {
			while (rs.next()) {
				Postturn postturn = new Postturn();
				postturn.setPturn_empid(rs.getInt("emp_id"));
				postturn.setEmp_name(rs.getString("emp_name"));
				postturn.setPturn_typeid(rs.getInt("pturn_typeid"));
				int beforeid = rs.getInt("pturn_beforeid");
				// System.out.println("beforeid:" + beforeid);
				String beforename = positionDao.getPostnameByid(beforeid);
				// System.out.println("beforename:" + beforename);
				postturn.setPturn_beforename(beforename);
				int afterid = rs.getInt("pturn_afterid");
				// System.out.println("afterid:" + afterid);
				String aftername = positionDao.getPostnameByid(afterid);
				// System.out.println("aftername:" + aftername);
				postturn.setPturn_aftername(aftername);
				postturn.setPturn_typeName(rs.getString("pturn_name"));
				// System.out.println("岗位动:" + rs.getString("pturn_name"));
				postturn.setPturn_date(rs.getString("pturn_date"));
				postturn.setPturn_reason(rs.getString("pturn_reason"));
				list.add(postturn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 已离职员工信息查询--员工编号，姓名，部门名称，岗位名称，离职类型，查询开始日期，结束日期
	@Override
	public List<Discharge> getDischgByitem(Employee employee,
			Discharge discharge) {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;
		StringBuffer buffer = new StringBuffer(
				"select * from t_employee,t_discharge,t_dept,t_position where emp_id=dischg_empid and dept_id=emp_deptid and post_id=emp_postid");
		List<Discharge> list = new ArrayList<Discharge>();
		List param = new ArrayList();
		empOptioninTerm(employee, buffer, param);
		empOptiondpTerm(employee, buffer, param);
		if (option(discharge.getBegintime())) {
			buffer.append(" and dischg_date >=?");
			param.add(discharge.getBegintime());
		}
		if (option(discharge.getEndtime())) {
			buffer.append(" and dischg_date <=?");
			param.add(discharge.getEndtime());
		}
		System.out.println(discharge.getDischg_type());
		if (option(discharge.getDischg_type())) {
			buffer.append(" and dischg_type =?");
			param.add(discharge.getDischg_type());
		}
		String sql = buffer.toString();
		System.out.println(sql);
		rs = db.exeQuery(sql, param.toArray());
		list = getDischgMesByTerm(rs);
		db.closeConnection();
		return list;
	}

	public List<Discharge> getDischgMesByTerm(ResultSet rs) {
		List<Discharge> list = new ArrayList<Discharge>();
		try {
			while (rs.next()) {
				Discharge discharge = new Discharge();
				discharge.setDischg_empid(rs.getInt("dischg_empid"));
				discharge.setEmp_name(rs.getString("emp_name"));
				discharge.setDept_name(rs.getString("dept_name"));
				discharge.setPost_name(rs.getString("post_name"));
				discharge.setDischg_date(rs.getString("dischg_date"));
				discharge.setDischg_type(rs.getString("dischg_type"));
				list.add(discharge);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Employee> getAllNewEmp(String date1, String date2, String dep) {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		String sql = null;
		int dept_id = 0;
		List<Employee> list = new ArrayList<Employee>();

		// System.out.println(date1);
		// System.out.println(date2);
		// System.out.println(dep);
		if ("全部".equals(dep)) {
			sql = "select * from t_employee where emp_entrydate >=? and emp_entrydate <=?";
			rs = db.exeQuery(sql, date1, date2);
			// System.out.println("rs:" + rs);
			list = Try(rs);
			// System.out.println(rs);
		} else {
			sql = "select * from t_dept where dept_name=?";
			rs2 = db.exeQuery(sql, dep);
			try {
				while (rs2.next()) {
					try {
						String sql1 = "select * from t_employee where emp_deptid=?";
						dept_id = rs2.getInt("dept_id");
						rs1 = db.exeQuery(sql1, dept_id);
						while (rs1.next()) {
							sql = "select * from t_employee where "
									+ "emp_entrydate>=? and emp_entrydate<=?";
							rs = db.exeQuery(sql, date1, date2);
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

	public List<Employee> Try(ResultSet rs) {
		db = new DbUtil();
		db.openConnection();
		// System.out.println("jinqu");
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		int i = 0, dept_id = 0, post_id = 0;
		List list = new ArrayList();

		// System.out.println(rs);
		try {
			while (rs.next()) {
				i++;
				Employee employee = new Employee();
				employee.setEmp_id(i);
				employee.setEmp_name(rs.getString("emp_name"));
				employee.setEmp_sex(rs.getString("emp_sex"));
				employee.setEmp_entrydate(rs.getString("emp_entrydate"));
				employee.setEmp_dgree(rs.getString("emp_dgree"));

				dept_id = rs.getInt("emp_deptid");
				post_id = rs.getInt("emp_postid");

				rs1 = db.exeQuery("select * from t_dept where dept_id="
						+ dept_id);
				try {
					while (rs1.next()) {
						employee.setEmp_deptname(rs1.getString("dept_name"));
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				rs2 = db.exeQuery("select * from t_position where post_id="
						+ post_id);
				try {
					while (rs2.next()) {
						employee.setEmp_postname(rs2.getString("post_name"));
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				// System.out.println(employee.getEmp_id());
				// System.out.println(employee.getEmp_name());
				// System.out.println(employee.getEmp_sex());
				// System.out.println(employee.getEmp_deptname());
				// System.out.println(employee.getEmp_postname());
				// System.out.println(employee.getEmp_entrydate());
				// System.out.println(employee.getEmp_dgree());
				// System.out.println("---------------------------");
				list.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		return list;
	}

	// new

	@Override
	public int addEmpMeaasge(Employee emp, Probation pro) {
		db = new DbUtil();
		db.openConnection();
		String sql = "insert into t_employee(emp_name,emp_sex,emp_birth,emp_idcard,emp_deptid,emp_postid,emp_entrydate,emp_partidate,emp_form,emp_source,emp_dgree) values(?,?,?,?,?,?,?,?,?,?,?)";
		int row = 0;
		int emp_id = 0;
		row = db.exeMod(sql, emp.getEmp_name(), emp.getEmp_sex(),
				emp.getEmp_birth(), emp.getEmp_idcard(), emp.getEmp_deptid(),
				emp.getEmp_postid(), emp.getEmp_entrydate(),
				emp.getEmp_partidate(), emp.getEmp_form(), emp.getEmp_source(),
				emp.getEmp_dgree());
		if (row == 1) {// 说明员工入职成功

			if (emp.getEmp_ifprobation() == 1) {// =1说明有试用期，这是需要录入试用期开始和结束时间
				String sql1 = "select max(emp_id) from t_employee";
				ResultSet rs = db.exeQuery(sql1);
				try {
					while (rs.next()) {
						emp_id = rs.getInt("max(emp_id)");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				String sq = "update t_employee set emp_ifprobation=? where emp_id=?";
				db.exeMod(sq, 1, emp_id);
				String sqll = "insert into t_probation(proba_empid,proba_begindate,proba_enddate,proba_stateid) values(?,?,?,?)";
				row += db.exeMod(sqll, emp_id, pro.getProba_begindate(),
						pro.getProba_enddate(), 1);
			}
		}
		db.closeConnection();
		return row;
	}
}
