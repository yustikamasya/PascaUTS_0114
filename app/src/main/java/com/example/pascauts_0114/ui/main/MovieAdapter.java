package com.example.pascauts_0114.ui.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pascauts_0114.DetailActivity;
import com.example.pascauts_0114.MainActivity;
import com.example.pascauts_0114.R;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ListViewHolder> {
    private ArrayList<Movie> listMovie;
    private Context context;
    public MovieAdapter(Context context,ArrayList<Movie> listMovie) {
        this.listMovie = listMovie;
        this.context=context;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        final Movie movie = listMovie.get(position);

        holder.txtTitle.setText(movie.getTitle());
        holder.txtYear.setText(movie.getYear());
        holder.txtOverview.setText(movie.getOverview());

        Glide.with(context)
                .load("https://themoviedb.org/t/p/w500"+movie.getImage().toString())
                .into(holder.imgPoster);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent detailActivity = new Intent(context, DetailActivity.class);
                detailActivity.putExtra("EXTRA_TITLE",movie.getTitle().toString());
                detailActivity.putExtra("EXTRA_YEAR",movie.getYear().toString());
                detailActivity.putExtra("EXTRA_OVERVIEW",movie.getOverview().toString());
                detailActivity.putExtra("EXTRA_IMAGE",movie.getImage().toString());

                context.startActivity(detailActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtYear;
        ImageView imgPoster;
        TextView txtOverview;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtYear = itemView.findViewById(R.id.txtYear);
            imgPoster = (itemView.findViewById(R.id.imgPoster));
            txtOverview = itemView.findViewById(R.id.txtOverview);
        }
    }
}
