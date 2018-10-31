package com.windsor.foodapp.foodapp.util;

import java.util.Random;

public class RandomStringUtils {

    private static Random random = new Random();

    private static String pattern = "abcderfghijklmnopqrst";

    public static String getRandomString(int len) {

        StringBuffer randomString = new StringBuffer();
        for(int i=0; i< len; i++) {
            int randomNumber = random.nextInt(pattern.length());
            randomString.append(pattern.charAt(randomNumber));
        }
        return randomString.toString();
    }
}
