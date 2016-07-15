package service;

import java.util.ArrayList;
import java.util.List;

import util.DbUtil;
import dao.DeptDao;
import dao.DeptTurnDao;
import dao.DischargeDao;
import dao.PostTurnDao;
import daoImpl.DeptDaoImpl;
import daoImpl.DeptTurnDaoImpl;
import daoImpl.DischargeDaoImpl;
import daoImpl.PostTurnDaoImpl;

public class ReportService {
	DischargeDao dischargeDao = new DischargeDaoImpl();

	public List getAllDischarge(String date1, String date2, String dep) {
		return dischargeDao.getAllDischarge(date1, date2, dep);
	}
	public List getdisinfo(String date1, String date2, String dep) {
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
		list = dischargeDao.getDisInfo(date1, date2, deptid);
		
		
		return list;
	}
}
