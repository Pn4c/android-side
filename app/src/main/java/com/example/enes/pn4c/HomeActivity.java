package com.example.enes.pn4c;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MotionEvent;

import com.example.enes.pn4c.JavaClasses.Post;
import com.example.enes.pn4c.JavaClasses.SimpleRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recycler_view;
    private List<Post> deneme;

    private Toolbar HomeToolbar;

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

        deneme = new ArrayList<Post>();
        deneme.add(new Post("Content","Title"));
        deneme.add(new Post("cONTEN2","title1"));
        deneme.add(new Post("cONTEN3","title2"));
        deneme.add(new Post("cONTEN4","title3"));
        deneme.add(new Post("cONTEN5","title4"));
        deneme.add(new Post("cONTEN6","title5"));
        deneme.add(new Post("cONTEN7","title6"));
        deneme.add(new Post("cONTEN8","title7"));
        deneme.add(new Post("cONTEN9","title8"));
        deneme.add(new Post("cONTEN10","title9"));
        deneme.add(new Post("cONTEN11","title10"));
        deneme.add(new Post("cONTEN12","title11"));
        deneme.add(new Post("cONTEN13","title12"));
        deneme.add(new Post("cONTEN14","title13"));


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


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
}
