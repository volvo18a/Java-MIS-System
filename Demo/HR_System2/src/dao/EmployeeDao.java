package dao;

import java.util.List;

import bean.Deptturn;
import bean.Discharge;
import bean.Employee;
import bean.Postturn;
import bean.Probation;

public interface EmployeeDao {

/**
 * 报表的新进员工
 * @param date1
 * @param date2
 * @param deptid
 * @return
 */
	public List getNewEmpInfo(String date1, String date2, int deptid);
	
	/**
	 * 通过员工编号修改部门
	 * 
	 * @param emp_id
	 * @return
	 */
	public int modDeptByEmpid(int empId, int deptId);

	/**
	 * 通过员工编号修改岗位
	 * 
	 * @param emp_id
	 * @return
	 */
	public int modPostByEmpid(int empId, int deptId);

	/**
	 * 根据员工编号查询员工部门编号
	 */
	public int getDeptIdByempid(int empId);

	/**
	 * 根据员工编号查询员工岗位编号
	 * 
	 * @param empId
	 * @return 返回值大于0位编号，等于0表示查询失败
	 */
	public int getPostIdByempid(int empId);

	/**
	 * 查询员工所有信息
	 * 
	 * @return List
	 */
	public List<Employee> getAllEmployee();

	/**
	 * 按员工编号查找信息
	 * 
	 * @param emp_id
	 * @return Employee
	 */
	public Employee getEmployeeByempid(int emp_id);

	/**
	 * 按部门编号查找
	 * 
	 * @param dept_id
	 * @return List
	 */
	public List<Employee> getEmpdeptid(int dept_id);

	/**
	 * 按岗位编号查找
	 * 
	 * @param post_id
	 * @return List
	 */
	public List<Employee> getEmppostid(int post_id);

	/**
	 * 按名称进行模糊查询
	 * 
	 * @param name
	 * @return List
	 */
	public List<Employee> getEmpByname(String name);

	/**
	 * 试用期管理--按照输入的条件查询
	 * 
	 * @param employee
	 * @param probation
	 * @return
	 */
	public List<Probation> getEmppbByTerm(Employee employee, Probation probation);

	/**
	 * 岗位调动管理，部门调动管理--根据部门编号，岗位编号，员工编号，员工姓名查询员工信息
	 * 
	 * @param employee
	 * @return
	 */
	public List<Employee> getEmpByFouritem(Employee employee);

	/**
	 * 部门调动管理--开始日期，结束日期，员工编号，员工姓名，调动方式。
	 * 
	 * @param employee
	 * @param deptturn
	 * @return
	 */
	public List<Deptturn> getDeptturnByitem(Employee employee, Deptturn deptturn);

	/**
	 * 岗位调动管理--开始日期，结束日期，员工编号，员工姓名，调动方式。
	 * 
	 * @param employee
	 * @param postturn
	 * @return
	 */
	public List<Postturn> getPostturnByitem(Employee employee, Postturn postturn);

	/**
	 * 已离职员工信息查询--员工编号，姓名，部门名称，岗位名称，离职类型，查询开始日期，结束日期
	 * 
	 * @param employee
	 * @param discharge
	 * @return
	 */
	public List<Discharge> getDischgByitem(Employee employee,
			Discharge discharge);

	/**
	 * 录入员工信息
	 * 
	 * @return
	 */
	public int addEmpMeaasge(Employee employee, Probation pro);
	public List<Employee> getAllNewEmp(String date1, String date2, String dep);
}