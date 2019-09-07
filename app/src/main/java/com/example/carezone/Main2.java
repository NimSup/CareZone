package com.example.carezone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2 extends AppCompatActivity {


    Button logscrbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        logscrbtn = findViewById(R.id.logscr_btn2);
       logscrbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent(Main2.this,loginact.class);
               startActivity(i);
           }
       });

        TextView textView;
        textView = findViewById(R.id.textView4);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main2.this,dashboard.class);
                startActivity(i);
            }
        });
    }
}
