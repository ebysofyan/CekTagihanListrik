package com.lc.belajar.helper;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by eby on 07/09/16.
 */
public class NumberFormatter {

    public static String currencyFormat(double value) {
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getNumberInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        return kursIndonesia.format(value);
    }
}
