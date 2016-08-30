package com.forestar.cas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CompareDate {
	public static boolean isOutTime(String outTime) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String nowDate = df.format(new Date());
		int flag = CompareDate.compare_date(outTime,nowDate);
		if (flag < 0) {
			return true;			//用户过期
		} 
		
		return false;		
	}
	
	public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
}
