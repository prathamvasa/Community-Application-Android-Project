package com.example.i851409.communityapp;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    Firebase fire;
    String users="Users";
    EditText ed_firstname, ed_lastname, ed_email, ed_contact, ed_username_signup, ed_password_signup, ed_dob;
    RadioGroup rg_gender;
    RadioButton rb_male, rb_female;
    Button btn_register;
    String str_lastname, str_email, str_contact, str_username_signup, str_password_signup, str_dob;
    String str_firstname;
    int checked_rb_id;
    int arr_flag[] = new int[8];
    String arr_fieldNames[] = new String[8];
    Resources res;
    private final Pattern hasUppercase = Pattern.compile("[A-Z]");
    private final Pattern hasLowercase = Pattern.compile("[a-z]");
    private final Pattern hasNumber = Pattern.compile("\\d");
    private final Pattern hasSpecialChar = Pattern.compile("[^a-zA-Z0-9 ]");
    String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setting up the context for Firebase object
        Firebase.setAndroidContext(this);

        //Setting the properties for the Toolbar and the Supporting Action Bar
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.BLACK);
        toolbar.setTitle(R.string.toolbar_title);
        setSupportActionBar(toolbar);

        //Fetching the associated Resources
        res = getResources();

        //Initializing the Button object
        btn_register = (Button) findViewById(R.id.button4);

        //Initializing the contents of an array for Validation purpose
        arr_fieldNames[0] = res.getString(R.string.login_firstname);
        arr_fieldNames[1] = res.getString(R.string.login_lastname);
        arr_fieldNames[2] = res.getString(R.string.login_email);
        arr_fieldNames[3] = res.getString(R.string.login_contact);
        arr_fieldNames[4] = res.getString(R.string.login_username);
        arr_fieldNames[5] = res.getString(R.string.login_password);
        arr_fieldNames[6] = res.getString(R.string.signup_radioButton);
        arr_fieldNames[7] = res.getString(R.string.login_dob);

        //Setting the onClick() Event Listener to the REGISTER button
        btn_register.setOnClickListener(new View.OnClickListener() {

            @Override
            //Method to proceed when the User clicks the Register button
            public void onClick(View v) {

                //Populating the two arrays defined above with default values for Validation Purpose
                for(int i=0;i<arr_flag.length;i++){
                    arr_flag[i] = 0;
                }

                //Initializing all the Widgets during the Activity Creation
                ed_firstname = (EditText) findViewById(R.id.editText3);
                ed_lastname = (EditText) findViewById(R.id.editText4);
                ed_email = (EditText) findViewById(R.id.editText5);
                ed_contact = (EditText) findViewById(R.id.editText6);
                ed_username_signup = (EditText) findViewById(R.id.editText7);
                ed_password_signup = (EditText) findViewById(R.id.editText8);
                ed_dob = (EditText) findViewById(R.id.editText9);
                rg_gender = (RadioGroup) findViewById(R.id.radioGroup);
                rb_male = (RadioButton) findViewById(R.id.rb_male);
                rb_female = (RadioButton) findViewById(R.id.rb_female);

                //Fetching the contents of the fields
                str_firstname = ed_firstname.getText().toString();
                str_lastname = ed_lastname.getText().toString();
                str_email = ed_email.getText().toString();
                str_contact = ed_contact.getText().toString();
                str_username_signup = ed_username_signup.getText().toString();
                str_password_signup = ed_password_signup.getText().toString();
                str_dob = ed_dob.getText().toString();

                //Fetching the ID of the Checked Radio Button in the RadioGroup
                checked_rb_id = rg_gender.getCheckedRadioButtonId();

                //Checking all the validations
                int flag = 0;
                //Validation 1: Validation to check for Empty Fields all together
                if(isEmptyFirstName(str_firstname)&&isEmptyLastName(str_lastname)&&isEmptyEmail(str_email)&&isEmptyContact(str_contact)&&isEmptyUsername(str_username_signup)&&isEmptyPassword(str_password_signup)&&isEmptyRadioGroup(checked_rb_id)&&isEmptyDOB(str_dob)){
                    Toast.makeText(getApplicationContext(), res.getString(R.string.allFieldsEmpty), Toast.LENGTH_SHORT).show();
                }

                else {

                    //Validation 2: General Validation to Check if a particular field is empty
                    if (!isEmptyFirstName(str_firstname)) {
                        arr_flag[0] = 1;
                    }
                    if (!isEmptyLastName(str_lastname)) {
                        arr_flag[1] = 1;

                    }
                    if (!isEmptyEmail(str_email)) {
                        arr_flag[2] = 1;
                    }
                    if (!isEmptyContact(str_contact)) {
                        arr_flag[3] = 1;
                    }
                    if (!isEmptyUsername(str_username_signup)) {
                        arr_flag[4] = 1;
                    }
                    if (!isEmptyPassword(str_password_signup)) {
                        arr_flag[5] = 1;
                    }
                    if (!isEmptyRadioGroup(checked_rb_id)) {
                        arr_flag[6] = 1;
                    }
                    if (!isEmptyDOB(str_dob)) {
                        arr_flag[7] = 1;
                    }
                    //This loop will check for each individual Empty Field Validations
                    //This loop will make it possible to display appropriate Toast messages for the specific Empty Fields
                    for (int i = 0; i < arr_flag.length; i++) {
                        //Check if a particular value in the array is 0, ie, for an Empty Field
                        if (arr_flag[i] == 0) {
                            Toast.makeText(getApplicationContext(), arr_fieldNames[i] + " " + res.getString(R.string.generalMessage), Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }

                    //Once the Validations for Empty Fields are checked we can proceed for further complex Validations
                    if(!isEmptyFirstName(str_firstname)&&!isEmptyLastName(str_lastname)&&!isEmptyEmail(str_email)&&!isEmptyContact(str_contact)&&!isEmptyUsername(str_username_signup)&&!isEmptyPassword(str_password_signup)&&!isEmptyRadioGroup(checked_rb_id)&&!isEmptyDOB(str_dob)){
                        //Proceed for further complex calculations when all the input fields are filled by the user
                        //Validation 3: Contact Number should be exactly of 10 digits
                        if(!validateContactForLength(str_contact)){
                            Toast.makeText(getApplicationContext(), res.getString(R.string.cv_contact), Toast.LENGTH_SHORT).show();
                        }


                        //Validation 4: Validation that checks that the Username should be atleast 8 characters long
                        else if(!validateUsernameForLength(str_username_signup)){
                            Toast.makeText(getApplicationContext(), res.getString(R.string.cv_username_signup), Toast.LENGTH_SHORT).show();
                        }

                        //Validation 5: Validation that checks that the password is atleast 8 characters long
                        else if(!validatePasswordForLength(str_password_signup)){
                            Toast.makeText(getApplicationContext(), res.getString(R.string.cv_password_length), Toast.LENGTH_SHORT).show();
                        }

                        //Validation 6: Validation that checks that the password must have atleast 1 LowerCase alphabet
                        else if(!hasLowercase.matcher(str_password_signup).find()){
                            Toast.makeText(getApplicationContext(), res.getString(R.string.cv_password_lowercase), Toast.LENGTH_SHORT).show();
                        }

                        //Validation 7: Validation that checks that the password must have atleast 1 UpperCase letter
                        else if(!hasUppercase.matcher(str_password_signup).find()){
                            Toast.makeText(getApplicationContext(), res.getString(R.string.cv_password_uppercase), Toast.LENGTH_SHORT).show();
                        }

                        //Validation 8: Validation that checks that the Password must have atleast 1 Number
                        else if(!hasNumber.matcher(str_password_signup).find()){
                            Toast.makeText(getApplicationContext(), res.getString(R.string.cv_password_number), Toast.LENGTH_SHORT).show();
                        }

                        //Validation 9: Validation that checks that the password must have atleast 1 Special Character
                        else if(!hasSpecialChar.matcher(str_password_signup).find()){
                            Toast.makeText(getApplicationContext(), res.getString(R.string.cv_password_specialCharacter), Toast.LENGTH_SHORT).show();
                        }

                        //Validation 10: Validation that checks for a Valid Email Address
                        else if(!(str_email.matches(EMAIL_REGEX))){
                            Toast.makeText(getApplicationContext(), res.getString(R.string.cv_email_valid), Toast.LENGTH_SHORT).show();
                        }

                        //Validation 11: Validation that checks for the correct format of the DOB
                        else if(!(validateDOB(str_dob))){
                            Toast.makeText(getApplicationContext(), res.getString(R.string.cv_dob_valid), Toast.LENGTH_SHORT).show();
                        }
                        //At this point all the validations are Complete and now proceed to insert the user data in the Users table
                        else{
                            //Instantiating the Firebase Class
                            fire=new Firebase("https://blinding-fire-7006.firebaseio.com/User");

                            fire.createUser(str_username_signup, str_password_signup, new Firebase.ValueResultHandler<Map<String, Object>>() {
                                @Override
                                public void onSuccess(Map<String, Object> result) {
                                    //System.out.println("Successfully created user account with uid: " + result.get("uid"));
                                    fire=fire.child(str_firstname);
                                    fire.child("FullName").setValue(str_firstname);
                                    fire.child("LastName").setValue(str_lastname);
                                    fire.child("Email").setValue(str_email);
                                    fire.child("Contact").setValue(str_contact);
                                    fire.child("Date OF Birth").setValue(str_dob);
                                    fire.child("Voted_Professor1").setValue("0");
                                    fire.child("Voted_Professor2").setValue("0");

                                    //Checking if the Preference already exists inside the Shared Preferences
                                    SharedPreferences spc = getSharedPreferences("commonx", MODE_PRIVATE);
                                    String temp = spc.getString("myValuex", null);
                                    if(temp == null) {

                                        //Creating a Shared Preferences file with the Firstname of the user as the filename
                                        SharedPreferences shared_pref = getSharedPreferences("commonx", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = shared_pref.edit();
                                        String sp_val = "commonx:1";
                                        editor.putString("myValuex", sp_val);
                                        editor.commit();
                                    }

                                    //Initializing an intent to start a new Activity
                                      Intent it2 = new Intent(SignUpActivity.this, MainApplicationWindow.class);
                                      it2.putExtra("firstname_key", str_firstname);
                                    //Destroying the current Activity
                                      finish();

                                    //Going to the Main Window page
                                     startActivity(it2);
                                }


                                @Override
                                public void onError(FirebaseError firebaseError) {
                                    //Displaying an error message if there was an error while creating the User inside the database
                                    Toast.makeText(getApplicationContext(), res.getString(R.string.create_user_in_database_warning), Toast.LENGTH_SHORT).show();
                                }


                            });
                        }
                    }
                }//end of else of All Fields together Verification check
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

    //Method to check if the First Name field is Empty
    public boolean isEmptyFirstName(String firstname){
        if(firstname.equals("")){
            return true;
        }
        else{
            return false;
        }
    }


    //Method to check if the Last Name field is Empty
    public boolean isEmptyLastName(String lastname){
        if(lastname.equals("")){
            return true;
        }
        else{
            return false;
        }
    }

    //Method to check if the Email field is Empty
    public boolean isEmptyEmail(String email){
        if(email.equals("")){
            return true;
        }
        else{
            return false;
        }
    }

    //Method to check if the Contact field is Empty
    public boolean isEmptyContact(String contact){
        if(contact.equals("")){
            return true;
        }
        else{
            return false;
        }
    }

    //Method to check if the Username field is Empty
    public boolean isEmptyUsername(String username){
        if(username.equals("")){
            return true;
        }
        else{
            return false;
        }
    }


    //Method to check if the Password field is Empty
    public boolean isEmptyPassword(String password){
        if(password.equals("")){
            return true;
        }
        else{
            return false;
        }
    }


    //Method to check if the Radio Group field is Empty
    public boolean isEmptyRadioGroup(int rgid){
        if(rgid == -1){
            return true;
        }
        else{
            return false;
        }
    }


    //Method to check if the Date of Birth field is Empty
    public boolean isEmptyDOB(String dob){
        if(dob.equals("")){
            return true;
        }
        else{
            return false;
        }
    }

    //Method to check whether the Contact Number entered by the user is exactly 10 digits long
    public boolean validateContactForLength(String contact){
        if(contact.length()==10){
            return true;
        }
        else{
            return false;
        }
    }

    //Method to check that the Username entered by the user should be atleast 8 characters long
    public boolean validateUsernameForLength(String username){
        if(username.length()>=8){
            return true;
        }
        else{
            return false;
        }
    }

    //Method to check that the Password entered by the user should be atleast 8 characters long
    public boolean validatePasswordForLength(String password){
        if(password.length()<8){
            return false;
        }
        else{
            return true;
        }
    }

    //Method to validate the Date of Birth
    public boolean validateDOB(String dob){
        try{
            date = sdf.parse(dob);
            return true;
        }
        catch(ParseException pe){
            return false;
        }
    }
}
