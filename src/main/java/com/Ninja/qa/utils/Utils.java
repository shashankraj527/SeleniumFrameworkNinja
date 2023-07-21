package com.Ninja.qa.utils;

import java.util.Date;

public class Utils {

    public static  final int IMPLICIT_WAIT=10;
    public static final int PAGE_LOAD_TIME=10;
    public static String generateEmailWithTimeStamp() {

        Date date = new Date();
        String timestamp= date.toString().replace(" ", "_").replace(":", "_");
        return "kalyanshaan25"+timestamp+"@gmail.com";
    }
}
