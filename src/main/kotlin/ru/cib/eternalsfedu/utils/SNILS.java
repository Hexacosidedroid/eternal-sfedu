package ru.cib.eternalsfedu.utils;

import java.util.Random;

public class SNILS {
    public SNILS( String strSNILS ) {
        this.strSnils = strSNILS;
    }

    public String getString() {
        return strSnils;
    }

    static public boolean checkValid( SNILS snils ) {
        return IsValidSNILSString( snils.getString() );
    }

    static public String Generate() {
        int random = Utils.generateRandomDigits(9);

        int control = ComputeControlNumber(random);

        StringBuilder ret = new StringBuilder();

        int pos = 0;

        do
        {
            int next_digit = random%10;
            random = random/10;

            if( ( pos++ ) %3 == 0 ) {
                if( pos == 0 )
                    ret.insert(  0, " " );
                else
                    ret.insert(  0, "-" );
            }

            ret.insert( 0, Integer.toString(next_digit) );
        }while( random != 0 );

        if( control == 0 )
            ret.append( "00" );
        else if( control < 10 )
            ret.append( "0" + Integer.toString(control) );
        else
            ret.append( Integer.toString(control) );

        return ret.toString();
    }

    static public int ComputeControlNumber(long no) {

        if( no < SNILS.minCheckableNumber )
            return 0;

        int ret = 0;
        int pos = 1;
        long remains = no;

        do
        {
            int next_digit = (int )remains%10;
            remains = remains/10;

            ret += next_digit*pos++;
        }while( remains != 0 );

        if( ret > 101 )
            ret = ret/101;

        if( ( ret == 100 ) || ( ret == 101 ) )
            ret = 0;

        return ret;
    }

    static public boolean IsValidSNILSString( String snils ) {
        String[] parts = snils.split("-| ");

        String first = parts[0];
        String second = parts[1];
        String third = parts[2];
        String fourth = parts[3];

        int number = Integer.parseInt(first)*1000000 + Integer.parseInt(second)*1000 +
                Integer.parseInt(third);

        if( number < SNILS.minCheckableNumber )
            return true;

        int control_computed = SNILS.ComputeControlNumber( number );

        int control = Integer.parseInt( fourth );

        return control_computed == control;
    }

    private String strSnils = null;

    private static int minCheckableNumber = 1001998;
}