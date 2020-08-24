package com.apsara.topic1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    RadioButton rdcity, rdole,rdtiger;
    ImageView imgehero;
    Button btnclose;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        rdcity=findViewById(R.id.rdcity);
        rdtiger=findViewById(R.id.rdtiger);
        rdole=findViewById(R.id.rdhole);
        imgehero=findViewById(R.id.imgehero);
       btnclose =(Button) findViewById(R.id.btnclose);

        builder=new AlertDialog.Builder(this);

        rdcity.setOnClickListener(this);
        rdole.setOnClickListener(this);
        rdtiger.setOnClickListener(this);
rdcity.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        builder.setMessage("Do you want to close this application ?")
                .setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int  id ) {
                        imgehero.setImageResource(R.drawable.citylight);

                        Toast.makeText(getApplicationContext(), "you clicked yes",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Action for 'No' Button
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(), "you clicked No",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        //Creating dialog box
        AlertDialog alert =builder.create();
        alert.setTitle("My Tittle");
        alert.show();
    }
});
 rdole.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         builder.setMessage("Do you want to close this application ?")
                 .setCancelable(false)
                 .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int  id ) {
                         imgehero.setImageResource(R.drawable.hole);

                         Toast.makeText(getApplicationContext(), "you clicked yes",
                                 Toast.LENGTH_SHORT).show();
                     }
                 })
                 .setNegativeButton("No", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int id) {
                         //Action for 'No' Button
                         dialog.cancel();
                         Toast.makeText(getApplicationContext(), "you clicked No",
                                 Toast.LENGTH_SHORT).show();
                     }
                 });
         //Creating dialog box
         AlertDialog alert =builder.create();
         alert.setTitle("My Tittle");
         alert.show();
     }
 });
 rdtiger.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         builder.setMessage("Do you want to close this application ?")
                 .setCancelable(false)
                 .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int  id ) {
                         imgehero.setImageResource(R.drawable.tiger);
                         Toast.makeText(getApplicationContext(), "you clicked yes",
                                 Toast.LENGTH_SHORT).show();
                     }
                 })
                 .setNegativeButton("No", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int id) {
                         //Action for 'No' Button
                         dialog.cancel();
                         Toast.makeText(getApplicationContext(), "you clicked No",
                                 Toast.LENGTH_SHORT).show();
                     }
                 });
         //Creating dialog box
         AlertDialog alert =builder.create();
         alert.setTitle("My Tittle");
         alert.show();
     }
 });
    }


    @Override
    public void onClick(View v) {
//        switch ((v.getId()))
//        {
//            case R.id.rdcity :
//                imgehero.setImageResource(R.drawable.citylight);
//                break;
//
//            case R.id.rdtiger :
//                imgehero.setImageResource(R.drawable.tiger);
//                break;
//
//            case R.id.rdhole :
//                imgehero.setImageResource(R.drawable.hole);
//                break;
//
//
//
//        }
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Do you want to close this application ?")
                        .setCancelable(false)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int  id ) {
                                Toast.makeText(getApplicationContext(), "you clicked yes",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //Action for 'No' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(), "you clicked No",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                AlertDialog alert =builder.create();
                alert.setTitle("My Tittle");
                alert.show();
            }
        });



    }
}
