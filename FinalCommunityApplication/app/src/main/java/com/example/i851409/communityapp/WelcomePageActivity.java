package com.example.i851409.communityapp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class WelcomePageActivity extends AppCompatActivity {


    Button login_button, signup_button;
    Intent intent_login, intent_signup;
    static Uri package_uri = Uri.parse("package:com.example.i851409.communityapp");
    static Intent intent_uninstall = new Intent(Intent.ACTION_DELETE, package_uri);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.BLACK);
        toolbar.setTitle(R.string.toolbar_title);
        setSupportActionBar(toolbar);

        //Adding an OnClick Event Listener to the LogIn button
        login_button = (Button) findViewById(R.id.button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override

            //On clicking the LogIn button the user will be taken to the LogIn Page
            public void onClick(View v) {
                intent_login = new Intent(WelcomePageActivity.this, LoginActivity.class);
                startActivity(intent_login);
            }
        });


        //Adding an OnClick Event Listener to the SignUp button
        signup_button = (Button) findViewById(R.id.button2);
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override

            //On clicking the SignUp button the user will be taken to the SignUp Page
            public void onClick(View v) {
                intent_signup = new Intent(WelcomePageActivity.this, SignUpActivity.class);
                startActivity(intent_signup);
            }
        });
    }

    @Override
    //This method deals with the menu items present on the ToolBar
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_uninstall, menu);
        return true;
    }

    @Override
    //This method sets the listener for triggering any events on the menu items present on the ToolBar
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        //Statements to Uninstall the Application
        if(id == R.id.item_uninstall){
            //Now call the appropriate Intent to uninstall the application
            startActivity(intent_uninstall);
        }

        return super.onOptionsItemSelected(item);
    }
}
