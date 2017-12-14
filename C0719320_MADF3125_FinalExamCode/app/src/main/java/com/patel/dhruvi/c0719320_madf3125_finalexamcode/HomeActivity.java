package com.patel.dhruvi.c0719320_madf3125_finalexamcode;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{

    ListView mapListView;

    List<String> locationName ;
    List<String> latList ;
    List<String> longList ;

    ArrayAdapter adapter ;






    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        mapListView = (ListView)findViewById(R.id.listview_map);
        populateListView();

    }

    private void populateListView()
    {
        locationName = new ArrayList<>();
        latList = new ArrayList<>();
        longList = new ArrayList<>();


        locationName.add("My Home ");
        locationName.add("My Job");
        locationName.add("Seriden");
        locationName.add("Cenica");
        locationName.add("WaterLoo");
        locationName.add("Ajex");
        locationName.add("Toronto");
        locationName.add("Missisauga");
        locationName.add("Sarnia");

        latList.add("43.774427");
        longList.add("-79.231582");

        latList.add("43.259971");
        longList.add("-79.387057");


        //mapListAdapter = new mapViewAdapter(getApplicationContext() , locationName);
        //mapListView.setAdapter(mapListAdapter);

        adapter = new ArrayAdapter(getApplicationContext() , android.R.layout.simple_list_item_1 , locationName);
        mapListView.setOnItemClickListener(this);
        mapListView.setAdapter(adapter);



    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Log.e("Home " , "Item Click");
        Toast.makeText(getApplicationContext(), locationName.get(i) , Toast.LENGTH_LONG).show();

    }
}


/*
*
* moxdroidlabs [7:36 PM]
locationArray.append(Location(locationID: 1,locationName: "My Home",latitude: 43.774427,longitude: -79.231582))
       locationArray.append(Location(locationID: 2,locationName: "My Job",latitude: 43.259971,longitude: 43.259971))
       locationArray.append(Location(locationID: 3,locationName: "CN Tower",latitude: 43.642566,longitude: -79.387057))
       locationArray.append(Location(locationID: 4,locationName: " Niagra Fall, Canada",latitude: 43.077275,longitude: -79.075320))
       locationArray.append(Location(locationID: 5,locationName: " Lambton College In Toronto",latitude: 43.6532,longitude: -79.3832))
       locationArray.append(Location(locationID: 5,locationName: " Statue of Liberty",latitude: 40.690498,longitude: -74.046500))
       locationArray.append(Location(locationID: 5,locationName: " Eiffel Tower",latitude: 48.858376,longitude: 2.294469))
       locationArray.append(Location(locationID: 5,locationName: " Mumbai, India",latitude: 18.921984,longitude: 72.834654))
       locationArray.append(Location(locationID: 5,locationName: " Mt. Everest",latitude: 27.987850,longitude: 86.925026))
       locationArray.append(Location(locationID: 5,locationName: " Taj Mahal",latitude: 27.175015,longitude: 78.042155))*/