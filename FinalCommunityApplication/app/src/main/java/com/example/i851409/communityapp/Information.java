package com.example.i851409.communityapp;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

public class Information extends AppCompatActivity {

    Button Phone1;
    Button email;
    Button pl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Phone1 = (Button)findViewById(R.id.button19);

        pl = (Button)findViewById(R.id.playing);
        email = (Button)findViewById(R.id.button17);
        Phone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callInt = new Intent(Intent.ACTION_DIAL);
                callInt.setData(Uri.parse("tel:6692378242"));

                try {
                    startActivity(callInt);
                } catch (Exception e) {
                    Toast.makeText(Information.this, "e + " + e, Toast.LENGTH_LONG).show();
                }
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email_intent = new Intent(Information.this,Email.class);

                startActivity(email_intent);
            }
        });


        pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent play_intent = new Intent(Information.this,Video.class);
                startActivity(play_intent);

            }
        });

    }

    @Override
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

}



