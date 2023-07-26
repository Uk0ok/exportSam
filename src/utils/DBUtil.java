package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	/*
	 * DB Connection
	 */
	public Connection DBconn() throws SQLException {
		Connection conn = null;

		try {
			Class.forName(confUtil.DB_DRIVER);
			conn = DriverManager.getConnection(confUtil.DB_URL, confUtil.DB_ID, confUtil.DB_PWD);
			conn.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("JDBC 드라이버를 찾을 수 없습니다.", e);
			// e.printStackTrace();
		} catch (SQLException e) {
			throw new SQLException("데이터베이스 연결에 실패했습니다.", e);
		}
		return conn;
	}
}
