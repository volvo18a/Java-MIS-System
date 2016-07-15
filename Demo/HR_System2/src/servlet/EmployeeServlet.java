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
import bean.Dept;
import bean.Deptturn;
import bean.Discharge;
import bean.Employee;
import bean.Position;
import bean.Postturn;
import bean.Probation;
import service.DeptService;
import service.EmployeeService;
import service.PositionService;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmployeeService employeeService = new EmployeeService();

	public EmployeeServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter("method");
		// System.out.println("message:" + message);
		switch (message) {
		case "searchAll":// 查询所有员工信息
			List<Employee> elist = new ArrayList<Employee>();
			elist = employeeService.getAllEmployee();
			request.setAttribute("employee", elist);
			// JSONArray array = new JSONArray();
			// array = array.fromObject(list);//将list转换成jso数据
			// PrintWriter out = response.getWriter();
			// out.print(array.toString());
			// System.out.println(array.toString());
			// out.close();
			break;
		case "seachproba":// 试用期员工查询
			List<Probation> plist = new ArrayList<Probation>();

			String emp_id = request.getParameter("emp_id");// 员工id
			String emp_name = request.getParameter("emp_name");// 员工姓名
			String dept_name = request.getParameter("dept_name");// 部门名称

			String post_name = request.getParameter("post_name");// 岗位名称

			// 试用期状态名称
			String state_name = request.getParameter("state_name");

			// 试用期开始时间
			String proba_begindate = request.getParameter("proba_begintime");
			// 试用期结束时 间
			String proba_enddate = request.getParameter("proba_endtime");
			Employee employee = new Employee();
			Probation probation = new Probation();

			// 处理员工id为空
			if (emp_id == null || emp_id.equals("")) {
				employee.setEmp_id(0);
			} else {
				employee.setEmp_id(Integer.parseInt(emp_id));
			}

			employee.setEmp_name(emp_name);
			employee.setDept_name(dept_name);
			employee.setPost_name(post_name);

			probation.setProba_begindate(proba_begindate);
			probation.setProba_enddate(proba_enddate);
			probation.setState_name(state_name);

			plist = employeeService.getEmppbByTerm(employee, probation);

			JSONArray array = new JSONArray();
			array = array.fromObject(plist);// 将list转换成jso数据
			PrintWriter out = response.getWriter();
			out.print(array.toString());
			//System.out.println(array.toString());
			out.close();
			break;
		case "seachregular":// 转正查询
			List<Probation> rlist = new ArrayList<Probation>();

			String remp_id = request.getParameter("emp_id");// 员工id
			//System.out.println("remp_id:" + remp_id);
			String remp_name = request.getParameter("emp_name");// 员工姓名
			String rdept_name = request.getParameter("dept_name");// 部门名称
			//System.out.println("转dept_name:" + rdept_name);
			String rpost_name = request.getParameter("post_name");// 岗位名称
			//System.out.println("post_name:" + rpost_name);
			// 开始时间
			String rbegintime = request.getParameter("begintime");
			// 结束时间
			//System.out.println("结束时间" + rbegintime);
			String rendtime = request.getParameter("endtime");

			Employee remployee = new Employee();
			Probation rprobation = new Probation();

			// 处理员工id为空
			if (remp_id == null || remp_id.equals("")) {
				remployee.setEmp_id(0);
			} else {
				remployee.setEmp_id(Integer.parseInt(remp_id));
			}

			remployee.setEmp_name(remp_name);
			remployee.setDept_name(rdept_name);
			remployee.setPost_name(rpost_name);

			rprobation.setState_name("转正");
			rprobation.setBegintime(rbegintime);
			rprobation.setEndtime(rendtime);

			rlist = employeeService.getEmppbByTerm(remployee, rprobation);

			JSONArray rarray = new JSONArray();
			rarray = rarray.fromObject(rlist);// 将list转换成jso数据
			PrintWriter rout = response.getWriter();
			rout.print(rarray.toString());
			//System.out.println(rarray.toString());
			rout.close();
			break;
		case "seaemplist":// 部门和岗位调动查询员工列表
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
		case "getdturnMessage":// 部门调转查询
			List<Deptturn> dtlist = new ArrayList<Deptturn>();
			//System.out.println("1111111");
			String dtemp_id = request.getParameter("emp_id");
			//System.out.println("emp_id:" + dtemp_id);
			String dtemp_name = request.getParameter("emp_name");
			String dttype_name = request.getParameter("dturn_name");
			System.out.println("type_ane:" + dttype_name);
			String dtbegintime = request.getParameter("begintime");
			String dtendtime = request.getParameter("endtime");

			Deptturn deptturn = new Deptturn();
			Employee dtemployee = new Employee();

			if (dtemp_id == null || dtemp_id.equals("")) {
				dtemployee.setEmp_id(0);
			} else {
				dtemployee.setEmp_id(Integer.parseInt(dtemp_id));
			}
			dtemployee.setEmp_name(dtemp_name);
			deptturn.setDturn_typeName(dttype_name);
			deptturn.setBegintime(dtbegintime);
			deptturn.setEndtime(dtendtime);

			dtlist = employeeService.getDeptturnByitem(dtemployee, deptturn);
			JSONArray dtarray = new JSONArray();
			dtarray = dtarray.fromObject(dtlist);// 将list转换成jso数据
			PrintWriter dtout = response.getWriter();
			dtout.print(dtarray.toString());
			//System.out.println(dtarray.toString());
			dtout.close();
			break;
		case "getpturnMessage":// 岗位调转查询
			List<Postturn> ptlist = new ArrayList<Postturn>();

			String ptemp_id = request.getParameter("emp_id");
			String ptemp_name = request.getParameter("emp_name");
			String pttype_name = request.getParameter("pturn_name");
			String ptbegintime = request.getParameter("begintime");
			String ptendtime = request.getParameter("endtime");

			Postturn postturn = new Postturn();
			Employee ptemployee = new Employee();

			if (ptemp_id == null || ptemp_id.equals("")) {
				ptemployee.setEmp_id(0);
			} else {
				ptemployee.setEmp_id(Integer.parseInt(ptemp_id));
			}
			ptemployee.setEmp_name(ptemp_name);
			postturn.setPturn_typeName(pttype_name);
			postturn.setBegintime(ptbegintime);
			postturn.setEndtime(ptendtime);

			ptlist = employeeService.getPostturnByitem(ptemployee, postturn);
			JSONArray ptarray = new JSONArray();
			ptarray = ptarray.fromObject(ptlist);// 将list转换成jso数据
			PrintWriter ptout = response.getWriter();
			ptout.print(ptarray.toString());
			//System.out.println(ptarray.toString());
			ptout.close();
			break;
		case "getdischarge":// 离职管理查询
			List<Discharge> dlist = new ArrayList<Discharge>();
			String demp_id = request.getParameter("emp_id");// 员工id
			String demp_name = request.getParameter("emp_name");// 员工姓名
			System.out.println("dempname:"+demp_name);
			String ddept_name = request.getParameter("dept_name");// 部门名称
			//System.out.println("dept_name:"+dept_name);
			String dpost_name = request.getParameter("post_name");// 岗位名称
			 System.out.println("post_name:"+dpost_name);
			// 开始时间
			String dbegintime = request.getParameter("begintime");
			// 结束时间
			String dendtime = request.getParameter("endtime");
			String distype = request.getParameter("distype");
			System.out.println("distype:"+distype);
			Employee demployee = new Employee();
			Discharge discharge = new Discharge();
			// 处理员工id为空
			if (demp_id == null || demp_id.equals("")) {
				demployee.setEmp_id(0);
			} else {
				demployee.setEmp_id(Integer.parseInt(demp_id));
			}

			demployee.setEmp_name(demp_name);
			demployee.setDept_name(ddept_name);
			demployee.setPost_name(dpost_name);
			discharge.setBegintime(dbegintime);
			discharge.setEndtime(dendtime);
			discharge.setDischg_type(distype);
			dlist = employeeService.getDischgByitem(demployee, discharge);
			JSONArray darray = new JSONArray();
			darray = darray.fromObject(dlist);// 将list转换成jso数据
			PrintWriter dout = response.getWriter();
			dout.print(darray.toString());
			//System.out.println(darray.toString());
			dout.close();
			break;
		default:
			break;
		}
	}

}