package com.Ninja.qa.utils;

import java.util.Date;

public class Utils {
    public static String generateEmailWithTimeStamp() {
        Date date = new Date();
        String timestamp= date.toString().replace(" ", "_").replace(":", "_");
        return "kalyanshaan25"+timestamp+"@gmail.com";
    }
}
