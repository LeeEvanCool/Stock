package pers.lxy.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * <p>TODO 进行数据库配置文件读取</p>
 *
 * @author  lxy
 * @date    2016年8月26日
 */
public class DBResourceUtils {
	
	private static Properties props = new Properties();

	static {
		try {
			props.load(DBResourceUtils.class.getResourceAsStream("/c3p0.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String get(String key) {
		return props.getProperty(key);
	}

    public static void setProps(Properties p){
        props = p;
    }
}
