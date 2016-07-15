package service;

import java.util.List;

import bean.Deptturn;
import bean.Employee;
import dao.DeptDao;
import dao.DeptTurnDao;
import dao.EmployeeDao;
import dao.PositionDao;
import daoImpl.DeptDaoImpl;
import daoImpl.DeptTurnDaoImpl;
import daoImpl.EmployeeDaoImpl;
import daoImpl.PositionDaoImpl;

public class DeptTurnService {
	/**
	 * 更新调动信息，实际是插入调动数据，该表用调用日期和员工编号为主键
	 * 如果要查看最新在员工表查看
	 * 调转后要及时更新员工表里的部门编号
	 * 根据员工id,调转后部门id更新员工岗位信息
	 * */
	DeptTurnDao deptturnDao = new DeptTurnDaoImpl();

	public List getAllDeptTurn(String date1, String date2) {
		return deptturnDao.getAllDeptTurn(date1, date2);
	}
	/**
	 * 
	 * 该函数执行调转业务，先从得到的信息中有类型表的得到编号
	 * 
	 * 业务分为插入一条调转信息；更改员工信息表里面的岗位信息
	 * @param dturn 从界面得到的所有信息
	 * @return放回值为是否成功
	 */
	public int deptTurn(Deptturn  dturn){
		int result = 0;
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		DeptDao deptDao = new DeptDaoImpl();
		DeptTurnDao deptTurnDao = new DeptTurnDaoImpl();
		//deptDao.testInsert();
		
		//需不需要判断是否查询成功？
		//根据员工编号查询员工部门编号
		int beforeId = employeeDao.getDeptIdByempid(dturn.getDturn_empid());
		dturn.setDturn_beforeid(beforeId);//查询调转前部门
		
		System.out.println("beforeId:"+beforeId);
		
		System.out.println("afterName:"+dturn.getDturn_aftername());
		int afterId = deptDao.getDeptidByName(dturn.getDturn_aftername());
		dturn.setDturn_afterid(afterId);//查询调转后部门编号
		System.out.println("afterId:"+afterId);
		
			
		int typeId = deptTurnDao.getTypeidByName(dturn.getDturn_typeName());
		dturn.setDturn_typeid(typeId);
		System.out.println("typeId:"+typeId); 
		
		//插入调动信息
		result = deptTurnDao.deptTrun(dturn);
		System.out.println("插入数据结果:"+result); 
		
		//更改员工表的部门信息
		result = employeeDao.modDeptByEmpid(dturn.getDturn_empid(),afterId);
		
		System.out.println("修改数据结果:"+result); 
		
		
		return result;
	}
	
	/**
	 * 通过类型名称查询类型编号
	 * @param typeName
	 * @return返回值大于0是查询成功
	 */
	public int getTypeidByName(String typeName){
		return deptturnDao.getTypeidByName(typeName);
	}
}
