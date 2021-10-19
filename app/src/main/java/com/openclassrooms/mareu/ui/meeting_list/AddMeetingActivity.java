package com.openclassrooms.mareu.ui.meeting_list;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Spinner;
import com.openclassrooms.mareu.R;
import com.openclassrooms.mareu.databinding.ActivityAddMeetingBinding;
import com.openclassrooms.mareu.di.DI;
import com.openclassrooms.mareu.model.Meeting;
import com.openclassrooms.mareu.service.MeetingApiService;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


public class AddMeetingActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener {

    private MeetingApiService mApiService;

    private ActivityAddMeetingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApiService = DI.getMeetingApiService ();

        initView();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        setMeeting();



        binding.buttonHourPicker.setOnClickListener ( v -> {
            DialogFragment timePicker = new TimePickerFragment ();
            timePicker.show(getSupportFragmentManager (),"time picker");
        } );


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.rooms, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner1.setAdapter(adapter);
        binding.spinner1.setOnItemSelectedListener( this );


        binding.createButton.setOnClickListener(v -> addMeeting());
    }

    private void initView() {
        binding = ActivityAddMeetingBinding.inflate(this.getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    private void setMeeting() {

        binding.nomReunion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.createButton.setEnabled(s.length() > 0);
            }
        });
    }

    private void addMeeting() {
        Meeting meeting = new Meeting(
                System.currentTimeMillis(),
                binding.nomReunion.getText().toString(),
                binding.etHourReunion.getText().toString(),
                binding.etRoom.getText().toString(),
                binding.etMailInvites.getText().toString()
        );
        mApiService.createMeeting(meeting);
        finish();
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String timeString =  hourOfDay +":"+ minute; // time formatting
        binding.etHourReunion.setText(timeString);

    }

    //to be able to go back to main activity with screen left arrow
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition ( position ).toString ();
        binding.etRoom.setText ( text );
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
