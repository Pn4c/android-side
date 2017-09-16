package com.example.enes.pn4c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Button btnRegister,btnLogin;
    EditText etEmail,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText)findViewById(R.id.etEmail1);
        etPassword = (EditText)findViewById(R.id.etPassword1);
        btnRegister = (Button)findViewById(R.id.btnSignUp);
        btnLogin = (Button)findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent HomeIntent = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(HomeIntent);

            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent register = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(register);

            }
        });
        Button searchbutton = (Button)findViewById(R.id.search1);
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Search = new Intent(MainActivity.this,SearchpageActivity.class);
                startActivity(Search);
            }
        });



    }




}