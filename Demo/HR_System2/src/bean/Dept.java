package bean;

/**
 * 部门信息表
 * 
 * @author Administrator
 *
 */
public class Dept {

	private int dept_id;// 部门编号
	private String dept_name;// 部门名称
	private int dept_typeid;// 部门类型
	private int dept_tel;// 部门电话
	private String dept_fax;// 传真
	private int dept_sup;// 上级部门编号
	private String dept_setdate;// 成立日期
	private String dept_description;// 描述
	private int dept_state;//部门数据是否有效
	private int dept_opempid;//操作人
	private int dept_optime;//操作时间
	private String dept_typename;//部门类型名称
	private String dept_supname;//上级部门名称

	public Dept()
	{
		dept_id = 0;// 部门编号
		dept_name = null;// 部门名称
		dept_typeid = 0;// 部门类型
		dept_tel = 0;// 部门电话
		dept_fax  = null;// 传真
		dept_sup  = 0;// 上级部门编号
		dept_setdate = null;// 成立日期
		dept_description = null;// 描述
		dept_state = 0;//部门数据是否有效
		dept_opempid = 0;//操作人
		dept_optime = 0;//操作时间
	
	}
	public int getDept_id() {
		return dept_id;
	}

	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public int getDept_typeid() {
		return dept_typeid;
	}

	public void setDept_typeid(int dept_typeid) {
		this.dept_typeid = dept_typeid;
	}

	public int getDept_tel() {
		return dept_tel;
	}

	public void setDept_tel(int dept_tel) {
		this.dept_tel = dept_tel;
	}

	public String getDept_fax() {
		return dept_fax;
	}

	public void setDept_fax(String dept_fax) {
		this.dept_fax = dept_fax;
	}

	public int getDept_sup() {
		return dept_sup;
	}

	public void setDept_sup(int dept_sup) {
		this.dept_sup = dept_sup;
	}

	public String getDept_setdate() {
		return dept_setdate;
	}

	public void setDept_setdate(String dept_setdate) {
		this.dept_setdate = dept_setdate;
	}

	public String getDept_description() {
		return dept_description;
	}

	public void setDept_description(String dept_description) {
		this.dept_description = dept_description;
	}

	public int getDept_state() {
		return dept_state;
	}

	public void setDept_state(int dept_state) {
		this.dept_state = dept_state;
	}

	public int getDept_opempid() {
		return dept_opempid;
	}

	public void setDept_opempid(int dept_opempid) {
		this.dept_opempid = dept_opempid;
	}

	public int getDept_optime() {
		return dept_optime;
	}

	public void setDept_optime(int dept_optime) {
		this.dept_optime = dept_optime;
	}
	public String getDept_typename() {
		return dept_typename;
	}
	public void setDept_typename(String dept_typename) {
		this.dept_typename = dept_typename;
	}
	public String getDept_supname() {
		return dept_supname;
	}
	public void setDept_supname(String dept_supname) {
		this.dept_supname = dept_supname;
	}

}
