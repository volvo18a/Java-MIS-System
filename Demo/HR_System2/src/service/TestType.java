package service;

import bean.Type;

public class TestType {
	public static void main(String[] args) {
		TypeService typeService = new TypeService();

		// 入职途径名称查询id
		// Type type = typeService.getWayByname("录入");
		// System.out.println(type.getWay_id());

		// 岗位调转名称查询id
		// Type type = typeService.getPturnByname("俩");
		// Type type = typeService.getPturnByname("个");
		// System.out.println(type.getPturn_id());

		// 部门调转名称查询id
		// Type type = typeService.getDturntpByname("fan");
		// Type type = typeService.getDturntpByname("ss");
		// System.out.println(type.getDturntp_id());

		// 岗位类型名称查询id
		// Type type = typeService.getPosttypeByname("飞");
		// System.out.println(type.getPosttype_id());

		// 入职途径
		 Type type = typeService.getStateByname("adsa");
		 System.out.println(type.getState_id());

	}
}
