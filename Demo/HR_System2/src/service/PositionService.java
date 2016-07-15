package service;

import java.util.List;

import bean.Employee;
import bean.Position;
import dao.PositionDao;
import daoImpl.PositionDaoImpl;

public class PositionService {
	PositionDao positionDao = new PositionDaoImpl();

	/**
	 * 通过岗位名称查岗位编号
	 * @param postName
	 * @return
	 */
	public int getPostidByName(String postName){
		System.out.println("12-poname:"+postName);
		return positionDao.getPostidByName(postName);
	}
	/**
	 * 通过岗位名称查岗位编号
	 * 
	 * @param postName
	 * @return
	 */
	public String getPostnameByid(int post_id){
		return positionDao.getPostnameByid(post_id);
	}
	/**
	 * 查询所有岗位信息
	 * 
	 * @return
	 */
	public List<Position> getAllPosition() {
		return positionDao.getAllPosition();
	}

	/**
	 * 根据岗位名称查询岗位信息
	 * 
	 * @return
	 */

	public Position getPostByname(String post_name) {
		return positionDao.getPostByname(post_name);
	}

	/**
	 * 根据岗位id查询岗位信息
	 * 
	 * @return
	 */
	public Position getPostByid(int post_id) {
		return positionDao.getPostByid(post_id);
	}

	/**
	 * 添加岗位信息
	 * 
	 * @return 0：失败 1：成功
	 */
	public int addPostMessage(Position post) {
		return positionDao.addPostMessage(post);
	}

	/**
	 * 修改岗位信息
	 * 
	 * @return 0：失败 1：成功
	 */
	public int modPostMessage(Position post) {
		return positionDao.modPostMessage(post);
	}

	/**
	 * 删除岗位信息
	 * 
	 * @return 0：失败 1：成功
	 */
	public int delPostMessage(int post_id) {
		return positionDao.delPostMessage(post_id);
	}
	
	/**
	 * 通过岗位类型名称查岗位类型id
	 * 
	 * @param postName
	 * @return
	 */
	public int getPostTypeidByName(String ptypeName) {
		return positionDao.getPostTypeidByName(ptypeName);
	}

	/**
	 * 根据岗位id查询员工信息
	 * 
	 * @param post_id
	 * @return
	 */
	public List<Employee> getEmpBypostid(int post_id) {
		return positionDao.getEmpBypostid(post_id);
	}
	
	/**
	 * 根据岗位名称查询员工岗位信息
	 * 
	 * @return
	 */
	public List<Employee> getEmpBypostname(String post_name) {
		return positionDao.getEmpBypostname(post_name);
	}
	
	/**
	 * 根据岗位类型查询员工岗位信息
	 * 
	 * @return
	 */
	public List<Employee> getEmpByposttypename(String posttype_name) {
		
		int posttypeid = getPostTypeidByName(posttype_name);
		return positionDao.getEmpByposttypename(posttypeid);
	}
	
	/**
	 *  按岗位条件查询员工信息
	 * @param post
	 * @return
	 */
	public List<Employee> getEmpByitem(Position post){
		String post_name = post.getPost_name();
		if(post_name != null && !post_name.equals("")){
			int post_id = getPostidByName(post_name);
			if(post.getPost_id() != 0){
				if(post_id != post.getPost_id()){
					return null;
				}
			} else {
				post.setPost_id(post_id);
			}
		}
		if(post.getPost_typename() != null && !post.getPost_typename().equals("")){
			int posttypeid = getPostTypeidByName(post.getPost_typename());
			post.setPost_typeid(posttypeid);
		}
		return positionDao.getEmpByitem(post);
		
	}
}
