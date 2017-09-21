package com.cy.common.converter;


import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zxj on 2017/4/7.
 */
public class DateConverter implements Converter<String, Date> {

    private static List<String> FORMART_LIST = new ArrayList<String>(4);

    static{
        FORMART_LIST.add("yyyy-MM");
        FORMART_LIST.add("yyyy-MM-dd");
        FORMART_LIST.add("yyyy-MM-dd hh:mm");
        FORMART_LIST.add("yyyy-MM-dd hh:mm:ss");
    }

    public Date convert(String source) {
        if (source == null || "".equals(source.trim())) {
            return null;
        }
        source = source.trim();
        if(source.matches("^\\d{4}-\\d{1,2}$")){
            return parseDate(source, FORMART_LIST.get(0));
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")){
            return parseDate(source, FORMART_LIST.get(1));
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")){
            return parseDate(source, FORMART_LIST.get(2));
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")){
            return parseDate(source, FORMART_LIST.get(3));
        }else {
            throw new IllegalArgumentException("Invalid String value '" + source + "'");
        }
    }

    public  Date parseDate(String dateStr, String format) {
        Date date = null;
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

}