package com.openclassrooms.mareu.ui.meeting_list;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.openclassrooms.mareu.R;
import com.openclassrooms.mareu.databinding.ActivityListMeetingBinding;
import com.openclassrooms.mareu.di.DI;
import com.openclassrooms.mareu.events.DeleteMeetingEvent;
import com.openclassrooms.mareu.model.Meeting;
import com.openclassrooms.mareu.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;


public class ListMeetingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    private ActivityListMeetingBinding binding;

    private MeetingApiService apiService;

    private MyMeetingRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        apiService = DI.getMeetingApiService();

        initView();

        setSupportActionBar(binding.toolbar);

        initList();

        binding.addMeeting.setOnClickListener( v -> {
            Intent intent = new Intent(getApplicationContext(), AddMeetingActivity.class);
            startActivity(intent);
        } );
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
            case R.id.sortbydate:
                DialogFragment datePicker = new DatePickerFragment ();
                datePicker.show ( getSupportFragmentManager (),"date picker" );
                return true;
            case R.id.sortbyroom:
                switch (item.getItemId ()){
                    case R.id.item_mozart:
                        apiService.returnMatchingMeetingsWithRoom ("Mozart");
                        return true;
                    case R.id.item_beethoven:
                        apiService.returnMatchingMeetingsWithRoom ("Beethoven");
                        return true;
                }
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
    // to be able to use date selected

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        List<Meeting> meetingListUpdated = apiService.returnMatchingMeetingsWithDate(currentDate);
        adapter = new MyMeetingRecyclerViewAdapter ( meetingListUpdated );
        binding.listMeetings.setAdapter(adapter);
        adapter.notifyDataSetChanged ();
    }

    // to be able to use spinner on the room selected
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getSelectedItem ().toString ();
        List<Meeting> meetingListUpdated = apiService.returnMatchingMeetingsWithRoom ( text );
        adapter = new MyMeetingRecyclerViewAdapter ( meetingListUpdated );
        binding.listMeetings.setAdapter(adapter);
        adapter.notifyDataSetChanged ();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
