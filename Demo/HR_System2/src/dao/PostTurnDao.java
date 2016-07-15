package dao;

import java.util.List;

import bean.Deptturn;
import bean.Postturn;

public interface PostTurnDao {
	
	  //判断岗位超编
	/**
	 * 插入岗位调动信息
	 * @param dturn
	 * @return
	 */
	public int postTrun(Postturn  pturn);
	public List getAllPostTurn(String date1, String date2);
	/**
	 * 通过调转类型名称查找id
	 * @param typeName
	 * @return
	 */
	public int getTypeidByName(String typeName);
}
