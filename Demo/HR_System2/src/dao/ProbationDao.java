package dao;

import bean.Probation;

public interface ProbationDao {

	/**
	 * 更新试用期状态
	 * 如果为查询已转正员工，再传入之前就给定状态
	 * @param probation
	 * @return 成功为1，失败为0
	 */
	public int updateDelayState(Probation probation);
	public int updateState(Probation probation);
	/**
	 * 通过状态名称返回状态编号
	 * @param stateName
	 * @return返回0为失败
	 */
	public int getStateIdByName(String stateName);
	/**
	 * 通过状态id返回状态ming
	 * @param stateName
	 * @return返回0为失败
	 */
	public String getStatenameByid(int state_id);
}
