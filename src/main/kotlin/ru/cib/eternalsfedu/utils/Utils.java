package ru.cib.eternalsfedu.utils;

import java.util.Random;

public class Utils {

    public static int generateRandomDigits( int ndigits ) {

        int minvalue = 1;

        for( int i = 1; i < ndigits; i++ )
            minvalue = minvalue*10 + 1;

        int maxvalue = minvalue*9;

        int pick = 0;

        while( pick < minvalue )
            pick = Utils.random.nextInt(maxvalue);

        return pick;
    }

    private static Random random = new Random( System.currentTimeMillis() );
}