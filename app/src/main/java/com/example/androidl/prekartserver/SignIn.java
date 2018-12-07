package com.example.androidl.prekartserver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidl.prekartserver.Common.Common;
import com.example.androidl.prekartserver.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {

    EditText editPhone,editPassword;
    Button btnSignIn;
    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        editPassword = (MaterialEditText)findViewById(R.id.editPassword);
        editPhone = (MaterialEditText)findViewById(R.id.editPhone);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);


       //Init firebase
        db = FirebaseDatabase.getInstance();
        users  = db.getReference("User");



        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    signInUser(editPhone.getText().toString(),editPassword.getText().toString());
            }
        });
    }

    private void signInUser(String phone, String password) {

        final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
        mDialog.setMessage("Please Wait");
        mDialog.show();
final String localphone  =  phone;
final String localPassword  =  password;
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                if(dataSnapshot.child(localphone).exists()) {
                    // get user information if it exists
                    mDialog.dismiss();
                    User user = dataSnapshot.child(localphone).getValue(User.class);
                    user.setPhone(localphone);

                    if(Boolean.parseBoolean((user.getIsStaff()))) // if isStaff == true
                    {
                        if( user.getPassword().equals(localPassword))
                        {
                            //Login ok
                            Intent login = new Intent(SignIn.this,Home.class);
                            Common.currentUser =user;
                            startActivity(login);
                            finish();
                        }
                        else
                            Toast.makeText(SignIn.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(SignIn.this, "Please login with staff account", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                  mDialog.dismiss();
                    Toast.makeText(SignIn.this, "Dataabase has no such user ", Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}












