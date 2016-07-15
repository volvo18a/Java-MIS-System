package service;

import util.DbUtil;
import dao.ProbationDao;
import daoImpl.ProbationDaoImpl;
import bean.Deptturn;
import bean.Discharge;
import bean.Postturn;
import bean.Probation;

public class testYuNa {
	
	public static void main(String[] args) {
		/**
		 * 试用期信息测试
		 */
		
	/*	Probation pro = new Probation();
		pro.setEmp_id(2);
		pro.setProba_comment("良好");
		pro.setProba_dealdate("2016-08-14");
		//延期
		//pro.setProba_stateName("延期");
		pro.setProba_enddate("2016-08-09");
		//非延期
		pro.setProba_stateName("转正");
		ProbationService proSer = new ProbationService();
		int i=proSer.updateState(pro);
		System.out.println("sdfs:i="+i);
*/
		/**
		 * 试用期状态改变
		 */
		/**
		 * 部门调动测试
		 */
		
		/*Deptturn  dturn = new Deptturn();
		
		dturn.setDturn_aftername("人事部");
		dturn.setDturn_typeName("主动调动");
		dturn.setDturn_reason("个人原因");
		
		dturn.setDturn_data("2016-07-05");
		dturn.setDturn_empid(1);
		DeptTurnService dturnSer = new  DeptTurnService();
		System.out.println(dturn.getDturn_aftername());
		int result = dturnSer.deptTurn(dturn);
		System.out.println("result:"+ result);*/
		/**
		 * 岗位调动测试
		 */
		
		/*Postturn  pturn = new Postturn();
		
		pturn.setPturn_aftername("软件测试");
		pturn.setPturn_typeName("升职");
		pturn.setPturn_remark("个人原因");
		
		pturn.setPturn_date("2016-07-05");
		pturn.setPturn_empid(2);
		PostTurnService pturnSer = new  PostTurnService();
		//System.out.println(pturn.getPturn_aftername());
		int result = pturnSer.postTurn(pturn);*/
		
		/**
		 * 离职处理测试
		 *
		 */
		
		/*Discharge dischg = new Discharge();
		dischg.setDischg_empid(2);
		dischg.setDischg_date("2016-07-06");
		dischg.setDischg_type("主动辞退");
		dischg.setDischg_dinec("无");
		dischg.setDischg_ifenter(2);//1表示是
		DischargeService dischgSer = new  DischargeService();
		//System.out.println(pturn.getPturn_aftername());
		int result = dischgSer.dischargeDeal(dischg);*/
		
		/*ProbationService probationService=new ProbationService();
		System.out.println(probationService.getStatenameByid(4));*/
		
		DeptTurnService deptTurnService=new DeptTurnService();
		System.out.println(deptTurnService.getTypeidByName("升职"));
				
		
	}
}
