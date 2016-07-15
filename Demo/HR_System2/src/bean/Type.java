package bean;

public class Type {

	private int posttype_id;//岗位类型表
	private String posttype_name;
	
	private int way_id;//入职途径表
	private String way_name;
	
	private int dturntp_id;//部门调转类型表
	private String dturntp_name;
	
	private int pturn_id;//岗位调转信息表
	private String pturn_name;
	
	private int state_id;//试用期状态表
	private String state_name;
	public int getPosttype_id() {
		return posttype_id;
	}
	public void setPosttype_id(int posttype_id) {
		this.posttype_id = posttype_id;
	}
	public String getPosttype_name() {
		return posttype_name;
	}
	public void setPosttype_name(String posttype_name) {
		this.posttype_name = posttype_name;
	}
	public int getWay_id() {
		return way_id;
	}
	public void setWay_id(int way_id) {
		this.way_id = way_id;
	}
	public String getWay_name() {
		return way_name;
	}
	public void setWay_name(String way_name) {
		this.way_name = way_name;
	}
	public int getDturntp_id() {
		return dturntp_id;
	}
	public void setDturntp_id(int dturntp_id) {
		this.dturntp_id = dturntp_id;
	}
	public String getDturntp_name() {
		return dturntp_name;
	}
	public void setDturntp_name(String dturntp_name) {
		this.dturntp_name = dturntp_name;
	}
	public int getPturn_id() {
		return pturn_id;
	}
	public void setPturn_id(int pturn_id) {
		this.pturn_id = pturn_id;
	}
	public String getPturn_name() {
		return pturn_name;
	}
	public void setPturn_name(String pturn_name) {
		this.pturn_name = pturn_name;
	}
	public int getState_id() {
		return state_id;
	}
	public void setState_id(int state_id) {
		this.state_id = state_id;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	
	
}
