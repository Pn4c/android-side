package com.example.enes.pn4c.JavaClasses;

/**
 * Created by Enes on 9/11/2017.
 */

public class SearchBackground {



    public static String search(String[] Usernames, String enteredName)
    {
        String myname = "";
       for(String username:Usernames)
       {
           if(enteredName.startsWith(username.substring(0,1)))
           {
               myname = enteredName;

           }

       }
        return  myname;

    }

}
