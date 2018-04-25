package com.r3dtech.life.logic.quests;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class GameDate implements Serializable{
    static final long serialVersionUID = 23L;

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
    }private Calendar getCalender() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day);
        return calendar;
    }

    public boolean isBefore(GameDate otherDate) {
        return getTime() < otherDate.getTime();
    }

    public boolean isAfter(GameDate otherDate) {
        return getTime() > otherDate.getTime();
    }

    public int getDayOfWeek() {
        return (getCalender().get(Calendar.DAY_OF_WEEK)-1)%7;
    }

    @Override
    public String toString() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day);
        return dateFormat.format(calendar.getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameDate)) return false;

        return getTime() == ((GameDate) o).getTime();
    }

    @Override
    public int hashCode() {
        return (int) getTime();
    }

    private void set(GameDate other) {
        day = other.day;
        month = other.month;
        year = other.year;
    }

    public void dec() {
        Calendar calendar = getCalender();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        set(fromCalendar(calendar));
    }
}
