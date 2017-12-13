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
        Card card = new Card();
        card = intent.getParcelableExtra("Card");



        editCardName.setText(card.getCardName());
        editBankName.setText(card.getBankName());
        editExpDate.setText(card.getCardExpDate());
        editCardNumber.setText(card.getCardLastFourNumber());
        editCashback1.setText(card.getCategory1Percent());
        editCashback2.setText(card.getCategory2Percent());
        editCashback3.setText(card.getCategory3Percent());

        buttonClear.setOnClickListener(this);
        buttonConfirm.setOnClickListener(this);

// Customized tool bar begins
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        mActionBarToolbar.setTitle("Edit Cards");
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
        final DatabaseReference cardRef = db.getReference().child("Users").child(user.getUid()).child("Cards");

        if (view == buttonClear) {
        final String cardLookupName = editCardName.getText().toString();
        cardRef.orderByChild("cardName").equalTo(cardLookupName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() == null) {
                    Toast.makeText(EditCard.this, "Card Not Found", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditCard.this, "Card Updated!", Toast.LENGTH_SHORT).show();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Card updateCard = new Card();
                        updateCard = snapshot.getValue(Card.class);
                        updateCard.bankName = editBankName.getText().toString();
                        updateCard.cardExpDate = editExpDate.getText().toString();
                        updateCard.cardLastFourNumber = editCardNumber.getText().toString();
                        updateCard.category1Percent = editCashback1.getText().toString();
                        updateCard.category2Percent = editCashback2.getText().toString();
                        updateCard.category3Percent = editCashback3.getText().toString();

                        String cardKey = snapshot.getKey();
                        cardRef.child(cardKey).setValue(updateCard);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        } else if (view == buttonConfirm) {
            String cardName = editCardName.getText().toString();
            String bankName = editBankName.getText().toString();
            String cardNumber = editCardNumber.getText().toString();
            String cardExpDate = editExpDate.getText().toString();
            String categoryGasPerc = editCashback1.getText().toString();
            String categoryGroceryPerc = editCashback2.getText().toString();
            String categoryeCommPerc = editCashback3.getText().toString();

            Card myCard = new Card(cardName, bankName, cardNumber, cardExpDate, categoryGasPerc, categoryGroceryPerc, categoryeCommPerc);
            cardRef.push().setValue(myCard);
            Toast.makeText(EditCard.this, "Card Added Successfully", Toast.LENGTH_SHORT).show();

        }

    }
}
