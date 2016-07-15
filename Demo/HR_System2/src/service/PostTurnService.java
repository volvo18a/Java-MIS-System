package service;

import java.util.List;

import bean.Deptturn;
import bean.Postturn;
import dao.DeptDao;
import dao.DeptTurnDao;
import dao.EmployeeDao;
import dao.PositionDao;
import dao.PostTurnDao;
import daoImpl.DeptDaoImpl;
import daoImpl.DeptTurnDaoImpl;
import daoImpl.EmployeeDaoImpl;
import daoImpl.PositionDaoImpl;
import daoImpl.PostTurnDaoImpl;

public class PostTurnService {
	PostTurnDao postturnDao = new PostTurnDaoImpl();

	public List getAllPostTurn(String date1, String date2) {
		return postturnDao.getAllPostTurn(date1, date2);
	}

	/**
	 * 插入岗位调动信息
	 * 
	 * @param dturn
	 * @return
	 */
	public int postTurn(Postturn pturn) {
		int result = 0;
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		PositionDao postDao = new PositionDaoImpl();
		PostTurnDao postTurnDao = new PostTurnDaoImpl();
		// deptDao.testInsert();

		// 需不需要判断是否查询成功？
		int beforeId = employeeDao.getPostIdByempid(pturn.getPturn_empid());
		pturn.setPturn_beforeid(beforeId);// 查询调转前岗位
		System.out.println("beforeId:" + beforeId);

		System.out.println(pturn.getPturn_aftername());
		int afterId = postDao.getPostidByName(pturn.getPturn_aftername());
		pturn.setPturn_afterid(afterId);// 查询调转后部门编号
		System.out.println("afterId:" + afterId);

		int typeId = postTurnDao.getTypeidByName(pturn.getPturn_typeName());
		pturn.setPturn_typeid(typeId);
		System.out.println("typeId:" + typeId);

		result = postTurnDao.postTrun(pturn);

		// 更改员工表的部门信息
		result = employeeDao.modPostByEmpid(pturn.getPturn_empid(), afterId);

		System.out.println("修改数据结果:" + result);

		return result;
	}
	/**
	 * 通过调转类型名称查找id
	 * @param typeName
	 * @return
	 */
	public int getTypeidByName(String typeName){
		return postturnDao.getTypeidByName(typeName);
	}
}
