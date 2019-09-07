package com.example.carezone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {
    EditText semail, spass;
    Button btnsignup;
    ImageView backsign;
    FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mFirebaseAuth = FirebaseAuth.getInstance();
        semail =findViewById(R.id.emailsuped);
        spass = findViewById(R.id.paswdsuped);
        btnsignup = findViewById(R.id.supbutton);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = semail.getText().toString();
                String pass = spass.getText().toString();

                if(email.isEmpty())
                {
                    semail.setError("please enter the email id");
                    semail.requestFocus();

                }
                else if(pass.isEmpty())
                {
                    spass.setError("please enter the password");
                    spass.requestFocus();
                }
                else if(email.isEmpty()&& pass.isEmpty())
                {
                    Toast.makeText(signup.this, "feilds cannot be empty!",Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty()&& pass.isEmpty()))
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(signup.this,"Sign Up Unsuccessful",Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                startActivity(new Intent(signup.this, loginact.class));
                            }
                        }
                    });
                }
                else
                    {
                        Toast.makeText(signup.this,"An Error Occured!",Toast.LENGTH_SHORT).show();
                    }
            }
        });
        backsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(signup.this,Main2.class);
                startActivity(i);
            }
        });
    }
}
