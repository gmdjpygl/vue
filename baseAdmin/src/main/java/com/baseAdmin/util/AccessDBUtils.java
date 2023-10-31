package com.baseAdmin.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class AccessDBUtils {
	private static final String dbURL = "jdbc:ucanaccess://"
			+ ApplicationYmlUtil.getValue("access.address");
	// 单利模式 --懒汉式(双重锁定)保证线程的安全性
	public static AccessDBUtils db = null;

	public static AccessDBUtils getInstance() {
		if (db == null) {
			synchronized (AccessDBUtils.class) {
				if (db == null) {
					db = new AccessDBUtils();
				}
			}
		}
		return db;
	}

	/*
	 * 加载驱动
	 */
	static {
		try {
			// Step 1: Loading or registering Oracle JDBC driver class
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} catch (ClassNotFoundException cnfex) {
			System.out.println("Problem in loading or registering MS Access JDBC driver");
			cnfex.printStackTrace();
		}
	}

	// 建立连接
	public static Connection getConn() {
		try {
			// Step 2: Opening database connection
			// Step 2.A: Create and get connection using DriverManager class
			return DriverManager.getConnection(dbURL);
		} catch (Exception e) {
			System.out.println("AccessDB connection fail");
			e.printStackTrace();
		}
		return null;

	}

	// 关闭资源

	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();// 这里出现异常了，rs关闭了吗？，如果没有怎么解决,ps , con也是一样的。
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (con != null)
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}
	}

	/**
	 * 查询
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> query(String sql) {
		System.out.println("-------------------------query-------------------------------------");
		Connection con = null;
		List<Map<String, Object>> rstList = new ArrayList<Map<String, Object>>();
		Statement pst = null;
		ResultSet rs = null;
		try {
			// 载入驱动
			con = getConn();
			// 创建数据库操作对象
			pst = con.createStatement();
			// 查询
			rs = pst.executeQuery(sql);
			ResultSetMetaData rst = rs.getMetaData();
			int column = rst.getColumnCount();
			while (rs.next()) {
				// 创建Map容器存取每一列对应的值
				Map<String, Object> m = new HashMap<String, Object>();
				for (int i = 1; i <= column; i++) {
					String key = rst.getColumnName(i);
					if (key != null) {
						m.put(key.toUpperCase(), rs.getObject(i));
					}
				}
				// 将Map容器放入List容器中
				rstList.add(m);
				//m = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs, pst, con);
		}
		System.out.println("----------------------query end ---------------------");

		return rstList;
	}

	/**
	 * 关闭
	 * @param rs
	 * @param pst
	 * @param con
	 */
	public static void close(ResultSet rs, Statement pst, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pst = null;
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			con = null;
		}
	}

}
