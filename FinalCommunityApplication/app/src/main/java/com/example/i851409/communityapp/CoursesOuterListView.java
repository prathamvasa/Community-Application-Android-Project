package com.example.i851409.communityapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class CoursesOuterListView extends AppCompatActivity {

    String courses_array[] = new String[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_outer_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.BLACK);
        toolbar.setTitle(R.string.toolbar_title);
        setSupportActionBar(toolbar);

        //Initializing the array consisting of all the courses
        courses_array[0] = "College of Arts";
        courses_array[1] = "Continuing Education";
        courses_array[2] = "Counseling Psychology";
        courses_array[3] = "Leavy School of Business";
        courses_array[4] = "School of Engineering";
        courses_array[5] = "School of Law";

        //Instantiating the ListView
        ListView lv_courses = (ListView) findViewById(R.id.listView2);

        //Instantiating the ArrayAdapter
        ArrayAdapter<String> adapter_courses = new ArrayAdapter<String>(this, R.layout.lv_simplified_courses, R.id.textView12, courses_array);

        //Assigning Adapter to the ListView
        lv_courses.setAdapter(adapter_courses);

        //Setting a listener to the ListView items
        lv_courses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Depending upon the position, start the new Activity

                //If the user selects a course from College of Arts
                if(position == 0){
                    //Start a new activity that will contain related departments
                    Intent coa_intent = new Intent(CoursesOuterListView.this, CoaListView.class);
                    startActivity(coa_intent);
                }
                //If the user selects a course from Continuing Education
                else if(position == 1){
                    //Start a new activity that will contain related departments
                    Intent ce_intent = new Intent(CoursesOuterListView.this, CeListView.class);
                    startActivity(ce_intent);
                }
                //If the user selects a course from Counseling Psychology
                else if(position == 2){
                    //Start a new activity that will contain related departments
                    Intent cp_intent = new Intent(CoursesOuterListView.this, CpListView.class);
                    startActivity(cp_intent);
                }
                //If the user selects a course from Leavy School of Business
                else if(position == 3){
                    //Start a new activity that will contain related departments
                    Intent lsob_intent = new Intent(CoursesOuterListView.this, LsobListView.class);
                    startActivity(lsob_intent);
                }
                //If the user selects a course from School of Engineering
                else if(position == 4){
                    //Start a new activity that will contain related departments
                    Intent soe_intent = new Intent(CoursesOuterListView.this, SoeListView.class);
                    startActivity(soe_intent);
                }
                //If the user selects a course from School of Law
                else{
                    //Start a new activity that will contain related departments
                    Intent sol_intent = new Intent(CoursesOuterListView.this, SolListView.class);
                    startActivity(sol_intent);
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
