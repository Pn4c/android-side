package com.example.enes.pn4c;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity {

    Button btnNewRegister,btnLoginpage;
    EditText etnewuseremail,etnewusernickname,etnewuserpassword,etnewuserage;
    Spinner genderspinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


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


                //Mysql ile ilgili fonksiyon yazılacak


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
