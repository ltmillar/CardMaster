package com.example.ltmillar.cardmaster;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class HomeScreen extends Activity {

    //Declare Variables
    private Button buttonWhichCard;
    private Button buttonManageCards;
    private Button buttonManageProfile;
    private TextView textViewNotifications;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        buttonWhichCard = (Button) findViewById(R.id.buttonWhichCard);
        buttonManageCards = (Button) findViewById(R.id.buttonManageCards);
        buttonManageProfile = (Button) findViewById(R.id.buttonManageProfile);
        textViewNotifications = (TextView) findViewById(R.id.textViewNotifications);


    }
}
