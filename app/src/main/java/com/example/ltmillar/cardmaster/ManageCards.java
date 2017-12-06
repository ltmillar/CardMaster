package com.example.ltmillar.cardmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ManageCards extends AppCompatActivity implements View.OnClickListener {

    Toolbar mActionBarToolbar;
    private int mMenuId;
    private BottomNavigationView mBtmView;
    private Button buttonAddCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_cards);

        buttonAddCard = (Button) findViewById(R.id.buttonAddCard);
        buttonAddCard.setOnClickListener(this);



// Customized tool bar begins

        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        mActionBarToolbar.setTitle("Manage Cards"); // Change the title here
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
// Customized tool bar ends

//  Navigation bar begins
        mBtmView = (BottomNavigationView) findViewById(R.id.navigation);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.getMenu().findItem(R.id.menu_cards).setChecked(true); //Change the id that you want to select
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
                                startActivity(new Intent(ManageCards.this, HomeScreen.class));
                                return true;

                            case R.id.menu_search:
                                startActivity(new Intent(ManageCards.this, Search.class));
                                return true;

                            case R.id.menu_cards:
                                startActivity(new Intent(ManageCards.this, ManageCards.class));
                                return true;

                            case R.id.menu_profile:
                                startActivity(new Intent(ManageCards.this, Profile.class));
                                return true;

                        }
                        return true;
                    }
                });
//  Navigation bar Ends
    }

    @Override
    public void onClick(View v) {

        if (v == buttonAddCard) {
            Intent goToEditCards = new Intent(this, EditCard.class);
            this.startActivity(goToEditCards);
        }
    }
}
