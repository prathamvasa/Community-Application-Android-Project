package com.example.i851409.communityapp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
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
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Firebase fire = null;
    EditText ed_username, ed_password, ed_firstname;
    Button btn_login;
    Resources res;
    String str_username, str_password, str_firstname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialize firebase library with android context
        Firebase.setAndroidContext(this);
        fire=new Firebase("https://blinding-fire-7006.firebaseio.com");
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.BLACK);
        toolbar.setTitle(R.string.toolbar_title);
        setSupportActionBar(toolbar);

        //Getting the associated Resources
        res = getResources();

        //Instantiating the widgets
        ed_username = (EditText) findViewById(R.id.editText);
        ed_password = (EditText) findViewById(R.id.editText2);
        ed_firstname = (EditText) findViewById(R.id.editText10);
        btn_login = (Button) findViewById(R.id.button3);

        //Setting an onClick() Event Listener to the Log In Button
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fetching the contents of the Username and Password fields
                str_username = ed_username.getText().toString();
                str_password = ed_password.getText().toString();
                str_firstname = ed_firstname.getText().toString();

                //Check for all the Validations

                //Validation 1: Checking if all the fields together are empty or not
                if((!validateUsernameForEmpty(str_username))&&(!validatePasswordForEmpty(str_password))&&(str_username.equals(""))){
                    Toast.makeText(getApplicationContext(), res.getString(R.string.warning_fields), Toast.LENGTH_SHORT).show();
                }

                //Validation 2 : Checking if only the Username field is empty
                else if(!validateUsernameForEmpty(str_username)){
                    Toast.makeText(getApplicationContext(), res.getString(R.string.warning_username_only), Toast.LENGTH_SHORT).show();
                }

                //Validation 3: Checking if only the Password field is EMPTY
                else if(!validatePasswordForEmpty(str_password)){
                    Toast.makeText(getApplicationContext(), res.getString(R.string.warning_password_only), Toast.LENGTH_SHORT).show();
                }

                //Validation to check if only the Firstname is empty
                else if(str_username.equals("")){
                    Toast.makeText(getApplicationContext(), "First Name cannot be ampty", Toast.LENGTH_SHORT).show();
                }

                //Validation 4: Checking if the matching Username and Password Combinations are present inside the Firebase Database
                else{

                    //Setting up an Event Listener to the Firebase Object to check this Validation
                    fire.authWithPassword(str_username, str_password, new Firebase.AuthResultHandler() {
                        @Override
                        //If the user is successfully authenticated, go to the main page
                        public void onAuthenticated(AuthData authData) {

                            Intent t = new Intent(LoginActivity.this, MainApplicationWindow.class);
                            t.putExtra("firstname_key", str_firstname);
                            //Destroying the current activity
                            finish();
                            //Starting the Main Window Activity
                            startActivity(t);
                        }
                        @Override
                        //If matching credentials are not found then show an error message and stay on the same page
                        public void onAuthenticationError(FirebaseError firebaseError) {
                            // there was an error
                            Toast.makeText(getApplicationContext(), res.getString(R.string.wrong_credentials), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    //This method deals with the menu items present on the ToolBar
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_uninstall, menu);
        return true;
    }


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
            startActivity(WelcomePageActivity.intent_uninstall);
        }

        return super.onOptionsItemSelected(item);
    }

    //Method to check if the Username field is empty
    public boolean validateUsernameForEmpty(String username){
        if(username.equals("")){
            return false;
        }
        else{
            return true;
        }
    }
    //Method to check if the Password field is empty
    public boolean validatePasswordForEmpty(String password){
        if(password.equals("")){
            return false;
        }
        else{
            return true;
        }
    }
}
