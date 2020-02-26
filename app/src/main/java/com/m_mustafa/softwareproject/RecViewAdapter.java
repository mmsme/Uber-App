package com.m_mustafa.softwareproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.InboxViewHolder> {
    List<inboxRecData> Messages;

    public RecViewAdapter(List<inboxRecData> messages) {
        this.Messages = messages;
    }

    @NonNull
    @Override
    public InboxViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inbox_card_view, viewGroup, false);
        InboxViewHolder ivh = new InboxViewHolder(view);
        return ivh;
    }

    @Override
    public void onBindViewHolder(@NonNull InboxViewHolder inboxViewHolder, int i) {
        inboxViewHolder.senderImg.setImageResource(Messages.get(i).getSenderImg());
        inboxViewHolder.MessageDate.setText(Messages.get(i).getMsgDate());
        inboxViewHolder.senderMessage.setText(Messages.get(i).getMsg());
        inboxViewHolder.senderName.setText(Messages.get(i).getSenderName());
    }

    @Override
    public int getItemCount() {
        return Messages.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class InboxViewHolder extends RecyclerView.ViewHolder {
        TextView senderName;
        TextView senderMessage;
        TextView MessageDate;
        ImageView senderImg;

        public InboxViewHolder(@NonNull View itemView) {
            super(itemView);
            senderName = (TextView) itemView.findViewById(R.id.sender_name);
            senderMessage = (TextView) itemView.findViewById(R.id.inbox_messeage);
            senderImg = (ImageView) itemView.findViewById(R.id.sender_img);
            MessageDate = (TextView) itemView.findViewById(R.id.inbox_messeage_date);
        }
    }
}
