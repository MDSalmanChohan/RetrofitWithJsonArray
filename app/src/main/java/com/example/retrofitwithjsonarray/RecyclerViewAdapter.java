package com.example.retrofitwithjsonarray;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    // creating a variable for our array list and context.
    private List<Post> courseDataArrayList;
    private Context mcontext;

    // creating a constructor class.

    public RecyclerViewAdapter(List<Post> recyclerDataArrayList, Context mcontext) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview from our modal class.
        Post modal = courseDataArrayList.get(position);
        holder.id.setText(String.valueOf(modal.getId()));
        holder.title.setText(modal.getTitle());
        holder.body.setText(modal.getBody());
//        Picasso.get().load(modal.getCourseimg()).into(holder.courseIV);
    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return courseDataArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
         TextView id, title, body;
//        private ImageView courseIV;



        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our views with their ids.
            id = itemView.findViewById(R.id.idTVCourseName);
            title = itemView.findViewById(R.id.idTVBatch);
            body = itemView.findViewById(R.id.idTVTracks);
//            courseIV = itemView.findViewById(R.id.idIVCourse);
        }
    }
}

