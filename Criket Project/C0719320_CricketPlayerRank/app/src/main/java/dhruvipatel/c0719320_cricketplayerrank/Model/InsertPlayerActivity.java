package dhruvipatel.c0719320_cricketplayerrank.Model;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dhruvipatel.c0719320_cricketplayerrank.Database.DatabaseHandler;
import dhruvipatel.c0719320_cricketplayerrank.PlayersActivity;
import dhruvipatel.c0719320_cricketplayerrank.R;

public class InsertPlayerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    DatabaseHandler databaseHandler;

    EditText tv_name , tv_birthdate, tv_testmatch, tv_oneDay , tv_catch , tv_runs , tv_wicket, tv_stump;
    Button btn_insert , btn_show_all;
    Spinner sp_gender, sp_category, sp_country;

    List<String> gender_list = new ArrayList<String>();
    List<String> category_list = new ArrayList<String>();
    List<String> country_list = new ArrayList<String>();

    String gender, category, country;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_player_layout);

        getSupportActionBar().setTitle("IPL 2017");
        getSupportActionBar().setLogo(R.drawable.ipl);

        databaseHandler  = new DatabaseHandler(this);

        tv_name = (EditText) findViewById(R.id.tv_name);
        tv_birthdate = (EditText) findViewById(R.id.tv_bithdate);
        tv_testmatch = (EditText) findViewById(R.id.tv_testmatch);
        tv_oneDay   = (EditText) findViewById(R.id.tv_oneday);
        tv_catch = (EditText) findViewById(R.id.tv_catch);
        tv_runs = (EditText) findViewById(R.id.tv_runs);
        tv_wicket = (EditText) findViewById(R.id.tv_wicket);
        tv_stump    = (EditText) findViewById(R.id.tv_stumping);

        btn_insert = (Button)findViewById(R.id.btn_insertPlayers);
        btn_show_all = (Button)findViewById(R.id.btn_show_all);

        sp_gender = (Spinner)findViewById(R.id.spinner_gender);
        sp_category = (Spinner)findViewById(R.id.spinner_category);
        sp_country = (Spinner)findViewById(R.id.spinner_country);

        sp_gender.setOnItemSelectedListener(this);
        sp_category.setOnItemSelectedListener(this);
        sp_country.setOnItemSelectedListener(this);


        init();

        btn_insert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.e(" ---" ,"Name : " + tv_name.getText().toString());
                Log.e(" ---" ,"Gender : " + gender);
                Log.e(" ---" , tv_birthdate.getText().toString());
                Log.e(" ---" ,"category : " + category);
                Log.e(" ---" ,"country : " + country);
                Log.e(" ---" , tv_testmatch.getText().toString());
                Log.e(" ---" , tv_oneDay.getText().toString());
                Log.e(" ---" , tv_catch.getText().toString());
                Log.e(" ---" , tv_runs.getText().toString());
                Log.e(" ---" , tv_wicket.getText().toString());
                Log.e(" ---" , tv_stump.getText().toString());


                databaseHandler.addPlayers(new Players(1, tv_name.getText().toString()
                            , gender
                            , tv_birthdate.getText().toString()
                            , category
                            , country
                            , Integer.parseInt(tv_testmatch.getText().toString())
                            , Integer.parseInt(tv_oneDay.getText().toString())
                            , Integer.parseInt(tv_catch.getText().toString())
                            , Integer.parseInt(tv_runs.getText().toString())
                            , Integer.parseInt(tv_wicket.getText().toString())
                            , Integer.parseInt(tv_stump.getText().toString())

                ));
            }


        }); //btn_inster

        btn_show_all.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getApplicationContext(), PlayersActivity.class));
            }
        });




    }


    void init()
    {
        gender_list.add("Male");
        gender_list.add("Female");

        ArrayAdapter<String > genderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gender_list);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_gender.setAdapter(genderAdapter);

        category_list.add("Batsman");
        category_list.add("Bowler");
        category_list.add("Wicket keeper");

        ArrayAdapter<String >   categoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, category_list);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_category.setAdapter(categoryAdapter);

        country_list.add("India");
        country_list.add("Brazil");
        country_list.add("Canada");
        country_list.add("England");

        ArrayAdapter<String >   CountryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, country_list);
        CountryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_country.setAdapter(CountryAdapter);


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        Spinner spin = (Spinner)parent;
        switch (spin.getId())
        {
            case R.id.spinner_gender:
                Toast.makeText(this, "Your choose :" +  gender_list.get(position).toString() ,Toast.LENGTH_SHORT).show();
                gender = gender_list.get(position).toString();
                break;
            case R.id.spinner_category:
                Toast.makeText(this, "Your choose :" +  category_list.get(position).toString() ,Toast.LENGTH_SHORT).show();
                category = category_list.get(position).toString();
                break;
            case R.id.spinner_country:
                Toast.makeText(this, "Your choose :" +  country_list.get(position).toString() ,Toast.LENGTH_SHORT).show();
                country = country_list.get(position).toString();
                break;
        }
        /*if(spin2.getId() == R.id.sp_country)
        {
            Toast.makeText(this, "Your choose :" + country_list.get(position).toString(),Toast.LENGTH_SHORT).show();
        }*/
        //String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }
}
