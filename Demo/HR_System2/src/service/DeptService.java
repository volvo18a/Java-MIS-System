package service;

import java.util.List;

import bean.Dept;
import bean.Employee;
import dao.DeptDao;
import dao.PositionDao;
import daoImpl.DeptDaoImpl;
import daoImpl.PositionDaoImpl;

public class DeptService {
	DeptDao deptDao = new DeptDaoImpl();

	/**
	 * 通过部门名称查询部门编号
	 * 
	 * @param deptName
	 * @return返回大于0为部门编号，其他为失败
	 */
	public int getDeptidByName(String deptName) {
		//System.out.println("12--deptname:"+deptName);
		return deptDao.getDeptidByName(deptName);
	}

	/**
	 * 通过部门id查询部门名称
	 * 
	 * @param deptName
	 * @return返回大于0为部门编号，其他为失败
	 */
	public String getDeptnameByid(int dept_id) {
		return deptDao.getDeptnameByid(dept_id);
	}

	/**
	 * 通过部门类型查询部门下所有员工信息
	 * 
	 * @return
	 */
	public List<Employee> getEmpBydtypename(String dtypename) {

		int dtypeid = deptDao.getDeptTypeByname(dtypename);

		return deptDao.getEmpBydtypename(dtypeid);
	}

	/**
	 * 通过部门名称查询部门下所有员工信息
	 * 
	 * @return
	 */
	public List<Employee> getEmpBydeptname(String dept_name) {

		int emp_deptid = deptDao.getDeptidByName(dept_name);
		return deptDao.getEmpBydeptname(emp_deptid);
	}

	/**
	 * 查询所有部门信息
	 * 
	 * @return
	 */
	public List<Dept> getAllDept() {
		return deptDao.getAllDept();
	}

	/**
	 * 根据部门id查询部门下所有员工信息
	 * 
	 * @param dept_id
	 * @return
	 */
	public List<Employee> getEmpBydeptid(int dept_id) {
		return deptDao.getEmpBydeptid(dept_id);
	}

	/**
	 * 根据部门名称查询部门信息
	 * 
	 * @param dept_name
	 * @return Dept
	 */
	public Dept getDeptByname(String dept_name) {
		return deptDao.getDeptByname(dept_name);
	}

	/**
	 * 根据部门ID查询部门信息
	 * 
	 * @param dept_id
	 * @return
	 */
	public Dept getDeptByid(int dept_id) {
		return deptDao.getDeptByid(dept_id);
	}

	/**
	 * 添加部门信息
	 * 
	 * @param dept
	 * @return 0：失败 1：成功
	 */
	public int addDeptMessage(Dept dept) {
		return deptDao.addDeptMessage(dept);
	}

	/**
	 * 修改部门信息
	 * 
	 * @param dept_id
	 * @return 0：失败 1：成功
	 */
	public int modDeptMessage(Dept dept) {
		return deptDao.modDeptMessage(dept);
	}

	/**
	 * 修改部门信息(真)
	 * 
	 * @param dept_id
	 * @return 0：失败 1：成功
	 */
	public int modDept(Dept dept) {
		return deptDao.modDept(dept);
	}

	/**
	 * 删除部门信息
	 * 
	 * @param dept_id
	 * @return 0：失败 1：成功
	 */
	public int delDeptMessage(int dept_id) {
		return deptDao.delDeptMessage(dept_id);
	}

	/**
	 * 根据部门类型名称查找id
	 * 
	 * @param dtype_name
	 * @return
	 */
	public int getDeptTypeByname(String dtype_name) {
		return deptDao.getDeptTypeByname(dtype_name);
	}
	
	/**
	 *  按部门条件查询员工信息
	 * @param dept
	 * @return
	 */
	public List<Employee> getEmpByitem(Dept dept){
		String dept_name=dept.getDept_name();
		System.out.println(dept.getDept_id());
		if (dept_name != null && !dept_name.equals("")) {
			int dept_id = getDeptidByName(dept_name);// 根据部门名称获取部门id
			//如果部门编号和部门名称相背，直接返回null
			if(dept.getDept_id() != 0){
				if (dept_id != dept.getDept_id()) {
					return null;
				}
			}else{
				dept.setDept_id(dept_id);
			}
		}
		if(dept.getDept_typename() != null && !dept.getDept_typename().equals("")){
			int dtypeid = getDeptTypeByname(dept.getDept_typename());
			dept.setDept_typeid(dtypeid);
		}
		return deptDao.getEmpByitem(dept);
	}

}
