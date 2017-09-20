package com.example.enes.pn4c.JavaClasses;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.enes.pn4c.R;
import com.squareup.picasso.Picasso;

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
        public ImageView imageSource;

        public ViewHolder(View view) {
            super(view);

            card_view = view.findViewById(R.id.card_view);
            content = view.findViewById(R.id.content);
            title = view.findViewById(R.id.title);
            nickName = view.findViewById(R.id.nickName);
            imageSource = view.findViewById(R.id.image_post);

        }

    }

    List<Post> tryview;
    public SimpleRecyclerAdapter(List<Post> tryview1) {

        this.tryview = tryview1;
    }

    @Override
    public SimpleRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        ViewHolder viewHolder = null;
        switch (viewType) {
            case 0:
                view = View.inflate(parent.getContext(), R.layout.myviewlayout2, null);
                viewHolder = new ViewHolder(view);
                break;
            case 1:
                view = View.inflate(parent.getContext(), R.layout.myviewlayout2, null);
                viewHolder = new ViewHolder(view);
                break;
        }
        return viewHolder;

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
        holder.nickName.setText(tryview.get(position).getNickName());
        String [] content_array = tryview.get(position).getContent().split(",");

        int viewType = getItemViewType(position);

        if (tryview.get(position).getClass().getSimpleName().equals("ImagePost")){
            viewType = 1;
        }
        else{
            viewType = 0;

        }

        switch (viewType) {
            case 0:
                holder.content.setText(tryview.get(position).getContent());
                Picasso.with(holder.card_view.getContext()).load("http://185.16.237.199" + content_array[0]).fit().into(holder.imageSource);

                holder.imageSource.getLayoutParams().height = 0;

                break;
            case 1:
                Picasso.with(holder.card_view.getContext()).load("http://185.16.237.199" + content_array[0]).fit().into(holder.imageSource);

                try{
                    holder.content.setText(content_array[1]);
                }
                catch (Exception e){
                    holder.content.setText(content_array[0]);
                }
                break;
        }

        //Title color selection
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
