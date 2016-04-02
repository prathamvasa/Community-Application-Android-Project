package com.example.i851409.communityapp;

import android.content.Intent;
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

public class Emergency extends AppCompatActivity {

    public Button Phonecalls;
    public Button Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Phonecalls = (Button)findViewById(R.id.button12);

        Phone = (Button)findViewById(R.id.button13);

        Phonecalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callInt = new Intent(Intent.ACTION_DIAL);
                callInt.setData(Uri.parse("tel:408-554-4444"));

                try {
                    startActivity(callInt);
                } catch (Exception e) {
                    Toast.makeText(Emergency.this, "e + " + e, Toast.LENGTH_LONG).show();
                }
            }
        });

        Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callInt = new Intent(Intent.ACTION_DIAL);
                callInt.setData(Uri.parse("tel:911"));

                try {
                    startActivity(callInt);
                } catch (Exception e) {
                    Toast.makeText(Emergency.this, "e + " + e, Toast.LENGTH_LONG).show();
                }
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
