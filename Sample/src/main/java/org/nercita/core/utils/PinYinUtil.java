package org.nercita.core.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 拼音操作类
 * @author wangzz
 */
public class PinYinUtil {
	
	/**
	 * 获取汉字串拼音首字母，英文字符不变
	 * 
	 * @param chinese
	 *            汉字串
	 * @return 汉语拼音首字母
	 */
	public static String cn2FirstSpell(String chinese) {
//		chinese = SBCToDBC.ToDBC(chinese);
		StringBuffer pybf = new StringBuffer();
		char[] arr = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 128) {
				try {
					String[] _t = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
					if (_t != null) {
						pybf.append(_t[0].charAt(0));
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pybf.append(arr[i]);
			}
		}
		return pybf.toString().replaceAll("\\W", "").trim();
	}

	/**
	 * 获取汉字串拼音，英文字符不变
	 * 
	 * @param chinese
	 *            汉字串
	 * @return 汉语拼音
	 */
	public static String cn2Spell(String chinese) {
		chinese = SBCToDBC.ToDBC(chinese);
		StringBuffer pybf = new StringBuffer();
		char[] arr = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		String[] hanPinYins = null;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 128) {
				try {
					hanPinYins = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
					if(hanPinYins != null){
						pybf.append(hanPinYins[0]);
					}
//					pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pybf.append(arr[i]);
			}
		}
		return pybf.toString();
	}
     /**
      * 获取汉字串拼音大写，英文字符不变
      * @param hanzhis
      * @return
      */
	public static String toPinYin(String hanzhis) {
		hanzhis = SBCToDBC.ToDBC(hanzhis);
		CharSequence s = hanzhis;

		char[] hanzhi = new char[s.length()];
		for (int i = 0; i < s.length(); i++) {
			hanzhi[i] = s.charAt(i);
		}

		char[] t1 = hanzhi;
		String[] t2 = new String[s.length()];
		/** */
		/**
		 * 设置输出格式
		 */
		net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);

		int t0 = t1.length;
		String py = "";
		try {
			for (int i = 0; i < t0; i++) {
				t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
				py = py + t2[0].toString();
			}
		} catch (BadHanyuPinyinOutputFormatCombination e1) {
			e1.printStackTrace();
		}

		return py.trim();
	}
}
