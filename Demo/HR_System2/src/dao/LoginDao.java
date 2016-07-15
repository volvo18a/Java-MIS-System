package dao;

public interface LoginDao {
	
	/**
	 * 实现登录的方法
	 * @param log_name
	 * @param log_password
	 * @return
	 */

	public int AdminLogin(String log_name,String log_password);
}
