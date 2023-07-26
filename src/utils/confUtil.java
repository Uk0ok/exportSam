package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class confUtil {
	public static String DB_DRIVER = null;
	public static String DB_URL = null;
	public static String DB_ID = null;
	public static String DB_PWD = null;

	public static String SAM_PATH = null;
	public static String SELECT_QUERY = null;
	public static String DELIMITER = null;

	static {
		Properties DBconf = new Properties();
		Properties conf = new Properties();

		try {
			DBconf.load(new FileInputStream("conf/DBconfig.properties"));
			conf.load(new FileInputStream("conf/conf.properties"));
		} catch (IOException e) {
			throw new RuntimeException("properties 파일 로딩 중 오류가 발생했습니다.", e);
			/*
			 * static 초기화 블록 안에서 발생하는 예외를 RuntimeException으로 래핑하여 바깥으로 던져주면, 
			 * 이를 사용하는 코드에서 예외 처리를 하거나 예외를 던져주어야 합니다. 이렇게 함으로써 초기화 과정에서 발생하는 오류를 쉽게 파악할 수 있습니다.
			 */
			// e.printStackTrace();
		}

		DB_DRIVER = DBconf.getProperty("DBDRIVER");
		DB_URL = DBconf.getProperty("DBURL");
		DB_ID = DBconf.getProperty("DBID");
		DB_PWD = DBconf.getProperty("DBPWD");

		SAM_PATH = conf.getProperty("SAMPATH");
		SELECT_QUERY = conf.getProperty("SELECTQUERY");
		DELIMITER = conf.getProperty("DELIMITER");
	}
}
