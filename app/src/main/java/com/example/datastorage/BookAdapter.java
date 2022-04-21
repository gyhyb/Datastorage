package com.example.datastorage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class BookAdapter extends ArrayAdapter<Book> {
    private int resourceId;
    public BookAdapter(Context context, int textViewResourceId, List<Book> objects) {
        super(context, textViewResourceId,objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Book book = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView text_name = (TextView) view.findViewById(R.id.text_name);
        TextView text_price = (TextView) view.findViewById(R.id.text_price);
        TextView text_category = (TextView) view.findViewById(R.id.text_category);
        text_name.setText(book.getName());
        text_price.setText(book.getPrice());
        text_category.setText(book.getCategory());
        return view;
    }
}
