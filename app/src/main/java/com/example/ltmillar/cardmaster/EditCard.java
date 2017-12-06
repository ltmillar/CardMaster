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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditCard extends AppCompatActivity implements View.OnClickListener {
    Toolbar mActionBarToolbar;
    private int mMenuId;
    private BottomNavigationView mBtmView;
    private Button buttonConfirm, buttonClear;
    private EditText editCategory1, editCategory2, editCategory3, editCashback1, editCashback2;
    private EditText editCashback3, editCardName, editBankName, editCardNumber, editExpDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);

        buttonConfirm = (Button) findViewById(R.id.buttonConfirm);
        buttonClear = (Button) findViewById(R.id.buttonClear);
        editCategory1 = (EditText) findViewById(R.id.editCategory1);
        editCategory2 = (EditText) findViewById(R.id.editCategory2);
        editCategory3 = (EditText) findViewById(R.id.editCategory3);
        editCashback1 = (EditText) findViewById(R.id.editCashback1);
        editCashback2 = (EditText) findViewById(R.id.editCashback2);
        editCashback3 = (EditText) findViewById(R.id.editCashback3);
        editCardName = (EditText) findViewById(R.id.editCardName);
        editBankName = (EditText) findViewById(R.id.editBankName);
        editCardNumber = (EditText) findViewById(R.id.editCardNumber);
        editExpDate = (EditText) findViewById(R.id.editExpDate);

        buttonClear.setOnClickListener(this);
        buttonConfirm.setOnClickListener(this);

// Customized tool bar begins
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        mActionBarToolbar.setTitle("Edit Cards");
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
// Customized tool bar ends

//Navigation bar begins
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
                            case R.id.menu_search:
                                Toast.makeText(EditCard.this, "Search", Toast.LENGTH_SHORT).show();

                                return true;

                            case R.id.menu_cards:
                                startActivity(new Intent(EditCard.this, ManageCards.class));
                                return true;

                            case R.id.menu_profile:
                                startActivity(new Intent(EditCard.this, Profile.class));

                                return true;

                        }
                        return true;
                    }
                });
//Navigation bar ends

    }

    public void onClick (View view) {

        //Initializing Firebase database
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference cardRef = db.getReference("Cards");

        if (view == buttonClear) {
            editExpDate.setText("");
            editCardNumber.setText("");
            editBankName.setText("");
            editCardName.setText("");
            editCategory1.setText("");
            editCategory2.setText("");
            editCategory3.setText("");
            editCashback1.setText("");
            editCashback2.setText("");
            editCashback3.setText("");
        } else if (view == buttonConfirm) {
            String cardName = editCardName.getText().toString();
            String bankName = editBankName.getText().toString();
            String cardNumber = editCardNumber.getText().toString();
            String cardExpDate = editExpDate.getText().toString();

            Card myCard = new Card(cardName, bankName, cardNumber, cardExpDate);
            cardRef.push().setValue(myCard);

        }

    }
}
