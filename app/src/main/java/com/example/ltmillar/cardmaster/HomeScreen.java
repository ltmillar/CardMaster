package com.example.ltmillar.cardmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeScreen extends Activity implements Button.OnClickListener{

    //Declare Variables
    private Button buttonWhichCard;
    private Button buttonManageCards;
    private Button buttonManageProfile;
    private TextView textViewNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //Define local variables
        buttonWhichCard = (Button) findViewById(R.id.buttonWhichCard);
        buttonManageCards = (Button) findViewById(R.id.buttonManageCards);
        buttonManageProfile = (Button) findViewById(R.id.buttonManageProfile);
        textViewNotifications = (TextView) findViewById(R.id.textViewNotifications);

        //Set up Listeners
        buttonWhichCard.setOnClickListener(this);
        buttonManageCards.setOnClickListener(this);
        buttonManageProfile.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == buttonWhichCard){
            //Intent intentWhichCard = new Intent(this, NAME OF WHICH CARD PAGE GOES HERE.CLASS);
            //this.startActivity(intentWhichCard);
        } else if (view == buttonManageCards){
            Intent intentManageCards = new Intent(this, ManageCards.class);
            this.startActivity(intentManageCards);
        } else if (view == buttonManageProfile){
            //Intent intentManageProfile = new Intent(this, NAME OF MANAGE PROFILE PAGE GOES HERE.class);
            //this.startActivity(intentManageProfile);
        }
    }
}
