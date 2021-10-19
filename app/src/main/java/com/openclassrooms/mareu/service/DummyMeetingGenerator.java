package com.openclassrooms.mareu.service;


import com.openclassrooms.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting(1, "Réunion A", "Lundi", "08h00", "Lyon", "mouloud@tata.com"),
            new Meeting(2, "Réunion B", "Mardi", "09h00", "Paris", "farouk@tata.com"),
            new Meeting(3, "Réunion C", "Mercredi", "10h00", "Toulouse", "jean@tata.com")
    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}
