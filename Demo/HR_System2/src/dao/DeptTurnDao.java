package dao;

import java.util.List;

import bean.Deptturn;

public interface DeptTurnDao {
	public List getAllDeptTurn(String date1,String date2);
	
	/**
	 * 插入调转信息
	 * @param dturn
	 * @return
	 */
	public int deptTrun(Deptturn  dturn);

	/**
	 * 通过类型名称查询类型编号
	 * @param typeName
	 * @return返回值大于0是查询成功
	 */
	public int getTypeidByName(String typeName);
	
//	public int setInvalid(int emp_id);
}
