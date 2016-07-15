package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import service.DeptTurnService;
import service.MonthReportService;
import service.NewEmpService;
import service.PostTurnService;
import service.ReportService;

import bean.Monthreport;

@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ReportService reportService = new ReportService();
	PostTurnService postTurnService = new PostTurnService();
	DeptTurnService deptTurnService = new DeptTurnService();
	NewEmpService newempService = new NewEmpService();
	MonthReportService monthreportservice = new MonthReportService();

	public ReportServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List list = new ArrayList();
		String message = request.getParameter("method");
		String flag = request.getParameter("flag");
		String date1 = null, date2 = null, dep = null;
		System.out.println(message);
		switch (message) {
		case "LeaveEmp":// 离职员工报表
			date1 = request.getParameter("LeaveEmp_date1");
			date2 = request.getParameter("LeaveEmp_date2");
			dep = request.getParameter("dep");
			System.out.println(dep + "!!!!!!!!!!!!!!");
			//list = reportService.getAllDischarge(date1, date2, dep);//原来的
			list = reportService.getdisinfo(date1, date2, dep);
			JSONArray array = new JSONArray();
			array = array.fromObject(list);// 将list转换成jso数据
			PrintWriter out = response.getWriter();
			out.print(array.toString());
			out.close();
			break;

		case "DeptTurn":// 部门调转
			date1 = request.getParameter("deptturn_date1");
			date2 = request.getParameter("deptturn_date2");
			System.out.println("date2:" + date2);
			list = deptTurnService.getAllDeptTurn(date1, date2);
			// request.setAttribute("deptturn", list);

			// request.getRequestDispatcher("index.jsp")
			// .forward(request, response);
			JSONArray darray = new JSONArray();
			darray = darray.fromObject(list);// 将list转换成jso数据
			PrintWriter dout = response.getWriter();
			dout.print(darray.toString());
			dout.close();
			break;

		case "PostTurn":// 岗位调转
			date1 = request.getParameter("postturn_date1");
			date2 = request.getParameter("postturn_date2");

			list = postTurnService.getAllPostTurn(date1, date2);
			// request.setAttribute("postturn", list);

			// request.getRequestDispatcher("index.jsp")
			// .forward(request, response);
			JSONArray parray = new JSONArray();
			parray = parray.fromObject(list);// 将list转换成jso数据
			PrintWriter pout = response.getWriter();
			pout.print(parray.toString());
			pout.close();
			break;

		case "NewEmp":// 新聘员工表
			date1 = request.getParameter("newemp_date1");
			date2 = request.getParameter("newemp_date2");
			/*
			 * dep = new String(
			 * request.getParameter("dep").getBytes("iso-8859-1"), "utf-8");
			 */
			dep = request.getParameter("dep");
			System.out.println("date1:" + date1 + "date2:" + date2 + "dep:"
					+ dep);
			//list = newempService.getAllNewEmp(date1, date2, dep);
			list = newempService.getNewEmpInfo(date1, date2, dep);
			// request.setAttribute("newemp", list);

			// request.getRequestDispatcher("index.jsp")
			// .forward(request, response);
			JSONArray narray = new JSONArray();
			narray = narray.fromObject(list);// 将list转换成jso数据
			PrintWriter nout = response.getWriter();
			nout.print(narray.toString());
			nout.close();
			break;

		case "EmpReport":// 人事日报
			List<Monthreport> list1 = new ArrayList<Monthreport>();
			String year = request.getParameter("emp_year");
			String month = request.getParameter("emp_month");
			date1 = year + '-' + month + "-01";
			date2 = year + '-' + month + "-31";

			System.out.println(date1);
			System.out.println(date2);
			list1 = monthreportservice.getAllReport(date1, date2);
			for (Monthreport monthreport : list1) {
				System.out.println(monthreport.getDept_name());
			}
			// request.setAttribute("report", list1);

			// request.getRequestDispatcher("index.jsp")
			// .forward(request, response);
			JSONArray earray = new JSONArray();
			earray = earray.fromObject(list1);// 将list转换成jso数据
			PrintWriter eout = response.getWriter();
			eout.print(earray.toString());
			eout.close();
			break;

		default:
			break;
		}
	}

}
