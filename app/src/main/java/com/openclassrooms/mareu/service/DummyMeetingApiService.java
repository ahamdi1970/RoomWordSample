package com.openclassrooms.mareu.service;

import android.app.DatePickerDialog;

import com.openclassrooms.mareu.model.Meeting;
import com.openclassrooms.mareu.ui.meeting_list.DatePickerFragment;

import java.util.List;

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

    //@Override
    //public dateSelect(DatePickerFragment dateSelected){
     //   List<?> filteredList;
      //  for (date)
         //   return filteredList;

  //  }

}
