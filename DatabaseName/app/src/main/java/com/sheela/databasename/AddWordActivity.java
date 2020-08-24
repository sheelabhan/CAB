package com.sheela.databasename;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddWordActivity extends AppCompatActivity {
    private EditText etWord, etMeaning;
    private Button btnAddWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        etWord = findViewById(R.id.etWord);
        etMeaning = findViewById(R.id.etMeaning);

        btnAddWord = findViewById(R.id.btnAddWord);
        final MyHelper myHelper = new MyHelper(this);
        final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();
        btnAddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = myHelper.InsertData(etWord.getText().toString(), etMeaning.getText().toString(), sqLiteDatabase);
                if (id > 0) {
                    Toast.makeText(AddWordActivity.this, "Successfull", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(AddWordActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
