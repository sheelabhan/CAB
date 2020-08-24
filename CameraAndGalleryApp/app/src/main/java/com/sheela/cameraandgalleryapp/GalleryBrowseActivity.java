package com.sheela.cameraandgalleryapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class GalleryBrowseActivity extends AppCompatActivity {
     ImageView imgProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_browse);
        imgProfile=findViewById(R.id.imgProfile);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              BrowseImage();
            }
        });
    }
    private void BrowseImage(){
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(data==null){
                Toast.makeText(this, "please select an image",Toast.LENGTH_SHORT).show();

            }
        }
        Uri uri= data.getData();
        imgProfile.setImageURI(uri);
        

    }
    private  String getRealPathFromUri(Uri uri){
        String[] projection= {MediaStore.Images.Media.DATA};
        CursorLoader loader=  new CursorLoader(getApplicationContext(),uri, projection,null,
                null, null);
        Cursor cursor=loader.loadInBackground();
        int collIndex=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(collIndex);
        cursor.close();
        return result;

    }

}
