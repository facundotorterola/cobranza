package com.uy.cobranza.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String formatDateWithoutHour(Date date ){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static Date getTomorrow(){
        Date today = new Date();
        Date tomorrow = new Date();
        tomorrow.setDate(today.getDate()+1);
        return tomorrow;
    }

    public static Date getDateOfFirstDayInMonth(Integer month){
        Date date = new Date();
        date.setMonth(month);
        date.setDate(1);
        return date;
    }
}
