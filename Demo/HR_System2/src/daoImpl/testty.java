package daoImpl;

import java.util.List;

import service.DeptService;
import service.PositionService;
import bean.Dept;
import bean.Position;
import bean.Employee;

public class testty {
	public static void main(String[] args) {
		DeptDaoImpl daoImpl = new DeptDaoImpl();
		//PositionDaoImpl daoImpl = new PositionDaoImpl();
		// 测试按部门id查询部门下员工信息
		/*List<Employee> list = daoImpl.getEmpBydeptid(1);
		 for (Employee employee : list) {
		 System.out.println(employee.getEmp_name());
		 }*/
		
		//测试 查询所有部门信息
		/*List<Dept> list = daoImpl.getAllDept();
		for (Dept dept : list) {
			System.out.println(dept.getDept_name());
		}*/
		
		//测试 按照部门ID查询部门信息
		/*Dept dept =daoImpl.getDeptByid(2);
		System.out.println(dept.getDept_name());*/
		
		//测试 按照部门名称查询部门信息
		/*Dept dept =daoImpl.getDeptByname("a");
		System.out.println(dept.getDept_name());
		System.out.println(dept.getDept_id());*/
		
		//添加一个部门，需要部门名称，部门的类型...
		/*Dept dept =new Dept();
		dept.setDept_name("gf");
		dept.setDept_typeid(23);
		dept.setDept_sup(1);
		dept.setDept_state(1);
		daoImpl.addDeptMessage(dept);*/
		
		//修改部门信息
		/*Dept dept = new Dept();		
		dept.setDept_name("haha");
		dept.setDept_typeid(2);
		dept.setDept_sup(2);
		dept.setDept_state(1);
		dept.setDept_id(3);
		daoImpl.modDeptMessage(dept);*/
		
		
		//删除部门信息
		/*Dept dept = new Dept();		
		int i = daoImpl.delDeptMessage(5);
		System.out.println(i);*/
		
		//查询岗位下员工
		/*List<Employee> list = daoImpl.getEmpBypostid(1);
		 for (Employee employee : list) {
		 System.out.println(employee.getEmp_name());}*/
		 
		//查询所有岗位
		 /*List<Position> list1 = daoImpl.getAllPosition();
			for (Position post : list1) {
				System.out.println(post.getPost_name());}*/
			
		
		//按照岗位id查岗位信息
			/*Position post =daoImpl.getPostByid(2);
			System.out.println(post.getPost_name());*/
			
		//按照岗位名称查岗位
			/*Position post =daoImpl.getPostByname("x");
			System.out.println(post.getPost_name());
			System.out.println(post.getPost_id());*/
		
		//添加岗位信息
		/*Position post =new Position();
		post.setPost_name("qwqw");
		post.setPost_typeid(3);
		post.setPost_count(100);
		post.setPost_state(1);
		int i=daoImpl.addPostMessage(post);*/
		
		//修改岗位信息
		/*Position post = new Position();		
		post.setPost_name("gg");
		post.setPost_typeid(2);
		post.setPost_count(100);
		post.setPost_state(1);
		post.setPost_id(2);
		daoImpl.modPostMessage(post);*/
		
		//删除岗位信息
		//Position post = new Position();
		//int i = daoImpl.delPostMessage(2);
		//System.out.println(i);
		
		//int i=daoImpl.getDeptTypeByname("公司");
		//System.out.println("i="+i);
		DeptService deptService=new DeptService();
		//System.out.println(deptService.getDeptnameByid(12));	
		System.out.println(deptService.delDeptMessage(13));
	}
}

