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
import net.sf.json.JSONObject;
import bean.Dept;
import bean.Employee;
import service.DeptService;
import dao.DeptDao;
import daoImpl.DeptDaoImpl;

/**
 * Servlet implementation class DeptServlet
 */
@WebServlet("/DeptServlet")
public class DeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DeptService deptService = new DeptService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeptServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		//接受菜单点击信息
		String message = request.getParameter("message");
		
		
/*		//判断菜单点击信息并执行相应操作
		if("searchAllDept".equals(message)){
			//点击显示全部信息执行显示
				List<Dept> dalist = deptService.getAllDept();
				JSONArray darray = new JSONArray();
				darray = darray.fromObject(dalist);// 将list转换成json数据
				PrintWriter dout = response.getWriter();
				dout.print(darray.toString());
		}*/
		//判断按钮点击信息并执行相应操作
		switch(message){
		case "searchAllDept":
			//点击显示全部信息执行显示
			List<Dept> dalist = deptService.getAllDept();
			JSONArray darray = new JSONArray();
			darray = darray.fromObject(dalist);// 将list转换成json数据
			PrintWriter dout = response.getWriter();
			dout.print(darray.toString());
			break;
		case "checkRename":
			int flag_rename = 0;
			String check = request.getParameter("dept_name");
			//System.out.println("11-dept_name:"+check);
			flag_rename = deptService.getDeptidByName(check);
			if(flag_rename != 0){
				int rename = 0;
				JSONArray Rename = new JSONArray();
				Rename = Rename.fromObject(rename);
				PrintWriter Rename_dout = response.getWriter();
				Rename_dout.print(Rename.toString());
			} else{
				int rename = 1;
				JSONArray Rename = new JSONArray();
				Rename = Rename.fromObject(rename);
				PrintWriter Rename_dout = response.getWriter();
				Rename_dout.print(Rename.toString());
			}
			break;
		case "newDept":
			Dept dept = new Dept();
			String dept_name = request.getParameter("dept_name");
			String dept_type = request.getParameter("dept_type");
			int dept_typeid = deptService.getDeptTypeByname(dept_type);
			int dept_tel = Integer.parseInt(request.getParameter("dept_tel"));
			String dept_fax = request.getParameter("dept_fax");
			String dept_supname = request.getParameter("dept_sup");
			int dept_sup = deptService.getDeptidByName(dept_supname);
			String dept_setdate = request.getParameter("dept_setdate");
			String dept_description = request.getParameter("dept_description");
			dept.setDept_name(dept_name);
			dept.setDept_typeid(dept_typeid);
			dept.setDept_tel(dept_tel);
			dept.setDept_fax(dept_fax);
			dept.setDept_sup(dept_sup);
			dept.setDept_setdate(dept_setdate);
			dept.setDept_description(dept_description);
			deptService.addDeptMessage(dept);
			
			//新建后更新表格
			List<Dept> dlist = deptService.getAllDept();
			JSONArray darray_new = new JSONArray();
			darray_new = darray_new.fromObject(dlist);// 将list转换成json数据
			PrintWriter dout_new = response.getWriter();
			dout_new.print(darray_new.toString());
			break;
		case "modDept":
			Dept mod_dept = new Dept();
			int mod_dept_id = Integer.parseInt(request.getParameter("dept_id"));
			String mod_dept_name = request.getParameter("dept_name");
			String mod_dept_type = request.getParameter("dept_type");
			int mod_dept_typeid = deptService.getDeptTypeByname(mod_dept_type);
			int mod_dept_tel = Integer.parseInt(request.getParameter("dept_tel"));
			String mod_dept_fax = request.getParameter("dept_fax");
			String mod_dept_supname = request.getParameter("dept_sup");
			int mod_dept_sup = deptService.getDeptidByName(mod_dept_supname);
			String mod_dept_setdate = request.getParameter("dept_setdate");
			String mod_dept_description = request.getParameter("dept_description");
			mod_dept.setDept_id(mod_dept_id);
			mod_dept.setDept_name(mod_dept_name);
			mod_dept.setDept_typeid(mod_dept_typeid);
			mod_dept.setDept_tel(mod_dept_tel);
			mod_dept.setDept_fax(mod_dept_fax);
			mod_dept.setDept_sup(mod_dept_sup);
			mod_dept.setDept_setdate(mod_dept_setdate);
			mod_dept.setDept_description(mod_dept_description);
			
			deptService.modDept(mod_dept);
			
			//修改后更新表格
			List<Dept> dlist_mod = deptService.getAllDept();
			JSONArray darray_mod = new JSONArray();
			darray_mod = darray_mod.fromObject(dlist_mod);// 将list转换成json数据
			PrintWriter dout_mod = response.getWriter();
			dout_mod.print(darray_mod.toString());
			break;
		case "delDept":
			int dept_id1 = Integer.parseInt(request.getParameter("dept_id"));
			
			int flag = deptService.delDeptMessage(dept_id1);
			
			if(flag != 0){
				//删除后更新表格
				List<Dept> dlist1 = deptService.getAllDept();
				JSONObject jo =new JSONObject();
				JSONArray darray1 = new JSONArray();
				darray1 = darray1.fromObject(dlist1);// 将list转换成json数据
				jo.put("darray1", darray1);
				jo.put("flag", 1);
				PrintWriter dout1 = response.getWriter();
				dout1.print(jo.toString());
			} else {
				//删除后更新表格
				List<Dept> dlist1 = deptService.getAllDept();
				JSONObject jo =new JSONObject();
				JSONArray darray1 = new JSONArray();
				darray1 = darray1.fromObject(dlist1);// 将list转换成json数据
				jo.put("darray1", darray1);
				jo.put("flag", 0);
				PrintWriter dout1 = response.getWriter();
				dout1.print(jo.toString());
			}
			break;
		case "searchDept":
			
			//获取查询条件
			String deptId = request.getParameter("dept_id");
			//System.out.println("deptid:"+deptId);
			String deptName = request.getParameter("dept_name");
			//System.out.println("deptname:"+ deptName);
			String deptType = request.getParameter("dept_type");
			//System.out.println("depttype:"+ deptType);
			
			List<Employee> dtlist = new ArrayList<Employee>();
			
			Dept dept_emp = new Dept();
			if(deptId.length() != 0){
				dept_emp.setDept_id(Integer.parseInt(deptId));
			}
			
			dept_emp.setDept_name(deptName);
			dept_emp.setDept_typename(deptType);
			
			dtlist = deptService.getEmpByitem(dept_emp);
			JSONArray darray4 = new JSONArray();
			darray4 = darray4.fromObject(dtlist);// 将list转换成json数据
			PrintWriter dout4 = response.getWriter();
			dout4.print(darray4.toString());
			System.out.println(darray4.toString());
			
			
/*			if(deptId.length() != 0){
				dtlist = deptService.getEmpBydeptid(Integer.parseInt(deptId));
				JSONArray darray4 = new JSONArray();
				darray4 = darray4.fromObject(dtlist);// 将list转换成json数据
				PrintWriter dout4 = response.getWriter();
				dout4.print(darray4.toString());
				System.out.println(darray4.toString());
			}
			
			if(deptName.length() != 0){
				dtlist = deptService.getEmpBydeptname(deptName);
				JSONArray darray2 = new JSONArray();
				darray2 = darray2.fromObject(dtlist);// 将list转换成json数据
				PrintWriter dout2 = response.getWriter();
				dout2.print(darray2.toString());
			}
				
			if(deptType.length() != 0){
				dtlist = deptService.getEmpBydtypename(deptType);
				JSONArray darray3 = new JSONArray();
				darray3 = darray3.fromObject(dtlist);// 将list转换成json数据
				PrintWriter dout3 = response.getWriter();
				dout3.print(darray3.toString());
			}*/
			break;

		default:
			break;
		} 
		
	}

}
