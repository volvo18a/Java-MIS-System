package service;

import java.util.List;

import dao.DischargeDao;
import daoImpl.DischargeDaoImpl;
import bean.Discharge;

public class DischargeService {
	/**
	 * 插入离职信息
	 * @param dischg
	 * @return
	 */
	public int dischargeDeal(Discharge dischg)
	{
		int result = 0;
		DischargeDao dischgDao = new DischargeDaoImpl();
		result=dischgDao.dischgDeal(dischg);
		System.out.println("result:"+result);
		return result;
	}
	public List getAllDischarge(String date1,String date2,String dep){
		return getAllDischarge(date1, date2, dep);
	}
}
