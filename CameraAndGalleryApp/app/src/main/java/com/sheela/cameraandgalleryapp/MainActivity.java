package com.sheela.cameraandgalleryapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imgImage;
    Button btnPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgImage= findViewById(R.id.imgImage);
        btnPicture=findViewById(R.id.btnPicture);
        btnPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
                loadCamera();
            }
        });

    }
    private void checkPermission(){
        if(ContextCompat.checkSelfPermission( this,Manifest.permission.CAMERA)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }
    private void loadCamera(){
        Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent,0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== 0 && resultCode==RESULT_OK);
        Bundle extras= data.getExtras();
        Bitmap imageBitmap=(Bitmap) extras.get("data");
        imgImage.setImageBitmap(imageBitmap);
    }

}
