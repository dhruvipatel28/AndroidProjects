package dhruvipatel.littercontrol;


import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import dhruvipatel.littercontrol.Adapter.PostRecyclerViewAdapter;
import dhruvipatel.littercontrol.Database.DatabaseHandler;
import dhruvipatel.littercontrol.Model.Post;

public class ShowAllPostActivity extends AppCompatActivity
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
        setContentView(R.layout.show_all_post_layout);
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

