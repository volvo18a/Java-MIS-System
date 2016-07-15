package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import service.DeptService;
import service.DeptTurnService;
import service.DischargeService;
import service.EmployeeService;
import service.PositionService;
import service.PostTurnService;
import service.ProbationService;
import bean.Dept;
import bean.Deptturn;
import bean.Discharge;
import bean.Employee;
import bean.JsonNumber;
import bean.Position;
import bean.Postturn;
import bean.Probation;

@WebServlet("/DppSelect")
public class DppSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DeptService deptService = new DeptService();
	PositionService positionService = new PositionService();
	ProbationService probationService = new ProbationService();
	EmployeeService employeeService = new EmployeeService();

	public DppSelect() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter("method");
		switch (message) {
		case "dppidtoname":
			break;
		case "deptidtoname":// 获取部门名称
			List<Dept> dlist = deptService.getAllDept();
			JSONArray darray = new JSONArray();
			darray = darray.fromObject(dlist);// 将list转换成jso数据
			PrintWriter dout = response.getWriter();
			dout.print(darray.toString());
			break;
		case "postidtoname":// 获取岗位名称
			List<Position> plist = positionService.getAllPosition();
			JSONArray parray = new JSONArray();
			parray = parray.fromObject(plist);
			PrintWriter pout = response.getWriter();
			pout.print(parray.toString());
			break;

		case "modProbation":// 修改试用期状态
			int result;
			Probation probation = new Probation();
			String empId = request.getParameter("emp_id1");// 员工id
			// System.out.println("empid:" + empId);
			String comment = request.getParameter("comment");// 试用期评语
			// System.out.println("comment" + comment);
			String stateName = request.getParameter("statename");// 试用期状态
			// System.out.println("statename:" + stateName);
			String dealDate = request.getParameter("dealdate");// 处理日期
			// System.out.println("dealdate:"+dealDate);
			String dealyDate = request.getParameter("dealydate");// 延期日期
			// System.out.println("dealy:"+dealyDate);
			probation.setEmp_id(Integer.parseInt(empId));
			probation.setProba_comment(comment);
			probation.setProba_dealdate(dealDate);
			probation.setProba_stateName(stateName);
			probation.setProba_enddate(dealyDate);

			result = probationService.updateState(probation);
			List<Probation> prolist = new ArrayList<Probation>();
			if (result > 0) {
				System.out.println("操作成功");

				String pemp_id = request.getParameter("emp_id");// 员工id
				String pemp_name = request.getParameter("emp_name");// 员工姓名
				String pdept_name = request.getParameter("dept_name");// 部门名称

				String ppost_name = request.getParameter("post_name");// 岗位名称

				// 试用期状态名称
				String pstate_name = request.getParameter("state_name");

				// 试用期开始时间
				String proba_begindate = request
						.getParameter("proba_begintime");
				// 试用期结束时 间
				String proba_enddate = request.getParameter("proba_endtime");
				Employee pemployee = new Employee();
				Probation probation1 = new Probation();

				// 处理员工id为空
				if (pemp_id == null || pemp_id.equals("")) {
					pemployee.setEmp_id(0);
				} else {
					pemployee.setEmp_id(Integer.parseInt(pemp_id));
				}

				pemployee.setEmp_name(pemp_name);
				pemployee.setDept_name(pdept_name);
				pemployee.setPost_name(ppost_name);

				probation1.setProba_begindate(proba_begindate);
				probation1.setProba_enddate(proba_enddate);
				probation1.setState_name(pstate_name);

				prolist = employeeService.getEmppbByTerm(pemployee, probation1);
				System.out.println("prolist:"+prolist.size());
			} else {
				System.out.println("操作");
			}

			/*
			 * List<Integer> list=new ArrayList<Integer>(); list.add(result);
			 * JSONArray array = new JSONArray(); array =
			 * array.fromObject(list);// 将list转换成jso数据 PrintWriter out =
			 * response.getWriter(); out.print(array.toString());
			 */

			JSONArray array = new JSONArray();
			array = array.fromObject(prolist);// 将list转换成jso数据
			PrintWriter out = response.getWriter();
			out.print(array.toString());
			System.out.println(array.toString());
			out.close();
			// System.out.println(array.toString());
			break;
		case "deptTurn":// 部门调转
			Deptturn dturn = new Deptturn();
			String deptEmpId = request.getParameter("emp_id1");// 员工id
			// System.out.println("empid:"+deptEmpId);
			String aftername = request.getParameter("aftername");
			// System.out.println("aftername:"+aftername);
			String typeName = request.getParameter("typename");
			// System.out.println("typeName:"+typeName);
			String reason = request.getParameter("reson");
			// System.out.println("reason:"+reason);
			String date = request.getParameter("date");
			// System.out.println("date:"+date);

			dturn.setDturn_empid(Integer.parseInt(deptEmpId));
			dturn.setDturn_aftername(aftername);
			dturn.setDturn_typeName(typeName);
			dturn.setDturn_reason(reason);

			dturn.setDturn_data(date);

			DeptTurnService dturnSer = new DeptTurnService();
			int dtresult = dturnSer.deptTurn(dturn);
			if (dtresult == 0) {
				System.out.println("部门调动操作失败");
			} else {
				System.out.println("部门调动操作成功");
			}
		/*	List<Integer> ddlist = new ArrayList<Integer>();
			ddlist.add(dtresult);
			JSONArray ddarray = new JSONArray();
			ddarray = ddarray.fromObject(ddlist);// 将list转换成jso数据
			PrintWriter ddout = response.getWriter();
			ddout.print(ddarray.toString());*/
			
			List<Employee> dplist = new ArrayList<Employee>();
			// 员工编号
			String dpemp_id = request.getParameter("emp_id");
			// 员工姓名
			String dpemp_name = request.getParameter("emp_name");
			// 部门id
			String dpdept_id = request.getParameter("dept_id");
			// System.out.println("dpdept_id" + dpdept_id);
			// 部门名称
			String dpdept_name = request.getParameter("dept_name");
			System.out.println("dept_name:" + dpdept_name);

			// 岗位名称
			String dppost_name = request.getParameter("post_name");
			//System.out.println("postname:" + dppost_name);
			// 岗位id
			String dppost_id = request.getParameter("post_id");
			//System.out.println("postid" + dppost_id);

			Employee dpemployee = new Employee();
			// 处理员工编号为空
			if (dpemp_id == null || dpemp_id.equals("")) {
				dpemployee.setEmp_id(0);
			} else {
				dpemployee.setEmp_id(Integer.parseInt(dpemp_id));
			}
			dpemployee.setEmp_name(dpemp_name);
			// 处理部门编号为空
			if (dpdept_id == null || dpdept_id.equals("")) {
				dpemployee.setEmp_deptid(0);
			} else {
				dpemployee.setEmp_deptid(Integer.parseInt(dpdept_id));
			}
			dpemployee.setDept_name(dpdept_name);
			// 处理岗位编号为空
			if (dppost_id == null || dppost_id.equals("")) {
				dpemployee.setEmp_postid(0);
			} else {
				dpemployee.setEmp_postid(Integer.parseInt(dppost_id));
			}
			dpemployee.setPost_name(dppost_name);

			dplist = employeeService.getEmpByFouritem(dpemployee);
			JSONArray dparray = new JSONArray();
			dparray = dparray.fromObject(dplist);// 将list转换成jso数据
			PrintWriter dpout = response.getWriter();
			dpout.print(dparray.toString());
			System.out.println(dparray.toString());
			dpout.close();
			
			break;
		case "postTrun":// 岗位调转
			Postturn pturn = new Postturn();
			String postEmpId = request.getParameter("emp_id");// 员工id
			String paftername = request.getParameter("paftername");
			String ptypeName = request.getParameter("ptypeName");
			// System.out.println("ptypenma"+ptypeName);
			String remark = request.getParameter("remark");
			String pdate = request.getParameter("pdate");

			pturn.setPturn_empid(Integer.parseInt(postEmpId));
			pturn.setPturn_aftername(paftername);
			pturn.setPturn_typeName(ptypeName);
			// pturn.setPturn_remark(remark);
			pturn.setPturn_reason(remark);

			pturn.setPturn_date(pdate);

			PostTurnService pturnSer = new PostTurnService();
			int ptresult = pturnSer.postTurn(pturn);
			if (ptresult == 0) {
				System.out.println("岗位调动操作失败");
			} else {
				System.out.println("岗位调动操作成功");
			}
			List<Integer> pdlist = new ArrayList<Integer>();
			pdlist.add(ptresult);
			JSONArray pdarray = new JSONArray();
			pdarray = pdarray.fromObject(pdlist);// 将list转换成jso数据
			PrintWriter pdout = response.getWriter();
			pdout.print(pdarray.toString());
			break;
		case "dischargeDeal":// 离职操作
			Discharge dischg = new Discharge();

			String dischgEmpId = request.getParameter("emp_id");// 员工id
			// System.out.println("dischgEmpId:"+dischgEmpId);
			String dinec = request.getParameter("dinec");
			// System.out.println("dinec:"+dinec);
			String type = request.getParameter("type");
			// System.out.println("type:"+type);
			String ifenter = request.getParameter("ifenter");
			// System.out.println("ifenter:"+ifenter);
			String dischgdate = request.getParameter("dischgdate");
			// System.out.println("dischgdate:"+dischgdate);

			dischg.setDischg_empid(Integer.parseInt(dischgEmpId));
			dischg.setDischg_date(dischgdate);
			dischg.setDischg_type(type);
			dischg.setDischg_dinec(dinec);
			dischg.setDischg_ifenter(Integer.parseInt(ifenter));// 1表示是
			DischargeService dischgSer = new DischargeService();
			// System.out.println(pturn.getPturn_aftername());
			int disresult = dischgSer.dischargeDeal(dischg);
			if (disresult == 0) {
				System.out.println("离职操作失败");
			} else {
				System.out.println("离职操作成功");
			}
			List<Integer> dislist = new ArrayList<Integer>();
			dislist.add(disresult);
			JSONArray disarray = new JSONArray();
			disarray = disarray.fromObject(dislist);// 将list转换成jso数据
			PrintWriter disout = response.getWriter();
			disout.print(disarray.toString());
			break;
		case "insertemp":
			Employee employee = new Employee();
			Probation probation2 = new Probation();
			String emp_name = request.getParameter("emp_name");
			// System.out.println("emp_name:"+emp_name);
			String emp_sex = request.getParameter("emp_sex");
			// System.out.println("emp_sex:"+emp_sex);
			String emp_birth = request.getParameter("emp_birth");
			// System.out.println("emp_birth:"+emp_birth);
			String emp_idcard = request.getParameter("emp_idcard");
			// System.out.println("emp_idcard:"+emp_idcard);
			String dept_name = request.getParameter("dept_name");
			// System.out.println("dept_name:"+dept_name);
			String post_name = request.getParameter("post_name");
			// System.out.println("post_name:"+post_name);
			String emp_entrydate = request.getParameter("emp_entrydate");
			// System.out.println("emp_entrydate:"+emp_entrydate);
			String emp_paritdate = request.getParameter("emp_paritdate");
			// System.out.println("emp_paritdate:"+emp_paritdate);
			String emp_form = request.getParameter("emp_form");
			// System.out.println("emp_form:"+emp_form);
			String emp_source = request.getParameter("emp_source");
			// System.out.println("emp_source:"+emp_source);
			// 新加员工学历
			String emp_dgree = request.getParameter("emp_dgree");
			// System.out.println("emp_dgree:"+emp_dgree);
			String emp_ifprobation = request.getParameter("emp_ifprobation");
			// System.out.println("emp_ifprobation:"+emp_ifprobation);
			String begintime = request.getParameter("begintime");
			// System.out.println("begintime:"+begintime);
			String endtime = request.getParameter("endtime");
			// System.out.println("endtime:"+endtime);

			employee.setEmp_name(emp_name);
			employee.setEmp_sex(emp_sex);
			employee.setEmp_birth(emp_birth);
			employee.setEmp_idcard(emp_idcard);
			employee.setDept_name(dept_name);
			employee.setPost_name(post_name);
			employee.setEmp_entrydate(emp_entrydate);
			employee.setEmp_partidate(emp_paritdate);
			employee.setEmp_form(emp_form);
			employee.setEmp_source(emp_source);
			employee.setEmp_dgree(emp_dgree);
			employee.setEmp_ifprobation(Integer.parseInt(emp_ifprobation));

			probation2.setProba_begindate(begintime);
			probation2.setProba_enddate(endtime);

			System.out.println("emp-deptname:" + employee.getDept_name());
			int aresult = employeeService.addEmpMeaasge(employee, probation2);
			if (aresult == 0) {
				System.out.println("员工录入操作失败");
			} else {
				System.out.println("员工录入操作成功");
			}
			List<Integer> ilist = new ArrayList<Integer>();
			ilist.add(aresult);
			JSONArray iarray = new JSONArray();
			iarray = iarray.fromObject(ilist);// 将list转换成jso数据
			PrintWriter iout = response.getWriter();
			iout.print(iarray.toString());
			break;
		default:
			break;
		}
		// request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
