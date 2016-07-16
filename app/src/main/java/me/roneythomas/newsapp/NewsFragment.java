package me.roneythomas.newsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class NewsFragment extends ListFragment {

    ArrayList<News> newsArrayList;

    public static NewsFragment newInstance() {

        Bundle args = new Bundle();

        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_list_layout, container, false);
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            newsArrayList = NewsLab.getInstance(getContext()).getNews();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        NewsAdapter mNewsAdapter = new NewsAdapter(getContext(), newsArrayList);
        setListAdapter(mNewsAdapter);
    }

    public class NewsAdapter extends ArrayAdapter<News> {

        public NewsAdapter(Context context, ArrayList<News> mNews) {
            super(context, 0, mNews);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View listItemView = convertView;
            if (listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false);
            }

            listItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(newsArrayList.get(position).getLink());
                    startActivity(intent);
                }
            });

            TextView title_text_view = (TextView) listItemView.findViewById(R.id.news_heading);
            title_text_view.setText(newsArrayList.get(position).getTitle());

            TextView section_text_view = (TextView) listItemView.findViewById(R.id.section_name);
            section_text_view.setText(newsArrayList.get(position).getSectionName());
            return listItemView;
        }
    }
}
