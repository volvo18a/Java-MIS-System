package dao;

import bean.Type;

public interface TypeDao {

	/**
	 * 根据入职途径表名称 查询入职途径表id
	 * 
	 * @param posttype_name
	 * @return
	 */
	public Type getWayByname(String way_name);

	/**
	 * 根据岗位类型名称 查询岗位类型id
	 * 
	 * @param dturntp_name
	 * @return
	 */
	public Type getPosttypeByname(String posttype_name);

	/**
	 * 根据部门调转类型表名称 查询部门调转类型表id
	 * 
	 * @param pturn_name
	 * @return
	 */
	public Type getDturntpByname(String dturntp_name);

	/**
	 * 根据岗位调转信息表 查询岗位调转信息表id
	 * 
	 * @param posttype_name
	 * @return
	 */
	public Type getPturnByname(String pturn_name);

	/**
	 * 根据试用期状态表名称 查询试用期状态表id
	 * 
	 * @param state_name
	 * @return
	 */
	public Type getStateByname(String state_name);
}
