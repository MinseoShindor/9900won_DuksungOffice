package com.example.a9900won_hackathon_duksung_postoffice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {

    ListView mListView = null;
    BaseAdapterEx mAdapter = null;
    ArrayList<List> mData = null;
    TextView main_subject_tv;
    Button write;
    Button main_list_alignment_btn1;
    Button main_list_alignment_btn2;
    CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        mData = new ArrayList<List>();
        mAdapter = new BaseAdapterEx(this, mData);

        // main_subject_tv = (TextView) findViewById(R.id.TextView);

    }
}