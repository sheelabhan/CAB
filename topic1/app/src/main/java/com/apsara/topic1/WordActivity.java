package com.apsara.topic1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintStream;

public class WordActivity extends AppCompatActivity {
    private Button btnAddWord;
    private EditText etWord,etMeaning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        etWord=findViewById(R.id.etWord);
        etMeaning=findViewById(R.id.etMeaning);
        btnAddWord=findViewById(R.id.btnAddWord);

        btnAddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();

            }
        });

    }
    private void Save(){
        try{
            PrintStream printStream= new PrintStream(openFileOutput("words.txt", MODE_PRIVATE |MODE_APPEND));
            printStream.println(etWord.getText().toString()+ "->"+etMeaning.getText().toString());
            Toast.makeText(this,"Saved to"+getFilesDir(),Toast.LENGTH_SHORT).show();
        }
        catch (IOException e){
            Log.d("Dicitinary app","Error"+e.toString());
            e.printStackTrace();
        }
    }
}
