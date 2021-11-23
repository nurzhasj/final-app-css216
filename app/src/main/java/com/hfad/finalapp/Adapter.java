package com.hfad.finalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private Context mContext;
    private List<Movie> movieList;

    public Adapter(Context mContext, List<Movie> movieList){
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        v = layoutInflater.inflate(R.layout.movie_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText(movieList.get(position).getBirthday()+"");
        holder.title.setText(movieList.get(position).getName()+"");
        holder.status.setText(movieList.get(position).getStatus()+"");
        Glide.with(mContext)
                .load(movieList.get(position).getImg())
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView id;
        TextView status;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.text_title);
            id = itemView.findViewById(R.id.text_id);
            status = itemView.findViewById(R.id.text_status);
            img = itemView.findViewById(R.id.imgView);
        }
    }
}
