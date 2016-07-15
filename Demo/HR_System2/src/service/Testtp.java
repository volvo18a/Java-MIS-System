package service;

import java.util.List;
import bean.Position;

public class Testtp {
	public static void main(String[] args) {
		DeptService deptService = new DeptService();
		PositionService positionService = new PositionService();
		// 测试按部门id查询部门下员工信息
		// List<Employee> list = deptService.getEmpBydeptid(1);
		// for (Employee employee : list) {
		// System.out.println(employee.getEmp_name());
		// }

		// 测试 查询所有部门信息
		// List<Dept> list = deptService.getAllDept();
		// for (Dept dept : list) {
		// System.out.println(dept.getDept_name());
		// }

		// 测试 按照部门ID查询部门信息
		// Dept dept = deptService.getDeptByid(2);
		// System.out.println(dept.getDept_name());

		// 测试 按照部门名称查询部门信息
		// Dept dept = deptService.getDeptByname("a");
		// System.out.println(dept.getDept_name());
		// System.out.println(dept.getDept_id());

		// 查询岗位下员工
		// List<Employee> list = positionService.getEmpBypostid(1);
		// for (Employee employee : list) {
		// System.out.println(employee.getEmp_name());
		// }

		// 查询所有岗位
		// List<Position> list1 = positionService.getAllPosition();
		// for (Position post : list1) {
		// System.out.println(post.getPost_name());
		// }

		// 按照岗位id查岗位信息
		// Position post = positionService.getPostByid(2);
		// System.out.println(post.getPost_name());

		// 按照岗位名称查岗位
		// Position post = positionService.getPostByname("dd");
		// System.out.println(post.getPost_name());
		// System.out.println(post.getPost_id());
		
		//System.out.println(deptService.getDeptidByName("ss"));
		//System.out.println(positionService.getPostidByName("程序员"));
		//System.out.println(deptService.getDeptnameByid(20));
		//System.out.println(positionService.getPostnameByid(2));
	}
}
