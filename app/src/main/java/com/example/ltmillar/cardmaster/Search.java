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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference cardRef = db.getReference().child("Users").child(user.getUid()).child("Cards");
        if (view == buttonWhichCard){
            // identify the category
            final String spinnerCatValue = spinnerCatList.getSelectedItem().toString();
            if(spinnerCatValue.equals("Gas")) {
                cardRef.orderByChild("category1Percent").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getValue() == null){
                            Toast.makeText(Search.this, "No results found!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Search.this, "Card Found!", Toast.LENGTH_SHORT).show();
                            cardRef.orderByChild("category1Percent").addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                    Card foundCard = new Card();
                                    foundCard = dataSnapshot.getValue(Card.class);
                                    textViewSearchResults.setText("The best card to use is \n" +
                                            foundCard.cardName + " from bank " + foundCard.bankName + "\nwith rewards: " + foundCard.category1 + " at " + foundCard.category1Percent + " %");}
                                @Override public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                                @Override public void onChildRemoved(DataSnapshot dataSnapshot) {}
                                @Override public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                                @Override public void onCancelled(DatabaseError databaseError) {}});}}
                    @Override public void onCancelled(DatabaseError databaseError) {}});
            } else if (spinnerCatValue.equals("Grocery")) {
                cardRef.orderByChild("category2Percent").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getValue() == null){
                            Toast.makeText(Search.this, "No results found!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Search.this, "Card Found!", Toast.LENGTH_SHORT).show();
                            cardRef.orderByChild("category2Percent").addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                    Card foundCard = new Card();
                                    foundCard = dataSnapshot.getValue(Card.class);
                                    textViewSearchResults.setText("The best card to use is \n" +
                                            foundCard.cardName + " from bank " + foundCard.bankName + "\nwith rewards: " + foundCard.category2 + " at " + foundCard.category2Percent + " %");}
                                @Override public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                                @Override public void onChildRemoved(DataSnapshot dataSnapshot) {}
                                @Override public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                                @Override public void onCancelled(DatabaseError databaseError) {}});}}
                    @Override public void onCancelled(DatabaseError databaseError) {}});
            } else if (spinnerCatValue.equals("eCommerce")) {
                cardRef.orderByChild("category3Percent").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getValue() == null){
                            Toast.makeText(Search.this, "No results found!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Search.this, "Card Found!", Toast.LENGTH_SHORT).show();
                            cardRef.orderByChild("category3Percent").addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                    Card foundCard = new Card();
                                    foundCard = dataSnapshot.getValue(Card.class);
                                    textViewSearchResults.setText("The best card to use is \n" +
                                            foundCard.cardName + " from bank " + foundCard.bankName + "\nwith rewards: " + foundCard.category3 + " at " + foundCard.category3Percent + " %");}
                                @Override public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                                @Override public void onChildRemoved(DataSnapshot dataSnapshot) {}
                                @Override public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                                @Override public void onCancelled(DatabaseError databaseError) {}});}}
                    @Override public void onCancelled(DatabaseError databaseError) {}});
            }
        }

    }
}
