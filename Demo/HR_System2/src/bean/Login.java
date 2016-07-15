package bean;

public class Login {
	
	private int log_id;//登陆者id（员工id）
	private String log_name;//登录名
	private String log_password;//登录密码
	
	public int getLog_Id() {
		return log_id;
	}
	public void setId(int log_id) {
		this.log_id = log_id;
	}
	public String getLog_name() {
		return log_name;
	}
	public void setName(String log_name) {
		this.log_name = log_name;
	}
	public String getLog_password() {
		return log_password;
	}
	public void setType(String log_password) {
		this.log_password = log_password;
	}

}
