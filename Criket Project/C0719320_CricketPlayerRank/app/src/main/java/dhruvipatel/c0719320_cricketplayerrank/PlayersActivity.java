package dhruvipatel.c0719320_cricketplayerrank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import dhruvipatel.c0719320_cricketplayerrank.Database.DatabaseHandler;
import dhruvipatel.c0719320_cricketplayerrank.Model.Players;

public class PlayersActivity extends AppCompatActivity
{
    DatabaseHandler db ;

    List<Players> playersList;
    List<String> records ;
    List<Integer> ids;

    ListView lv_player;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.players_layout);
        getSupportActionBar().setTitle("IPL 2017");
        getSupportActionBar().setLogo(R.drawable.ipl);
        db = new DatabaseHandler(this);

        lv_player = (ListView)findViewById(R.id.player_listview);

        playersList = new ArrayList<Players>();
        records = new ArrayList<String>();
        ids = new ArrayList<>();

        lv_player.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id)
            {
                Players pl = playersList.get(position);
               Log.e("Name  : " + ids.get(position).toString() , "Total points  : " + pl.getTotalPoints() + "" );
                String str = "Name  : " + ids.get(position).toString() + "Total points  : " + pl.getTotalPoints() + "" ;

                Toast.makeText(getApplicationContext(),  str + " " ,Toast.LENGTH_SHORT).show();
            }
        });

        readPost();
    }


    void readPost()
    {
        //Log.e("Reading: ", "Reading all contacts..");
        playersList = db.getAllPlayerList();
        for (Players cn : playersList)
        {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getName() + " ,Points  : " + cn.getTotalPoints();

            records.add(log);
            ids.add(cn.getId());

            Log.e("Name: ", log);
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.listview_cell,records);
        lv_player.setAdapter(adapter);
    }



}
