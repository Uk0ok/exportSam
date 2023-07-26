package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class samUtil {
	// 파일 존재여부 확인
	public void chkFile() throws IOException {
		File samFile = new File(confUtil.SAM_PATH);

		if (!samFile.exists() && !samFile.createNewFile()) {
			throw new IOException("파일 생성에 실패하였습니다.");
		}
	}
	
	// DB에서 ASYSCONTENTELEMENT의 값 test.sam 파일에  write 하는 프로그램
	public void exportSam(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			pstmt = conn.prepareStatement(confUtil.SELECT_QUERY);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount(); // 컬럼 수 판별

			try {
				fw = new FileWriter(confUtil.SAM_PATH);
				bw = new BufferedWriter(fw);
				// fileWriter와 bufferedWriter랑 같이 쓰면 효율이 좋다.

				while (rs.next()) {
					for (int i = 1; i <= cols; i++) {
						if (i == cols) {
							if (rs.getString(i) == null) {
								bw.write("NULL");
							} else {
								bw.write(rs.getString(i).trim());
							}
						} else {
							if (rs.getString(i) == null) {
								bw.write("NULL");
								bw.write(confUtil.DELIMITER);
							} else {
								bw.write(rs.getString(i).trim());
								bw.write(confUtil.DELIMITER);
							}
						}
					}
					bw.write("\n");
					bw.flush();
				}
			} finally {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
