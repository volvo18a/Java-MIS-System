package service;

import java.util.List;

import bean.Dept;
import bean.Deptturn;
import bean.Discharge;
import bean.Employee;
import bean.Position;
import bean.Postturn;
import bean.Probation;
import bean.Type;
import dao.EmployeeDao;
import daoImpl.EmployeeDaoImpl;

public class EmployeeService {
	EmployeeDao employeeDao = new EmployeeDaoImpl();
	DeptService deptService = new DeptService();
	PositionService positionService = new PositionService();
	ProbationService probationService = new ProbationService();
	DeptTurnService deptTurnService = new DeptTurnService();
	PostTurnService postTurnService = new PostTurnService();

	/**
	 * 通过员工编号修改部门
	 * 
	 * @param emp_id
	 * @return
	 */
	public int modDeptByEmpid(int empId, int deptId) {
		return employeeDao.modDeptByEmpid(empId, deptId);
	}

	/**
	 * 通过员工编号修改岗位
	 * 
	 * @param emp_id
	 * @return
	 */
	public int modPostByEmpid(int empId, int deptId) {
		return employeeDao.modPostByEmpid(empId, deptId);
	}

	/**
	 * 根据员工编号查询员工部门编号
	 */
	public int getDeptIdByempid(int empId) {
		return employeeDao.getDeptIdByempid(empId);
	}

	/**
	 * 根据员工编号查询员工岗位编号
	 * 
	 * @param empId
	 * @return 返回值大于0位编号，等于0表示查询失败
	 */
	public int getPostIdByempid(int empId) {
		return employeeDao.getPostIdByempid(empId);
	}

	/**
	 * 查询员工所有信息
	 * 
	 * @return List
	 */
	public List<Employee> getAllEmployee() {
		return employeeDao.getAllEmployee();
	}

	/**
	 * 按员工编号查找信息
	 * 
	 * @param emp_id
	 * @return Employee
	 */
	public Employee getEmployeeByempid(int emp_id) {
		return employeeDao.getEmployeeByempid(emp_id);
	}

	/**
	 * 按部门编号查找
	 * 
	 * @param dept_id
	 * @return List
	 */
	public List<Employee> getEmpdeptid(int dept_id) {
		return employeeDao.getEmpdeptid(dept_id);
	}

	/**
	 * 按岗位编号查找
	 * 
	 * @param post_id
	 * @return List
	 */
	public List<Employee> getEmppostid(int post_id) {
		return employeeDao.getEmppostid(post_id);
	}

	/**
	 * 按名称进行模糊查询
	 * 
	 * @param name
	 * @return List
	 */
	public List<Employee> getEmpByname(String name) {
		return employeeDao.getEmpByname(name);
	}

	/**
	 * 试用期管理--按照输入的条件查询
	 * 
	 * @param employee
	 * @param probation
	 * @return
	 */
	public List<Probation> getEmppbByTerm(Employee employee, Probation probation) {

		int dept_id = 0;
		int post_id = 0;
		int state_id = 0;
		String dept_name = employee.getDept_name();
		if (dept_name != null && !dept_name.equals("")) {
			// 根据部门名称获取部门id
			dept_id = deptService.getDeptidByName(dept_name);
			employee.setEmp_deptid(dept_id);
			//System.out.println("dept_id:" + dept_id);
		}
		String post_name = employee.getPost_name();
		if (post_name != null && !post_name.equals("")) {
			// 根据岗位名称获取岗位id
			post_id = positionService.getPostidByName(post_name);
			employee.setEmp_postid(post_id);
			//System.out.println("post_id:" + post_id);
		}
		String state_name = probation.getState_name();
		if (state_name != null && !state_name.equals("")) {
			// 根据试用期状态名称获取id
			state_id = probationService.getStateIdByName(state_name);
			probation.setPrroba_stateid(state_id);
			//System.out.println("state_id:" + state_id);
		}
		return employeeDao.getEmppbByTerm(employee, probation);
	}

