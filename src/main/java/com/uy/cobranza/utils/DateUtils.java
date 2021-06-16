package com.uy.cobranza.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String formatDateWithoutHour(Date date ){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static Date dateToString(String strDate) throws ParseException {
      return new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
    }

    public static Date getTomorrow(){
        Date today = new Date();
        Date tomorrow = new Date();
        tomorrow.setDate(today.getDate()+1);
        return tomorrow;
    }


    public static Date getTomorrowByDate(Date date){
        Date tomorrow = new Date();
        tomorrow = date;
        tomorrow.setDate(date.getDate()+1);
        return tomorrow;
    }

    public static Date getDateOfFirstDayInMonth(Integer month){
        Date date = new Date();
        date.setMonth(month);
        date.setDate(1);
        return date;
    }
}
