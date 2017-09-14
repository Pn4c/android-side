package com.example.enes.pn4c.JavaClasses;

import android.content.Context;

/**
 * Created by Enes on 9/9/2017.
 */

public class Post {

    private String content;
    private String title;

    public Post(String Content,String Title)
    {
        this.content = Content;
        this.title = Title;


    }
    public Post(){

    }

    public String getContent()
    {

        return this.content;

    }
    public String getTitle()
    {
        return this.title;

    }
}
