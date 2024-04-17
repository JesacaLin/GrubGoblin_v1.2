package org.JesacaLin.models;

import java.time.LocalTime;

public class Availability {
    private int availabilityId;
    private int dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public Availability() {
    }

    public Availability(int availabilityId, int dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.availabilityId = availabilityId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(int availabilityId) {
        this.availabilityId = availabilityId;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Availability {" +
                " ID = " + availabilityId +
                ", DAY OF THE WEEK = " + dayOfWeek +
                ", START TIME = " + startTime +
                ", END TIME = " + endTime +
                " }";
    }
}
