package com.example.i851409.communityapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

public class ProfessorDescription extends AppCompatActivity {

    String professor1_names[][] = {{"John Doe", "Mike Phelan", "Chris Benton", "Lui Wng", "Apryl John", "Lisa Jocekic"},{"Sachin Sharma", "Rohan Phatak", "Roger Boss", "Terry Luis", "David Alaba", "Cristiano Li"},{"Ji Sung", "Chris Smalling", "Neha Porwal", "Maitri Vasa", "Kashap Pandya", "Rafael Nadal"},{"Federico Costa", "Cesc Fabregas", "Peter Lu", "Rohit Verma", "Salman Khan", "Hrithik Roshan"},{"Santosh Kumar", "Carl Soane", "Ram Iyer", "Krishna Mohan", "Raj Adhiya", "Yong Kong"},{"Seetha Iyer", "Mona Khan", "Bill Gates", "Andrea Pirlo", "Balaji Films", "Ekta Kapoor"}};
    String professor2_names[][] = {{"Michael Sontoro", "Ahmed Ezzat", "Angela Musurlian", "Radhika Grover", "Akshay Khole", "Kusum Savani"},{"Keyvan Moatghed", "Vishesh Kamdar", "Scott Bennett", "Jay Shah", "Anthony Martial", "Grace Jiras"},{"Sabrina Lucius", "Naina Raut", "Adit Vira", "Richard Charles", "Jasim Kaskar", "Keylor Navas"},{"Matt Daemon", "Filix Baumb", "John Terry", "De Gea", "Wayne Rooney", "Ted Cruz"},{"Nathan Gaini", "Rahul Urmania", "Shaan Indra", "Arnab Goswami", "Peter Cech", "Thomas Mueller"},{"Arjen Robben", "Suhel Sharma", "Raj Kotak", "Vishal Kotak", "Ashok Sir", "David Luiz"}};

    String professor1_times[][] = {{"TW 7.00-9.00", "MW 8.00-10.00", "F 12.00-13.00", "Sa 13.00-17.00", "M 8.00-9.00", "TR 10.00-11.00"}, {"S 14.00-18.00", "MW 17.00-19.00", "TR 19.00-21.00", "M 6.00-9.00", "TW 13.00-15.00", "SaS 13.00-15.00"}, {"M 7.00-11.00", "TW 7.00-9.00", "WR 10.00-12.00", "TR 18.00-20.00", "WF 7.00-9.00", "S 10.00-14.00"}, {"MW 13.00-15.00", "TF 14.00-17.00", "M 10.00-14.00", "T 10.00-14.00", "W 10.00-14.00", "TF 15.00-17.00"}, {"S 10.00-14.00", "Sa 8.00-17.00", "S 8.00-17.00", "MT 7.00-9.00", "WF 19.00-21.00", "R 18.00-22.00"}, {"MW 7.00-9.00", "TR 9.00-11.00", "W 16.00-20.00", "RF 7.00-9.00", "MW 13.00-16.00", "Sa 13.00-17.00"}};
    String professor2_times[][] = {{"T 7.00-9.00", "M 8.00-10.00", "FSa 12.00-13.00", "SaS 13.00-17.00", "MW 8.00-9.00", "TF 10.00-11.00"}, {"MS 14.00-18.00", "MT 17.00-19.00", "T 19.00-21.00", "MW 6.00-9.00", "TWF 13.00-15.00", "Sa 13.00-15.00"}, {"MW 7.00-11.00", "TR 7.00-9.00", "WF 10.00-12.00", "T 18.00-20.00", "W 7.00-9.00", "Sa 10.00-14.00"}, {"MF 13.00-15.00", "TR 14.00-17.00", "MT 10.00-14.00", "TW 10.00-14.00", "WR 10.00-14.00", "TSa 15.00-17.00"}, {"Sa 10.00-14.00", "SaS 8.00-17.00", "Sa 8.00-17.00", "S 7.00-9.00", "W 19.00-21.00", "RF 18.00-22.00"}, {"WS 7.00-9.00", "TSa 9.00-11.00", "WR 16.00-20.00", "F 7.00-9.00", "MT 13.00-16.00", "Sas 13.00-17.00"}};


    TextView tv_professor1_name, tv_professor2_name;
    TextView tv_professor1_time, tv_professor2_time;

    TextView p1_mr, p1_nov, p2_mr, p2_nov;

    String professor1_name, professor2_name;

    String row_position, column_position;
    int rpos, colpos;

    String professor1_number_of_votes_old;
    String professor1_mean_rating_old;
    String professor2_number_of_votes_old;
    String professor2_mean_rating_old;

    String professor1_number_of_votes_new;
    String professor1_mean_rating_new;
    String professor2_number_of_votes_new;
    String professor2_mean_rating_new;


    Button prof1_submit, prof2_submit;

    Firebase fire, fire_professor;

