package Leetcode.ChatGpt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DateParser {
    private static final Lock lock = new ReentrantLock();
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date parseDate(String dateString) throws ParseException {
        if (dateString == null || dateString.isEmpty()) {
            throw new IllegalArgumentException("日期字符串不能为空");
        }

        // 对字符串进行URL解码
        String decodedDateString = decodeURL(dateString);

        // 判断是否是纯数字字符串
        if (isNumeric(decodedDateString)) {
            int daysToAdd = Integer.parseInt(decodedDateString);
            return calculateFutureDate(daysToAdd);
        } else {
            // 尝试解析日期字符串为"yyyy-MM-dd"或"yyyy-MM-dd HH:mm:ss"格式
            try {
                lock.lock(); // 加锁确保线程安全
                return dateFormatter.parse(decodedDateString);
            } finally {
                lock.unlock();
            }
        }
    }

    private static String decodeURL(String input) {
        // 使用URL解码
        try {
            return java.net.URLDecoder.decode(input, "UTF-8");
        } catch (Exception e) {
            // 解码失败时，可以选择抛出异常或者返回原始字符串
            throw new IllegalArgumentException("URL解码失败", e);
        }
    }

    private static boolean isNumeric(String str) {
        // 使用正则表达式判断是否为纯数字字符串
        return str.matches("-?\\d+");
    }

    private static Date calculateFutureDate(int daysToAdd) {
        // 获取当前时间
        Date currentDate = new Date();

        // 计算天数对应的毫秒数
        long millisToAdd = daysToAdd * 24L * 60 * 60 * 1000; // 天数 * 小时 * 分钟 * 秒 * 毫秒

        // 计算未来日期的毫秒数
        long futureMillis = currentDate.getTime() + millisToAdd;

        // 创建未来日期对象
        return new Date(futureMillis);
    }

    public static void main(String[] args) throws ParseException {
        String input = "2023-10-08 10:05:30";
        Date result = parseDate(input);
        System.out.println("解析结果：" + result);
    }
}

