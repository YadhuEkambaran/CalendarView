package com.aya.ringcalendarapplication;

import java.util.Calendar;

public class TestEnum {

    enum Day {
        SUNDAY(1), MONDAY(2), TUESDAY(3), WEDNESDAY(4), THURSDAY(5), FRIDAY(6), SATURDAY(7);

        private int value;

        Day (int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 3);
        calendar.set(Calendar.YEAR, 2021);
        int lastDay = calendar.getActualMaximum(Calendar.DATE);
        System.out.println("--- LAST DATE ---" + lastDay);
    }
}
