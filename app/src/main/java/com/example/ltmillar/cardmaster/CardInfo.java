package com.example.ltmillar.cardmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CardInfo extends Activity implements View.OnClickListener {
    Toolbar mActionBarToolbar;
    private int mMenuId;
    private BottomNavigationView mBtmView;
    private Button buttonConfirm, buttonClear;
    private EditText editCategory1, editCategory2, editCategory3, editCashback1, editCashback2;
    private EditText editCashback3, editCardName, editBankName, editCardNumber, editExpDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_info);

        buttonConfirm = (Button) findViewById(R.id.buttonConfirm);
        buttonClear = (Button) findViewById(R.id.buttonClear);
        editCategory1 = (EditText) findViewById(R.id.editCategory1);
        editCategory2 = (EditText) findViewById(R.id.editCategory2);
        editCategory3 = (EditText) findViewById(R.id.editCategory3);
        editCashback1 = (EditText) findViewById(R.id.editCashback2);
        editCashback2 = (EditText) findViewById(R.id.editCashback2);
        editCashback3 = (EditText) findViewById(R.id.editCashback1);
        editCardName = (EditText) findViewById(R.id.editCardName);
        editBankName = (EditText) findViewById(R.id.editBankName);
        editCardNumber = (EditText) findViewById(R.id.editCardNumber);
        editExpDate = (EditText) findViewById(R.id.editExpDate);
        buttonConfirm.setOnClickListener(this);


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
                                startActivity(new Intent(CardInfo.this, HomeScreen.class));
                                return true;

                            case R.id.menu_search:
                                startActivity(new Intent(CardInfo.this, Search.class));
                                return true;

                            case R.id.menu_cards:
                                startActivity(new Intent(CardInfo.this, ManageCards.class));
                                return true;

                            case R.id.menu_profile:
                                startActivity(new Intent(CardInfo.this, Profile.class));
                                return true;

                        }
                        return true;
                    }
                });
//  Navigation bar Ends
    }

    public void onClick(View view) {

        // FirebaseDatabase db = FirebaseDatabase.getInstance();
        // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // final DatabaseReference cardRef = db.getReference().child("Users").child(user.getUid()).child("Cards");

        if (view == buttonConfirm) {
            String s = getIntent().getStringExtra("CARD_ID");
            Toast.makeText(this, "Card ID is: " + s, Toast.LENGTH_SHORT).show();

        }


    }
}

