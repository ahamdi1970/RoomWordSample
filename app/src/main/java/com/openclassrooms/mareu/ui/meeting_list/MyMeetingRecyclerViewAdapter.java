package com.openclassrooms.mareu.ui.meeting_list;

import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.mareu.databinding.ItemMeetingBinding;
import com.openclassrooms.mareu.events.DeleteMeetingEvent;
import com.openclassrooms.mareu.model.Meeting;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.MeetingViewHolder> {

    private final List<Meeting> mMeetings;

    public MyMeetingRecyclerViewAdapter(List<Meeting> items) {
        mMeetings = items;
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new MeetingViewHolder(ItemMeetingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final MeetingViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        holder.binding.itemListReunion.setText( String.format ( "%s - %s - %s", meeting.getMeetingName (), meeting.getHour (), meeting.getRoom () ) );
        holder.binding.itemListMail.setText ( meeting.getEmails () );
        holder.binding.reunionColor.setColorFilter ( meeting.getColor (), PorterDuff.Mode.SRC_ATOP );
        holder.binding.itemListDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    protected static class MeetingViewHolder extends RecyclerView.ViewHolder {

        ItemMeetingBinding binding;

        public MeetingViewHolder(ItemMeetingBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }
}
