package service;

import java.util.ArrayList;
import java.util.List;

import dao.DeptDao;
import dao.EmployeeDao;
import dao.PostTurnDao;
import daoImpl.DeptDaoImpl;
import daoImpl.EmployeeDaoImpl;
import daoImpl.PostTurnDaoImpl;

public class NewEmpService {
	EmployeeDao postturnDao=new EmployeeDaoImpl();
	public List getAllNewEmp(String date1,String date2,String dep){
		return postturnDao.getAllNewEmp(date1, date2, dep);
	}
	//新加
	public List getNewEmpInfo(String date1,String date2,String dep){
		int deptid;
		DeptDao deptdao = new DeptDaoImpl();
		List list = new ArrayList();
		
		
		if ("全部".equals(dep) || "".equals(dep))
		{
			System.out.println("全部部门");
			deptid = 0;
		}
		else
		{
			deptid = deptdao.getDeptidByName(dep);//这里返回0为没有部门
			if (deptid == 0)
			{
				System.out.println("没有该部门");
				return list;
			}
		}
		list = postturnDao.getNewEmpInfo(date1, date2, deptid);
		
		return list;
	}
	
}
