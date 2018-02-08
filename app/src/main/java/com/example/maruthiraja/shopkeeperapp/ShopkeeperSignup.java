package com.example.maruthiraja.shopkeeperapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShopkeeperSignup extends Activity {

    DatabaseReference mDatabase;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    EditText repass,emailId,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopkeeper_signup);
        repass = (EditText)findViewById(R.id.);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("ShopkeeperSignup");
    }
}
