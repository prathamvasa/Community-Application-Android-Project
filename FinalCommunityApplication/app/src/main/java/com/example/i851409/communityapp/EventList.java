package com.example.i851409.communityapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class EventList extends AppCompatActivity implements View.OnClickListener
{

    Firebase fire;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fire=new Firebase("https://blinding-fire-7006.firebaseio.com/Event");

        final ArrayList<String> title = new ArrayList<String>();
        final ArrayList<String> location = new ArrayList<String>();
        final ArrayList<String> description = new ArrayList<String>();

        fire.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Map<String, String> map = d.getValue(Map.class);

                    String a = map.get("EventTitle");
                    title.add(a);
                    String b = map.get("EventLocation");
                    location.add(b);
                    String c = map.get("EventDescription");
                    description.add(c);
                }

                ArrayAdapter adapter = new ArrayAdapter<String>(EventList.this, R.layout.single_row, R.id.tv, title);

                //Instantiating the ListView object
                ListView lv = (ListView) findViewById(R.id.listView);

                //Setting the ArrayAdapter for the ListView to display the contents in the ListView fetched from the sqlite database
                lv.setAdapter(adapter);

                //Setting up a listener for each item inside the ListView
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Whenever a particular item is clicked a new activity will be invoked

                        Intent intent2 = new Intent(EventList.this, EventView.class);
                        intent2.putExtra("key1", title.get(position));
                        intent2.putExtra("key2", location.get(position));
                        intent2.putExtra("key3", description.get(position));

                        //Launching the activity PhotoDescriptionActivity
                        startActivity(intent2);
                    }
                });
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(EventList.this, EventDescription.class);
                startActivity(intent1);
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

    @Override
    public void onClick(View v) {

    }
}
