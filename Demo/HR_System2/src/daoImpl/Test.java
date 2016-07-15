package daoImpl;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import service.EmployeeService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import bean.Deptturn;
import bean.Discharge;
import bean.Employee;
import bean.JsonNumber;
import bean.Postturn;
import bean.Probation;

public class Test {

	public static void main(String[] args) {
		EmployeeService employeeService = new EmployeeService();

		// 测试Json数据
		// Tes tes = new Tes();
		// List<Employee> list = new ArrayList<Employee>();
		// list = tes.get();
		// JSONArray jsonarray = JSONArray.fromObject(list);
		// System.out.println(jsonarray.toString());

		// 测试按编号查找
		// Employee employee=daoImpl.getEmployeeByempid(2);

		// 测试按部门编号查找
		// List<Employee> list = daoImpl.getEmpdeptid(1);
		// for (Employee employee : list) {
		// System.out.println(employee.getEmp_name());
		// }

		// 测试 按岗位编号查找
		// List<Employee> list = daoImpl.getEmppostid(2);
		// for (Employee employee : list) {
		// System.out.println(employee.getEmp_name());
		// }

		// 测试姓名模糊查询
		// List<Employee> list = daoImpl.getEmpByname("a");
		// for (Employee employee : list) {
		// System.out.println(employee.getEmp_name());
		// }

		/*
		 * // 试用期管理模块条件查询 Employee employee = new Employee(); Probation
		 * probation = new Probation(); // employee.setEmp_id(1);// 员工编号 //
		 * employee.setEmp_name("");// 员工姓名 //employee.setDept_name("财务部");//
		 * 页面数据为部门名称，根据部门名称查找到部门id //employee.setPost_name("程序员");//
		 * 页面数据为岗位名称，根据岗位名称查找到岗位id //probation.setState_name("正常"); //
		 * probation.setProba_begindate("2016-07-04"); //
		 * probation.setProba_enddate("2016-07-05");
		 * probation.setBegintime("2013-02-01");
		 * probation.setEndtime("2013-12-04"); List<Probation> list =
		 * employeeService.getEmppbByTerm(employee, probation); for (Probation
		 * emproba : list) { System.out.println("员工编号" + emproba.getEmp_id());
		 * System.out.println("员工姓名" + emproba.getEmp_name());
		 * System.out.println("部门编号" + emproba.getEmp_deptid());
		 * System.out.println("岗位编号" + emproba.getEmp_postid());
		 * System.out.println("试用期状态号" + emproba.getPrroba_stateid());
		 * System.out.println("试用期开始时间" + emproba.getProba_begindate());
		 * System.out.println("试用期结束时间" + emproba.getProba_enddate());
		 * System.out.println("处理时间" + emproba.getProba_dealdate()); }
		 */
		/*
		 * // 测试部门，岗位调动模块 查询员工列表 Employee employee = new Employee();
		 * //employee.setEmp_id(1); // employee.setEmp_name("姜");
		 * employee.setEmp_deptid(1); //employee.setDept_name("财务部");
		 * employee.setEmp_postid(1); employee.setPost_name("经理");
		 * List<Employee> list=employeeService.getEmpByFouritem(employee);
		 * if(list != null){ System.out.println("size:"+list.size());
		 * for(Employee employee2:list){
		 * System.out.println("员工编号"+employee2.getEmp_id());
		 * System.out.println("员工姓名"+employee2.getEmp_name());
		 * System.out.println("部门编号"+employee2.getEmp_deptid());
		 * System.out.println("岗位编号"+employee2.getEmp_postid());
		 * System.out.println("出生日期"+employee2.getEmp_birth()); } }else {
		 * System.out.println("空"); }
		 */

		// 部门调动管理--开始日期，结束日期，员工编号，员工姓名，调动方式。
		/*
		 * Employee employee = new Employee(); Deptturn deptturn = new
		 * Deptturn(); employee.setEmp_id(1); //employee.setEmp_name("");
		 * deptturn.setBegintime("2011-01-01");
		 * deptturn.setEndtime("2014-01-01");
		 * deptturn.setDturn_typeName("主动调动");; List<Deptturn> list
		 * =employeeService.getDeptturnByitem(employee, deptturn);
		 * System.out.println("size:" + list.size()); for (Deptturn deptturn2 :
		 * list) { System.out.println("员工姓名:" + deptturn2.getEmp_name());
		 * System.out.println("调转状态:" + deptturn2.getDturn_typeid());
		 * System.out.println("调转时间:" + deptturn2.getDturn_data()); }
		 */
		// gangwei调动管理--开始日期，结束日期，员工编号，员工姓名，调动方式。
		/*
		 * Employee employee = new Employee(); Postturn postturn = new
		 * Postturn(); //employee.setEmp_id(2); // employee.setEmp_name("");
		 * //postturn.setBegintime("2012-11-11"); //
		 * postturn.setEndtime("2013-12-12"); postturn.setPturn_typeName("降职");
		 * List<Postturn> list = employeeService.getPostturnByitem(employee,
		 * postturn); System.out.println("size:" + list.size()); for (Postturn
		 * postturn2 : list) { System.out.println("员工姓名" +
		 * postturn2.getEmp_name()); System.out.println("调转状态" +
		 * postturn2.getPturn_typeid()); System.out.println("调转时间" +
		 * postturn2.getPturn_date()); }
		 */

		// 已离职员工信息查询--员工编号，姓名，部门名称，岗位名称，离职类型，查询开始日期，结束日期
	//	Employee employee = new Employee();
	//	Discharge discharge = new Discharge();
		// employee.setEmp_id(1);// 员工编号
		// employee.setEmp_name("a");// 员工姓名
		// employee.setEmp_deptid(1);// 页面数据为部门名称，根据部门名称查找到部门id
		// employee.setEmp_postid(1);// 页面数据为岗位名称，根据岗位名称查找到岗位id
		/*
		 * employee.setDept_name("财务部"); discharge.setBegintime("2011-11-11");
		 * discharge.setEndtime("2012-12-12"); discharge.setDischg_type("主动辞职");
		 * List<Discharge> list=employeeService.getDischgByitem(employee,
		 * discharge); System.out.println("size:"+list.size()); for(Discharge
		 * discharge2 :list){ System.out.println(discharge2.getEmp_name());
		 * System.out.println(discharge2.getDischg_type());
		 * System.out.println(discharge2.getDischg_date()); }
		 */

		/*
		 * Employee employee=new Employee(); List<Employee> list=new
		 * ArrayList<Employee>(); list.add(employee);
		 * System.out.println(list.get(0).getEmp_id());
		 */

		/*Employee employee = new Employee();
		employee.setEmp_name("王丛生");
		employee.setEmp_sex("男");
		employee.setEmp_birth("1999-09-09");
		employee.setEmp_idcard("1111111111");
		employee.setEmp_deptid(1);
		employee.setEmp_postid(1);
		employee.setEmp_entrydate("1999-09-09");
		employee.setEmp_partidate("1999-09-09");
		employee.setEmp_form("临时");
		employee.setEmp_source("校园招聘");
		employee.setEmp_ifprobation(1);
		employee.setEmp_dgree("高中及以下");
		Probation probation = new Probation();
		probation.setProba_begindate("2000-01-01");
		probation.setProba_enddate("2000-05-05");
		int i = employeeService.addEmpMeaasge(employee, probation);
		System.out.println("执行  :" + i);*/
		
		List list=new ArrayList();
		int flag=0;
		JsonNumber jsonNumber=new JsonNumber();
		jsonNumber.setNumber(flag);
		Employee employee=new Employee();
		employee.setEmp_name("as");
		list.add(employee);
		list.add(jsonNumber);
		
		JSONArray array = new JSONArray();
		array = array.fromObject(list);// 将list转换成jso数据
		//PrintWriter out = response.getWriter();
		//out.print(array.toString());
		System.out.println(array.toString());
		/*for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
			
		}*/
	}
}