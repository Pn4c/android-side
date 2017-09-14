package com.example.enes.pn4c.JavaClasses;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.enes.pn4c.R;

import java.util.List;

/**
 * Created by Enes on 9/7/2017.
 */

public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView content;
        public TextView title;
        public CardView card_view;


        public ViewHolder(View view) {
            super(view);

            card_view = view.findViewById(R.id.card_view);
            content = view.findViewById(R.id.content);
            title = view.findViewById(R.id.title);

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

        holder.title.setText(tryview.get(position).getTitle());
        holder.content.setText(tryview.get(position).getContent());

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
