package com.example.i851409.communityapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainApplicationWindow extends AppCompatActivity {

    GridView cgv;
    String title_array[] = new String[6];
    int image_id_array[] = new int[6];
    Resources res;
    static String user_firstname;
    TextView firstname_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_application_window);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.BLACK);
        toolbar.setTitle(R.string.toolbar_title);
        setSupportActionBar(toolbar);

        //Receiving the intent from either the Login Page or the Sign Up Page
        Intent intent_rec = getIntent();
        user_firstname = intent_rec.getStringExtra("firstname_key");

        //Loading and setting up the data sources
        //Fetching the values from the string-array and assigning it to the above created String array
        res = getResources();
        title_array = res.getStringArray(R.array.items_gridview);

        firstname_tv = (TextView) findViewById(R.id.textView);
        firstname_tv.setText("Welcome " + user_firstname);

        image_id_array[0] = R.drawable.chat_icon;
        image_id_array[1] = R.drawable.events_icon;
        image_id_array[2] = R.drawable.courses;
        image_id_array[3] = R.drawable.weather_icon;
        image_id_array[4] = R.drawable.information_icon;
        image_id_array[5] = R.drawable.emergency_icon;

        //Instantiating the GridView object
        cgv = (GridView) findViewById(R.id.gridView);

        //Instantiating the customized adapter
        MyCustomAdapter mca = new MyCustomAdapter(this, title_array, image_id_array);

        //Setting the customized adapter for the Grid View
        cgv.setAdapter(mca);

        //Setting the listener to individual items of the GridView
        cgv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Now depending upon which position is clicked, display the appropriate activity

                //If the user decides to read the tweets
                if(position == 0){
                    Intent tweets_intent = new Intent(MainApplicationWindow.this,tweets.class);

                    startActivity(tweets_intent);
                }

                //If the user decides to view the events
                else if(position == 1){
                    //Toast.makeText(getApplicationContext(), title_array[position], Toast.LENGTH_SHORT).show();

                    Intent event_intent = new Intent(MainApplicationWindow.this,EventList.class);

                    startActivity(event_intent);
                }

                //If the user decides to view the courses offered in the University
                else if(position == 2){
                    //Toast.makeText(getApplicationContext(), title_array[position], Toast.LENGTH_SHORT).show();

                    //Instantiate the intent to display the courses
                    Intent course_intent = new Intent(MainApplicationWindow.this, CoursesOuterListView.class);

                    //Start the activity
                    startActivity(course_intent);
                }

                //If the user wants to view the Weather around Santa Clara University
                else if(position == 3){
                    //Toast.makeText(getApplicationContext(), title_array[position], Toast.LENGTH_SHORT).show();

                    //Instantiate the Intent to Display the Weather
                    Intent weather_intent = new Intent(MainApplicationWindow.this, WeatherActivity.class);
                    startActivity(weather_intent);
                }

                //If the user wants Information about the Application Developers
                else if(position == 4){
                    Intent info_intent = new Intent(MainApplicationWindow.this,Information.class);

                    startActivity(info_intent);
                }

                //If the user wants to view the Emergency Contacts
                else{
                    Intent em_intent = new Intent(MainApplicationWindow.this,Emergency.class);

                    startActivity(em_intent);
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


//Class implementation for the Customized Adapter
class MyCustomAdapter extends ArrayAdapter<String> {
    Context context;
    int images[];
    String tls[];
    MyCustomAdapter(Context c, String[] titles, int img[]){
        super(c, R.layout.customized_gridview, R.id.textView2, titles);
        this.context = c;
        this.images = img;
        this.tls = titles;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.customized_gridview, parent, false);

        //Setting the background color of the row
        row.setBackgroundColor(Color.WHITE);

        //Getting the reference to the TextView and the ImageView
        ImageView iv = (ImageView) row.findViewById(R.id.imageView);
        TextView tv = (TextView) row.findViewById(R.id.textView2);

        //Populating the ImageView and the TextView with appropriate data
        iv.setImageResource(images[position]);
        tv.setText(tls[position]);

        return row;
    }

}
