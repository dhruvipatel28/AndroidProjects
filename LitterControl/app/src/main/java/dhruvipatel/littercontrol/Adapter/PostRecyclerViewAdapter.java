package dhruvipatel.littercontrol.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import dhruvipatel.littercontrol.Model.Post;
import dhruvipatel.littercontrol.R;


/**
 * Created by macstudent on 2017-11-24.
 */

public class PostRecyclerViewAdapter extends RecyclerView.Adapter<PostRecyclerViewAdapter.MyViewHolder>
{

    ArrayList<String> PosterNames;
    List<Post> AllPost;
    Context context;
    RecyclerView.ViewHolder viewHolder1;
    View posterView;
    TextView txtPosterName;

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView txtViewPosterName , txtViewPostDateAndTime , txtViewPostComment;
        public ImageView postImageView ;

        public MyViewHolder(View v)
        {
            super(v);
            txtViewPosterName = (TextView)v.findViewById(R.id.posterName);
            txtViewPostDateAndTime = (TextView)v.findViewById(R.id.txtDateTime);
            txtViewPostComment = (TextView)v.findViewById(R.id.txtComment);
        }
    }

    public PostRecyclerViewAdapter(ArrayList<String> posterNames, Context context , List<Post> allPost)
    {
        this.PosterNames = posterNames;
        this.context = context;
        this.AllPost = allPost;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        posterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_cell,parent,false);
        //viewHolder1 = new MyViewHolder(posterView);
        return new MyViewHolder(posterView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        Post postObject = new Post();
        postObject = AllPost.get(position);
        Log.e(" Adapter " , PosterNames.get(position));
        Log.e(" Adapter "," PostObject Name " + postObject.getName());
        Log.e(" Adapter "," PostObject Date & Time " + postObject.getDate() + " " + postObject.getTime()) ;
        Log.e(" Adapter "," PostObject Comment " + postObject.getComment());
        holder.txtViewPosterName.setText(PosterNames.get(position));
        holder.txtViewPostDateAndTime.setText(postObject.getDate() + " " + postObject.getTime());
        holder.txtViewPostComment.setText(postObject.getComment());
    }

    @Override
    public int getItemCount()
    {
        Log.e(" Size " , "" + PosterNames.size());
        return PosterNames.size();
    }




} //PostRecyclerViewAdapter
