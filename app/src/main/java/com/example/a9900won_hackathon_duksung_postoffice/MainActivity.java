package com.example.a9900won_hackathon_duksung_postoffice;

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
    Button btn1, btn2, btn3 = null;

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

        btn1 = (Button) findViewById(R.id.main_list_btn1);
        btn2 = (Button) findViewById(R.id.main_list_btn2);
        btn3 = (Button) findViewById(R.id.main_list_btn3);


    }
}