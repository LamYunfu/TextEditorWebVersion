package javaExp10.Service;

//提供一些工具
public class Tools {
	/**
	 * 通过文件或者文件夹的地址获得用户的账号
	 * @param path
	 * @return
	 */
	public static String getUserID(String path) {
		String[] arr = path.split("/");
		return arr[0];
	}
}
