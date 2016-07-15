package bean;

/**
 * 岗位信息表
 * 
 * @author Administrator
 *
 */
public class Position {

	private int post_id;// 岗位编号
	private String post_name;// 岗位名称
	private int post_typeid;// 岗位类型
	private int post_count;// 岗位编制
	private int post_state;//部门数据是否有效
	private int post_opempid;//操作人
	private String post_optime;//操作时间
	private String post_typename;
	
	public Position(){
		post_id = 0;
		post_name = null;
		post_typeid = 0;
		post_count = 0;
		post_state = 0;
		post_optime = null;
		post_opempid = 0;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getPost_name() {
		return post_name;
	}

	public void setPost_name(String post_name) {
		this.post_name = post_name;
	}

	public int getPost_typeid() {
		return post_typeid;
	}

	public void setPost_typeid(int post_typeid) {
		this.post_typeid = post_typeid;
	}

	public int getPost_count() {
		return post_count;
	}

	public void setPost_count(int post_count) {
		this.post_count = post_count;
	}

	public int getPost_state() {
		return post_state;
	}

	public void setPost_state(int post_state) {
		this.post_state = post_state;
	}

	public int getPost_opempid() {
		return post_opempid;
	}

	public void setPost_opempid(int post_opempid) {
		this.post_opempid = post_opempid;
	}

	public String getPost_optime() {
		return post_optime;
	}

	public void setPost_optime(String post_optime) {
		this.post_optime = post_optime;
	}

	public String getPost_typename() {
		return post_typename;
	}

	public void setPost_typename(String post_typename) {
		this.post_typename = post_typename;
	}

}
