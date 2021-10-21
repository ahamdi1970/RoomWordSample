package com.openclassrooms.mareu.service;

import android.widget.DatePicker;
import android.widget.Toast;

import com.openclassrooms.mareu.model.Meeting;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.widget.Toast.makeText;

/**
 * Dummy mock for the Api
 */
public class DummyMeetingApiService implements MeetingApiService {

    private final List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    /**
     * {@inheritDoc}
     *
     * @param meeting
     */
    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public List<Meeting> returnMatchingMeetingsWithDate(String dateToFilter){
        List<Meeting> meetingsMatchingDate = new ArrayList<> ();
        for(int i = 0 ; i<meetings.size(); i++ ){
            if (meetings.get(i).getDate().equals(dateToFilter))
                meetingsMatchingDate.add(meetings.get(i));
        }
        return meetingsMatchingDate;
    }

    @Override
    public List<Meeting> returnMatchingMeetingsWithRoom(String roomSelected){
        List<Meeting> meetingsMatchingRoom = new ArrayList<> ();
        for(int i = 0 ; i<meetings.size(); i++ ){
            if (meetings.get(i).getDate().equals(roomSelected))
                meetingsMatchingRoom.add(meetings.get(i));
        }
        return meetingsMatchingRoom;
    }
}
