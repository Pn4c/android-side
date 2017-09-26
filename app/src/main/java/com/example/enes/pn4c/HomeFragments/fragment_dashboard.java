package com.example.enes.pn4c.HomeFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class fragment_dashboard extends Fragment {
    public View rootView;
    public RecyclerView recycler_view;

    TextView textView;

    private List<Post> Posts = new ArrayList<>();
    RequestQueue requestQueue;
    String url_goster="http://185.16.237.199/androidScripts/getPosts.php";

    public void init(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(fragment_dashboard.this.getContext());

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);

        recycler_view.setLayoutManager(layoutManager);

        textView = (TextView)rootView.findViewById(R.id.textView2);

        getAllPosts();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        recycler_view = (RecyclerView)rootView.findViewById(R.id.recycle_view_dashboard);

        requestQueue = Volley.newRequestQueue(this.getContext());

        init();

        return rootView;
    }

    public void getAllPosts(){
        textView.append(UserCollection.getCurrentUser().getNickName());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url_goster, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray ogrenciler =response.getJSONArray("ogrenciler");

                    //hindering to repeat posts again and again
                    getPosts().removeAll(getPosts());

                    for (int i=0; i<ogrenciler.length(); i++){
                        JSONObject ogrenci = ogrenciler.getJSONObject(i);

                        String title = ogrenci.getString("Title");
                        String content = ogrenci.getString("Content");
                        String nickName = ogrenci.getString("UserNickName");
                        String feeling = ogrenci.getString("Feeling");
                        int type = ogrenci.getInt("Type");

                        //get post's image form server
                        String source = "http://185.16.237.199" + content.split(",")[0];
                        if (!UserCollection.getCurrentUser().getNickName().equals(nickName)) {
                            //seperating imagepost and tectpost
                            if (type == 0) {
                                getPosts().add(new TextPost(content, title, nickName, feeling));
                                textView.append(nickName);
                            } else if (type == 1) {
                                getPosts().add(new ImagePost(content, title, nickName, feeling, source));
                            }
                        }
                    }

                    SimpleRecyclerAdapter adapter_items = new SimpleRecyclerAdapter(Posts);
                    recycler_view.setHasFixedSize(true);
                    recycler_view.setAdapter(adapter_items);
                    recycler_view.setItemAnimator(new DefaultItemAnimator());
                    recycler_view.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

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


                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.d("ERROR IN THE VOLLEY", error.getMessage());

            }
        }
        );

        requestQueue.add(jsonObjectRequest);


    }

    public List<Post> getPosts(){return Posts;}

}
