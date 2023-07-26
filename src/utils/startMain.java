package utils;

import java.io.IOException;
import java.sql.SQLException;

public class startMain {
	public static void main(String[] args) {
		samUtil su = new samUtil();
		DBUtil du = new DBUtil();

		try {
			su.chkFile();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1); // 오류 발생 시 프로그램 종료
		}

		try {
			su.exportSam(du.DBconn());
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1); // 오류 발생 시 프로그램 종료
		}
	}
}
