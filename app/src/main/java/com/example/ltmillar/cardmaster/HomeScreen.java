package com.example.ltmillar.cardmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class HomeScreen extends AppCompatActivity {

    Toolbar mActionBarToolbar;

    //Declare Variables
    private Button buttonWhichCard;
    private Button buttonManageCards;
    private Button buttonManageProfile;
    private TextView textViewNotifications;



// changes!


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        mActionBarToolbar.setTitle("Homepage");
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        buttonWhichCard = (Button) findViewById(R.id.buttonWhichCard);
        buttonManageCards = (Button) findViewById(R.id.buttonManageCards);
        buttonManageProfile = (Button) findViewById(R.id.buttonManageProfile);
        textViewNotifications = (TextView) findViewById(R.id.textViewNotifications);

        buttonManageCards.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(HomeScreen.this, ManageCards.class);
                        startActivity(intent);
                    }
                }
        );


    }
}
