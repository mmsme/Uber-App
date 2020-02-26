package com.m_mustafa.softwareproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HistoryViewHolder> {
    List<HistoryData> TripHistory;

    public RecycleViewAdapter(List<HistoryData> tripHistory) {
        this.TripHistory = tripHistory;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_card_view, parent, false);
        HistoryViewHolder hvh = new HistoryViewHolder(view);
        return hvh;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder historyViewHolder, int i) {
        historyViewHolder.DriverName.setText(TripHistory.get(i).driverName);
        historyViewHolder.DriverImg.setImageResource(TripHistory.get(i).driverImg);
        historyViewHolder.TripStartPoint.setText(TripHistory.get(i).tripStratPoint);
        historyViewHolder.TripEndPoint.setText(TripHistory.get(i).tripEndPoint);
        historyViewHolder.TripDate.setText(TripHistory.get(i).date);
    }

    @Override
    public int getItemCount() {
        return TripHistory.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView DriverName;
        TextView TripStartPoint;
        TextView TripEndPoint;
        TextView TripDate;
        ImageView DriverImg;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.history_card_view);
            DriverImg = (ImageView) itemView.findViewById(R.id.Driver_img);
            TripDate = (TextView) itemView.findViewById(R.id.Trip_date);
            TripEndPoint = (TextView) itemView.findViewById(R.id.Trip_end_point);
            DriverName = (TextView) itemView.findViewById(R.id.Driver_name);
            TripStartPoint = (TextView) itemView.findViewById(R.id.Trip_start_point);
        }
    }
}
