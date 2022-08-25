package com.example.a9900won_hackathon_duksung_postoffice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class WriteActivity extends AppCompatActivity {

    Uri uri;
    ImageView clipIv;
    TextView textView;
    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        clipIv = findViewById(R.id.write_clip_iv);
        textView = findViewById(R.id.write_clip_tv);
        spinner1 = findViewById(R.id.spinner_1);
        spinner2 = findViewById(R.id.spinner_2);
        spinner3 = findViewById(R.id.spinner_3);

        ArrayAdapter subAdapter = ArrayAdapter.createFromResource(this,R.array.pick_1, android.R.layout.simple_spinner_dropdown_item);

        subAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
            }
        });



    }

}