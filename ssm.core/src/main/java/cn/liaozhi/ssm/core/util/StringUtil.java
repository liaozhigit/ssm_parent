package cn.liaozhi.ssm.core.util;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;


/**
 * 字符串辅助类
 * 
 * @author l54883
 * @since 2011-8-25
 */
public final class StringUtil {

	private StringUtil() {
	}

	/**
	 * 空字符串
	 */
	public static final String EMPTY = "";
	
	/**
	 * 
	 */
	private final static String[] EMPTY_STRINGS = new String[0];

	/**
	 * 判断字符串是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNullOrEmpty(String s) {
		return s == null || s.length() == 0;
	}
	
	/** 
	 * 判断多个字符串是否为空
	 * @author l54883
	 * @since 2012-8-30
	 * @param ss 多个String
	 * @return
	 */
	public static boolean isNullOrEmpty(String... ss) {
		for(String s : ss ){
			if(s == null || s.length() ==0){
				return true;
			}
		}
		return false;
	}

	/**
	 * 将字符串处理为数字
	 * 
	 * @author cKF49709
	 * @since 2011-9-15
	 * @param str
	 * @param delimiters
	 * @return
	 */
	public static String[] tokenizeToStringArray(String str, String delimiters) {
		return tokenizeToStringArray(str, delimiters, true, true);
	}

	/**
	 * 将字符串处理为数字
	 * 
	 * @author cKF49709
	 * @since 2011-9-15
	 * @param str
	 * @param delimiters
	 * @param trimTokens
	 * @param ignoreEmptyTokens
	 * @return
	 */
	public static String[] tokenizeToStringArray(String str, String delimiters,
			boolean trimTokens, boolean ignoreEmptyTokens) {
		if (str == null) {
			return null;
		}

		StringTokenizer st = new StringTokenizer(str, delimiters);
		List<String> tokens = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			processOneToken(st.nextToken(), trimTokens, ignoreEmptyTokens,
					tokens);
		}

