package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import util.DbUtil;
import dao.LoginDao;

public class LoginDaoImpl implements LoginDao {
	
	DbUtil db=null;

	@Override
	public int AdminLogin(String log_name, String log_password) {
		db = new DbUtil();
		db.openConnection();
		ResultSet rs=null;
		int succ=0;
		String strSql="SELECT log_name,log_password FROM t_LOGIN WHERE log_name=? and log_password=?";
		 rs=db.exeQuery(strSql,log_name,log_password);
		
		try {
			if(rs.next()){
				succ=1;
			}
			db.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.closeConnection();
		return succ;
	}

}
