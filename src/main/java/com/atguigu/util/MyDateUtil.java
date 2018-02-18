package com.atguigu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDateUtil {

	public static void main(String[] args) {

	}

	public static String getMyTime(Date date, String format) {
		// 时间的显示格式
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		return sdf.format(date);
	}

	public static Date getMyDate(int i) {
		// 时间的加减
		Calendar c = Calendar.getInstance();

		c.add(Calendar.DATE, i);

		Date time = c.getTime();

		return time;
	}
}
