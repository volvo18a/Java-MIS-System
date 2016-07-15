package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Dept;
import bean.Position;
import bean.Employee;
import util.DbUtil;
import dao.DeptDao;
import dao.PositionDao;

public class PositionDaoImpl implements PositionDao {

	DbUtil db = null;

	public int getPostidByName(String postName) {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;
		String sql = "select post_id from t_position where post_name like ? and post_state = 1";
		rs = db.exeQuery(sql, postName);
		// System.out.println(sql);
		int postId = 0;

		try {
			if (rs.next()) {
				postId = rs.getInt("post_id");
				if (rs.next()) {
					postId = 0;// 多条也为失败
					System.out.println("多条");
				}
			} else {
				postId = 0;
				System.out.println("0条");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();

		return postId;
	}

	@Override
	public List<Position> getAllPosition() {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;

		List<Position> list = new ArrayList<Position>();
		String sql = "select * from t_position,t_positiontype where post_typeid = posttype_id and post_state='1' order by post_id asc";
		rs = db.exeQuery(sql);
		list = getMessage(rs);
		db.closeConnection();
		return list;
	}

	@Override
	public List<Employee> getEmpBypostid(int post_id) {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;
		List<Employee> list = new ArrayList<Employee>();
		DeptDao deptDao = new DeptDaoImpl();
		String sql = "select * from t_employee where emp_postid= ? and emp_ifdischarge = '1'";
		rs = db.exeQuery(sql, post_id);
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
				int dept_id = rs.getInt("emp_deptid");
				employee.setEmp_deptname(deptDao.getDeptnameByid(dept_id));
				// 根据岗位id查询岗位名称
				int postid = rs.getInt("emp_postid");
				employee.setEmp_postname(getPostnameByid(postid));

				list.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		return list;
	}

	public List<Position> getMessage(ResultSet rs) {
		List<Position> ls = new ArrayList<Position>();
		try {
			while (rs.next()) {
				Position post = new Position();
				post.setPost_id(rs.getInt("post_id"));
				post.setPost_name(rs.getString("post_name"));
				post.setPost_typename(rs.getString("posttype_name"));
				post.setPost_count(rs.getInt("post_count"));
				ls.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	@Override
	public Position getPostByname(String post_name) {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;
		Position post = null;
		List<Position> list = new ArrayList<Position>();
		String sql = "select * from t_position where post_name=?";
		rs = db.exeQuery(sql, post_name);
		list = getMessage(rs);
		db.closeConnection();
		if (list.size() == 0) {
			post = null;
		} else {
			post = list.get(0);
		}

		return post;
	}

	@Override
	public Position getPostByid(int post_id) {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;
		Position post = null;
		List<Position> list = new ArrayList<Position>();
		String sql = "select * from t_position where post_id=?";
		rs = db.exeQuery(sql, post_id);
		list = getMessage(rs);
		db.closeConnection();
		if (list.size() == 0) {
			post = null;
		} else {
			post = list.get(0);
		}
		return post;
	}

	@Override
	public int addPostMessage(Position post) {
		db = new DbUtil();
		db.openConnection();
		int row = 0;
		String strSql = "insert into t_position(post_name,post_typeid,post_count,post_state) values(?,?,?,?)";
		row = db.exeMod(strSql, post.getPost_name(), post.getPost_typeid(),
				post.getPost_count(), 1);
		db.closeConnection();
		return row;
	}

	@Override
	public int modPostMessage(Position post) {
		db = new DbUtil();
		db.openConnection();
		int row = 0;
		String strSql = "update t_position set post_name=?,post_typeid=?,post_count=? where post_id=?";
		row = db.exeMod(strSql, post.getPost_name(), post.getPost_typeid(),
				post.getPost_count(), post.getPost_id());
		db.closeConnection();
		return row;
	}

	@Override
	public int delPostMessage(int post_id) {

		// 删除实际上并删除数据，而是将数据是否有效字段（dept_state）置为0
		int row = 0;

		List<Employee> list = new ArrayList<Employee>();
		list = getEmpBypostid(post_id);

		// list.isEmpty()判断list里边有没有元素
		// list == null 有没有list ---没有水杯
		if (list == null || list.isEmpty()) {
			db = new DbUtil();
			db.openConnection();
			String strSql = "update t_position set post_state = '0' where post_id = ?";
			row = db.exeMod(strSql, post_id);
		}
		db.closeConnection();
		return row;
	}

	@Override
	public String getPostnameByid(int post_id) {
		db = new DbUtil();
		db.openConnection();
		String sql = "select post_name from t_position where post_id=?";
		ResultSet rs = db.exeQuery(sql, post_id);
		String post_name = null;
		try {
			if (rs.next()) {
				post_name = rs.getString("post_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		return post_name;
	}

	@Override
	public int getPostTypeidByName(String ptypeName) {
		// TODO Auto-generated method stub
		db = new DbUtil();
		db.openConnection();
		int posttype_id = 0;
		ResultSet rs = null;
		String sql = "select posttype_id from t_positiontype where posttype_name=?";
		rs = db.exeQuery(sql, ptypeName);

		try {
			if (rs.next()) {
				posttype_id = rs.getInt("posttype_id");
				if (rs.next()) {
					posttype_id = 0;// 多条也为失败
					System.out.println("多条");
				}
			} else {
				posttype_id = 0;
				System.out.println("0条");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		return posttype_id;
	}

	@Override
	public List<Employee> getEmpBypostname(String post_name) {
		// TODO Auto-generated method stub
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;
		List<Employee> list = new ArrayList<Employee>();
		int emp_postid = 0;
		emp_postid = getPostidByName(post_name);
		// System.out.println(emp_postid);
		if (emp_postid != 0) {
			list = getEmpBypostid(emp_postid);
		}
		return list;
	}

	@Override
	public List<Employee> getEmpByposttypename(int posttypeid) {
		// TODO Auto-generated method stub
		db = new DbUtil();
		db.openConnection();
		ResultSet rs = null;
		List<Employee> list = new ArrayList<Employee>();
		int emp_postid = 0;
		//int posttypeid = 0;
		//posttypeid = getPostTypeidByName(posttype_name);
		String sql = "select * from t_dept,t_position,t_employee where emp_deptid=dept_id and emp_postid=post_id and post_typeid=? and emp_ifdischarge = '1'";
		// String sql = "select post_id from t_position where post_typeid=?";
		rs = db.exeQuery(sql, posttypeid);
		try {
			while (rs.next()) {
				emp_postid = rs.getInt("post_id");

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
		db.closeConnection();
		return list;
	}

	@Override
	public List<Employee> getEmpByitem(Position post) {
		// TODO Auto-generated method stub
		db = new DbUtil();
		db.openConnection();
		ResultSet rs=null;
		StringBuffer buffer = new StringBuffer(
				"select * from t_dept,t_position,t_employee where emp_deptid=dept_id and emp_postid=post_id and emp_ifdischarge = '1'");
		List param=new ArrayList();
		List<Employee> list=new ArrayList<Employee>();
		if (post.getPost_id() != 0) {// 说明postid不为空
			buffer.append(" and post_id =?");
			param.add(post.getPost_id());
		}
		if(post.getPost_typeid() != 0){
			buffer.append(" and post_typeid=?");
			param.add(post.getPost_typeid());
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