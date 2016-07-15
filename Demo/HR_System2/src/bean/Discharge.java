package bean;

/**
 * 离职信息表
 * 
 * @author Administrator
 *
 */
public class Discharge {
	private int dischg_id;// 编号
	private int dischg_empid;// 员工编号
	private String dischg_date;// 离职日期
	private String dischg_type;// 离职类型
	private String dischg_dinec;// 离职去向
	private int dischg_ifenter;// 去否进入人才库
	private int dischg_state;// 数据是否有效
	private int dischg_opempid;// 操作人
	private String dischg_optime;// 操作时间
	private String emp_sex;// 员工性别
	private String dept_name;// 部门名称
	private String post_name;// 岗位名称
	private String emp_name;// 员工姓名
	private String begintime;// 开始时间
	private String endtime;// 结束时间

	public Discharge() {
		dischg_id = 0;// 编号
		dischg_empid = 0;// 员工编号
		dischg_date = null;// 离职日期
		dischg_type = null;// 离职类型
		dischg_dinec = null;// 离职去向
		dischg_ifenter = 0;// 去否进入人才库
		dischg_state = 0;// 数据是否有效
		dischg_opempid = 0;// 操作人
		dischg_optime = null;// 操作时间
		emp_sex = null;// 员工性别
		dept_name = null;// 部门名称
		post_name = null;// 岗位名称
		emp_name = null;// 员工姓名
		begintime = null;// 开始时间
		endtime = null;// 结束时间
	}

	public int getDischg_id() {
		return dischg_id;
	}

	public void setDischg_id(int dischg_id) {
		this.dischg_id = dischg_id;
	}

	public int getDischg_empid() {
		return dischg_empid;
	}

	public void setDischg_empid(int dischg_empid) {
		this.dischg_empid = dischg_empid;
	}

	public String getDischg_date() {
		return dischg_date;
	}

	public void setDischg_date(String dischg_date) {
		this.dischg_date = dischg_date;
	}

	public String getDischg_dinec() {
		return dischg_dinec;
	}

	public void setDischg_dinec(String dischg_dinec) {
		this.dischg_dinec = dischg_dinec;
	}

	public int getDischg_ifenter() {
		return dischg_ifenter;
	}

	public void setDischg_ifenter(int dischg_ifenter) {
		this.dischg_ifenter = dischg_ifenter;
	}

	public int getDischg_state() {
		return dischg_state;
	}

	public void setDischg_state(int dischg_state) {
		this.dischg_state = dischg_state;
	}

	public int getDischg_opempid() {
		return dischg_opempid;
	}

	public void setDischg_opempid(int dischg_opempid) {
		this.dischg_opempid = dischg_opempid;
	}

	public String getDischg_optime() {
		return dischg_optime;
	}

	public void setDischg_optime(String dischg_optime) {
		this.dischg_optime = dischg_optime;
	}

	public String getEmp_sex() {
		return emp_sex;
	}

	public void setEmp_sex(String emp_sex) {
		this.emp_sex = emp_sex;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getPost_name() {
		return post_name;
	}

	public void setPost_name(String post_name) {
		this.post_name = post_name;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getDischg_type() {
		return dischg_type;
	}

	public void setDischg_type(String dischg_type) {
		this.dischg_type = dischg_type;
	}
	
}
