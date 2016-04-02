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

public class SoeListView extends AppCompatActivity {

    String courses_array[] = new String[6];
    String e00 = "40";
    String e01 = "41";
    String e02 = "42";
    String e03 = "43";
    String e04 = "44";
    String e05 = "45";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_outer_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.BLACK);
        toolbar.setTitle(R.string.toolbar_title);
        setSupportActionBar(toolbar);

        //Initializing the array consisting of all the courses
        courses_array[0] = "Mathematics";
        courses_array[1] = "BioEngineering";
        courses_array[2] = "Civil Engineering";
        courses_array[3] = "Computer Engineering";
        courses_array[4] = "Electrical Engineering";
        courses_array[5] = "Engineering Management";

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
                    Intent coa_intent = new Intent(SoeListView.this, ProfessorDescription.class);
                    coa_intent.putExtra("key0", e00);
                    startActivity(coa_intent);
                }
                //If the user selects a course from Continuing Education
                else if(position == 1){
                    //Start a new activity that will contain related departments
                    Intent ce_intent = new Intent(SoeListView.this, ProfessorDescription.class);
                    ce_intent.putExtra("key0", e01);
                    startActivity(ce_intent);
                }
                //If the user selects a course from Counseling Psychology
                else if(position == 2){
                    //Start a new activity that will contain related departments
                    Intent cp_intent = new Intent(SoeListView.this, ProfessorDescription.class);
                    cp_intent.putExtra("key0", e02);
                    startActivity(cp_intent);
                }
                //If the user selects a course from Leavy School of Business
                else if(position == 3){
                    //Start a new activity that will contain related departments
                    Intent lsob_intent = new Intent(SoeListView.this, ProfessorDescription.class);
                    lsob_intent.putExtra("key0", e03);
                    startActivity(lsob_intent);
                }
                //If the user selects a course from School of Engineering
                else if(position == 4){
                    //Start a new activity that will contain related departments
                    Intent soe_intent = new Intent(SoeListView.this, ProfessorDescription.class);
                    soe_intent.putExtra("key0", e04);
                    startActivity(soe_intent);
                }
                //If the user selects a course from School of Law
                else{
                    //Start a new activity that will contain related departments
                    Intent sol_intent = new Intent(SoeListView.this, ProfessorDescription.class);
                    sol_intent.putExtra("key0", e05);
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
