package com.example.enes.pn4c.JavaClasses;

import android.provider.ContactsContract;
import android.widget.ImageView;

/**
 * Created by Enes on 9/9/2017.
 */

public class ImagePost extends Post {

    private ImageView imageView;

    public ImagePost(String Content, String NickName, String Feeling, String Source) {
        super(Content, NickName, Feeling);
    }
}
