package bean;

/**
 * 员工信息表
 * 
 * @author Administrator
 *
 */
public class Employee {
	private int emp_id;// 入职编号
	private String emp_name;// 姓名
	private String emp_birth;// 出生日期
	private String emp_idcard;// 身份证
	private int emp_deptid;// 所在部门编号
	private int emp_postid;// 所在岗位编号
	private String emp_sex;// 性别
	private String emp_entrydate;// 入职日期
	private String emp_form;// 用工形式
	private String emp_partidate;// 参加工作日期
	private String emp_source;// 入职来源
	private String emp_dgree;// 最高学历
	private int emp_ifprobation;// 是否有试用期
	private int emp_ifdischarge;// 是否离职(员工数据是否有效)
	private int emp_wayid;// 入职途径
	private int emp_opempid;// 操作人
	private String emp_optime;// 操作时间
	private String dept_name;//部门名称
	private String post_name;//岗位名称
	private String emp_deptname;//new
	private String emp_postname;//new

	public String getEmp_deptname() {
		return emp_deptname;
	}

	public void setEmp_deptname(String emp_deptname) {
		this.emp_deptname = emp_deptname;
	}

	public String getEmp_postname() {
		return emp_postname;
	}

	public void setEmp_postname(String emp_postname) {
		this.emp_postname = emp_postname;
	}

	public Employee(){
		emp_id = 0;// 入职编号
		emp_name = null;// 姓名
		emp_birth = null;// 出生日期
		emp_idcard = null;// 身份证
		emp_deptid = 0;// 所在部门编号
		emp_postid = 0;// 所在岗位编号
		emp_sex = null;// 性别
		emp_entrydate = null;// 入职日期
		emp_form = null;// 用工形式
		emp_partidate = null;// 参加工作日期
		emp_source = null;// 入职来源
		emp_dgree = null;// 最高学历
		emp_ifprobation = 0;// 是否有试用期
		emp_ifdischarge = 0;// 是否离职(员工数据是否有效)
		emp_wayid = 0;// 入职途径
		emp_opempid = 0;// 操作人
		emp_optime = null;// 操作时间
		dept_name = null;
		post_name = null;
	}
	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmp_birth() {
		return emp_birth;
	}

	public void setEmp_birth(String emp_birth) {
		this.emp_birth = emp_birth;
	}

	public String getEmp_idcard() {
		return emp_idcard;
	}

	public void setEmp_idcard(String emp_idcard) {
		this.emp_idcard = emp_idcard;
	}

	public int getEmp_deptid() {
		return emp_deptid;
	}

	public void setEmp_deptid(int emp_deptid) {
		this.emp_deptid = emp_deptid;
	}

	public int getEmp_postid() {
		return emp_postid;
	}

	public void setEmp_postid(int emp_postid) {
		this.emp_postid = emp_postid;
	}

	public String getEmp_sex() {
		return emp_sex;
	}

	public void setEmp_sex(String emp_sex) {
		this.emp_sex = emp_sex;
	}

	public String getEmp_entrydate() {
		return emp_entrydate;
	}

	public void setEmp_entrydate(String emp_entrydate) {
		this.emp_entrydate = emp_entrydate;
	}

	public String getEmp_form() {
		return emp_form;
	}

	public void setEmp_form(String emp_form) {
		this.emp_form = emp_form;
	}

	public String getEmp_partidate() {
		return emp_partidate;
	}

	public void setEmp_partidate(String emp_partidate) {
		this.emp_partidate = emp_partidate;
	}

	public String getEmp_source() {
		return emp_source;
	}

	public void setEmp_source(String emp_source) {
		this.emp_source = emp_source;
	}

	public String getEmp_dgree() {
		return emp_dgree;
	}

	public void setEmp_dgree(String emp_dgree) {
		this.emp_dgree = emp_dgree;
	}

	public int getEmp_ifprobation() {
		return emp_ifprobation;
	}

	public void setEmp_ifprobation(int emp_ifprobation) {
		this.emp_ifprobation = emp_ifprobation;
	}

	public int getEmp_ifdischarge() {
		return emp_ifdischarge;
	}

	public void setEmp_ifdischarge(int emp_ifdischarge) {
		this.emp_ifdischarge = emp_ifdischarge;
	}

	public int getEmp_wayid() {
		return emp_wayid;
	}

	public void setEmp_wayid(int emp_wayid) {
		this.emp_wayid = emp_wayid;
	}

	public int getEmp_opempid() {
		return emp_opempid;
	}

	public void setEmp_opempid(int emp_opempid) {
		this.emp_opempid = emp_opempid;
	}

	public String getEmp_optime() {
		return emp_optime;
	}

	public void setEmp_optime(String emp_optime) {
		this.emp_optime = emp_optime;
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

}
