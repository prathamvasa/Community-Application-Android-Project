package com.example.i851409.communityapp;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

public class EventDescription extends AppCompatActivity implements View.OnClickListener
{

    EditText ed;
    EditText ed1;
    EditText ed2;
    Button btn1;
    Button btn2;
    String userfirstname;
    boolean flag_capture_button = false;
    File image_file;
    static String image_path;
    String image_name;
    public static final int IMAGE_CAPTURE_IDENTIFIER = 21;
    Bitmap current_image;
    ImageView imgView;
    Intent current_photo_intent;
    File external_picture_directory;
    Uri image_uri;
    String image_caption;
    ContentValues cv;
    Firebase fire;
    String e;

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Firebase.setAndroidContext(this);

        ed = (EditText) findViewById(R.id.editText);

        ed1 = (EditText)findViewById(R.id.editText1);

        ed2 = (EditText) findViewById(R.id.editText2);

        //Setting an onClick() listener to the SAVE image button
        btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                e = ed.getText().toString();
                String ee = ed1.getText().toString();
                String eee = ed2.getText().toString();

                String hello = "Event Field Cannot Be Empty";
                String hello1 = "Location Field Cannot Be Empty";
                String hello2 = "Event Detail Field Cannot Be Empty";


                if (TextUtils.isEmpty(e))
                {
                    ed.setError(hello);
                }

                else if (TextUtils.isEmpty(ee))
                {

                    ed1.setError(hello1);
                }

                else if (TextUtils.isEmpty(eee))
                {
                    ed2.setError(hello2);
                }
                else
                {

                    Toast.makeText(getApplicationContext(), "CREATING EVENT MAY TAKE SOME TIME...", Toast.LENGTH_SHORT).show();
                    fire=new Firebase("https://blinding-fire-7006.firebaseio.com/Event");

                    fire = fire.child(e);
                    fire.child("EventTitle").setValue(e);
                    fire.child("EventLocation").setValue(ee);
                    fire.child("EventDescription").setValue(eee);

                    Toast.makeText(getApplicationContext(), "Created", Toast.LENGTH_SHORT).show();

                    //Starting the MainActivity
                    Intent it = new Intent(EventDescription.this, EventList.class);
                    finish();
                    startActivity(it);
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_uninstall, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.item_uninstall){
            //Now call the appropriate Intent to uninstall the application
            startActivity(WelcomePageActivity.intent_uninstall);
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(EventDescription.this,"Be Patient Analyzing Image.. May take Few Seconds",Toast.LENGTH_SHORT).show();

        //Check whether the Camera Activity handles the correct activity
        if(requestCode == IMAGE_CAPTURE_IDENTIFIER){
            //Now you can do whatever you want to depending upon whether the user ticks right or wrong option

            //Do something if the user hits the OK button
            if(resultCode == RESULT_OK)
            {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 1;
                Bitmap b = BitmapFactory.decodeFile(image_path, options);

                imgView = (ImageView) findViewById(R.id.imageView);

                imgView.setImageBitmap(null);
                imgView.setImageBitmap(b);

            }

            //Do nothing if the user hits the cancel button
            if(resultCode == RESULT_CANCELED){
                //This will not allow the user to save the image after selecting to not capture the image
                flag_capture_button = false;
            }
        }
    }

    @Override
    public void onClick(View v) {

    }
}
