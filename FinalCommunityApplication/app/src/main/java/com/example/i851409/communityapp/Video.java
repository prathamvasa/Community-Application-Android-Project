package com.example.i851409.communityapp;

import android.graphics.PixelFormat;
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
import android.widget.VideoView;

public class Video extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button playvideo = (Button) findViewById(R.id.button16);

        getWindow().setFormat(PixelFormat.UNKNOWN);


        VideoView videoView = (VideoView) findViewById(R.id.videoView);

        String uri_Path2 = "android.resource://com.example.i851409.communityapp/"+R.raw.s;
        Uri uri2 = Uri.parse(uri_Path2);
        videoView.setVideoURI(uri2);
        videoView.requestFocus();
        videoView.start();


        playvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                VideoView videoView = (VideoView) findViewById(R.id.videoView);

                String uri_Path = "android.resource://com.example.i851409.communityapp/"+R.raw.s;
                Uri uri2 = Uri.parse(uri_Path);
                videoView.setVideoURI(uri2);
                videoView.requestFocus();
                videoView.start();


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

}
