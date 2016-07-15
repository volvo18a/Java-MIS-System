package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Employee;
import service.DeptTurnService;
import service.MonthReportService;
import service.NewEmpService;
import service.PostTurnService;
import service.ReportService;
import util.Excel;
import util.Files;

@WebServlet("/ReportExcel")
public class ReportExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ReportService reportService = new ReportService();
	PostTurnService postTurnService=new PostTurnService();
	DeptTurnService deptTurnService=new DeptTurnService();
	NewEmpService newempService=new NewEmpService();
	MonthReportService monthreportservice=new MonthReportService();

	public ReportExcel() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 创建当前日子
		Date date = new Date();
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		// 格式化日期(产生文件名)
		String filename = sdf.format(date);

		// 创建文件
		File f = new File("E://" + filename + ".xls");
		f.createNewFile();

		String message = request.getParameter("method");
		String date1 = null, date2 = null, dep = null;
		
		switch(message)
		{
		    case "NewEmp":
		    	
				date1 = request.getParameter("newemp_date1");
				date2 = request.getParameter("newemp_date2");
				dep =request.getParameter("dep");
			

				String title[] = { "序号","部门名称","岗位名称","姓名","性别","入职日期","学历"};
				List lists = new ArrayList();
				//lists = newempService.getAllNewEmp(date1, date2, dep);
				lists = newempService.getNewEmpInfo(date1, date2, dep);
				System.out.println("size:"+lists.size());
				// 生成excel文件(保存在服务器机上)
				try {

					Object ob = lists.get(0);
					Class cl = ob.getClass();
					Field[] fi = new Field[7];
					
					fi[0] = cl.getDeclaredField("emp_id");
					fi[1] = cl.getDeclaredField("emp_deptname");
					fi[2] = cl.getDeclaredField("emp_postname");
					fi[3] = cl.getDeclaredField("emp_name");
					fi[4] = cl.getDeclaredField("emp_sex");
					fi[5] = cl.getDeclaredField("emp_entrydate");
					fi[6] = cl.getDeclaredField("emp_dgree");
					
					System.out.println(fi);
					
					Excel.writeExcel(new FileOutputStream(f), title, lists,fi);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 导出文件(下载到客户机上,并删除服务器机上的excel文件)
				Files.exportFile(response, f, true);
			    break;
		    case "Discharge":
		    	//获取查询信息
				date1 = request.getParameter("disBeginDate");
				date2 = request.getParameter("disEndDate");
				dep =request.getParameter("deptName");
				
				System.out.println("excel"+date1);
				System.out.println("excel"+date2);
				
				String distitle[] = { "序号","部门名称","岗位名称","姓名","性别","离职日期","离职原因"};
				List  dislists = new ArrayList();
				//获取数据
				dislists = reportService.getdisinfo(date1, date2, dep);
				System.out.println("size:"+dislists.size());
				// 生成excel文件(保存在服务器机上)
				try {

					Object ob = dislists.get(0);
					Class cl = ob.getClass();
					Field[] fi = new Field[7];
					//通过名字选出字段，注意看是那个对象的，这你是discharge
					fi[0] = cl.getDeclaredField("dischg_id");
					fi[1] = cl.getDeclaredField("dept_name");
					fi[2] = cl.getDeclaredField("post_name");
					fi[3] = cl.getDeclaredField("emp_name");
					fi[4] = cl.getDeclaredField("emp_sex");
					fi[5] = cl.getDeclaredField("dischg_date");
					fi[6] = cl.getDeclaredField("dischg_type");
					
					System.out.println(fi);
					
					Excel.writeExcel(new FileOutputStream(f), distitle, dislists,fi);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 导出文件(下载到客户机上,并删除服务器机上的excel文件)
				Files.exportFile(response, f, true);
		    	break;
		    case "deptTurn":
		    	//获取查询信息
				date1 = request.getParameter("dturnBeginDate");
				date2 = request.getParameter("dturnEndDate");
				
				System.out.println("excel"+date1);
				System.out.println("excel"+date2);
				
				String dtruntitle[] = { "序号","原部门名称","新部门名称","姓名","性别","调动日期","调动原因"};
				List  dturnlists = new ArrayList();
				//获取数据
				dturnlists = deptTurnService.getAllDeptTurn(date1, date2);
				System.out.println("size:"+dturnlists.size());
				// 生成excel文件(保存在服务器机上)
				try {

					Object ob = dturnlists.get(0);
					Class cl = ob.getClass();
					Field[] fi = new Field[7];
					//通过名字选出字段，注意看是那个对象的，这你是deptturn
					fi[0] = cl.getDeclaredField("dturn_id");
					fi[1] = cl.getDeclaredField("dturn_beforename");
					fi[2] = cl.getDeclaredField("dturn_aftername");
					fi[3] = cl.getDeclaredField("emp_name");
					fi[4] = cl.getDeclaredField("emp_sex");
					fi[5] = cl.getDeclaredField("dturn_data");
					fi[6] = cl.getDeclaredField("dturn_reason");
					
					System.out.println(fi);
					
					Excel.writeExcel(new FileOutputStream(f), dtruntitle, dturnlists,fi);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 导出文件(下载到客户机上,并删除服务器机上的excel文件)
				Files.exportFile(response, f, true);
		    	break;
		    case "postTurn":
		    	//获取查询信息
				date1 = request.getParameter("pturnBeginDate");
				date2 = request.getParameter("pturnEndDate");
				
				System.out.println("excel"+date1);
				System.out.println("excel"+date2);
				
				String ptruntitle[] = { "序号","部门名称","原岗位名称","新岗位名称","姓名","性别","调动日期","调动原因"};
				List  pturnlists = new ArrayList();
				//获取数据
				pturnlists = postTurnService.getAllPostTurn(date1, date2);
				System.out.println("size:"+pturnlists.size());
				// 生成excel文件(保存在服务器机上)
				try {

					Object ob = pturnlists.get(0);
					Class cl = ob.getClass();
					Field[] fi = new Field[8];
					//通过名字选出字段，注意看是那个对象的，这你是postturn
					fi[0] = cl.getDeclaredField("pturn_id");
					fi[1] = cl.getDeclaredField("pturn_deptname");
					fi[2] = cl.getDeclaredField("pturn_beforename");
					fi[3] = cl.getDeclaredField("pturn_aftername");
					fi[4] = cl.getDeclaredField("emp_name");
					fi[5] = cl.getDeclaredField("emp_sex");
					fi[6] = cl.getDeclaredField("pturn_date");
					fi[7] = cl.getDeclaredField("pturn_reason");
					
					System.out.println(fi);
					
					Excel.writeExcel(new FileOutputStream(f), ptruntitle, pturnlists,fi);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 导出文件(下载到客户机上,并删除服务器机上的excel文件)
				Files.exportFile(response, f, true);
		    	break;
		    case "empReport":
		    	//获取查询信息
				date1 = request.getParameter("empBeginDate");
				date2 = request.getParameter("empEndDate");
				
				System.out.println("excel"+date1);
				System.out.println("excel"+date2);
				
				String emptitle[] = { "部门名称","月初人数","月末人数","本月新入职","本月离职","本月调入","本月调出","研究生","本科","大专","高中及高中以下"};
				List  emplists = new ArrayList();
				//获取数据
				emplists = monthreportservice.getAllReport(date1, date2);
				System.out.println("size:"+emplists.size());
				// 生成excel文件(保存在服务器机上)
				try {

					Object ob = emplists.get(0);
					Class cl = ob.getClass();
					Field[] fi = new Field[11];

					//通过名字选出字段，注意看是那个对象的，这你是Monthreport
					fi[0] = cl.getDeclaredField("dept_name");
					fi[1] = cl.getDeclaredField("emp_beforenum");
					fi[2] = cl.getDeclaredField("emp_afternum");
					fi[3] = cl.getDeclaredField("emp_newnum");
					fi[4] = cl.getDeclaredField("emp_dischargenum");
					fi[5] = cl.getDeclaredField("turnin_num");
					fi[6] = cl.getDeclaredField("turnout_num");
					fi[7] = cl.getDeclaredField("graduate_num");
					fi[8] = cl.getDeclaredField("college_num");
					fi[9] = cl.getDeclaredField("senior_num");
					fi[10] = cl.getDeclaredField("junior_num");
					
					System.out.println(fi);
					
					Excel.writeExcel(new FileOutputStream(f), emptitle, emplists,fi);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 导出文件(下载到客户机上,并删除服务器机上的excel文件)
				Files.exportFile(response, f, true);
		    	break;	
			default:
				break;
		}
	}
}


