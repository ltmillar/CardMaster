package com.example.ltmillar.cardmaster;

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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditCard extends AppCompatActivity implements View.OnClickListener {
    Toolbar mActionBarToolbar;
    private int mMenuId;
    private BottomNavigationView mBtmView;
    private Button buttonClear;
    private EditText editCategory1, editCategory2, editCategory3, editCashback1, editCashback2;
    private EditText editCashback3, editCardName, editBankName, editCardNumber, editExpDate;
    private String cardID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);


        buttonClear = (Button) findViewById(R.id.buttonClear);
        editCategory1 = (EditText) findViewById(R.id.editCategory1);
        editCategory1.setText("Gas");
        editCategory2 = (EditText) findViewById(R.id.editCategory2);
        editCategory2.setText("Grocery");
        editCategory3 = (EditText) findViewById(R.id.editCategory3);
        editCategory3.setText("eCommerce");
        editCashback1 = (EditText) findViewById(R.id.editCashback1);
        editCashback2 = (EditText) findViewById(R.id.editCashback2);
        editCashback3 = (EditText) findViewById(R.id.editCashback3);
        editCardName = (EditText) findViewById(R.id.editCardName);
        editBankName = (EditText) findViewById(R.id.editBankName);
        editCardNumber = (EditText) findViewById(R.id.editCardNumber);
        editExpDate = (EditText) findViewById(R.id.editExpDate);


        Intent intent = getIntent();

        cardID = intent.getStringExtra("Card Key");

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        final DatabaseReference cardRef = db.getReference().child("Users").child(user.getUid()).child("Cards").child(cardID);
        cardRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Card card = new Card();
                card = dataSnapshot.getValue(Card.class);

                editCardName.setText(card.getCardName());
                editBankName.setText(card.getBankName());
                editExpDate.setText(card.getCardExpDate());
                editCardNumber.setText(card.getCardLastFourNumber());
                editCashback1.setText(card.getCategory1Percent());
                editCashback2.setText(card.getCategory2Percent());
                editCashback3.setText(card.getCategory3Percent());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        buttonClear.setOnClickListener(this);


// Customized tool bar begins
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        mActionBarToolbar.setTitle("Edit Card");
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
                                startActivity(new Intent(EditCard.this, HomeScreen.class));
                                return true;

                            case R.id.menu_search:
                                startActivity(new Intent(EditCard.this, Search.class));
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
//  Navigation bar Ends

    }

    public void onClick (View view) {

        //Initializing Firebase database
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference cardRef = db.getReference().child("Users").child(user.getUid()).child("Cards").child(cardID);

        if (view == buttonClear) {
        //final String cardLookupName = editCardName.getText().toString();
        //cardRef.orderByChild("cardName").equalTo(cardLookupName).addListenerForSingleValueEvent(new ValueEventListener() {
        cardRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() == null) {
                    Toast.makeText(EditCard.this, "Card Not Found", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditCard.this, "Card Updated!", Toast.LENGTH_SHORT).show();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Card updateCard = new Card();
                        //a@updateCard = snapshot.getValue(Card.class);
                        updateCard.cardName = editCardName.getText().toString();
                        updateCard.bankName = editBankName.getText().toString();
                        updateCard.cardExpDate = editExpDate.getText().toString();
                        updateCard.cardLastFourNumber = editCardNumber.getText().toString();
                        updateCard.category1Percent = editCashback1.getText().toString();
                        updateCard.category2Percent = editCashback2.getText().toString();
                        updateCard.category3Percent = editCashback3.getText().toString();

                        //a@aString cardKey = snapshot.getKey();
                        cardRef.setValue(updateCard);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        }

    }
}
