package com.openclassrooms.mareu.ui.meeting_list;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.openclassrooms.mareu.R;
import com.openclassrooms.mareu.databinding.ActivityListMeetingBinding;
import com.openclassrooms.mareu.di.DI;
import com.openclassrooms.mareu.events.DeleteMeetingEvent;
import com.openclassrooms.mareu.model.Meeting;
import com.openclassrooms.mareu.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

public class ListMeetingActivity extends AppCompatActivity {

    private ActivityListMeetingBinding binding;

    private MeetingApiService apiService;

    private MyMeetingRecyclerViewAdapter adapter;

    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        apiService = DI.getMeetingApiService();

        initView();

        setSupportActionBar(binding.toolbar);

        initList();



        binding.addMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddMeetingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater ();
        inflater.inflate ( R.menu.sort_menu,menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId ()){
            case R.id.sortbyhour:
                Toast.makeText ( this,"heure",Toast.LENGTH_SHORT ).show ();
                return true;
            case R.id.sortbyroom:
                Toast.makeText ( this,"salle de réunion",Toast.LENGTH_SHORT ).show ();
                return true;
            case R.id.reset:
                Toast.makeText ( this,"Reset",Toast.LENGTH_SHORT ).show ();
                return true;
        }

        return super.onOptionsItemSelected ( item );
    }

    private void initView() {
        binding = ActivityListMeetingBinding.inflate(this.getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Random random = new Random ();
        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        }


    private void initList() {
        binding.listMeetings.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.listMeetings.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        //private List<Meeting> meetingList;
        List<Meeting> meetingList = apiService.getMeetings ();
        adapter = new MyMeetingRecyclerViewAdapter ( meetingList );
        binding.listMeetings.setAdapter(adapter);
    }

    /**
     * Fired if the user clicks on a delete button
     *
     * @param event
     */
    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {
        apiService.deleteMeeting(event.meeting);
        adapter.notifyDataSetChanged();
    }
}
