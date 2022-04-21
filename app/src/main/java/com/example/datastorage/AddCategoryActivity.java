package com.example.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCategoryActivity extends AppCompatActivity {

    EditText idEdit;
    EditText category_nameEdit;
    EditText category_codeEdit;
    private MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_categoty);

        myDatabaseHelper = new MyDatabaseHelper(this,"library.db",null,3);
        myDatabaseHelper.getReadableDatabase();

        idEdit = (EditText) findViewById(R.id.id);
        category_nameEdit = (EditText) findViewById(R.id.category_name);
        category_codeEdit = (EditText) findViewById(R.id.category_code);

        Button addconfirm = (Button) findViewById(R.id.add_confirm_category);
        addconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idEdit.getText().toString();
                String category_name = category_nameEdit.getText().toString();
                String category_code =category_codeEdit.getText().toString();

                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("id",id);
                values.put("category_name",category_name);
                values.put("category_code",category_code);
                db.insert("Category",null,values);
                values.clear();
                Intent intent = new Intent(AddCategoryActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}