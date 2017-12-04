package com.example.ltmillar.cardmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class Profile extends AppCompatActivity {
    Toolbar mActionBarToolbar;
    private int mMenuId;
    private BottomNavigationView mBtmView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

// Customized tool bar begins
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        mActionBarToolbar.setTitle("Profile");
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
// Customized tool bar ends

//Navigation bar begins
        mBtmView = (BottomNavigationView) findViewById(R.id.navigation);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.getMenu().findItem(R.id.menu_profile).setChecked(true); //Change the id that you want to select
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
                            case R.id.menu_home:
                                startActivity(new Intent(Profile.this, HomeScreen.class));
                                return true;

                            case R.id.menu_search:
                                startActivity(new Intent(Profile.this, Search.class));

                                return true;

                            case R.id.menu_cards:
                                startActivity(new Intent(Profile.this, ManageCards.class));
                                return true;

                            case R.id.menu_profile:
                                startActivity(new Intent(Profile.this, Profile.class));

                                return true;

                        }
                        return true;
                    }
                });
//Navigation bar ends


    }
}
