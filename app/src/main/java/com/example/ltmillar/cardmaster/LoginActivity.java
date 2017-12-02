package com.example.ltmillar.cardmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends Activity {

    private static Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        signUp = (Button)findViewById(R.id.buttonSignUp);

        signUp.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(LoginActivity.this, HomeScreen.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
