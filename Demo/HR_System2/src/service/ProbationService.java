package service;

import bean.Discharge;
import bean.Probation;
import dao.ProbationDao;
import daoImpl.ProbationDaoImpl;

public class ProbationService {
	ProbationDao proDao = new ProbationDaoImpl();

	/**
	 * 更新试用期状态 如果为查询已转正员工，再传入之前就给定状态
	 * 
	 * @param probation
	 * @return 成功为1，失败为0
	 */
	public int updateState(Probation probation) {
		int result = 0;

		String stateName = probation.getProba_stateName();
		//System.out.println("aaa--stateName:"+stateName);
		int stateId = proDao.getStateIdByName(stateName);
	//	int stateId = proDao.getStateIdByName(probation.getProba_stateName());
		probation.setPrroba_stateid(stateId);
		//System.out.println("stateId:"+stateId);

		if ("延期".equals(stateName) ) {
			System.out.println("延期测试");
			result = proDao.updateDelayState(probation);
		}
		else {
			System.out.println("其他测试");
			result = proDao.updateState(probation);
		} 
		//result =2说明试用期状态改变以及员工表中是否有试用期已更改
		//应该进入离职信息的插入
		//if(stateName == "不予录用" && result != 0)
		//System.out.println("statename:"+stateName);
		if("不予录用".equals(stateName)&& result != 0 )
		{
			System.out.println("statename:"+stateName+"---"+"result:"+result);
			Discharge dischg = new Discharge();
			dischg.setDischg_empid(probation.getEmp_id());
			dischg.setDischg_date(probation.getProba_dealdate());
			dischg.setDischg_type("试用期不通过");
			dischg.setDischg_dinec("无");
			dischg.setDischg_ifenter(1);//1表示是
			DischargeService dischgSer = new  DischargeService();
			//System.out.println(pturn.getPturn_aftername());
			if (dischgSer.dischargeDeal(dischg) == 0)
			{
				System.out.println("试用期不通过后的离职操作失败");
			}else
			{
				System.out.println("试用期不通过后的离职操作成功");	
			}
		}
		else
		{
			System.out.println("dfsd");
		}
		 
		System.out.println("result:" + result);

		return result;
	}
	/**
	 * 通过状态名称返回状态编号
	 * 
	 * @param stateName
	 * @return返回0为失败
	 */
	public int getStateIdByName(String stateName) {
		return proDao.getStateIdByName(stateName);
	}
	
	/**
	 * 通过状态id返回状态ming
	 * @param stateName
	 * @return返回0为失败
	 */
	public String getStatenameByid(int state_id){
		return proDao.getStatenameByid(state_id);
	}

}
