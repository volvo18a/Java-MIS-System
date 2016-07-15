package bean;

/**
 * 部门调转信息表
 * 
 * @author Administrator
 *
 */
public class Deptturn {

	private int dturn_id;// 编号
	private int dturn_empid;// 员工编号
	private int dturn_beforeid;// 调转前部门编号
	private int dturn_afterid;// 调转后部门编号
	// 新加
	private String dturn_typeName;// 调转类型名称

	private int dturn_typeid;// 调转类型编号
	private String dturn_reason;// 调转原因
	private String dturn_remark;// 备注
	private String dturn_data;// 调转日期
	private int dturn_state;// 数据是否有效
	private int dturn_opempid;// 操作人
	private String dturn_optime;// 操作时间
	private String emp_name;// 员工姓名
	private String emp_sex;
	private String dturn_beforename;// 调转前部门名称
	private String dturn_aftername;// 调转后部门名称
	private String begintime;
	private String endtime;

	public Deptturn() {

		dturn_typeName = null;

		dturn_id = 0;// 编号
		dturn_empid = 0;// 员工编号
		dturn_beforeid = 0;// 调转前部门编号
		dturn_afterid = 0;// 调转后部门编号
		dturn_typeid = 0;// 调转类型
		dturn_reason = null;// 调转原因
		dturn_remark = null;// 备注
		dturn_data = null;// 调转日期
		dturn_state = 0;// 数据是否有效
		dturn_opempid = 0;// 操作人
		dturn_optime = null;// 操作时间
		emp_name = null;// 员工姓名
		emp_sex = null;
		dturn_beforename = null;
		dturn_aftername = null;
		begintime = null;
		endtime = null;

	}

	public String getDturn_typeName() {
		return dturn_typeName;
	}

	public void setDturn_typeName(String dturn_typeName) {
		this.dturn_typeName = dturn_typeName;
	}

	public String getEmp_sex() {
		return emp_sex;
	}

	public void setEmp_sex(String emp_sex) {
		this.emp_sex = emp_sex;
	}

	public String getDturn_beforename() {
		return dturn_beforename;
	}

	public void setDturn_beforename(String dturn_beforename) {
		this.dturn_beforename = dturn_beforename;
	}

	public String getDturn_aftername() {
		return dturn_aftername;
	}

	public void setDturn_aftername(String dturn_aftername) {
		this.dturn_aftername = dturn_aftername;
	}

	public int getDturn_id() {
		return dturn_id;
	}

	public void setDturn_id(int dturn_id) {
		this.dturn_id = dturn_id;
	}

	public int getDturn_empid() {
		return dturn_empid;
	}

	public void setDturn_empid(int dturn_empid) {
		this.dturn_empid = dturn_empid;
	}

	public int getDturn_beforeid() {
		return dturn_beforeid;
	}

	public void setDturn_beforeid(int dturn_beforeid) {
		this.dturn_beforeid = dturn_beforeid;
	}

	public int getDturn_afterid() {
		return dturn_afterid;
	}

	public void setDturn_afterid(int dturn_afterid) {
		this.dturn_afterid = dturn_afterid;
	}

	public int getDturn_typeid() {
		return dturn_typeid;
	}

	public void setDturn_typeid(int dturn_typeid) {
		this.dturn_typeid = dturn_typeid;
	}

	public String getDturn_reason() {
		return dturn_reason;
	}

	public void setDturn_reason(String dturn_reason) {
		this.dturn_reason = dturn_reason;
	}

	public String getDturn_remark() {
		return dturn_remark;
	}

	public void setDturn_remark(String dturn_remark) {
		this.dturn_remark = dturn_remark;
	}

	public String getDturn_data() {
		return dturn_data;
	}

	public void setDturn_data(String dturn_data) {
		this.dturn_data = dturn_data;
	}

	public int getDturn_state() {
		return dturn_state;
	}

	public void setDturn_state(int dturn_state) {
		this.dturn_state = dturn_state;
	}

	public int getDturn_opempid() {
		return dturn_opempid;
	}

	public void setDturn_opempid(int dturn_opempid) {
		this.dturn_opempid = dturn_opempid;
	}

	public String getDturn_optime() {
		return dturn_optime;
	}

	public void setDturn_optime(String dturn_optime) {
		this.dturn_optime = dturn_optime;
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

}