    RatingBar rating_bar1, rating_bar2;
    float professor1_rating, professor2_rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.BLACK);
        toolbar.setTitle(R.string.toolbar_title);
        setSupportActionBar(toolbar);

        p1_mr = (TextView) findViewById(R.id.textView26);
        p1_nov = (TextView) findViewById(R.id.textView28);
        p2_mr = (TextView) findViewById(R.id.textView31);
        p2_nov = (TextView) findViewById(R.id.textView32);

        fire=new Firebase("https://blinding-fire-7006.firebaseio.com/User");


        //Creating the professor structure in the Firebase Database
        //Also check if the corresponding Shared Preferences file contents
        SharedPreferences settings = getSharedPreferences("commonx", MODE_PRIVATE);
        String mSilent = settings.getString("myValuex", "");
        String myArr[] = mSilent.split(":");
        String part1 = myArr[0];
        String part2 = myArr[1];

            for (int i = 0; i < professor1_names.length; i++) {
                for (int j = 0; j < professor1_names.length; j++) {
                    fire_professor = new Firebase("https://blinding-fire-7006.firebaseio.com/Professor");
                    fire_professor = fire_professor.child(professor1_names[i][j]);
                    if((part1.equals("commonx")) && (part2.equals("1"))) {
                        fire_professor.child("Number of Votes").setValue("0");
                        fire_professor.child("Mean Rating").setValue("0");

                        //Delete the old Shared Preferences file
                        settings.edit().clear().commit();

                        //Recreate the Shared Preference File for that User with Value: 2
                        SharedPreferences shared_pref1 = getSharedPreferences("commonx", MODE_PRIVATE);
                        SharedPreferences.Editor editor = shared_pref1.edit();
                        String sp_val = "commonx:2";
                        editor.putString("myValuex", sp_val);
                        editor.commit();
                    }
                }
            }
            for (int i = 0; i < professor1_names.length; i++) {
                for (int j = 0; j < professor1_names.length; j++) {
                    fire_professor = new Firebase("https://blinding-fire-7006.firebaseio.com/Professor");
                    fire_professor = fire_professor.child(professor2_names[i][j]);
                    if(part1.equals("commonx") && part2.equals("1")) {
                        fire_professor.child("Number of Votes").setValue("0");
                        fire_professor.child("Mean Rating").setValue("0");

                        //Delete the old Shared Preferences file
                        settings.edit().clear().commit();

                        //Recreate the Shared Preference File for that User with Value: 2
                        SharedPreferences shared_pref2 = getSharedPreferences("commonx", MODE_PRIVATE);
                        SharedPreferences.Editor editor = shared_pref2.edit();
                        String sp_val2 = "commonx:2";
                        editor.putString("myValuex", sp_val2);
                        editor.commit();
                    }
                }
            }



        tv_professor1_name = (TextView) findViewById(R.id.textView10);
        tv_professor2_name = (TextView) findViewById(R.id.textView20);

        tv_professor1_time = (TextView) findViewById(R.id.textView21);
        tv_professor2_time = (TextView) findViewById(R.id.textView22);

        //Receiving the intent
        Intent received_intent = getIntent();
        String position_received = received_intent.getExtras().getString("key0");

        //Splitting the received string into two indexes of the multidimensional array
        row_position = String.valueOf(position_received.charAt(0));
        column_position = String.valueOf(position_received.charAt(1));

        //Converting from String to an Integer
        rpos = Integer.parseInt(row_position);
        colpos = Integer.parseInt(column_position);

        //Setting the name of the apropriate professor in both the Text Views
        tv_professor1_name.setText(professor1_names[rpos][colpos]);
        tv_professor2_name.setText(professor2_names[rpos][colpos]);

        //Setting the class timings of both the professors in the Text Views
        tv_professor1_time.setText(professor1_times[rpos][colpos]);
        tv_professor2_time.setText(professor2_times[rpos][colpos]);

        //Instantiating the buttons
        prof1_submit = (Button) findViewById(R.id.button13);
        prof2_submit = (Button) findViewById(R.id.button12);

        rating_bar1 = (RatingBar) findViewById(R.id.ratingBar);
        rating_bar2 = (RatingBar) findViewById(R.id.ratingBar2);

        //Setting the listener to the Rating Bar 1
        rating_bar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                professor1_rating = rating;
            }
        });

        //Setting the listener to the Rating Bar 2
        rating_bar2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                professor2_rating = rating;
            }
        });

        //Setting the event listeners to both the SUBMIT buttons
        prof1_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if the User has already voted for this professor1
                fire.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot d : dataSnapshot.getChildren()) {
                            Map<String, String> map = d.getValue(Map.class);

                            if(professor1_name == null) {

                                if (d.getKey().equals(MainApplicationWindow.user_firstname)) {
                                    String vp1 = map.get("Voted_Professor1");
                                    if (!(vp1.equals("0"))) {
                                        Toast.makeText(getApplicationContext(), "You have already rated the Professor", Toast.LENGTH_SHORT).show();
                                        break;
                                    } else {
                                        //Mark as Voted for Professor1
                                        fire.child(MainApplicationWindow.user_firstname).child("Voted_Professor1").setValue("1");
                                        Toast.makeText(getApplicationContext(), "Voted Successfully", Toast.LENGTH_SHORT).show();

                                        //Calculate the Mean Rating for the Professor
                                        //Fetch the previous mean rating and no of voters of that professor
                                        fire_professor = new Firebase("https://blinding-fire-7006.firebaseio.com/Professor");
                                        professor1_name = tv_professor1_name.getText().toString();

                                        fire_professor.addValueEventListener(new ValueEventListener() {
                                            @Override

                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                for (DataSnapshot d1 : dataSnapshot.getChildren()) {
                                                    Map<String, String> map1 = d1.getValue(Map.class);
                                                    if (d1.getKey().equals(professor1_name)) {
                                                        //Fetch the old values
                                                        professor1_mean_rating_old = map1.get("Mean Rating");
                                                        professor1_number_of_votes_old = map1.get("Number of Votes");

                                                        //Calculating the new mean rating
                                                        float a1 = ((professor1_rating + (Float.parseFloat(professor1_number_of_votes_old)) * (Float.parseFloat(professor1_mean_rating_old))) / ((Float.parseFloat(professor1_number_of_votes_old)) + 1.0f));
                                                        professor1_mean_rating_new = Float.toString(a1);

                                                        //Insert the updated values inside the Firebase database
                                                        fire_professor.child(professor1_name).child("Mean Rating").setValue(professor1_mean_rating_new);
                                                        professor1_number_of_votes_new = Integer.toString(Integer.parseInt(professor1_number_of_votes_old) + 1);
                                                        fire_professor.child(professor1_name).child("Number of Votes").setValue(Integer.toString(Integer.parseInt(professor1_number_of_votes_old) + 1));

                                                        //Displaying the updated values in the TextView
                                                        p1_mr.setText(professor1_mean_rating_new);
                                                        p1_nov.setText(professor1_number_of_votes_new);

                                                        //Testing purpose
                                                        professor1_name = "";

                                                        break;
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(FirebaseError firebaseError) {

                                            }
                                        });
                                    }
                                }
                            }
                            break;
                            }
                        }

                        @Override
                        public void onCancelled (FirebaseError firebaseError){
                        }
                    }

                    );
                }
            });

                prof2_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Check if the User has already voted for this professor2
                        fire.addValueEventListener(new ValueEventListener() {
                            @Override

                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot d: dataSnapshot.getChildren()){
                                    Map<String, String> map = d.getValue(Map.class);

                                    if(professor2_name == null) {
                                        if (d.getKey().equals(MainApplicationWindow.user_firstname)) {
                                            String vp1 = map.get("Voted_Professor2");
                                            if (!(vp1.equals("0"))) {
                                                Toast.makeText(getApplicationContext(), "You have already rated the Professor", Toast.LENGTH_SHORT).show();
                                                break;
                                            } else {
                                                //Mark as Voted for Professor2
                                                fire.child(MainApplicationWindow.user_firstname).child("Voted_Professor2").setValue("1");
                                                Toast.makeText(getApplicationContext(), "Voted Successfully", Toast.LENGTH_SHORT).show();

                                                //Calculate the Mean Rating for the Professor
                                                //Fetch the previous mean rating and no of voters of that professor
                                                fire_professor = new Firebase("https://blinding-fire-7006.firebaseio.com/Professor");
                                                professor2_name = tv_professor2_name.getText().toString();

                                                fire_professor.addValueEventListener(new ValueEventListener() {
                                                    @Override

                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        for (DataSnapshot d2 : dataSnapshot.getChildren()) {
                                                            Map<String, String> map2 = d2.getValue(Map.class);
                                                            if (d2.getKey().equals(professor2_name)) {
                                                                //Fetch the old values
                                                                professor2_mean_rating_old = map2.get("Mean Rating");
                                                                professor2_number_of_votes_old = map2.get("Number of Votes");

                                                                //Calculating the new mean rating
                                                                float a2 = ((professor2_rating + (Float.parseFloat(professor2_number_of_votes_old)) * (Float.parseFloat(professor2_mean_rating_old))) / ((Float.parseFloat(professor2_number_of_votes_old)) + 1.0f));
                                                                professor2_mean_rating_new = Float.toString(a2);

                                                                //Insert the updated values inside the Firebase database
                                                                fire_professor.child(professor2_name).child("Mean Rating").setValue(professor2_mean_rating_new);
                                                                professor2_number_of_votes_new = Integer.toString(Integer.parseInt(professor2_number_of_votes_old) + 1);
                                                                fire_professor.child(professor2_name).child("Number of Votes").setValue(Integer.toString(Integer.parseInt(professor2_number_of_votes_old) + 1));

                                                                //Displaying the updated values in the TextView
                                                                p2_mr.setText(professor2_mean_rating_new);
                                                                p2_nov.setText(professor2_number_of_votes_new);

                                                                //Testing purpose
                                                                professor2_name = "";

                                                                break;
                                                            }
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(FirebaseError firebaseError) {

                                                    }
                                                });

                                                break;
                                            }
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {
                            }
                        });
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        //Statements to Uninstall the Application
        if(id == R.id.item_uninstall){
            //Now call the appropriate Intent to uninstall the application
            startActivity(WelcomePageActivity.intent_uninstall);
        }

        return super.onOptionsItemSelected(item);
    }

}
