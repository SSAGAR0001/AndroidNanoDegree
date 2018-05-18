package com.example.sainisagar.project61;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Adapter1 extends ArrayAdapter<News> {
    private final static String DATE_FORMAT = "MM/dd/yyyy ";
    public Adapter1(Context context, ArrayList<News> news) {
        super(context, 0, news);
    }
    static class ViewHolder {
        TextView tvTitle;
        TextView tvDescription;
        TextView tvCategory;
        TextView tvAuthor;
        TextView tvPublishedDate;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        ViewHolder view;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
            view = new ViewHolder();
            view.tvTitle = (TextView) convertView.findViewById(R.id.title);
            view.tvDescription = (TextView) convertView.findViewById(R.id.description);
            view.tvCategory = (TextView) convertView.findViewById(R.id.category);
            view.tvAuthor = (TextView) convertView.findViewById(R.id.author);
            view.tvPublishedDate = (TextView) convertView.findViewById(R.id.published_date);
            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        view.tvTitle.setText(news.getTitle());
        view.tvDescription.setText(news.getDesc());
        view.tvCategory.setText(news.getCategory());
        view.tvAuthor.setText(news.getAuthor());

        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        if (news.getPublishDate() != null) {
            view.tvPublishedDate.setText(formatter.format(news.getPublishDate()));
        } else {
            view.tvPublishedDate.setText(R.string.no_date);
        }

        return convertView;
    }
}
