package cn.liaozhi.ssm.core.util;

import java.util.regex.Matcher;

/**
 * StringUtil.replaceAll, StringUtil.replaceFirst方法内的回调接口 
 * @see com.huawei.it.jalor5.core.util.StringUtil	
 * @author z00199787
 * @since 2011-12-14
 */
public interface IReplaceCallback {
	/**
	 * 将text转化为特定的字串返回
	 * 
	 * @param text
	 *            指定的字符串
	 * @param index
	 *            替换的次序
	 * @param matcher
	 *            Matcher对象
	 * @return
	 */
	public String replace(String text, int index, Matcher matcher);
}
