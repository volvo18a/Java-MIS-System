package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DbUtil;
import bean.Dept;
import bean.Employee;
import dao.DeptDao;
import dao.PositionDao;

public class DeptDaoImpl implements DeptDao {

	DbUtil db = null;
	PositionDao postDao = new PositionDaoImpl();

	// 根据部门名称查询部门id
	public int getDeptidByName(String deptName) {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;
		String sql = "select dept_id from t_dept where dept_name like ? and dept_state = 1";
		rs = db.exeQuery(sql, deptName);
		// System.out.println(sql);
		int deptId = 0;

		try {
			if (rs.next()) {
				deptId = rs.getInt("dept_id");
				if (rs.next()) {
					deptId = 0;// 多条也为失败
					 System.out.println("多条");
				}
			} else {
				deptId = 0;
				 System.out.println("0条");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		return deptId;
	}

	@Override
	public List<Dept> getAllDept() {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;
		List<Dept> list = new ArrayList<Dept>();
		String sql = "select * from t_dept,t_deptype where dept_typeid = dtype_id and dept_state='1' order by dept_id asc";
		rs = db.exeQuery(sql);
		list = getDeptMessage(rs);
		db.closeConnection();
		return list;
	}

	@Override
	public List<Employee> getEmpBydeptid(int dept_id) {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;

		List<Employee> list = new ArrayList<Employee>();

		String sql = "select * from t_employee where emp_deptid=? and emp_ifdischarge = '1'";
		rs = db.exeQuery(sql, dept_id);
		try {
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setEmp_id(rs.getInt("emp_id"));
				employee.setEmp_name(rs.getString("emp_name"));
				employee.setEmp_birth(rs.getString("emp_birth"));
				employee.setEmp_idcard(rs.getString("emp_idcard"));
				employee.setEmp_entrydate(rs.getString("emp_entrydate"));
				employee.setEmp_sex(rs.getString("emp_sex"));
				// 根据部门id查询部门名称
				int dept_id1 = rs.getInt("emp_deptid");
				employee.setEmp_deptname(getDeptnameByid(dept_id1));
				// 根据岗位id查询岗位名称
				int post_id = rs.getInt("emp_postid");
				employee.setEmp_postname(postDao.getPostnameByid(post_id));

				list.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		return list;
	}

	@Override
	public Dept getDeptByname(String dept_name) {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;
		List<Dept> list = new ArrayList<Dept>();
		Dept dept = null;
		String sql = "select * from t_dept where dept_name=?";
		rs = db.exeQuery(sql, dept_name);
		list = getDeptMessage(rs);
		db.closeConnection();
		if (list.size() == 0) {
			dept = null;
		} else if (list.size() == 1) {
			dept = list.get(0);
		}

		return dept;
	}

	@Override
	public Dept getDeptByid(int dept_id) {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;
		List<Dept> list = new ArrayList<Dept>();
		Dept dept = null;
		String sql = "select * from t_dept where dept_id=?";
		rs = db.exeQuery(sql, dept_id);
		list = getDeptMessage(rs);
		db.closeConnection();
		if (list.size() == 0) {
			dept = null;
		} else if (list.size() == 1) {
			dept = list.get(0);
		}
		return dept;
	}

	@Override
	public int addDeptMessage(Dept dept) {
		db = new DbUtil();
		db.openConnection();
		// 添加数据有效dept_state置为1,无效置为0
		int row = 0;
		String strSql = "insert into t_dept(dept_name,dept_typeid,dept_tel,dept_fax,dept_sup,dept_setdate,dept_description,dept_state) values(?,?,?,?,?,?,?,?)";
		row = db.exeMod(strSql, dept.getDept_name(), dept.getDept_typeid(),
				dept.getDept_tel(), dept.getDept_fax(), dept.getDept_sup(),
				dept.getDept_setdate(), dept.getDept_description(), 1);
		db.closeConnection();
		return row;
	}

	@Override
	public int modDeptMessage(Dept dept) {
		db = new DbUtil();
		db.openConnection();
		int row = 0;
		String strSql = "update t_dept set dept_name=?,dept_typeid=?,dept_sup=?,dept_state=? where dept_id=?";
		row = db.exeMod(strSql, dept.getDept_name(), dept.getDept_typeid(),
				dept.getDept_sup(), dept.getDept_state(), dept.getDept_id());
		db.closeConnection();
		return row;
	}

	@Override
	public int delDeptMessage(int dept_id) {

		// 删除实际上并删除数据，而是将数据是否有效字段（dept_state）置为0
		int row = 0;

		List<Employee> list = new ArrayList<Employee>();
		list = getEmpBydeptid(dept_id);
		System.out.println("size:" + list.size());

		// list.isEmpty()判断list里边有没有元素
		// list == null 有没有list ---没有水杯
		if (list == null || list.isEmpty()) {
			db = new DbUtil();
			db.openConnection();
			// System.out.println("jinru");
			String strSql = "update t_dept set dept_state = '0' where dept_id = ?";
			row = db.exeMod(strSql, dept_id);
		}
		db.closeConnection();
		return row;
	}

	public List<Dept> getDeptMessage(ResultSet rs) {
		List<Dept> ls = new ArrayList<Dept>();
		try {
			while (rs.next()) {
				Dept dept = new Dept();
				String dept_supname = null;
				dept.setDept_id(rs.getInt("dept_id"));
				dept.setDept_name(rs.getString("dept_name"));
				dept.setDept_typename(rs.getString("dtype_name"));
				dept.setDept_tel(rs.getInt("dept_tel"));
				dept.setDept_fax(rs.getString("dept_fax"));
				dept_supname = getDeptnameByid(rs.getInt("dept_sup"));
				dept.setDept_supname(dept_supname);
				dept.setDept_setdate(rs.getString("dept_setdate"));
				dept.setDept_description(rs.getString("dept_description"));
				ls.add(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	@Override
	public int getDeptTypeByname(String dtype_name) {
		db = new DbUtil();
		db.openConnection();
		//System.out.println(dtype_name);
		ResultSet rs = null;
		String sql = "select dtype_id from t_deptype where dtype_name like ?";
		rs = db.exeQuery(sql, dtype_name);
		// System.out.println(sql);
		int dtype_id = 0;

		try {
			if (rs.next()) {
				// System.out.println("222");
				dtype_id = rs.getInt("dtype_id");
				// System.out.println("111"+dtype_id);
				if (rs.next()) {
					dtype_id = 0;// 多条也为失败
					// System.out.println("多条");
				}
			} else {
				dtype_id = 0;
				// System.out.println("0条");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		return dtype_id;
	}

	@Override
	public String getDeptnameByid(int dept_id) {
		db = new DbUtil();
		db.openConnection();
		String sql = "select dept_name from t_dept where dept_id=?";
		ResultSet rs = db.exeQuery(sql, dept_id);
		String dept_name = null;
		try {
			if (rs.next()) {
				dept_name = rs.getString("dept_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		return dept_name;
	}

	// 通过部门名称查询部门下所有员工信息
	@Override
	public List<Employee> getEmpBydeptname(int emp_deptid) {
		db = new DbUtil();
		db.openConnection();
		// TODO Auto-generated method stub
		// int emp_deptid = 0;
		List<Employee> list = new ArrayList<Employee>();
		// emp_deptid = getDeptidByName(dept_name);
		if (emp_deptid != 0) {
			list = getEmpBydeptid(emp_deptid);
		}
		db.closeConnection();
		return list;
	}

	// 通过部门类型来查询部门下的员工信息
	@Override
	public List<Employee> getEmpBydtypename(int dtypeid) {
		// TODO Auto-generated method stub
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;
		int emp_deptid = 0;
		// int dtypeid = 0;
		List<Employee> list = new ArrayList<Employee>();
		// dtypeid = getDeptTypeByname(dtypename);
		// System.out.println(dtypeid);
		String sql = "select * from t_dept,t_position,t_employee where emp_deptid=dept_id and emp_postid=post_id and dept_typeid=? and emp_ifdischarge = '1'";
		rs = db.exeQuery(sql, dtypeid);
		try {
			while (rs.next()) {
				emp_deptid = rs.getInt("dept_id");
				// System.out.println("dept_id:" + emp_deptid);

				Employee employee = new Employee();
				employee.setEmp_id(rs.getInt("emp_id"));
				employee.setEmp_name(rs.getString("emp_name"));
				employee.setEmp_birth(rs.getString("emp_birth"));
				employee.setEmp_idcard(rs.getString("emp_idcard"));
				employee.setEmp_entrydate(rs.getString("emp_entrydate"));
				employee.setEmp_sex(rs.getString("emp_sex"));
				employee.setEmp_deptname(rs.getString("dept_name"));
				employee.setEmp_postname(rs.getString("post_name"));

				list.add(employee);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// System.out.println("size:" + list.size());
		db.closeConnection();
		return list;
	}

	@Override
	public int modDept(Dept dept) {
		// TODO Auto-generated method stub
		db = new DbUtil();
		db.openConnection();
		int row = 0;
		String strSql = "update t_dept set dept_name=?,dept_typeid=?,dept_tel=?,dept_fax=?,dept_sup=?,dept_setdate=?,dept_description=? where dept_id=?";
		row = db.exeMod(strSql, dept.getDept_name(), dept.getDept_typeid(),
				dept.getDept_tel(), dept.getDept_fax(), dept.getDept_sup(),
				dept.getDept_setdate(), dept.getDept_description(),
				dept.getDept_id());
		db.closeConnection();
		return row;
	}

	public List<Employee> getEmpByitem(Dept dept) {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs=null;
		StringBuffer buffer = new StringBuffer(
				"select * from t_dept,t_position,t_employee where emp_deptid=dept_id and emp_postid=post_id and emp_ifdischarge = '1'");
		List param=new ArrayList();
		List<Employee> list=new ArrayList<Employee>();
		if (dept.getDept_id() != 0) {// 说明deptid不为空
			buffer.append(" and dept_id =?");
			param.add(dept.getDept_id());
		}
		if(dept.getDept_typeid() != 0){
			buffer.append(" and dept_typeid=?");
			param.add(dept.getDept_typeid());
		}
		String sql=buffer.toString();
		rs=db.exeQuery(sql, param.toArray());
		list=getEmpMessage(rs);
		db.closeConnection();
		return list;
	}
	
	public List<Employee> getEmpMessage(ResultSet rs){
		List<Employee> list=new ArrayList<Employee>();
		try {
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setEmp_id(rs.getInt("emp_id"));
				employee.setEmp_name(rs.getString("emp_name"));
				employee.setEmp_birth(rs.getString("emp_birth"));
				employee.setEmp_idcard(rs.getString("emp_idcard"));
				employee.setEmp_entrydate(rs.getString("emp_entrydate"));
				employee.setEmp_sex(rs.getString("emp_sex"));
				employee.setEmp_deptname(rs.getString("dept_name"));
				employee.setEmp_postname(rs.getString("post_name"));

				list.add(employee);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
