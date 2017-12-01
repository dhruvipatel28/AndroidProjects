package com.example.patel.dhruvi.sqlight.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.patel.dhruvi.sqlight.Model.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macstudent on 2017-11-24.
 */

public class DatabaseHandler extends SQLiteOpenHelper
{
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "LitterControl";

    // Contacts table name
    private static final String TABLE_POST = "post";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_TIME = "time";
    private static final String KEY_DATE = "date";
    private static final String KEY_PATH = "path";
    private static final String KEY_COMMENT = "comment";


    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //Log.e("My Create Query  : " , " $$$$$$$$$$$$$$ , $$$$$$$$$$$$$$ , $$$$$$$$$$$$$$ , $$$$$$$$$$$$$$");
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_POST + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_TIME + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_PATH + " TEXT,"
                + KEY_COMMENT + " TEXT"
                + ")";
        //Log.e("My Create Query  : " , CREATE_USER_TABLE);
        db.execSQL(CREATE_USER_TABLE);
    }


    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_POST);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    // Adding new post
    public void addPost(Post post)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, post.getName()); // Post Name
        values.put(KEY_TIME, post.getTime()); // Post time
        values.put(KEY_DATE, post.getDate()); // Post date
        values.put(KEY_PATH, post.getPath()); // Post path
        values.put(KEY_COMMENT, post.getComment()); // Post comment

        // Inserting Row
        db.insert(TABLE_POST, null, values);
        db.close(); // Closing database connection
    }

    // Getting single post
    public Post getPost(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_POST, new String[]{
                        KEY_ID,
                        KEY_NAME,
                        KEY_TIME,
                        KEY_DATE,
                        KEY_PATH,
                        KEY_COMMENT
        }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Post post = new Post(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1) //name
                , cursor.getString(2) // time
                , cursor.getString(3) //date
                , cursor.getString(4) //path
                , cursor.getString(5) //comment
                ) ;
                //new Post();

        return post;
    }

    // Getting All post
    public List<Post> getAllPost()
    {
        List<Post> postList = new ArrayList<Post>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_POST;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do {
                Post post = new Post();
                post.setId(Integer.parseInt(cursor.getString(0)));
                post.setName(cursor.getString(1));
                post.setTime(cursor.getString(2));
                post.setDate(cursor.getString(3));
                post.setPath(cursor.getString(4));
                post.setComment(cursor.getString(5));
                // Adding contact to list
                postList.add(post);
            } while (cursor.moveToNext());
        }
        //Log.e("------------- " , cursor.toString());
        return postList;
    }

    // Getting post Count
    public int getPostCount()
    {

        String countQuery = "SELECT  * FROM " + TABLE_POST;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();

    }

    // Updating single post
    public int updatePost(Post post)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, post.getName());
        values.put(KEY_TIME, post.getTime());
        values.put(KEY_DATE, post.getDate());
        values.put(KEY_PATH, post.getPath());
        values.put(KEY_COMMENT, post.getComment());

        // updating row
        return db.update(TABLE_POST, values, KEY_ID + " = ?",
                new String[] { String.valueOf(post.getId()) });
    }

    // Deleting single post
    public void deletePost(Post post)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_POST, KEY_ID + " = ?",
                new String[] { String.valueOf(post.getId()) });
        db.close();
    }

}// DatabaseHandler
