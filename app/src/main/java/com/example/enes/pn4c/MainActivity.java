package com.example.enes.pn4c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.enes.pn4c.JavaClasses.User;
import com.example.enes.pn4c.JavaClasses.UserCollection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Button btnRegister,btnLogin;
    EditText etEmail,etPassword;

    RequestQueue requestQueue;
    String url_goster="http://185.16.237.199/androidScripts/getUsers.php";
    private List<User> Users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText)findViewById(R.id.etEmail1);
        etPassword = (EditText)findViewById(R.id.etPassword1);
        btnRegister = (Button)findViewById(R.id.btnSignUp);
        btnLogin = (Button)findViewById(R.id.btnLogin);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url_goster, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray ogrenciler =response.getJSONArray("users");

                            for (int i=0; i<ogrenciler.length(); i++){
                                JSONObject ogrenci = ogrenciler.getJSONObject(i);

                                String email = ogrenci.getString("Email");
                                String nickName = ogrenci.getString("NickName");
                                String gender = ogrenci.getString("Gender");
                                String age = ogrenci.getString("Age");
                                String password = ogrenci.getString("Password");
                                String registerDate = ogrenci.getString("RegisterDate");

                                getUsers().add(new User(email, nickName, gender, age, password, registerDate));
                            }

                            //check if user is exits and, pass and email are correct
                            Boolean isLogged = false;
                            for(User u : Users){
                                if (etEmail.getText().toString().equals(u.getEmail())  && etPassword.getText().toString().equals(u.getPassword())) {
                                    UserCollection.setCurrentUser(u);
                                    isLogged = true;
                                    break;
                                }
                            }

                            if(isLogged){
                                Intent home = new Intent(MainActivity.this,HomeAct.class);
                                startActivity(home);

                            }
                            else{
                                Toast toast = Toast.makeText(getApplicationContext(), "The entered password or e mail is wrong",Toast.LENGTH_SHORT);
                                toast.show();
                            }

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

    private List<User> getUsers(){return Users;}


}