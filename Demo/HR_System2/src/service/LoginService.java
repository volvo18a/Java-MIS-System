package service;

import dao.LoginDao;
import dao.PositionDao;
import daoImpl.LoginDaoImpl;

public class LoginService {
	LoginDao loginDao = new LoginDaoImpl();

	/** 登录
	 * 
	 * @param log_name
	 * @param log_password
	 * @return
	 */
	public int AdminLogin(String log_name, String log_password) {

		return loginDao.AdminLogin(log_name, log_password);
	}
}
