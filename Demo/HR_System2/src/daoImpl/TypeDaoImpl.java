package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import util.DbUtil;
import bean.Type;
import dao.TypeDao;

public class TypeDaoImpl implements TypeDao {

	DbUtil db = new DbUtil();

	// 根据入职途径表名称 查询入职途径表id
	@Override
	public Type getWayByname(String way_name) {
		ResultSet rs = null;
		Type type = new Type();
		String sql = "select * from t_entryway where way_name=?";
		rs = db.exeQuery(sql, way_name);
		try {
			while (rs.next()) {
				type.setWay_id(rs.getInt("way_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return type;
	}

	// 根据岗位类型名称 查询岗位类型id
	@Override
	public Type getPosttypeByname(String posttype_name) {
		ResultSet rs = null;
		Type type = new Type();
		String sql = "select * from t_positiontype where posttype_name=?";
		rs = db.exeQuery(sql, posttype_name);
		try {
			while (rs.next()) {
				type.setPosttype_id(rs.getInt("posttype_id"));;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return type;
	}

	// 根据部门调转类型表名称 查询部门调转类型表id
	@Override
	public Type getDturntpByname(String dturntp_name) {
		ResultSet rs = null;
		Type type = new Type();
		String sql = "select * from t_dturntype where dturntp_name=?";
		rs = db.exeQuery(sql, dturntp_name);
		try {
			while (rs.next()) {
				type.setDturntp_id(rs.getInt("dturntp_id"));;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return type;
	}

	// 根据岗位调转信息表 查询岗位调转信息表id
	@Override
	public Type getPturnByname(String pturn_name) {

		ResultSet rs = null;
		Type type = new Type();
		String sql = "select * from t_pturntype where pturn_name=?";
		rs = db.exeQuery(sql, pturn_name);
		try {
			while (rs.next()) {
				type.setPturn_id(rs.getInt("pturn_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return type;
	}

	// 根据试用期状态表名称 查询试用期状态表id
	@Override
	public Type getStateByname(String state_name) {

		ResultSet rs = null;
		Type type = new Type();
		String sql = "select * from t_prostate where state_name=?";
		rs = db.exeQuery(sql, state_name);
		try {
			while (rs.next()) {
				type.setState_id(rs.getInt("state_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return type;
	}

}
