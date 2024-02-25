package com.example.intent_filter;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.intent_filter.R;


public class MainActivity extends AppCompatActivity{

    Button search_button;
    Button camera_button;
    EditText search_field;
    ImageView camera_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_button = findViewById(R.id.search_button);
        camera_button = findViewById(R.id.camera_button);
        camera_result = findViewById(R.id.camera_result);
        search_field = findViewById(R.id.search_field);
        search_button.setOnClickListener(this::onClickSearch);
        camera_button.setOnClickListener(this::onCLickCamera);
    }


    public void onClickSearch(View view){
        Intent intent = new Intent("android.intent.action.WEB_SEARCH");
        intent.putExtra(SearchManager.QUERY, search_field.getText().toString());
        startActivity(intent);
    }

    public void onCLickCamera (View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                Bitmap picture = (Bitmap)data.getExtras().get("data");
                camera_result.setImageBitmap(picture);
            default:
                return;
        }
    }
}