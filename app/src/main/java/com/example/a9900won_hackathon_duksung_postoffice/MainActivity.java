package com.example.a9900won_hackathon_duksung_postoffice;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public SharedPreferences settings;
    private ListView listView1, listView2;
    private TextView titleTv, contentTv, scrapTv;

    private ListAdapter adapter1;
    private ListAdapter adapter2;

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

        adapter1 = new ListAdapter();
        adapter2 = new ListAdapter();

        Intent intent = getIntent();
        String userId = intent.getStringExtra("userId");

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
                        Intent intent_applyComplete = new Intent(getApplicationContext(), ContentInsideActivity.class);
                        startActivity(intent_applyComplete);
                        overridePendingTransition(0, 0);
                    }
                }
        );

        findViewById(R.id.main_floating_write_btn).setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        Intent intent_write = new Intent(getApplicationContext(), WriteActivity.class);
                        intent_write.putExtra("userId", userId);
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

        databaseReference.child("Post").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    //String key = postSnapshot.getKey();

                    //String value = postSnapshot.child("postType").getValue(String.class); // 분야
                            /* 분야별 불러오기
                            if (value.equals("글로벌융합대학")) {
                                String text = postSnapshot.child("postContent").getValue(String.class);
                                Toast.makeText(DB.this, text, Toast.LENGTH_LONG).show();
                            }
                            */

                    // int date = postSnapshot.child("postTime").getValue(Integer.class); // 최신
                    // 최신순 불러오기 (3개)
                    // 이미 파이어 베이스에 제일 최신글이 맨 위에 있음 불러올 때도 맨 위 데이터부터 가져옴
                    // count 세어서 3번 넘어가면 끝

                    int num = postSnapshot.child("postLikeCount").getValue(Integer.class); // 추천

                    // 추천순 불러오기 (2개)
                    if (num > 99) {
                        nums.add(num);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        Collections.sort(nums, Collections.reverseOrder());


        listView1.setAdapter(adapter1);
        listView2.setAdapter(adapter2);

        databaseReference.child("Post").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    int num = postSnapshot.child("postLikeCount").getValue(Integer.class);

                    if (num == nums.get(0)) {
                        // 주목받는 글 5개까지
                        String title = postSnapshot.child("postTitle").getValue(String.class);
                        String content = postSnapshot.child("postContent").getValue(String.class);
                        String postWriterName = postSnapshot.child("postWriterName").getValue(String.class);
                        String time = postSnapshot.child("time").getValue(String.class);
                        int likes = postSnapshot.child("postLikeCount").getValue(Integer.class);
                        int scraps = postSnapshot.child("postScrapCount").getValue(Integer.class);
                        int replies = postSnapshot.child("postReplyCount").getValue(Integer.class);
                        Boolean hasPhoto = postSnapshot.child("postHasPhoto").getValue(Boolean.class);

                        adapter1.addItem(new List(title, content, postWriterName, time, likes, scraps, replies, hasPhoto));
                        adapter1.notifyDataSetChanged();
                        // List list = new List(title, content, postWriterName, time, likes, scraps, replies, hasPhoto);
                    }

                    if (num == nums.get(1)) {
                        String title = postSnapshot.child("postTitle").getValue(String.class);
                        String content = postSnapshot.child("postContent").getValue(String.class);
                        String postWriterName = postSnapshot.child("postWriterName").getValue(String.class);
                        String time = postSnapshot.child("time").getValue(String.class);
                        int likes = postSnapshot.child("postLikeCount").getValue(Integer.class);
                        int scraps = postSnapshot.child("postScrapCount").getValue(Integer.class);
                        int replies = postSnapshot.child("postReplyCount").getValue(Integer.class);
                        Boolean hasPhoto = postSnapshot.child("postHasPhoto").getValue(Boolean.class);

                        adapter1.addItem(new List(title, content, postWriterName, time, likes, scraps, replies, hasPhoto));
                        adapter1.notifyDataSetChanged();
                        // List list = new List(title, content, postWriterName, time, likes, scraps, replies, hasPhoto);
                    }

                    if (num == nums.get(2)) {
                        String title = postSnapshot.child("postTitle").getValue(String.class);
                        String content = postSnapshot.child("postContent").getValue(String.class);
                        String postWriterName = postSnapshot.child("postWriterName").getValue(String.class);
                        String time = postSnapshot.child("time").getValue(String.class);
                        int likes = postSnapshot.child("postLikeCount").getValue(Integer.class);
                        int scraps = postSnapshot.child("postScrapCount").getValue(Integer.class);
                        int replies = postSnapshot.child("postReplyCount").getValue(Integer.class);
                        Boolean hasPhoto = postSnapshot.child("postHasPhoto").getValue(Boolean.class);

                        adapter1.addItem(new List(title, content, postWriterName, time, likes, scraps, replies, hasPhoto));
                        adapter1.notifyDataSetChanged();
                        // List list = new List(title, content, postWriterName, time, likes, scraps, replies, hasPhoto);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        // 이벤트 처리 리스너 설정
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List item = (List) adapter1.getItem(position);
            }
        });

        databaseReference.child("Post").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    int count = (int) postSnapshot.getChildrenCount();

                    String title = postSnapshot.child("postTitle").getValue(String.class);
                    String content = postSnapshot.child("postContent").getValue(String.class);
                    String postWriterName = postSnapshot.child("postWriterName").getValue(String.class);
                    String time = postSnapshot.child("time").getValue(String.class);
                    int likes = postSnapshot.child("postLikeCount").getValue(Integer.class);
                    int scraps = postSnapshot.child("postScrapCount").getValue(Integer.class);
                    int replies = postSnapshot.child("postReplyCount").getValue(Integer.class);
                    Boolean hasPhoto = postSnapshot.child("postHasPhoto").getValue(Boolean.class);

                    adapter2.addItem(new List(title, content, postWriterName, time, likes, scraps, replies, hasPhoto));
                    adapter2.notifyDataSetChanged();
                    // List list = new List(title, content, postWriterName, time, likes, scraps, replies, hasPhoto);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        // 이벤트 처리 리스너 설정
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List item = (List) adapter2.getItem(position);
            }
        });

    }

    class ListAdapter extends BaseAdapter {
        ArrayList<List> items = new ArrayList<List>();

        // Generate > implement methods
        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(List item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 뷰 객체 재사용
            ListItemView view = null;

            if (convertView == null) {
                view = new ListItemView(getApplicationContext());
            } else {
                view = (ListItemView) convertView;
            }

            List item = items.get(position);

            view.setTitle(item.getTitle());
            view.setContent(item.getContent());
            view.setLikeCount(item.getLikes());
            view.setScrapCount(item.getScraps());
            view.setReplyCount(item.getReplies());
            view.setDate(item.getDate());
            view.setPhoto(item.getHasPhoto());
            view.setWriterName(item.getWriter());

            return view;
        }
    }
}