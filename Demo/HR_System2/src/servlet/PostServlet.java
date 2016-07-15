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

import service.PositionService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import bean.Dept;
import bean.Employee;
import bean.Position;

/**
 * Servlet implementation class PostServlet
 */
@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PositionService positionservice = new PositionService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostServlet() {
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
		
		//接受菜单点击信息
		String message = request.getParameter("message");
		
		//判断按钮点击信息并执行相应操作
		switch(message){
		case "searchAllPost":
			//点击显示全部信息执行显示
			List<Position> plist = positionservice.getAllPosition();
			JSONArray darray = new JSONArray();
			darray = darray.fromObject(plist);// 将list转换成json数据
			PrintWriter dout = response.getWriter();
			dout.print(darray.toString());
			break;
		case "checkPost":
			int flag_Rename = 0;
			String check = request.getParameter("post_name"); 
			System.out.println("11-poname:"+check);
			flag_Rename =  positionservice.getPostidByName(check);
			if(flag_Rename != 0){
				int rename = 0;
				JSONArray Rename = new JSONArray();
				Rename = Rename.fromObject(rename);
				PrintWriter Rename_dout = response.getWriter();
				Rename_dout.print(Rename.toString());
			} else {
				int rename = 1;
				JSONArray Rename = new JSONArray();
				Rename = Rename.fromObject(rename);
				PrintWriter Rename_dout = response.getWriter();
				Rename_dout.print(Rename.toString());
			}
			break;
		case "newPost":
			Position post = new Position();
			String post_name = request.getParameter("post_name");
			String post_type = request.getParameter("post_type");
			int post_typeid = positionservice.getPostTypeidByName(post_type);
			int post_count = Integer.parseInt(request.getParameter("post_count"));
			post.setPost_name(post_name);
			post.setPost_typeid(post_typeid);
			post.setPost_count(post_count);
			
			positionservice.addPostMessage(post);
			
			//新建后表格更新
			List<Position> new_plist = positionservice.getAllPosition();
			JSONArray new_darray = new JSONArray();
			new_darray = new_darray.fromObject(new_plist);// 将list转换成json数据
			PrintWriter new_dout = response.getWriter();
			new_dout.print(new_darray.toString());
			break;
		case "modPost":
			Position mod_post = new Position();
			int mod_post_id = Integer.parseInt(request.getParameter("post_id"));
			String mod_post_name = request.getParameter("post_name");
			String mod_post_type = request.getParameter("post_type");
			int mod_post_typeid = positionservice.getPostTypeidByName(mod_post_type);
			int mod_post_count = Integer.parseInt(request.getParameter("post_count"));
			mod_post.setPost_id(mod_post_id);
			mod_post.setPost_name(mod_post_name);
			mod_post.setPost_typeid(mod_post_typeid);
			mod_post.setPost_count(mod_post_count);
			
			
			positionservice.modPostMessage(mod_post);
			
			//修改后表格更新
			List<Position> mod_plist = positionservice.getAllPosition();
			JSONArray mod_darray = new JSONArray();
			mod_darray = mod_darray.fromObject(mod_plist);// 将list转换成json数据
			PrintWriter mod_dout = response.getWriter();
			mod_dout.print(mod_darray.toString());
			break;
		case "delPost":
			int post_id1 = Integer.parseInt(request.getParameter("post_id"));
			
			int flag = positionservice.delPostMessage(post_id1);
			
			if(flag != 0){
				//删除后表格更新
				List<Position> plist1 = positionservice.getAllPosition();
				JSONObject jo =new JSONObject();
				JSONArray darray1 = new JSONArray();
				darray1 = darray1.fromObject(plist1);// 将list转换成json数据
				jo.put("darray1", darray1);
				jo.put("flag", 1);
				PrintWriter dout1 = response.getWriter();
				dout1.print(jo.toString());
			} else {
				List<Position> plist1 = positionservice.getAllPosition();
				JSONObject jo =new JSONObject();
				JSONArray darray1 = new JSONArray();
				darray1 = darray1.fromObject(plist1);// 将list转换成json数据
				jo.put("darray1", darray1);
				jo.put("flag", 0);
				PrintWriter dout1 = response.getWriter();
				dout1.print(jo.toString());
			}

			break;
		case "searchPost":
			//获取下拉条查询条件
			String postId = request.getParameter("post_id");
			String postName = request.getParameter("post_name");
			String postType = request.getParameter("post_type");
			
			List<Employee> pelist = new ArrayList<Employee>();
			
			Position post_emp = new Position();
			
			if(postId.length() != 0){
				post_emp.setPost_id(Integer.parseInt(postId));
			}
			
			post_emp.setPost_name(postName);
			post_emp.setPost_typename(postType);
			
			pelist = positionservice.getEmpByitem(post_emp);
			JSONArray darray4 = new JSONArray();
			darray4 = darray4.fromObject(pelist);// 将list转换成json数据
			PrintWriter dout4 = response.getWriter();
			dout4.print(darray4.toString());
			
/*			if(postId.length() != 0){
				pelist = positionservice.getEmpBypostid(Integer.parseInt(postId));
				JSONArray darray4 = new JSONArray();
				darray4 = darray4.fromObject(pelist);// 将list转换成json数据
				PrintWriter dout4 = response.getWriter();
				dout4.print(darray4.toString());
			}

				
			if(postName.length() != 0){
				pelist = positionservice.getEmpBypostname(postName);
				JSONArray darray2 = new JSONArray();
				darray2 = darray2.fromObject(pelist);// 将list转换成json数据
				PrintWriter dout2 = response.getWriter();
				dout2.print(darray2.toString());
			}

				
			if(postType.length() != 0){
				pelist = positionservice.getEmpByposttypename(postType);
				JSONArray darray3 = new JSONArray();
				darray3 = darray3.fromObject(pelist);// 将list转换成json数据
				PrintWriter dout3 = response.getWriter();
				dout3.print(darray3.toString());
			}*/
			break;
		default:
			break;
		}
	}
}
