package dhruvipatel.c0719320_cricketplayerrank.Database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dhruvipatel.c0719320_cricketplayerrank.Model.Players;

public class DatabaseHandler extends SQLiteOpenHelper
{


    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "CRICKET";

    // Contacts table name
    private static final String TABLE_PLAYERS = "Players";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_BIRTHDATE = "birthdate";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_TEAM = "team";
    private static final String KEY_TEST = "test";
    private static final String KEY_ONEDAY = "one_day";
    private static final String KEY_CATCH = "catch";
    private static final String KEY_RUNS = "runs";
    private static final String KEY_WICKET = "wicket";
    private static final String KEY_STUMING = "stumping";
    private static final String KEY_TOTAL_POINT = "total_points";


    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_PLAYERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_GENDER + " TEXT,"
                + KEY_BIRTHDATE + " TEXT,"
                + KEY_CATEGORY + " TEXT,"
                + KEY_TEAM + " TEXT,"
                + KEY_TEST + " INTEGER,"
                + KEY_ONEDAY + " INTEGER,"
                + KEY_CATCH + " INTEGER,"
                + KEY_RUNS  + " INTEGER,"
                + KEY_WICKET  + " INTEGER,"
                + KEY_STUMING   + " INTEGER,"
                + KEY_TOTAL_POINT    + " INTEGER"


                + ")";
        Log.e("My Create Query  : " , CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        // TODO  ENable execution
    } //oncreate

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public void addPlayers(Players players)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, players.getName()); // Players Name
        values.put(KEY_GENDER, players.getGender()); // Players gender
        values.put(KEY_BIRTHDATE, players.getBirthdate()); // Players birthdate
        values.put(KEY_CATEGORY, players.getCategory()); // Players category
        values.put(KEY_TEAM, players.getTeam()); // Players team

        values.put(KEY_TEST, players.getTestMatch()); // Players testmatch
        values.put(KEY_ONEDAY, players.getOneDayMatch()); // Players oneday
        values.put(KEY_CATCH, players.getNoOfCatch()); // Players  catch
        values.put(KEY_RUNS, players.getNoOfRuns()); // Players  runs
        values.put(KEY_WICKET, players.getNoOfWickets()); // Players  wickets
        values.put(KEY_STUMING, players.getNoOfStuming()); // Players  stump

        int total_points = 0;

        total_points += players.getTestMatch()*5;
        total_points += players.getOneDayMatch()*2;
        total_points += players.getNoOfCatch()*3;
        total_points += players.getNoOfRuns()*1;
        total_points += players.getNoOfWickets()*5;
        total_points += players.getNoOfStuming()*3;

        Log.e("Total Points  : " ,  total_points + "" );


        players.setTotalPoints(total_points);
        values.put(KEY_TOTAL_POINT, total_points ); // Players  total


        Log.e("My Insert  Query  : " , values.toString());

        // Inserting Row
        db.insert(TABLE_PLAYERS, null, values);
        //TODO enable exicution
        db.close(); // Closing database connection
    }//addPlayers

    public List<Players> getAllPlayerList()
    {
        List<Players> playerList = new ArrayList<Players>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PLAYERS + " ORDER BY " + KEY_TOTAL_POINT + "  DESC" ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do
            {
                Players player = new Players();
                player.setId(Integer.parseInt(cursor.getString(0)));

                player.setName(cursor.getString(1));
                player.setGender(cursor.getString(2));
                player.setBirthdate(cursor.getString(3));
                player.setCategory(cursor.getString(4));
                player.setTeam(cursor.getString(5));


                /*values.put(KEY_TEST, players.getTestMatch()); // Players testmatch
                values.put(KEY_ONEDAY, players.getOneDayMatch()); // Players oneday
                values.put(KEY_CATCH, players.getNoOfCatch()); // Players  catch
                values.put(KEY_RUNS, players.getNoOfRuns()); // Players  runs
                values.put(KEY_WICKET, players.getNoOfWickets()); // Players  wickets
                values.put(KEY_STUMING, players.getNoOfStuming()); // Players  stump
                */
                player.setTestMatch(cursor.getInt(6));
                player.setOneDayMatch(cursor.getInt(7));
                player.setNoOfCatch(cursor.getInt(8));
                player.setNoOfRuns(cursor.getInt(9));
                player.setNoOfWickets(cursor.getInt(10));
                player.setNoOfStuming(cursor.getInt(11));
                player.setTotalPoints(cursor.getInt(12));


                // Adding contact to list
                playerList.add(player);
            } while (cursor.moveToNext());
        }
        Log.e("------------- " , cursor.toString());
        return playerList;
    }



}
