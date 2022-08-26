package com.example.a9900won_hackathon_duksung_postoffice;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.a9900won_hackathon_duksung_postoffice.R;

public class ListItemView extends LinearLayout {

    TextView main_list_title_tv;
    TextView main_list_content_tv;
    TextView main_list_thumbs_up_tv;
    TextView main_list_scrap_tv;
    TextView main_list_replay_tv;
    TextView main_list_photo_tv;
    TextView main_list_item_writer_tv;
    TextView main_list_item_date_tv;
    ImageView main_list_photo_iv;

    // Generate > Constructor

    public ListItemView(Context context) {
        super(context);
        init(context);
    }

    public ListItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    // singer_item.xml을 inflation
    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.main_list_item, this, true);

        main_list_title_tv = (TextView) findViewById(R.id.main_list_title_tv);
        main_list_content_tv = (TextView) findViewById(R.id.main_list_content_tv);
        main_list_thumbs_up_tv = (TextView) findViewById(R.id.main_list_thumbs_up_tv);
        main_list_scrap_tv = (TextView) findViewById(R.id.main_list_scrap_tv);
        main_list_replay_tv = (TextView) findViewById(R.id.main_list_replay_tv);
        main_list_item_writer_tv = (TextView) findViewById(R.id.main_list_item_writer_tv);
        main_list_item_date_tv = (TextView) findViewById(R.id.main_list_item_date_tv);
        main_list_photo_tv = (TextView) findViewById(R.id.main_list_photo_tv);
        main_list_photo_iv = (ImageView) findViewById(R.id.main_list_photo_iv);
    }

    public void setTitle(String title) {
        main_list_title_tv.setText(title);
    }

    public void setContent(String content) {
        main_list_content_tv.setText(content);
    }

    public void setLikeCount(int likes) {
        main_list_thumbs_up_tv.setText(Integer.toString(likes));
    }

    public void setScrapCount(int scraps) {
        main_list_scrap_tv.setText(Integer.toString(scraps));
    }

    public void setReplyCount(int replies) {
        main_list_replay_tv.setText(Integer.toString(replies));
    }

    public void setWriterName(String name) {
        main_list_item_writer_tv.setText(name);
    }

    public void setDate(String date) {
        main_list_item_date_tv.setText(date);
    }

    public void setPhoto(Boolean hasPhoto) {
        if(!hasPhoto) {
            main_list_photo_tv.setVisibility(View.INVISIBLE);
            main_list_photo_iv.setVisibility(View.INVISIBLE);
        } else {
            main_list_photo_tv.setText("1");
        }
    }
}