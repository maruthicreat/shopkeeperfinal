package com.example.maruthiraja.shopkeeperapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShopkeeperSignup extends Activity {

    DatabaseReference mDatabase;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    Button mapButton;
    EditText repass,emailId,password,shopName,shopType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopkeeper_signup);
        shopName = (EditText)findViewById(R.id.shop_name);
        shopType = (EditText)findViewById(R.id.shop_type);
        mapButton = (Button)findViewById(R.id.map_button);
        repass = (EditText)findViewById(R.id.retypepass);
        emailId = (EditText) findViewById(R.id.emailid);
        password = (EditText) findViewById(R.id.password);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("ShopkeeperSignup");
    }
    public void storeUserData(View view)
    {
        final String rep = repass.getText().toString().trim();
        final String emailid = emailId.getText().toString().trim();
        final String passwordid = password.getText().toString().trim();
        final String shop_name = shopName.getText().toString().trim();
        final String shop_type = shopType.getText().toString().trim();
        if(TextUtils.isEmpty(rep) && TextUtils.isEmpty(emailid) && TextUtils.isEmpty(passwordid))
        {
            Toast.makeText(this, "Pls fill the text fields...!!!", Toast.LENGTH_SHORT).show();
        }
        else {
            if (rep.equals(passwordid)) {
                progressDialog.setMessage("Signing Up...!!!");
                progressDialog.show();
                firebaseAuth.createUserWithEmailAndPassword(emailid, passwordid).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(ShopkeeperSignup.this, "Verification email sent to your Email id..!", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(ShopkeeperSignup.this, "Failed to sent the Email Verification..! ", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            String u_id = firebaseAuth.getCurrentUser().getUid();
                            DatabaseReference c_uid = mDatabase.child(u_id);
                            c_uid.child("user_id").setValue("2");
                            c_uid.child("mail_id").setValue(emailid);
                            c_uid.child("password").setValue(passwordid);
                            c_uid.child("shop_name").setValue(shop_name);
                            c_uid.child("shop_type").setValue(shop_type);
                            progressDialog.dismiss();
                            Toast.makeText(ShopkeeperSignup.this, "You are Successfully Signed up...!!!", Toast.LENGTH_SHORT).show();
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(ShopkeeperSignup.this, "User with this email already exist.", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                            else {
                                Toast.makeText(ShopkeeperSignup.this, "Please Enter the valid mail id.", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    }
                });
            }
            else {
                Toast.makeText(this, "Pls Check the Re-Type Password..!!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
