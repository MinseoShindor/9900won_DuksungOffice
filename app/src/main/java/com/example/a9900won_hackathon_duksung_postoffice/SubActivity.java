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

        for(int i = 0; i < 20; i++) {
            List list = new List();

            list.title = "길고양이를 학대하는 갤러리를 폐쇄하고 엄중한 수사를 해주십시오 " +i;
//            list.context = "청원내용" + i;
            list.writer = "이현민" + i;
            list.date = "2022-08-23";

            mData.add(list);

        }

        mAdapter = new BaseAdapterEx(this, mData);

        mListView = (ListView) findViewById(R.id.main_list_view);
        mListView.setAdapter(mAdapter);

    }
}