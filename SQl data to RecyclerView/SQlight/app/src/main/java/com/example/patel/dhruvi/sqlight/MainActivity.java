package com.example.patel.dhruvi.sqlight;


import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.patel.dhruvi.sqlight.Database.DatabaseHandler;
import com.example.patel.dhruvi.sqlight.Model.Post;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    DatabaseHandler db = new DatabaseHandler(this);
    ArrayList<String> postersName = new ArrayList<String>();

    RecyclerView recyclerViewPost ;
    PostRecyclerViewAdapter recyclerViewPostAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Context context;

    List<Post> post;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        //addPost();
        context = getApplicationContext();
        init();
 } //on create

    void init()
    {
        readPost();
        recyclerViewPost = (RecyclerView) findViewById(R.id.recyclerViewAllPost);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerViewPost.setLayoutManager(mLayoutManager);
        recyclerViewPostAdapter =  new PostRecyclerViewAdapter(postersName , context , post);
        recyclerViewPost.setAdapter(recyclerViewPostAdapter);
    }


    void addPost()
    {
        Log.e("Insert: ", "Inserting ..");
        db.addPost(new Post(1, "Ravi", "21:34" , "2017/09/10", "path" ,"At Toronto"));
        db.addPost(new Post(2, "Ravina", "21:34" , "2017/09/10", "path" ,"At Toronto Metro"));
        db.addPost(new Post(3, "Akshay", "21:34" , "2017/09/10", "path" ,"At STC Mall"));
        db.addPost(new Post(4, "Rutvij", "21:34" , "2017/09/10", "path" ,"At BayView"));
        db.addPost(new Post(5, "Kreena", "21:34" , "2017/09/10", "path" ,"At Victoria Park"));
    }

    void readPost()
    {
        //Log.e("Reading: ", "Reading all contacts..");
        post = db.getAllPost();
        for (Post cn : post)
        {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getName() + " ,Phone: " + cn.getComment();
            Log.e("Name: ", log);
            postersName.add(cn.getName());
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            Toast.makeText(this , "Landscape", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this , "Portrait ", Toast.LENGTH_LONG).show();
        }
        super.onConfigurationChanged(newConfig);
    }
}// class

