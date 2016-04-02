package com.example.i851409.communityapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class tweets extends AppCompatActivity implements View.OnClickListener {

    Firebase fire;
    String e;
    EditText f;
    Button send;
    Button ref;

    String formattedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweets);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent tweets_intent = getIntent();


        send = (Button) findViewById(R.id.button35);
        ref = (Button) findViewById(R.id.button36);

        fire = new Firebase("https://blinding-fire-7006.firebaseio.com/Tweets");

        final ArrayList<String> tw = new ArrayList<String>();

        fire.addValueEventListener(new ValueEventListener() {



            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Map<String, String> map = d.getValue(Map.class);


                    String a = map.get("Tweets");
                    tw.add(" Anonymous User >>                                                              "+a);

                    Collections.reverse(tw);
                }

                ArrayAdapter adapter = new ArrayAdapter<String>(tweets.this, R.layout.sing_row, R.id.textView30, tw);

                //Instantiating the ListView object
                ListView lv = (ListView) findViewById(R.id.listView9);

                //Setting the ArrayAdapter for the ListView to display the contents in the ListView fetched from the sqlite database
                lv.setAdapter(adapter);

                //Setting up a listener for each item inside the ListView
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Whenever a particular item is clicked a new activity will be invoked

                    }
                });


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }


        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                fire = new Firebase("https://blinding-fire-7006.firebaseio.com/Tweets");

                f = (EditText) findViewById(R.id.editText35);
                String e = f.getText().toString();

                // String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss", Locale.US).format(new Date());
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy h:mm:ss a");
                formattedDate = sdf.format(date);
                // System.out.println(formattedDate);

                if (TextUtils.isEmpty(e)) {
                    f.setError("Tweet Cannot Be Empty");
                } else {

                    fire = fire.child(formattedDate);
                    fire.child("Tweets").setValue(e);

                    Toast.makeText(getApplicationContext(), "Keep Tweeting ;)", Toast.LENGTH_SHORT).show();

                    //Starting the MainActivity
                    Intent it = new Intent(tweets.this, tweets.class);
                    finish();
                    startActivity(it);

                }
            }
        });

        ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "REFRESHING...", Toast.LENGTH_SHORT).show();

                //Starting the MainActivity
                Intent itt = new Intent(tweets.this, tweets.class);
                finish();
                startActivity(itt);


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_uninstall, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){

        int id = item.getItemId();


        //Statements to Uninstall the Application
        if (id == R.id.item_uninstall) {
            //Now call the appropriate Intent to uninstall the application
            startActivity(WelcomePageActivity.intent_uninstall);
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onClick(View v) {

    }
}
