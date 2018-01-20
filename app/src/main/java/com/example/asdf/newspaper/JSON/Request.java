package com.example.asdf.newspaper.JSON;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by asdf on 2018/1/5.
 */

public class Request {
    public int code;
    public String message;
    public int page;
    public int page_size;
    public int total_count;
    @SerializedName("data")
    public List<News> newsList;
}
