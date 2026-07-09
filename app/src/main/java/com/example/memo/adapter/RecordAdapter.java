package com.example.memo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.memo.R;
import com.example.memo.bean.Record;

import java.util.List;

/**
 * 备忘录列表适配器
 */
public class RecordAdapter extends BaseAdapter {

    private Context context;
    private List<Record> list;

    public RecordAdapter(Context context, List<Record> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_record, parent, false);
            holder = new ViewHolder();
            holder.tvTitle = convertView.findViewById(R.id.tv_item_title);
            holder.tvContent = convertView.findViewById(R.id.tv_item_content);
            holder.tvTime = convertView.findViewById(R.id.tv_item_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Record record = list.get(position);
        holder.tvTitle.setText(record.getS_title());
        holder.tvContent.setText(record.getS_con());
        holder.tvTime.setText(record.getS_time());

        return convertView;
    }

    static class ViewHolder {
        TextView tvTitle;
        TextView tvContent;
        TextView tvTime;
    }
}
