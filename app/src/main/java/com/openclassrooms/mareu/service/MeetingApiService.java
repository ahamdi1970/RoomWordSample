package com.openclassrooms.mareu.service;

import com.openclassrooms.mareu.model.Meeting;

import java.util.List;

/**
 * Meeting API client
 */
public interface MeetingApiService {

    /**
     * Get all my Meetings
     * @return {@link List}
     */
    List<Meeting> getMeetings();

    /**
     * Deletes a meeting
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Create a meeting
     * @param meeting
     */
    void createMeeting(Meeting meeting);

    /**
     * filter by date a meeting
     * @param dateToFilter
     */

    List<Meeting> returnMatchingMeetingsWithDate(String dateToFilter);

    /**
     * filter by room a meeting
     * @param roomSelected
     */


    List<Meeting> returnMatchingMeetingsWithRoom(String roomSelected);
}
