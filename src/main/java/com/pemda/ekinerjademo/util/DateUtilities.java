package com.pemda.ekinerjademo.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by bagus on 16/11/17.
 */
public class DateUtilities {
    public static String createLocalDate(
            Date tanggalDanWaktu, String pola, Locale lokal) {
        String tanggalStr = null;
        SimpleDateFormat formatter = null;

        if (lokal == null) {
            formatter = new SimpleDateFormat(pola);
        } else {
            formatter = new SimpleDateFormat(pola, lokal);
        }

        tanggalStr = formatter.format(tanggalDanWaktu);

        return tanggalStr;
    }

}
