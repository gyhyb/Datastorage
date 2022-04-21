package com.example.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddBookActivity extends AppCompatActivity {

    private EditText nameEdit;
    private EditText authorEdit;
    private EditText pagesEdit;
    private EditText priceEdit;
    private EditText categoryEdit;
    private MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        myDatabaseHelper = new MyDatabaseHelper(this,"library.db",null,3);
        myDatabaseHelper.getReadableDatabase();

        nameEdit = (EditText) findViewById(R.id.name);
        authorEdit = (EditText) findViewById(R.id.author);
        pagesEdit = (EditText) findViewById(R.id.pages);
        priceEdit = (EditText) findViewById(R.id.price);
        categoryEdit = (EditText) findViewById(R.id.category);

        Button addconfirm = (Button) findViewById(R.id.add_confirm_book);
        addconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEdit.getText().toString();
                String author = authorEdit.getText().toString();
                Integer pages = Integer.valueOf(pagesEdit.getText().toString());
                double price = Double.parseDouble(priceEdit.getText().toString());
                Integer category = Integer.valueOf(categoryEdit.getText().toString());
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name",name);
                values.put("author",author);
                values.put("pages",pages);
                values.put("price",price);
                values.put("category_id",category);
                db.insert("Book",null,values);
                values.clear();
                Intent intent = new Intent(AddBookActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}