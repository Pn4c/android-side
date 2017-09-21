package com.example.enes.pn4c.HomeFragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.enes.pn4c.JavaClasses.UserCollection;
import com.example.enes.pn4c.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by erama on 9/19/17.
 */

public class fragment_relaxing extends Fragment {
    //rootview is come here because of using in init() function;
    View rootView;

    //request and script url for adding textpost
    RequestQueue requestQueue;
    String url_kaydet="http://185.16.237.199/androidScripts/addTextPost.php";

    //activity elements
    public EditText editText_content, editText_title;
    public Button button_add_textPost;
    public Spinner spinner_feelings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_relaxing, container, false);

        requestQueue = Volley.newRequestQueue(fragment_relaxing.this.getContext());

        init();

        return rootView;
    }

    public void init(){

        editText_content = (EditText)rootView.findViewById(R.id.editText_Content);
        editText_title = (EditText)rootView.findViewById(R.id.editText_Title);

        button_add_textPost = (Button)rootView.findViewById(R.id.button_add_textPost);
        button_add_textPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTextPost();
            }
        });

        spinner_feelings = (Spinner)rootView.findViewById(R.id.spinner_feelings);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(fragment_relaxing.this.getContext(),R.array.array_feelings, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_feelings.setAdapter(adapter);
    }

    public void addTextPost(){
        StringRequest request = new StringRequest(Request.Method.POST, url_kaydet, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.wtf("hata olustu", error.getLocalizedMessage());
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //conditions for adding post

                //if content is read something
                if(!editText_content.getText().toString().equals("")){
                    String user = UserCollection.getCurrentUser().getNickName();

                    Map<String, String> parametreler = new HashMap<>();
                    parametreler.put("Title", editText_title.getText().toString());
                    parametreler.put("UserNickName", user);
                    parametreler.put("Content", editText_content.getText().toString());
                    parametreler.put("Feeling", spinner_feelings.getSelectedItem().toString());
                    //type and date are already added in server script

                    //Message after adding post
                    Snackbar.make(rootView, "Post is added", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    return parametreler;
                }
                else{
                    //Message for not adding post
                    Snackbar.make(rootView, "Post is not added, please write something to content", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return null;
                }
            }
        };

        requestQueue.add(request);
    }
}
