package com.example.a9900won_hackathon_duksung_postoffice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView mListView = null;
    BaseAdapterEx mAdapter = null;
    ArrayList<List> mData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mData = new ArrayList<List>();

        for(int i = 0; i < 20; i++) {
            List list = new List();

            list.title = "길고양이를 학대하는 갤러리를 폐쇄하고 엄중한 수사를 해주십시오 길고양이를 학대하는 갤러리를 폐쇄하고 엄중한 수사를 해주십시오 " +i;
//            list.context = "청원내용" + i;
            list.writer = "이현민" + i;
            list.date = "2022-08-23";

            mData.add(list);

        }

        mAdapter = new BaseAdapterEx(this, mData);

        mListView = (ListView) findViewById(R.id.main_list_view_1);
        mListView.setAdapter(mAdapter);

        mListView = (ListView) findViewById(R.id.main_list_view_2);
        mListView.setAdapter(mAdapter);

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

        findViewById(R.id.main_floating_write_btn).setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent_write = new Intent(getApplicationContext(), WriteActivity.class);
                        startActivity(intent_write);
                    }
                }
        );





    }
}