package com.example.androidl.prekartserver;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnSignUp = (Button)findViewById(R.id.btnSignUp);
        Button  btnSignIn = (Button) findViewById(R.id.btnSignIn);

        TextView textSlogan = (TextView)findViewById(R.id.textSlogan);
          Typeface face = Typeface.createFromAsset(getAssets(),"fonts/Nabila.ttf");
textSlogan.setTypeface(face);



        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signIn = new Intent(MainActivity.this, SignIn.class);
                startActivity(signIn);
            }
        });
    }
}
