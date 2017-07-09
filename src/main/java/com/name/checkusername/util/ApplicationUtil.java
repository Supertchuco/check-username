package com.name.checkusername.util;

import java.util.Random;

/**
 * Created by rafae on 08/07/2017.
 */
public class ApplicationUtil {

    static StringBuffer sb;
    static Random random;
    static String alphabet = "0123456789";

    public static String generateValue(int charNumbers){
        sb = new StringBuffer();
        random = new Random();
        for(int index=0; index < charNumbers; index++){
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return sb.toString();
    }
}
