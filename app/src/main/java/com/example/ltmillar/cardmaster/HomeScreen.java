package com.example.ltmillar.cardmaster;

import android.app.Activity;
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

        } else if (view == buttonManageCards){

        } else if (view == buttonManageProfile){

        }
    }
}