		return toStringArray(tokens);
	}

	/**
	 * 处理单一Token
	 * 
	 * @author cKF49709
	 * @since 2011-10-31
	 * @param token
	 * @param trimTokens
	 * @param ignoreEmptyTokens
	 * @param tokens
	 */
	private static void processOneToken(String token, boolean trimTokens,
			boolean ignoreEmptyTokens, List<String> tokens) {
		if (trimTokens) {
			token = token.trim();
		}
		if (!ignoreEmptyTokens || token.length() > 0) {
			tokens.add(token);
		}
	}

	/**
	 * 将一个字符串集合转换成字符串数组。
	 * 
	 * @author cKF49709
	 * @since 2011-9-15
	 * @param collection
	 * @return
	 */
	public static String[] toStringArray(Collection<String> collection) {
		if (collection == null) {
			return null;
		}

		return collection.toArray(new String[collection.size()]);
	}

	/**
	 * 将集合Join成字符串
	 * 
	 * @author l54883
	 * @since 2011-11-7
	 * @param collection
	 * @param separator
	 * @return
	 */
	public static String join(Collection<?> collection, String separator) {
		return StringUtils.collectionToDelimitedString(collection, separator);
	}

	/**
	 * 将数组Join成一个字符串
	 * 
	 * @author l54883
	 * @since 2011-11-7
	 * @param array
	 * @param separator
	 * @return
	 */
	public static String join(Object[] array, String separator) {
		return StringUtils.arrayToDelimitedString(array, separator);
	}

	/**
	 * 将String中的所有regex匹配的字串全部替换掉
	 * 
	 * @param string
	 *            代替换的字符串
	 * @param regex
	 *            替换查找的正则表达式
	 * @param callback
	 *            替换函数
	 * @return 替换后的文本
	 * @author z00199787
	 * @since 2011-12-14
	 */
	public static String replaceAll(String string, String regex,
			IReplaceCallback callback) {
		return replaceAll(string, Pattern.compile(regex), callback);
	}

	/**
	 * 将String中的所有pattern匹配的字串替换掉
	 * 
	 * @param string
	 *            代替换的字符串
	 * @param pattern
	 *            替换查找的正则表达式对象
	 * @param callback
	 *            替换函数
	 * @return 替换后的文本
	 * @author z00199787
	 * @since 2011-12-14
	 */
	public static String replaceAll(String string, Pattern pattern,
			IReplaceCallback callback) {
		if (string == null) {
			return null;
		}
		Matcher m = pattern.matcher(string);
		if (m.find()) {
			StringBuffer sb = new StringBuffer();
			int index = 0;
			while (true) {
				String repalStr = replaceAll2Dollar(callback.replace(m.group(0), index++, m));
				m.appendReplacement(sb, repalStr);
				if (!m.find()) {
					break;
				}
			}
			m.appendTail(sb);
			return sb.toString();
		}
		return string;
	}

	/**
	 * 
	 * @param string
	 * @param pattern
	 * @param repalStr
	 * @return
	 * Created：2014-3-14
	 * @author:hKF16418
	 */
	public static String replaceAll(String string, Pattern pattern,String repalStr) {
		if (string == null) {
			return null;
		}
		Matcher m = pattern.matcher(string);
		if (m.find()) {
			StringBuffer sb = new StringBuffer();
			while (true) {
				m.appendReplacement(sb, repalStr);
				if (!m.find()) {
					break;
				}
			}
			m.appendTail(sb);
			return sb.toString();
		}
		return string;
	}
	
	 public static String replaceAll2Dollar(String str){
		 if(isNullOrEmpty(str)){
			 return str;
		 }
		String[] tempStr = str.split("\\$");
		StringBuffer sb = new StringBuffer();
		for(int index =0;index<tempStr.length;index++){
			sb.append(tempStr[index]);
			if(index<tempStr.length-1){
				sb.append("\\$");
			}
		}
		if(str.endsWith("$")){
			sb.append("\\$");
		}
		return sb.toString();
	 }
	/**
	 * 将String中的regex第一次匹配的字串替换掉
	 * 
	 * @param string
	 *            代替换的字符串
	 * @param regex
	 *            替换查找的正则表达式
	 * @param callback
	 *            替换函数
	 * @return 替换后的文本
	 * @author z00199787
	 * @since 2011-12-14
	 */
	public static String replaceFirst(String string, String regex,
			IReplaceCallback callback) {
		return replaceFirst(string, Pattern.compile(regex), callback);
	}

	/**
	 * 将String中的pattern第一次匹配的字串替换掉
	 * 
	 * @param string
	 *            代替换的字符串
	 * @param pattern
	 *            替换查找的正则表达式对象
	 * @param callback
	 *            替换函数
	 * @return 替换后的文本
	 * @author z00199787
	 * @since 2011-12-14
	 */
	public static String replaceFirst(String string, Pattern pattern,
			IReplaceCallback callback) {
		if (string == null) {
			return null;
		}
		Matcher m = pattern.matcher(string);
		StringBuffer sb = new StringBuffer();
		if (m.find()) {
			m.appendReplacement(sb, callback.replace(m.group(0), 0, m));
		}
		m.appendTail(sb);
		return sb.toString();
	}
	
	/**
	 * 判断是否存在当前表达式的字符串
	 * @param string
	 * @param pattern
	 * @return
	 * Created：2013-3-28
	 * @author:hKF16418
	 */
	public static boolean isExistStr(String string, Pattern pattern){
		if (isNullOrEmpty(string)) {
			return false;
		}
		Matcher m = pattern.matcher(string);
		return m.find();
	}
	
	/**
	 * 判断是否存在当前表达式的字符串  如果存在返回字符串
	 * @param string
	 * @param pattern
	 * @return
	 * Created：2013-3-28
	 * @author:hKF16418
	 */
	public static String returnExistStr(String string, Pattern pattern){
		if (isNullOrEmpty(string)) {
			return null;
		}
		Matcher m = pattern.matcher(string);
		if (m.find()) {
			return m.group(1);
		}
		return null;
	}
	/**
	 * 将字符串转换为日期
	 * 
	 * @author f00196405
	 * @since 2011-12-15
	 * @param string
	 * @return date
	 * @throws ParseException 
	 */
    public static Date stringToDate(String string) throws ParseException
    {
    	Date date = null;
    	if(null==string){
    		return date;
    	}
    	SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = formate.parse(string);
    	return date;
    }
    
    /** 
     * 填充左侧至指定长度
     * @author l54883
     * @since 2012-3-7
     * @param oldString
     * @param len
     * @param padChar
     * @return
     */
    public static String padLeft(String oldString,int len,char padChar){
    	if(oldString == null || oldString.length() >= len) {
    		return oldString;
    	}
    	StringBuffer sb = new StringBuffer(oldString);
    	
    	while(sb.length() < len){
    		sb.insert(0,padChar);
    	}
    	return sb.toString();
    }
    
    /** 
     * 填充右侧至指定长度
     * @author l54883
     * @since 2012-3-7
     * @param oldString
     * @param len
     * @param padChar
     * @return
     */
    public static String padRight(String oldString,int len,char padChar){
    	if(oldString == null || oldString.length() >= len) {
    		return oldString;
    	}
    	StringBuffer sb = new StringBuffer(oldString);
    	
    	while(sb.length() < len){
    		sb.append(padChar);
    	}
    	return sb.toString();
    }
    
    /**
	 * 返回字符串在UTF-8编码时的字节数目
	 * 即数据库的内部表示的字节数目
	 * 
	 * @param content 源字符串
	 * @return 字节数目
	 * Created：2012-1-12
	 * @author:Wugang KF22423
     * @throws UnsupportedEncodingException 
	 */
