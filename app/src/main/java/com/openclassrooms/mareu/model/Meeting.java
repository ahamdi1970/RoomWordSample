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
     * Full name
     */
    private String meetingName;

    /**
     * room
     */
    private String hour;

    /**
     * Phone number
     */
    private String room;

    /**
     * About me
     */
    private String emails;

    /**
     * Constructor
     *
     * @param id
     * @param meetingName
     * @param
     */
    public Meeting(long id, String meetingName, String hour,
                   String room, String emails) {
        this.id = id;
        this.meetingName = meetingName;
        this.hour = hour;
        this.room = room;
        this.emails = emails;
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
