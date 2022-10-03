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

        Button b_list_b1;

        findViewById(R.id. b_list_b1).setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    }
                }
        );
    }
}