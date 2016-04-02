package com.example.i851409.communityapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Email extends AppCompatActivity {

    Button send;
    EditText mes;
    EditText fr;
    String to[] = {"harshpandya0905@gmail.com"};

    String message1,from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        send = (Button)findViewById(R.id.button14);

        fr = (EditText)findViewById(R.id.editText21);

        mes = (EditText)findViewById(R.id.editText23);

        from = fr.getText().toString();

        message1 = mes.getText().toString();


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (from.isEmpty() && message1.isEmpty()) {
                    composeEmail(to);
                }

                else
                {
                    Toast.makeText(Email.this,"Some Fields are missing, complete all the field",Toast.LENGTH_SHORT).show();
                }

            }
        });



    }



    public void composeEmail(String[] addresses) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + "harshpandya0905@gmail.com")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_TEXT, mes.getText());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
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
