package com.example.a9900won_hackathon_duksung_postoffice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BaseAdapterEx extends android.widget.BaseAdapter {

    Context mContext = null;
    ArrayList<List> mData = null;
    LayoutInflater mLayoutInflater = null;

    public BaseAdapterEx(Context context, ArrayList<List> data)
    {
        mContext = context;
        mData = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public List getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemLayout = convertView;
        ViewHolder viewHolder = null;

        if(itemLayout == null)
        {
            itemLayout = mLayoutInflater.inflate(R.layout.subject_list_item, null);

            viewHolder = new ViewHolder();
//
            viewHolder.mContentTv = (TextView) itemLayout.findViewById(R.id.main_list_content_tv);
            viewHolder.mTitleTv = (TextView) itemLayout.findViewById(R.id.main_list_title_tv);
            viewHolder.mWriterTv = (TextView) itemLayout.findViewById(R.id.list_writer_tv);
            viewHolder.mDateTv = (TextView) itemLayout.findViewById(R.id.list_date_tv);

            itemLayout.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) itemLayout.getTag();
        }

        viewHolder.mContentTv = (TextView) itemLayout.findViewById(R.id.main_list_content_tv);
        viewHolder.mTitleTv = (TextView) itemLayout.findViewById(R.id.list_title_tv);
        viewHolder.mWriterTv = (TextView) itemLayout.findViewById(R.id.list_writer_tv);
        viewHolder.mDateTv = (TextView) itemLayout.findViewById(R.id.list_date_tv);

        return itemLayout;
    }

    class ViewHolder
    {
        TextView mTitleTv;
        TextView mContentTv;
        TextView mWriterTv;
        TextView mDateTv;

    }

    public void add(int index, List addData) {
        mData.add(index, addData);
        notifyDataSetChanged();
    }

    public void delete(int index) {
        mData.remove(index);
        notifyDataSetChanged();
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }
}