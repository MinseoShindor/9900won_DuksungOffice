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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    ListView mListView = null;
    BaseAdapterEx mAdapter = null;
    ArrayList<List> mData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        ArrayList<Integer> nums = new ArrayList<Integer>();

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


        databaseReference.child("Post").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    String value = postSnapshot.child("postType").getValue(String.class); // 분야
                    int num = postSnapshot.child("postLikeCount").getValue(Integer.class); // 추천
                    int date = postSnapshot.child("postTime").getValue(Integer.class); // 최신

                            /* 분야별 불러오기
                            if (value.equals("글로벌융합대학")) {
                                String text = postSnapshot.child("postContent").getValue(String.class);
                                Toast.makeText(DB.this, text, Toast.LENGTH_LONG).show();
                            }
                            */

                    // 추천순 불러오기 (2개)
                    if (num > 99) {
                        nums.add(num);
                    }

                    // 최신순 불러오기 (3개)
                    // 이미 파이어 베이스에 제일 최신글이 맨 위에 있음 불러올 때도 맨 위 데이터부터 가져옴
                    // count 세어서 3번 넘어가면 끝
                }

                        /*
                        String value = dataSnapshot.getValue(String.class);
                        Toast.makeText(DB.this, value, Toast.LENGTH_LONG).show();
                        dataSnapshot.getChildren();

                       \for (DataSnapshot postSnapshot2: postSnapshot.getChildren()) {
                              String value = postSnapshot2.getValue(String.class);
                         }
                        */

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });
    }
}