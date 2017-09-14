package com.example.enes.pn4c;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SearchpageActivity extends AppCompatActivity {

    private String[] ulkeler =  {"Türkiye", "Almanya", "Avusturya", "Amerika","İngiltere",
            "Macaristan", "Yunanistan", "Rusya", "Suriye", "İran", "Irak",
            "Şili", "Brezilya", "Japonya", "Portekiz", "İspanya",
            "Makedonya", "Ukrayna", "İsvicre"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchpage);

        ListView LVsearchedUsers = (ListView)findViewById(R.id.LVsearchResults);
        ArrayAdapter<String> listviewadapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,ulkeler);
        LVsearchedUsers.setAdapter(listviewadapter);
        LVsearchedUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder diyalogOlusturucu =
                        new AlertDialog.Builder(SearchpageActivity.this);

                diyalogOlusturucu.setMessage(ulkeler[i]);
                diyalogOlusturucu.create().show();
            }
        });
    }
}
