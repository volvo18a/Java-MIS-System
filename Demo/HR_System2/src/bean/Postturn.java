package bean;

/**
 * 岗位调转信息表
 * 
 * @author Administrator
 *
 */
public class Postturn {

	private int pturn_id;// 编号
	private int pturn_empid;// 员工编号
	private int pturn_beforeid;// 调转前岗位编号
	private int pturn_afterid;// 调转后岗位编号
	private int pturn_typeid;// 调转类型编号
	//新加
	private String pturn_typeName;//调转岗位类型名称
	
	private String pturn_reason;// 调转原因
	private String pturn_remark;// 备注
	private String pturn_date;// 调转日期
	private int pturn_state;// 数据是否有效
	private int pturn_opempid;// 操作人
	private String pturn_optime;// 操作时间
	private String emp_name;//员工姓名
	private String emp_sex;//员工性别
	private String pturn_beforename; //调转前岗位名称
	private String pturn_aftername;//调转后岗位名称
	private String pturn_deptname;//
	private String begintime;//开始时间
	private String endtime;//结束时间

	public Postturn()
	{
		pturn_id = 0;// 编号
		pturn_empid = 0;// 员工编号
		pturn_beforeid = 0;// 调转前岗位编号
		pturn_afterid = 0;// 调转后岗位编号
		pturn_typeid  = 0;// 调转类型编号
		//新加
		pturn_typeName = null;//调转岗位类型名称
		
		pturn_reason = null;// 调转原因
		pturn_remark = null;// 备注
		pturn_date = null;// 调转日期
		pturn_state = 0;// 数据是否有效
		pturn_opempid = 0;// 操作人
		pturn_optime = null;// 操作时间
		emp_name = null;
		emp_sex = null;
		pturn_beforename = null;
		pturn_aftername = null;
		pturn_deptname = null;
		begintime = null;
		endtime = null;	
	}
	public String getPturn_typeName() {
		return pturn_typeName;
	}
	public void setPturn_typeName(String pturn_typeName) {
		this.pturn_typeName = pturn_typeName;
	}
	public String getEmp_sex() {
		return emp_sex;
	}

	public void setEmp_sex(String emp_sex) {
		this.emp_sex = emp_sex;
	}

	public String getPturn_beforename() {
		return pturn_beforename;
	}

	public void setPturn_beforename(String pturn_beforename) {
		this.pturn_beforename = pturn_beforename;
	}

	public String getPturn_aftername() {
		return pturn_aftername;
	}

	public void setPturn_aftername(String pturn_aftername) {
		this.pturn_aftername = pturn_aftername;
	}

	public String getPturn_deptname() {
		return pturn_deptname;
	}

	public void setPturn_deptname(String pturn_deptname) {
		this.pturn_deptname = pturn_deptname;
	}
	public int getPturn_id() {
		return pturn_id;
	}

	public void setPturn_id(int pturn_id) {
		this.pturn_id = pturn_id;
	}

	public int getPturn_empid() {
		return pturn_empid;
	}

	public void setPturn_empid(int pturn_empid) {
		this.pturn_empid = pturn_empid;
	}

	public int getPturn_beforeid() {
		return pturn_beforeid;
	}

	public void setPturn_beforeid(int pturn_beforeid) {
		this.pturn_beforeid = pturn_beforeid;
	}

	public int getPturn_afterid() {
		return pturn_afterid;
	}

	public void setPturn_afterid(int pturn_afterid) {
		this.pturn_afterid = pturn_afterid;
	}

	public int getPturn_typeid() {
		return pturn_typeid;
	}

	public void setPturn_typeid(int pturn_typeid) {
		this.pturn_typeid = pturn_typeid;
	}

	public String getPturn_reason() {
		return pturn_reason;
	}

	public void setPturn_reason(String pturn_reason) {
		this.pturn_reason = pturn_reason;
	}

	public String getPturn_remark() {
		return pturn_remark;
	}

	public void setPturn_remark(String pturn_remark) {
		this.pturn_remark = pturn_remark;
	}

	public String getPturn_date() {
		return pturn_date;
	}

	public void setPturn_date(String pturn_date) {
		this.pturn_date = pturn_date;
	}

	public int getPturn_state() {
		return pturn_state;
	}

	public void setPturn_state(int pturn_state) {
		this.pturn_state = pturn_state;
	}

	public int getPturn_opempid() {
		return pturn_opempid;
	}

	public void setPturn_opempid(int pturn_opempid) {
		this.pturn_opempid = pturn_opempid;
	}

	public String getPturn_optime() {
		return pturn_optime;
	}

	public void setPturn_optime(String pturn_optime) {
		this.pturn_optime = pturn_optime;
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

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

}
