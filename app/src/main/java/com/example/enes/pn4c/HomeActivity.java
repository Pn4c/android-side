package com.example.enes.pn4c;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.enes.pn4c.JavaClasses.Post;
import com.example.enes.pn4c.JavaClasses.SimpleRecyclerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {

    private RecyclerView recycler_view;

    private Toolbar HomeToolbar;

    private List<Post> deneme = new ArrayList<>();
    TextView textView;
    RequestQueue requestQueue;
    String url_goster="http://10.0.2.2/egitim/ogrenciGoster.php";
    ArrayList<String> users = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HomeToolbar = (Toolbar)findViewById(R.id.homeToolbar);
        setSupportActionBar(HomeToolbar);



        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recycler_view.setLayoutManager(layoutManager);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        getAllPosts();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    public void getAllPosts(){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url_goster, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray ogrenciler =response.getJSONArray("ogrenciler");

                    for (int i=0; i<ogrenciler.length(); i++){
                        JSONObject ogrenci = ogrenciler.getJSONObject(i);

                        String title = ogrenci.getString("Title");
                        String content = ogrenci.getString("Content");

                        getDeneme().add(new Post(content, title));
                    }

                    SimpleRecyclerAdapter adapter_items = new SimpleRecyclerAdapter(deneme);
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

            }
        }
        );

        requestQueue.add(jsonObjectRequest);


    }

    private List<Post> getDeneme(){return deneme;}

}
