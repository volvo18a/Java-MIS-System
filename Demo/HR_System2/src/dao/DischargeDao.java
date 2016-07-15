package dao;

import java.util.List;

import bean.Discharge;

public interface DischargeDao {
	
	/**
	 * 插入离职信息
	 * @param dischg
	 * @return
	 */
	public int dischgDeal(Discharge dischg);
 
	public List getAllDischarge(String date1,String date2,String dep);
	public List getDisInfo(String date1, String date2, int deptid);
	

}
