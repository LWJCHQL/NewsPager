package com.example.asdf.newspaper.JSON;

import java.io.Serializable;

/**
 * Created by asdf on 2018/1/5.
 */

public class New implements Serializable{
    private String title;
    private String content;
    private String image;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
