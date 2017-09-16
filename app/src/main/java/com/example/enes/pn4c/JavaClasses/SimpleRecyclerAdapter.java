package com.example.enes.pn4c.JavaClasses;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.enes.pn4c.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Enes on 9/7/2017.
 */

public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView content;
        public TextView title;
        public TextView nickName;
        public CardView card_view;

        public ViewHolder(View view) {
            super(view);

            card_view = view.findViewById(R.id.card_view);
            content = view.findViewById(R.id.content);
            title = view.findViewById(R.id.title);
            nickName = view.findViewById(R.id.nickName);

        }
    }
    List<Post> tryview;
    public SimpleRecyclerAdapter(List<Post> tryview1) {

        this.tryview = tryview1;
    }

    @Override
    public SimpleRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.myviewlayout, parent, false);

        ViewHolder view_holder = new ViewHolder(v);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(SimpleRecyclerAdapter.ViewHolder holder, int position) {

        Map<String,String> colors = new HashMap<>();
        colors.put("Successful","#8150BB");
        colors.put("Lonely","#CDC9C7");
        colors.put("Dead","#474741");
        colors.put("Excited","#FFB036");
        colors.put("Sad","#656990");
        colors.put("Happy","#FAFF52");

        holder.title.setText(tryview.get(position).getTitle());
        holder.content.setText(tryview.get(position).getContent());
        holder.nickName.setText(tryview.get(position).getNickName());

        //Title renkleri ayarlama
        try {
            holder.title.setBackgroundColor(Color.parseColor(colors.get(tryview.get(position).getFeeling())));
        }
        catch (NullPointerException e){

        }

    }

    @Override
    public int getItemCount() {

        return tryview.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
