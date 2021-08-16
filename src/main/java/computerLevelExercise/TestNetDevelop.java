package computerLevelExercise;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class TestNetDevelop {

    public static void main(String[] args) {
        System.out.println(getTimeByAMorPMFormat("2020-11-09T06:41:01-0800", "yyyy-MM-dd'T'hh:mm:ss-ssss"));
    }
    public static String getTimeByAMorPMFormat(String time, String format) {
        SimpleDateFormat formatTime = new SimpleDateFormat(format, Locale.ENGLISH);
        SimpleDateFormat formatTime1 = new SimpleDateFormat("HH:mm a");
        String str = "";
        try {
            str = formatTime1.format(formatTime.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;

    }


}
