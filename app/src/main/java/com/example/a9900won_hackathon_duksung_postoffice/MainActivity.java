package com.example.a9900won_hackathon_duksung_postoffice;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.invoke.MethodType;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public SharedPreferences settings;
    private ListView listView1, listView2;
    private TextView titleTv, contentTv, scrapTv;

    private ArrayList<String> contentData = null; //data list
    private ArrayAdapter<String> arrayAdapter = null; // listview에 사용되는 ArrayAdapter

    ArrayList<Integer> nums = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        listView1 = (ListView) findViewById(R.id.main_list_view_1); //인기글
        listView2 = (ListView) findViewById(R.id.main_list_view_2); // 최신글

        titleTv = (TextView) findViewById(R.id.main_list_title_tv); //제목 tv
        contentTv = (TextView) findViewById(R.id.main_list_content_tv); //내용 tv
        scrapTv = (TextView) findViewById(R.id.main_list_scrap_tv); //스크랩수

        contentData = new ArrayList<String>();

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contentData);

        listView1.setAdapter(arrayAdapter);
        listView2.setAdapter(arrayAdapter);


        findViewById(R.id.main_list_btn1).setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent_main = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent_main);
                        overridePendingTransition(0, 0);
                    }
                }
        );

        findViewById(R.id.main_list_btn2).setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent_subject = new Intent(getApplicationContext(), SubjectActivity.class);
                        startActivity(intent_subject);
                        overridePendingTransition(0, 0);

                    }
                }
        );

        findViewById(R.id.main_list_btn3).setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        // Intent intent_applyComplete = new Intent(getApplicationContext(), applyCompleteActivity.class);
                        // startActivity(intent_applyComplete);
                        overridePendingTransition(0, 0);
                    }
                }
        );

        findViewById(R.id.main_floating_write_btn).setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent_write = new Intent(getApplicationContext(), WriteActivity.class);
                        startActivity(intent_write);
                        overridePendingTransition(0, 0);
                    }
                }
        );

        findViewById(R.id.main_ic_circle_user_iv).setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent_myPage = new Intent(getApplicationContext(), MyPage.class);
                        startActivity(intent_myPage);
                        overridePendingTransition(0, 0);
                    }
                }
        );

    }
}