package com.example.enes.pn4c;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    Button btnNewRegister,btnLoginpage;
    EditText etnewuseremail,etnewusernickname,etnewuserpassword,etnewuserage;
    Spinner genderspinner;

    RequestQueue requestQueue;
    String url_kaydet="http://185.16.237.199/egitim/ogrenciKaydet.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        etnewuseremail = (EditText)findViewById(R.id.etnewEmail);
        etnewuseremail.setTextColor(Color.BLACK);

        etnewusernickname = (EditText)findViewById(R.id.etNickname);
        etnewusernickname.setTextColor(Color.BLACK);

        etnewuserpassword = (EditText)findViewById(R.id.etnewPassword);

        genderspinner = (Spinner)findViewById(R.id.GenderSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Genders_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderspinner.setAdapter(adapter);

        etnewuserage = (EditText)findViewById(R.id.etnewAge);
        etnewuserage.setTextColor(Color.CYAN);


        btnNewRegister = (Button)findViewById(R.id.btnCreatenewuser);
        btnNewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                        Map<String, String> parametreler = new HashMap<String, String>();
                        parametreler.put("Email", etnewuseremail.getText().toString());
                        parametreler.put("NickName", etnewusernickname.getText().toString());
                        parametreler.put("Gender", genderspinner.getSelectedItem().toString());
                        parametreler.put("Age", etnewuserage.getText().toString());
                        parametreler.put("Password", etnewuserpassword.getText().toString());

                        return parametreler;

                    }
                };

                requestQueue.add(request);

            }
        });

        btnLoginpage = (Button)findViewById(R.id.btnReturnLoginpage);
        btnLoginpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent return2loginpage = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(return2loginpage);
            }
        });





    }
}
