package com.example.enes.pn4c.HomeFragments;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.enes.pn4c.JavaClasses.ImagePost;
import com.example.enes.pn4c.JavaClasses.Post;
import com.example.enes.pn4c.JavaClasses.SimpleRecyclerAdapter;
import com.example.enes.pn4c.JavaClasses.TextPost;
import com.example.enes.pn4c.JavaClasses.UserCollection;
import com.example.enes.pn4c.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erama on 9/19/17.
 */

public class fragment_profile extends Fragment {


    public TextView tvyournickname,tvyourage,tvyourgender;
    public View rootView;
    public RecyclerView Profilerecycler_view;


    private List<Post> Posts = new ArrayList<>();
    RequestQueue requestQueue;

    String url_goster="http://185.16.237.199/androidScripts/getPosts.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        Profilerecycler_view = (RecyclerView)rootView.findViewById(R.id.profilerecycler_view);

        tvyournickname = (TextView)rootView.findViewById(R.id.tvNickname);
        tvyournickname.setText(UserCollection.currentUser.getNickName());

        tvyourgender =(TextView)rootView.findViewById(R.id.tvGender);
        tvyourgender.setText(UserCollection.currentUser.getGender());

        tvyourage = (TextView)rootView.findViewById(R.id.tvAge);
        tvyourage.setText(UserCollection.currentUser.getAge());

        requestQueue = Volley.newRequestQueue(this.getContext());

        init();

        return rootView;
    }

    //get all posts from server
    public void getAllPosts() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url_goster, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray ogrenciler = response.getJSONArray("ogrenciler");

                    //hindering to repeat posts again and again
                    getPosts().removeAll(getPosts());

                    for (int i = 0; i < ogrenciler.length(); i++) {
                        JSONObject ogrenci = ogrenciler.getJSONObject(i);

                        String content = ogrenci.getString("Content");
                        String nickName = ogrenci.getString("UserNickName");
                        String feeling = ogrenci.getString("Feeling");
                        int type = ogrenci.getInt("Type");

                        //get post's image form server
                        String source = "http://185.16.237.199" + content.split(",")[0];

                        //if post's owner is current user then :
                        if (nickName.equals(UserCollection.getCurrentUser().getNickName())) {
                            //seperating imagepost and tectpost
                            if (type == 0) {
                                getPosts().add(new TextPost(content, nickName, feeling));
                            } else if (type == 1) {
                                getPosts().add(new ImagePost(content, nickName, feeling, source));
                            }
                        }
                    }

                    SimpleRecyclerAdapter adapter_items = new SimpleRecyclerAdapter(Posts);
                    Profilerecycler_view.setHasFixedSize(true);
                    Profilerecycler_view.setAdapter(adapter_items);
                    Profilerecycler_view.setItemAnimator(new DefaultItemAnimator());
                    Profilerecycler_view.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

                        @Override
                        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                            return false;
                        }

                        @Override
                        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                        }

                        @Override
                        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }
        }
        );

        requestQueue.add(jsonObjectRequest);
    }

    public void init(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(fragment_profile.this.getContext());

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);

        Profilerecycler_view.setLayoutManager(layoutManager);

        getAllPosts();

    }

    private List<Post> getPosts(){return Posts;}
}
