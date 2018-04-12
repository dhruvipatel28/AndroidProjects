package com.example.indravadan.androidexamvalley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
{

    RequestQueue RQ;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callJson();

        Button btn = findViewById(R.id.buttonClick);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("-----------", "----------CLick");
                callJson();

            }
        });

            //Log.e("-----------", "----------CLick");
    }

    public void callJson()
    {

        Log.e("-----------", "----------  call json");
        if(RQ == null)
        {
            RQ = Volley.newRequestQueue(getApplicationContext());
        }

        String url = "https://api.darksky.net/forecast/ede17adca3ab4fe466ab84c31ee04366/48.8566,2.3522";

        JsonObjectRequest JR;
        JR = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Log.e("-----",response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("-----",error.toString());
                    }
                }


        );

        RQ.add(JR);
    }//callJSon



}
