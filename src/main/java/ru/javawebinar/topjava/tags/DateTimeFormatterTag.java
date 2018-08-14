package ru.javawebinar.topjava.tags;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterTag {
    public static String doFormat(LocalDateTime ldt, String pattern) {
        return ldt.format(DateTimeFormatter.ofPattern(pattern));
    }
}
