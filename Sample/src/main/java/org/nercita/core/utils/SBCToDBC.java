package org.nercita.core.utils;
/**
 * 字符串全角/半角转换
 * @author zhangyf
 *
 */
public class SBCToDBC {
    /**
     * 全角转半角
     * @param input 
     * @return 
     */
    public static String ToDBC(String input) {
    	char c[] = input.toCharArray();
    	for (int i = 0; i < c.length; i++) {
    		if (c[i] == '\u3000') {
    			c[i] = ' ';
    		} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
    			c[i] = (char) (c[i] - 65248);
    		}
    	}
    	String returnString = new String(c);
    	return returnString;
    }
	/**
     * 半角转全角
     * @param input 
     * @return 
     */
    public static String ToSBC(String input) {
    	char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
          if (c[i] == ' ') {
            c[i] = '\u3000';
          } else if (c[i] < '\177') {
            c[i] = (char) (c[i] + 65248);

          }
        }
        return new String(c);
    }    
}
