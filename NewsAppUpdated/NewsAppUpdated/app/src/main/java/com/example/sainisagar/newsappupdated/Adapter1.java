package com.example.sainisagar.newsappupdated;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saini Sagar on 2017-12-26.
 */

public class Adapter1 extends ArrayAdapter<News> {
    private static final String DATE_FORMAT = "MM/DD/yyyy";
    public Adapter1(Context context, ArrayList<News> news) {
        super(context, 0, news);
    }
    static class ViewHolder {
        TextView tvTitle;
        TextView tvDesc;
        TextView tvCategory;
        TextView tvDate;
        TextView tvAuthor;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        ViewHolder view;
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
            view = new ViewHolder();
            view.tvTitle = (TextView) convertView.findViewById(R.id.title);
            view.tvDesc = (TextView) convertView.findViewById(R.id.description);
            view.tvAuthor = (TextView) convertView.findViewById(R.id.author);
            view.tvCategory = (TextView) convertView.findViewById(R.id.category);
            view.tvDate = (TextView) convertView.findViewById(R.id.published_date);
            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        view.tvTitle.setText(news.getTitle());
        view.tvDesc.setText(news.getDesc());
        view.tvCategory.setText(news.getCategory());
        view.tvAuthor.setText(news.getAuthor());
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        if (news.getPublishDate() != null) {
            view.tvDate.setText(formatter.format(news.getPublishDate()));
        } else {
            view.tvDate.setText(R.string.no_date);
        }
        return convertView;
    }
}