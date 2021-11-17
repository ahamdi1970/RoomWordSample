package com.openclassrooms.mareu.model;

import java.util.Objects;

/**
 * Model object representing a Meeting
 */

public class Meeting {

    /**
     * Identifier
     */
    private long id;

    /**
     * Meeting name
     */
    private String meetingName;

    /**
     * Meeting date
     */
    private String date;

    /**
     * Meeting hour
     */
    private String hour;

    /**
     * Meeting room
     */
    private String room;

    /**
     * Meeting emails
     */
    private String emails;

    /**
     * Color
     */
    private int color;


    /**
     * Constructor
     *
     * @param id            long
     * @param meetingName   String
     * @param date          String
     * @param hour          String
     * @param room          String
     * @param emails        String
     * @param color      int
     *
     */

    public Meeting(long id, String meetingName, String date, String hour,
                   String room, String emails, int color) {
        this.id = id;
        this.meetingName = meetingName;
        this.date = date;
        this.hour = hour;
        this.room = room;
        this.emails = emails;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getDate() { return date;  }

    public void setDate(String date) { this.date = date; }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public int getColor() { return color; }

    public void setColor(int color) { this.color = color; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(id, meeting.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
