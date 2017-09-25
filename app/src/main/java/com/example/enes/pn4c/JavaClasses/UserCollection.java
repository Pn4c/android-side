package com.example.enes.pn4c.JavaClasses;

import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.enes.pn4c.HomeAct;
import com.example.enes.pn4c.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erama on 9/19/17.
 */

//bu Classın amacı ne deneme amacıyla mı kullanacağız

public class UserCollection {

    public static User currentUser;

    String url_goster="http://183.16.237.199/egitim/getUsers.php";

    public UserCollection(){



    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentuser) {
       currentUser = currentuser;
    }

    public static User findUserByNickName(final String searchNickName) {
        return new User("","","","","","");
    }



}
