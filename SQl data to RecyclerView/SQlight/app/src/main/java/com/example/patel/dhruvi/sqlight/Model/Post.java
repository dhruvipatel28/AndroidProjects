package com.example.patel.dhruvi.sqlight.Model;

/**
 * Created by macstudent on 2017-11-24.
 */

public class Post
{
    //private variable

    int id;
    String name;
    String time;
    String date;
    String path;
    String comment;

    //Empty constructor
    public Post()
    {

    }

    // constructor
    public Post(int id, String _name, String _time, String _date, String _path, String _comment)
    {
        this.id = id;
        this.name = _name;
        this.time = _time;
        this.date = _date;
        this.path = _path;
        this.comment = _comment;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
