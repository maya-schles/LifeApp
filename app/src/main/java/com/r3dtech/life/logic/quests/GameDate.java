package com.r3dtech.life.logic.quests;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class GameDate {
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.CANADA);
    private int day;
    private int month;
    private int year;

    public static GameDate now() {
        return fromCalendar(Calendar.getInstance());
    }

    private static GameDate fromCalendar(Calendar calendar) {
        return new GameDate(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR));
    }

    public static GameDate parse(String string) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFormat.parse(string));
        return fromCalendar(calendar);
    }

    public GameDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    private long getTime() {
        return (year*12+month)*31+day;
    }
    public boolean isBefore(GameDate otherDate) {
        return getTime() < otherDate.getTime();
    }
    public boolean isAfter(GameDate otherDate) {
        return getTime() > otherDate.getTime();
    }

    public int getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day);
        return (calendar.get(Calendar.DAY_OF_WEEK)-1)%7;
    }

    @Override
    public String toString() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day);
        return dateFormat.format(calendar.getTime());
    }
}
