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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Search extends AppCompatActivity implements View.OnClickListener{
    Toolbar mActionBarToolbar;
    private int mMenuId;
    private BottomNavigationView mBtmView;
    private Button buttonWhichCard;
    private Spinner spinnerCatList;
    private TextView textViewSearchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

       buttonWhichCard = (Button) findViewById(R.id.buttonWhichCard);
        textViewSearchResults = (TextView) findViewById(R.id.textViewSearchResults);

       // Does this find the value?
       spinnerCatList = (Spinner) findViewById(R.id.spinnerCatList);
       buttonWhichCard.setOnClickListener(this);

        // Customized tool bar begins

        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        mActionBarToolbar.setTitle("Search"); // Change the title here
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
// Cusomized tool bar ends

//  Navigation bar begins
        mBtmView = (BottomNavigationView) findViewById(R.id.navigation);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.getMenu().findItem(R.id.menu_search).setChecked(true); //Change the id that you want to select
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
                                startActivity(new Intent(Search.this, HomeScreen.class));
                                return true;

                            case R.id.menu_search:
                                startActivity(new Intent(Search.this, Search.class));
                                return true;

                            case R.id.menu_cards:
                                startActivity(new Intent(Search.this, ManageCards.class));
                                return true;

                            case R.id.menu_profile:
                                startActivity(new Intent(Search.this, Profile.class));
                                return true;

                        }
                        return true;
                    }
                });
//  Navigation bar Ends
    }

    @Override
    public void onClick(View view) {
        if (view == buttonWhichCard){
            // identify the category
            String spinnerCatValue = spinnerCatList.getSelectedItem().toString();
            Toast.makeText(Search.this, "Selection is: " + spinnerCatValue, Toast.LENGTH_SHORT).show();
            // look up cards


            // find the card with the highest %
            Card fakeCard = new Card("Discover Card", "US Bank", "1234", "12/24", "1%", "2%", "3%");
//public Card (String cardName, String bankName, String cardNumber, String cardExpDate, String category1Percent, String category2Percent, String category3Percent) {

            // update the text field
            textViewSearchResults.setText("The best card to use is \n" +
                    fakeCard.bankName + " with rewards: " + fakeCard.category1 + " at " + fakeCard.category1Percent);

        }

    }
}
