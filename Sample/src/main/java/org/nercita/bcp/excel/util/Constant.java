package org.nercita.bcp.excel.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Random;

public class Constant {
	/**
	 * 导入文件允许的格式
	 */
	public static final String[] allowedContentTypes = {
			"application/vnd.ms-excel",
			"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" };

	/**
	 * 获得当前工程的路径
	 * @return
	 */
	public String getProjectPath() {
		String nowPath = "";
		String xmlFile = this.getClass().getClassLoader().getResource("applicationContext.xml").getFile(); 
		String classPathName = "";
		try {
			classPathName =  URLDecoder.decode(xmlFile,"utf-8");
			int endingIndex = classPathName.length()-"applicationContext.xml".length(); 
			if (endingIndex > 17) {
				nowPath = classPathName.substring(0, endingIndex-17); 
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		return nowPath;
	}

	/**
	 * 判断数组是否含有某个字符
	 * @param substring
	 * @param source
	 * @return
	 */
	public static boolean isIn(String substring, String[] source) {
		if (source == null || source.length == 0) {
			return false;
		}
		for (int i = 0; i < source.length; i++) {
			String aSource = source[i];
			if (aSource.equals(substring)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 获取随机数
	 * @param length 随机数长度
	 * @return
	 */
	public static String getRandom(int length ) {
		String allChar = "0123456789abcdefghijklmnopqrstuvwxyz";
		String letterChar = "abcdefghijklmnopqrstuvwxyz";
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
		sb.append(allChar.charAt(random.nextInt(letterChar.length())));
		}
		return sb.toString();
	}
	
	/**
	 *新建文件，并赋值
	 * @param value 随机数长度
	 * @return
	 */
	@SuppressWarnings("finally")
	public static boolean createFile(File file, String value ) {
		boolean result = false;
		BufferedWriter out = null;  
		try {
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));  
			out.write(value);  
			out.close();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			return result;
		}
	}
	
	/**  
	  * 判断字符串是否是整数  
	  */  
	 public static boolean isInteger(String value) {   
		 try {   
	         Integer.parseInt(value);   
	         return true;   
	     } catch (NumberFormatException e) {   
	         return false;   
	     }   
	}   

	public static void main(String[] args) {
		Constant constant = new Constant();
		System.out.println("=="+constant.getProjectPath());
	}
}
