package model;


import java.util.HashMap;

// A class that describes a date: month, day, and year
public class Date {
    private int month;
    private int day;
    private int year;
    private HashMap<Integer, Integer> calender = new HashMap<>();

    public Date(int month, int day, int year) {
      //fill the calender with the days of the month depending on the year
      //uses the gregorian calender
      // months are stored as a modulo of 12
        calender.put(1, 31);  //January
        calender.put(2, 29 - year % 4); //February leap years are divisible by 4
        calender.put(3, 31); //March
        calender.put(4, 30); //April
        calender.put(5, 31); //May
        calender.put(6, 30); //June
        calender.put(7, 31); //July
        calender.put(8, 31); //August
        calender.put(9, 30); //September
        calender.put(10, 31); //October
        calender.put(11, 30); //November
        calender.put(0, 31);  //December
        calcDate(month, day, year); //calculate the date

    }

  //MODIFIES: this
  //EFFECTS: calculates the date with the given days according to the gregorian
  // calender
    public void calcDate(int month, int day, int year) {
      //the date is calculated in case a room is booked for a very lengthy time
        this.year = year + calcMonth(month + calcDays(day, month));
    }

  //MODIFIES: this
  //EFFECTS: calculates and changes the current Day and returns how many extra Months are needed
  // if the current Day exceeds the amount of days in the given month
    private int calcDays(int day, int month) {
      //when adding a day over the current month it goes to the next month
        int addMonths = 0;
        if (day > calender.get(month % 12)) { //if the days are over the given month
            while (day > calender.get((month % 12 + addMonths % 12) % 12)) {
                day -= calender.get((month % 12 + addMonths % 12) % 12);
                addMonths += 1;
            }
        }
        this.day = day;
        return addMonths; //return the amount of add Months
    }

  //MODIFIES: this
  //EFFECTS: calculates and changes the current month and returns how many extra years are needed
  // if the current Month exceeds the amount of Months in the given year
    private int calcMonth(int month) {
      //when adding a month over 12, it goes into the next year
        int addYears = 0;
        this.month = month % 12;
        if (month > 12) {
            addYears = month / 12;
        }
        return addYears;
    }

  //REQUIRES: startTime is between 0 and 24 hours
  //EFFECTS: adds a duration in hours from a starting time
  // and creates a new date if it exceeds that day
  // returns the new date
    public Date addHours(int startTime, int duration) {
        int addDays;
        Date newDay = new Date(this.month, this.day, this.year);
        if (startTime + duration >= 24) {
            addDays = (startTime + duration) / 24;
            newDay.year = newDay.year
                + newDay.calcMonth(newDay.month + newDay.calcDays(newDay.day + addDays, newDay.month));
        }
        return newDay;
    }

  //EFFECTS: compares this Date to a given Date and returns true if the
  // given Date happens after this Date
    public Boolean isAfter(Date date) {
        if (this.year > date.getYear()) {
            return true;
        } else if (this.year == date.getYear()) {
            if (this.month > date.getMonth()) {
                return true;
            } else if (this.month == date.getMonth()) {
                return (this.day > date.getDay());
            }
        }
        return false;
    }

  //EFFECTS: compares this Date to a given Date and returns true if the
  // given Dates are the same
    public Boolean isEqual(Date date) {
        if (this.year == date.getYear()) {
            if (this.month == date.getMonth()) {
                return (this.day == date.getDay());
            }
        }
        return false;
    }

    //EFFECTS: returns the day of the month stored
    public int getDay() {
        return this.day;
    }

    //EFFECTS: returns the month stored
    public int getMonth() {
        if (this.month == 0) {
            return 12; //december is stored as 0
        }
        return this.month;
    }

    //EFFECTS: returns the year stored
    public int getYear() {
        return this.year;
    }
}

