package com.example.enes.pn4c.JavaClasses;

import android.content.Context;

/**
 * Created by Enes on 9/9/2017.
 */

public class Post {

    private String content;
    //private String title;
    private String nickName;
    private String feeling;
    //private boolean isPrivate;

    public Post(String Content, String nickName, String feeling)
    {
        this.content = Content;
        this.nickName = nickName;
        this.feeling = feeling;

    }
    public Post(){

    }

    public String getContent()
    {

        return this.content;

    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }
}