//	public static int getUTF8Length(String content){
//		try {
//			return content.getBytes("UTF-8").length;
//		} catch (UnsupportedEncodingException e) {
//			ExceptionHandler.logException(e);
//			return content.length();
//		}
//	}
	
	/**
	 * @param toSplit
	 * @return
	 * 
	 *         分割','分割的字符串,并去除重复
	 */
	public static String[] split(String toSplit) {
		if (!isNullOrEmpty(toSplit)) {
			Set<String> valueSet = new TreeSet<String>(Arrays.asList(toSplit.split(",")));
			return valueSet.toArray(new String[valueSet.size()]);
		}
		return EMPTY_STRINGS;
	}
	
	/** 
	 * 转换小写	
	 * @author l54883
	 * @since 2012-6-12
	 * @param original
	 * @return
	 */
	public static String toLower(String original){
		if( isNullOrEmpty(original)){
			return original;
		}
		return original.toLowerCase(Locale.ENGLISH);
	}
	
	/**
	 * 根据给定的regex 返回最后一次出现的位置到结尾的字符串
	 * @param string
	 * @param regex
	 * @return
	 */
	public static String subString4lastIndex(String string, String regex){
		if(isNullOrEmpty(string) || isNullOrEmpty(regex) ){
			return string;
		}
		if(string.indexOf(regex)==-1){
			return string;
		}
		return string.substring(string.lastIndexOf(regex)+regex.length());
	}
	
	/**
	 * 首字母大写
	 * @param string
	 * @return
	 */
	public static String firstUpperCase(String string){
		if(isNullOrEmpty(string)){
			return string;
		}
		return string.substring(0,1).toUpperCase(Locale.ENGLISH)+string.substring(1);
	}
	
	/**
	 * 首字母小写
	 * @param string
	 * @return
	 */
	public static String firstLowerCase(String string){
		if(isNullOrEmpty(string)){
			return string;
		}
		return string.substring(0,1).toLowerCase(Locale.ENGLISH)+string.substring(1);
	}
	/**
	 * 
	 * 判断 字符串是否是数字
	 * @param str
	 * @return
	 * Created：2012-7-26
	 * @author:hKF16418
	 */
	public static boolean isNumeric(String str){
		if(isNullOrEmpty(str)){
			return false;
		}
		String NUMERIC_PATTERN = "^(-?\\d+)(\\.\\d+)?$";
		Pattern pattern=Pattern.compile(NUMERIC_PATTERN);
		Matcher matcher=pattern.matcher(str);
		return matcher.matches();
	}

	/** 
	 * 字符串变大写	
	 * @author l54883
	 * @since 2012-9-29
	 * @param original
	 * @return
	 */
	public static String toUpper(String original) {
		if( isNullOrEmpty(original)){
			return original;
		}
		return original.toUpperCase(Locale.ENGLISH);
	}
	
	/** 
	 * 判断两个字符串是否相等
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean isEqual(String a, String b){
		return (a == null && b == null) || (a!= null && a.equals(b));
	}
	
	public  static String charNext(String charStr) { 
		if(isNullOrEmpty(charStr)){
			return "";
		}
		String tempcharStr = charStr.toUpperCase();
		StringBuffer returnStr = new StringBuffer();
		int sumber = tempcharStr.charAt(tempcharStr.length()-1);
		if(getCharA(tempcharStr,sumber,returnStr)){
			return returnStr.toString(); 
		}
		char[] tempC =  Character.toChars(sumber+1);
		returnStr.append(tempcharStr.substring(0, tempcharStr.length()-1));
		for(char c :tempC){
			returnStr.append(c);
		}
		return  returnStr.toString();
    }
	/**
	 * 判断是否返回最后一个Z
	 * @param tempcharStr
	 * @param sumber
	 * @param returnStr
	 * @return
	 * Created：2013-9-16
	 * @author:hKF16418
	 */
	private static boolean getCharA(String tempcharStr,int sumber,StringBuffer returnStr){
		if(sumber==90 && tempcharStr.length() == 1){
			returnStr.append("AA");
			return true;
		} 
		if(sumber==90 && tempcharStr.length()>1){
			returnStr.append(charNext(tempcharStr.substring(0, tempcharStr.length()-1)));
			returnStr.append("A"+"");
			return true;
		}
		return false;
	}

}