	/**
	 * 岗位调动管理，部门调动管理--根据部门编号，岗位编号，员工编号，员工姓名查询员工信息
	 * 
	 * @param employee
	 * @return
	 */
	public List<Employee> getEmpByFouritem(Employee employee) {
		String dept_name = employee.getDept_name();
		if (dept_name != null && !dept_name.equals("")) {
			int dept_id = deptService.getDeptidByName(dept_name);// 根据部门名称获取部门id
			// 若果输入的部门名称和id相左，输出为空
			if(employee.getEmp_deptid() != 0){
				if (dept_id != employee.getEmp_deptid()) {
					return null;
				}
			}else{
				employee.setEmp_deptid(dept_id);
			}
			
		}

		String post_name = employee.getPost_name();
		if (post_name != null && !post_name.equals("")) {
			int post_id = positionService.getPostidByName(post_name);
			// 若果输入的部门名称和id相左，输出为空
			if(employee.getEmp_postid() != 0){
				if (post_id != employee.getEmp_postid()) {
					return null;
				}
			}else{
				employee.setEmp_postid(post_id);
			}
		}
		return employeeDao.getEmpByFouritem(employee);
	}

	/**
	 * 部门调动管理--开始日期，结束日期，员工编号，员工姓名，调动方式。
	 * 
	 * @param employee
	 * @param deptturn
	 * @return
	 */
	public List<Deptturn> getDeptturnByitem(Employee employee, Deptturn deptturn) {
		// System.out.println("emp_id:" + employee.getEmp_id());
		int dtype_id = 0;
		String dtype_name = deptturn.getDturn_typeName();
		//
		//System.out.println("ser-dtypename:" + dtype_name);
		if (dtype_name != null && !dtype_name.equals("")) {
			dtype_id = deptTurnService.getTypeidByName(dtype_name);
			//System.out.println("dtype_id:" + dtype_id);
			deptturn.setDturn_typeid(dtype_id);
		}
		return employeeDao.getDeptturnByitem(employee, deptturn);
	}

	/**
	 * 岗位调动管理--开始日期，结束日期，员工编号，员工姓名，调动方式。
	 * 
	 * @param employee
	 * @param postturn
	 * @return
	 */
	public List<Postturn> getPostturnByitem(Employee employee, Postturn postturn) {
		// System.out.println("emp_id:" + employee.getEmp_id());
		int ptype_id = 0;
		String ptype_name = postturn.getPturn_typeName();
		//System.out.println("dtype_name:" + ptype_name);
		if (ptype_name != null && !ptype_name.equals("")) {
			ptype_id = postTurnService.getTypeidByName(ptype_name);
			//System.out.println("dtype_id:" + ptype_id);
			postturn.setPturn_typeid(ptype_id);
		}
		return employeeDao.getPostturnByitem(employee, postturn);
	}

	/**
	 * 已离职员工信息查询--员工编号，姓名，部门名称，岗位名称，离职类型，查询开始日期，结束日期
	 * 
	 * @param employee
	 * @param discharge
	 * @return
	 */
	public List<Discharge> getDischgByitem(Employee employee,
			Discharge discharge) {
		int dept_id = 0;
		int post_id = 0;
		String dept_name = employee.getDept_name();
		if (dept_name != null && !dept_name.equals("")) {
			// 根据部门名称获取部门id
			dept_id = deptService.getDeptidByName(dept_name);
			employee.setEmp_deptid(dept_id);
			//System.out.println("dept_id:" + dept_id);
		}
		String post_name = employee.getPost_name();
		if (post_name != null && !post_name.equals("")) {
			// 根据岗位名称获取岗位id
			post_id = positionService.getPostidByName(post_name);
			employee.setEmp_postid(post_id);
			//System.out.println("post_id:" + post_id);
		}

		return employeeDao.getDischgByitem(employee, discharge);
	}

	/**
	 * 录入员工信息
	 * 
	 * @return
	 */
	public int addEmpMeaasge(Employee employee, Probation probation) {
		int dept_id = 0;
		int post_id = 0;
		String dept_name = employee.getDept_name();
		//System.out.println("servise-deptname:"+dept_name);
		if (dept_name != null && !dept_name.equals("")) {
			// 根据部门名称获取部门id
			dept_id = deptService.getDeptidByName(dept_name);
			//System.out.println("servise-deptname:"+dept_id);
			employee.setEmp_deptid(dept_id);
			//System.out.println("dept_id:" + dept_id);
		}
		String post_name = employee.getPost_name();
		if (post_name != null && !post_name.equals("")) {
			// 根据岗位名称获取岗位id
			post_id = positionService.getPostidByName(post_name);
			employee.setEmp_postid(post_id);
			//System.out.println("post_id:" + post_id);
		}

		return employeeDao.addEmpMeaasge(employee, probation);
	}
}
