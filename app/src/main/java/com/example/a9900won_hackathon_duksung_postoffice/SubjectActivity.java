package com.example.a9900won_hackathon_duksung_postoffice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        findViewById(R.id.main_list_btn1).setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent_main = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent_main);
                    }
                }
        );

        findViewById(R.id.main_list_btn2).setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent_subject = new Intent(getApplicationContext(), SubjectActivity.class);
                        startActivity(intent_subject);
                    }
                }
        );

        findViewById(R.id.main_list_btn3).setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent_applyComplete = new Intent(getApplicationContext(), applyCompleteActivity.class);
                        startActivity(intent_applyComplete);
                    }
                }
        );

    }
}