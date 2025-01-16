package com.example.detailstudy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.libraries.places.api.model.Review;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private ArrayList<String> reviews;
    private Context context;

    public ReviewAdapter(ArrayList<String> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    //RecyclerView 샘명주기 onCreateViewHolder 통해서 View가 생성된다.
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ReviewViewHolder(view);
    }

    //onBindViewHolder 를 통해서 값을 반영하는 코드
    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        holder.tvReview.setText(reviews.get(position));

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView tvReview;
        TextView et_ReviewID;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            tvReview = itemView.findViewById(android.R.id.text1);

        }
    }
}