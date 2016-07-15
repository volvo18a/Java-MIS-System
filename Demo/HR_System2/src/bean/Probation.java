package bean;

/**
 * 试用期信息表
 * 
 * @author Administrator
 *
 */
public class Probation {

	private int proba_id;// 编号
	private int emp_id;// 入职编号
	private String emp_name;// 姓名
	private int emp_deptid;// 所在部门编号
	private int emp_postid;// 所在岗位编号
	private String proba_begindate;// 试用期开始时间
	private String proba_enddate;// 试用期结束时间
	private int prroba_stateid;// 试用期状态
	private String proba_dealdate;// 处理时间（修改试用期状态）
	private String begintime;// 开始时间
	private String endtime;// 结束时间
	private String proba_comment;// 期末评语
	private String state_name;//试用期状态名称
	private String dept_name;//部门名称
	private String post_name;//岗位名称

	// 新加
	private String proba_stateName;

	public Probation() {
		proba_stateName = null;

		proba_id = 0;// 编号
		emp_id = 0;// 入职编号
		emp_name = null;// 姓名
		emp_deptid = 0;// 所在部门编号
		emp_postid = 0;// 所在岗位编号
		proba_begindate = null;// 试用期开始时间
		proba_enddate = null;// 试用期结束时间
		prroba_stateid = 0;// 试用期状态
		proba_dealdate = null;// 处理时间（修改试用期状态）
		begintime = null;// 开始时间
		endtime = null;// 结束时间
		proba_comment = null;// 期末评语
		state_name = null;
		dept_name = null;
		post_name = null;

	}

	public String getProba_stateName() {
		return proba_stateName;
	}

	public void setProba_stateName(String proba_stateName) {
		this.proba_stateName = proba_stateName;
	}

	public String getProba_comment() {
		return proba_comment;
	}

	public void setProba_comment(String sremark) {
		this.proba_comment = sremark;
	}

	public int getProba_id() {
		return proba_id;
	}

	public void setProba_id(int proba_id) {
		this.proba_id = proba_id;
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

	public String getProba_begindate() {
		return proba_begindate;
	}

	public void setProba_begindate(String proba_begindate) {
		this.proba_begindate = proba_begindate;
	}

	public String getProba_enddate() {
		return proba_enddate;
	}

	public void setProba_enddate(String proba_enddate) {
		this.proba_enddate = proba_enddate;
	}

	public int getPrroba_stateid() {
		return prroba_stateid;
	}

	public void setPrroba_stateid(int prroba_stateid) {
		this.prroba_stateid = prroba_stateid;
	}

	public String getProba_dealdate() {
		return proba_dealdate;
	}

	public void setProba_dealdate(String proba_dealdate) {
		this.proba_dealdate = proba_dealdate;
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

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
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
