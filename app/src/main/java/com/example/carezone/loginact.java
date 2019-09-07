package com.example.carezone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginact extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    FirebaseAuth mFirebaseAuth;
    FirebaseAuth.AuthStateListener mAuthStateListner;
    EditText lemail, lpass;
    Button btnsignup;
    ImageView backsign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginact);


        mFirebaseAuth = FirebaseAuth.getInstance();
        lemail =findViewById(R.id.editText);
        lpass = findViewById(R.id.editText2);
        btnsignup = findViewById(R.id.btn_Login);

        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFireBaseUser = mFirebaseAuth.getCurrentUser();
                if(mFireBaseUser != null)
                {
                    Toast.makeText(loginact.this,"You have successfully logged in",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(loginact.this,dashboard.class);
                    startActivity(i);
                }
                else
                    {
                        Toast.makeText(loginact.this,"Please Login Again", Toast.LENGTH_SHORT).show();
                    }
            }
        };
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = lemail.getText().toString();
                String pass = lpass.getText().toString();

                if(email.isEmpty())
                {
                    lemail.setError("please enter the email id");
                    lpass.requestFocus();

                }
                else if(pass.isEmpty())
                {
                    lemail.setError("please enter the password");
                    lpass.requestFocus();
                }
                else if(email.isEmpty()&& pass.isEmpty())
                {
                    Toast.makeText(loginact.this, "feilds cannot be empty!",Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty()&& pass.isEmpty()))
                {
                    mFirebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(loginact.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful())
                                {
                                    Toast.makeText(loginact.this,"Login Error", Toast.LENGTH_SHORT).show();


                                }
                                else
                                    {
                                        Intent IntHome = new Intent(loginact.this,dashboard.class);
                                        startActivity(IntHome);
                                    }
                        }
                    });
                }
                else
                {
                    Toast.makeText(loginact.this,"An Error Occured!",Toast.LENGTH_SHORT).show();
                }


            }
        });



        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(loginact.this,Main2.class);
                startActivity(i);
            }
        });
        textView = findViewById(R.id.textView12);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent log = new Intent(loginact.this,signup.class);
                startActivity(log);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseApp.initializeApp(this);
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);



    }
}
