package com.nytime_test_app;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nagaraju P on 21-12-2016.
 */

public class MovieReviewsAdapter extends RecyclerView.Adapter<MovieReviewsAdapter.MyViewHolder> {

    private final List<MovieReviews> dataList;
    private final Activity parentActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        final TextView titleTV;
        final TextView descriptionTV;
        final TextView authorTV;
        final TextView dateTV;
        final LinearLayout itemViewLL;

        MyViewHolder(View view) {
            super(view);
            titleTV = view.findViewById(R.id.titleTV);
            descriptionTV = view.findViewById(R.id.descriptionTV);
            authorTV = view.findViewById(R.id.authorTV);
            dateTV = view.findViewById(R.id.dateTV);
            itemViewLL = view.findViewById(R.id.itemViewLL);
        }
    }


    public MovieReviewsAdapter(Activity parentActivity,
                               List<MovieReviews> dataList) {
        this.dataList = dataList;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_items, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final MovieReviews dataItem = dataList.get(position);

        holder.titleTV.setText(dataItem.getTitle());
        holder.descriptionTV.setText(dataItem.getAbstractString());
        holder.authorTV.setText(dataItem.getSource());
        holder.dateTV.setText(dataItem.getPublished_date());

        holder.itemViewLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent detailsActivity = new Intent(parentActivity, DetailsActivity.class);
                detailsActivity.putExtra("sourceDataItem", dataItem);
                parentActivity.startActivity(detailsActivity);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}