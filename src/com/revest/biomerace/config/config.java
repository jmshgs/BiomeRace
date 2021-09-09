package com.revest.biomerace.config;

public class config {

    public static boolean isStringInt(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }
    public static String removefrontfromstring(String str, int numb) {
        str = str.substring(numb);
        return str;
    }
}
