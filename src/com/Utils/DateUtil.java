package com.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/12/03/14:39
 * @Description:
 */
public class DateUtil {
    public static String getDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
