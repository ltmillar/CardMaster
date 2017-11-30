package com.example.ltmillar.cardmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class HomeScreen extends AppCompatActivity {

    Toolbar mActionBarToolbar;

    //Declare Variables
    private Button buttonWhichCard;
    private Button buttonManageCards;
    private Button buttonManageProfile;
    private TextView textViewNotifications;
    private int mMenuId;
    private BottomNavigationView mBtmView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

//set up title
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
//nav bar action

        mBtmView = (BottomNavigationView) findViewById(R.id.navigation);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.getMenu().findItem(R.id.menu_home).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        mMenuId = item.getItemId();

                        for (int i = 0; i < mBtmView.getMenu().size(); i++) {
                            MenuItem menuItem = mBtmView.getMenu().getItem(i);
                            boolean isChecked = menuItem.getItemId() == item.getItemId();
                            menuItem.setChecked(isChecked);
                        }

                        switch (item.getItemId()) {
                            case R.id.menu_search:
                                Toast.makeText(HomeScreen.this, "Search", Toast.LENGTH_SHORT).show();

                                return true;

                            case R.id.menu_cards:
                                startActivity(new Intent(HomeScreen.this, ManageCards.class));
                                return true;

                            case R.id.menu_profile:
                                Toast.makeText(HomeScreen.this, "Profile", Toast.LENGTH_SHORT).show();

                                return true;

                        }
                        return true;
                    }
                });


    }
}