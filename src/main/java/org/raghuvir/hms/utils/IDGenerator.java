package org.raghuvir.hms.utils;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IDGenerator {

    private static IDGenerator instance;

    private IDGenerator() {
    }

    public static IDGenerator getInstance() {
        if (IDGenerator.instance == null) {
            IDGenerator.instance = new IDGenerator();
        }
        return IDGenerator.instance;
    }

    public synchronized String getNextId(String prefix) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(IDGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
//        OffsetDateTime now = OffsetDateTime.now();//for local time
        int year = now.getYear();
        year -= 2000;
        long num = (now.getDayOfYear() - 1) * 60 * ((60 * 23) + 56);
        num += (now.getHour() * 60 * 60) + (now.getMinute() * 60) + (now.getSecond());
        return prefix + year + convertBase36(num);
    }
    
    public synchronized long getNextId() {
         long time=System.currentTimeMillis();
         while(time==System.currentTimeMillis());         
         return System.currentTimeMillis();
    }
    
    public synchronized String convertBase36(long num) {
        String num36 = "";
        while (num != 0) {
            long temp = num % 36;
            if (temp < 10) {
                num36 = temp + num36;
            } else {
                num36 = (char) (temp + 65 - 10) + num36;
            }
            num /= 36;
        }
        return num36;
    }
}
