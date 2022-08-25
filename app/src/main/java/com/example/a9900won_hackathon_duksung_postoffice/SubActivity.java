package com.example.a9900won_hackathon_duksung_postoffice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {

    ListView mListView = null;
    BaseAdapterEx mAdapter = null;
    ArrayList<List> mData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        mData = new ArrayList<List>();


        mAdapter = new BaseAdapterEx(this, mData);

        mListView = (ListView) findViewById(R.id.main_list_view);
        mListView.setAdapter(mAdapter);

    }
}