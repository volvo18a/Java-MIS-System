package dao;

import java.util.List;

import bean.Dept;
import bean.Employee;
import bean.Position;

public interface PositionDao {
	/**
	 * 通过岗位名称查岗位编号
	 * 
	 * @param postName
	 * @return
	 */
	public int getPostidByName(String postName);
	/**
	 * 通过岗位id查岗位名称
	 * 
	 * @param postName
	 * @return
	 */
	public String getPostnameByid(int post_id);
	
	/**
	 * 通过岗位类型名称查岗位类型id
	 * 
	 * @param postName
	 * @return
	 */
	public int getPostTypeidByName(String ptypeName);

	/**
	 * 查询所有岗位信息
	 * 
	 * @return
	 */
	public List<Position> getAllPosition();

	/**
	 * 根据岗位id查询岗位员工信息
	 * 
	 * @return
	 */
	public List<Employee> getEmpBypostid(int post_id);
	
	/**
	 * 根据岗位名称查询员工岗位信息
	 * 
	 * @return
	 */
	public List<Employee> getEmpBypostname(String post_name);
	
	/**
	 * 根据岗位类型查询员工岗位信息
	 * 
	 * @return
	 */
	public List<Employee> getEmpByposttypename(int posttypeid);

	/**
	 * 根据岗位名称查询岗位信息
	 * 
	 * @return
	 */
	public Position getPostByname(String post_name);

	/**
	 * 根据岗位id查询岗位信息
	 * 
	 * @return
	 */
	public Position getPostByid(int post_id);

	/**
	 * 添加岗位信息
	 * 
	 * @return 0：失败 1：成功
	 */
	public int addPostMessage(Position post);

	/**
	 * 修改岗位信息
	 * 
	 * @return 0：失败 1：成功
	 */
	public int modPostMessage(Position post);

	/**
	 * 删除岗位信息
	 * 
	 * @return 0：失败 1：成功
	 */
	public int delPostMessage(int post_id);
	
	/**
	 *  按岗位条件查询员工信息
	 * @param post
	 * @return
	 */
	public List<Employee> getEmpByitem(Position post);
}