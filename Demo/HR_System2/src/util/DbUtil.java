package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//未解决数据库关闭问题
public class DbUtil {
	// 连接
	Connection conn;
	// 预编译命令对象
	PreparedStatement pst;
	// 结果集
	ResultSet rs;

	/***
	 * 获得数据库连接
	 */
	public void openConnection() {
		try {
			// 连接驱动
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/hr_system", "root", "111111");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 执行查询的方法
	/***
	 * 执行Select语句
	 */
	// 使用Object...obj可以不用考虑传进来的参数个数
	public ResultSet exeQuery(String strSql, Object... obj) {
		// 打开连接
		try {
			pst = conn.prepareStatement(strSql);
			for (int i = 0; i < obj.length; i++) {
				pst.setObject((i + 1), obj[i]);
			}
			rs = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		return rs;

	}

	// 执行更新、添加、删除的方法
	/***
	 * 执行update,insert,delete语句
	 */
	public int exeMod(String strSql, Object... obj) {

		int rows = 0;
		try {
			pst = conn.prepareStatement(strSql);
			for (int i = 0; i < obj.length; i++) {
				pst.setObject((i + 1), obj[i]);
			}
			rows = pst.executeUpdate();

		} catch (Exception e) {
		} finally {

		}
		return rows;
	}

	/***
	 * 关闭数据库连接
	 */
	public void closeConnection() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {

		} finally {
		}
	}
}
