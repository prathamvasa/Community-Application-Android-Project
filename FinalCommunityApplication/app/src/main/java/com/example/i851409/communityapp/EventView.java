package com.example.i851409.communityapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.util.Map;

public class EventView extends AppCompatActivity implements View.OnClickListener {

    TextView tvr;
    TextView tvr1;
    TextView tvr2;
    ImageView ivr;
    Button map;
    Firebase fire;
    String e;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fire=new Firebase("https://blinding-fire-7006.firebaseio.com/Event");
        Intent intent3 = getIntent();
        final String easyPuzzle =  intent3.getExtras().getString("key1");
        final String easyPuzzle1 = intent3.getExtras().getString("key2");
        final String easyPuzzle2 = intent3.getExtras().getString("key3");
        tvr = (TextView) findViewById(R.id.textView4);
        tvr1 = (TextView) findViewById(R.id.textView5);
        tvr2 = (TextView) findViewById(R.id.textView6);
        tvr.setText(easyPuzzle);
        tvr1.setText(easyPuzzle2);
        tvr2.setText(easyPuzzle1);
        map = (Button)findViewById(R.id.b3);
        map.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent y = new Intent(EventView.this,EventMaps.class);

                String t = tvr1.getText().toString();
                y.putExtra("h", t);
                startActivity(y);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_uninstall, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //Statements to Uninstall the Application
        if(id == R.id.item_uninstall){
            //Now call the appropriate Intent to uninstall the application
            startActivity(WelcomePageActivity.intent_uninstall);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    { }
}
