package dao;

import java.util.List;

import bean.Dept;
import bean.Employee;

public interface DeptDao {

	/**
	 * 通过部门名称查询部门编号
	 * 
	 * @param deptName
	 * @return返回大于0为部门编号，其他为失败
	 */
	public int getDeptidByName(String deptName);

	/**
	 * 通过部门id查询部门名称
	 * 
	 * @param deptName
	 * @return返回大于0为部门编号，其他为失败
	 */
	public String getDeptnameByid(int dept_id);

	/**
	 * 查询所有部门信息
	 * 
	 * @return
	 */

	public List<Dept> getAllDept();

	/**
	 * 根据部门id查询部门下所有员工信息
	 * 
	 * @param dept_id
	 * @return
	 */
	public List<Employee> getEmpBydeptid(int dept_id);

	/**
	 * 根据部门名称查询部门信息
	 * 
	 * @param dept_name
	 * @return Dept
	 */
	public Dept getDeptByname(String dept_name);

	/**
	 * 通过部门名称查询部门下所有员工信息
	 * 
	 * @return
	 */
	public List<Employee> getEmpBydeptname(int emp_deptid);

	/**
	 * 通过部门类型查询部门下所有员工信息
	 * 
	 * @return
	 */
	public List<Employee> getEmpBydtypename(int dtypeid);

	/**
	 * 修改部门信息(真)
	 * 
	 * @param dept_id
	 * @return 0：失败 1：成功
	 */
	public int modDept(Dept dept);

	/**
	 * 根据部门ID查询部门信息
	 * 
	 * @param dept_id
	 * @return
	 */
	public Dept getDeptByid(int dept_id);

	/**
	 * 添加部门信息
	 * 
	 * @param dept
	 * @return 0：失败 1：成功
	 */
	public int addDeptMessage(Dept dept);

	/**
	 * 修改部门信息
	 * 
	 * @param dept_id
	 * @return 0：失败 1：成功
	 */
	public int modDeptMessage(Dept dept);

	/**
	 * 删除部门信息
	 * 
	 * @param dept_id
	 * @return 0：失败 1：成功
	 */
	public int delDeptMessage(int dept_id);

	/**
	 * 根据部门类型名称查找id
	 * 
	 * @param dtype_name
	 * @return
	 */
	public int getDeptTypeByname(String dtype_name);
	/**
	 *  按部门条件查询员工信息
	 * @param dept
	 * @return
	 */
	public List<Employee> getEmpByitem(Dept dept);

}
