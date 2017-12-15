package com.example.ltmillar.cardmaster;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManageCards extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    Toolbar mActionBarToolbar;
    private int mMenuId;
    private BottomNavigationView mBtmView;
    private Button buttonAddCard, buttonDelete;
    private ListView listViewCard;
    private String selectedItem;
    private final Context context = this;
    private String passCard;

    ArrayList<String> list = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_cards);

        buttonAddCard = (Button) findViewById(R.id.buttonAddCard);
        buttonAddCard.setOnClickListener(this);

        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(this);

        listViewCard = (ListView) findViewById(R.id.listCards);
        adapter = new ArrayAdapter<String>(this, R.layout.card_list, R.id.textCardName, list);
        listViewCard.setAdapter(adapter);

        listViewCard.setOnItemClickListener(this);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference myRef = database.getReference().child("Users").child(user.getUid()).child("Cards");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Card card = new Card();
                card = dataSnapshot.getValue(Card.class);
                list.add(0,card.cardName);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("card");

        if (v == buttonAddCard) {
            Intent goToAddCard = new Intent(this, AddCard.class);
            this.startActivity(goToAddCard);
        } else if (v == buttonDelete) {
            Intent goToDelete = new Intent (this, DeleteCards.class);
            this.startActivity(goToDelete);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        final String cardLookup = listViewCard.getItemAtPosition(i).toString();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference myRef = database.getReference("Users").child(user.getUid()).child("Cards");


        myRef.orderByChild("cardName").equalTo(cardLookup).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null) {
                    Toast.makeText(ManageCards.this, "Error!", Toast.LENGTH_SHORT).show();
                } else {
                    myRef.orderByChild("cardName").equalTo(cardLookup).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            //Card passCard = new Card();
                            passCard = dataSnapshot.getKey();
                            goToEditCard(passCard);

                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        //goToCard.putExtra("X","Y");
        //this.startActivity(goToCard);
        Toast.makeText(ManageCards.this, "You clicked" + listViewCard.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
    }
    public void goToEditCard(String parcelCard){
        Intent goToCard = new Intent(ManageCards.this, EditCard.class);
        goToCard.putExtra("Card Key", parcelCard);
// this is crashing
        this.startActivity(goToCard);

    }

}
