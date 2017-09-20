package com.example.enes.pn4c.HomeFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.enes.pn4c.JavaClasses.Post;
import com.example.enes.pn4c.JavaClasses.SimpleRecyclerAdapter;
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

    private RecyclerView Profilerecycler_view;

    private Toolbar ProfileToolbar;
    public String degisiklik;

    private List<Post> Posts = new ArrayList<>();
    RequestQueue requestQueue;
    String url_goster="http://185.16.237.199/egitim/ogrenciGoster.php";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);

        ProfileToolbar = (Toolbar)findViewById(R.id.myProfileToolbar);
        setSupportActionBar(ProfileToolbar);

        Profilerecycler_view= (RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        Profilerecycler_view.setLayoutManager(layoutManager);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        getAllPosts();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }


    private List<Post> getPosts(){return Posts;}


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        init();

        return rootView;
    }
    public void getAllPosts() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url_goster, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray ogrenciler = response.getJSONArray("ogrenciler");

                    for (int i = 0; i < ogrenciler.length(); i++) {
                        JSONObject ogrenci = ogrenciler.getJSONObject(i);

                        String title = ogrenci.getString("Title");
                        String content = ogrenci.getString("Content");
                        String nickName = ogrenci.getString("UserNickName");
                        String feeling = ogrenci.getString("Feeling");

                        getPosts().add(new Post(content, title, nickName, feeling));
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


    }

}
