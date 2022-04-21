package com.example.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper myDatabaseHelper;
    private List<Book> bookList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabaseHelper = new MyDatabaseHelper(this, "library.db", null, 3);

        initBooks();
        BookAdapter adapter = new BookAdapter(MainActivity.this, R.layout.book_item, bookList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        Button add_category = (Button) findViewById(R.id.add_category);
        add_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCategoryActivity.class);
                startActivity(intent);
            }
        });

        Button add_book = (Button) findViewById(R.id.add_book);
        add_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initBooks() {
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        Cursor cursor_book = db.rawQuery("select Book.name,Book.price,Category.category_name " +
                "from Book INNER JOIN Category ON Book.category_id = Category.id", null);

        if (cursor_book.moveToFirst()) {
            do {
                String name = cursor_book.getString(cursor_book.getColumnIndex("name"));
                String price = String.valueOf(cursor_book.getDouble(cursor_book.getColumnIndex("price")));
                String category = cursor_book.getString(cursor_book.getColumnIndex("Category.category_name"));
                Book book = new Book(name, price, category);
                bookList.add(book);
            } while (cursor_book.moveToNext());
        }
        cursor_book.close();

        //myDatabaseHelper.getReadableDatabase();
        /*SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        Cursor cursor_book = db.query("Book",null,null,null,null,null,null);

        if(cursor_book.moveToFirst()){
            do{
                String name = cursor_book.getString(cursor_book.getColumnIndex("name"));
                String price = String.valueOf(cursor_book.getDouble(cursor_book.getColumnIndex("price")));
                String category = String.valueOf(cursor_book.getInt(cursor_book.getColumnIndex("category_id")));
                Cursor cursor_category = db.rawQuery("select * from Category where id=?",new String[]{""+category});
                if(cursor_category.moveToFirst()){
                    do{
                        String categoryname = cursor_category.getString(cursor_category.getColumnIndex("category_name"));
                        Intent intent = new Intent();
                        intent.putExtra("category_name",categoryname);
                    }while(cursor_category.moveToNext());
                }
                Book book = new Book(name,price,getIntent().getStringExtra("category_name"));
                bookList.add(book);
                cursor_category.close();
            } while(cursor_book.moveToNext());
        }
        cursor_book.close();*/

    }
}