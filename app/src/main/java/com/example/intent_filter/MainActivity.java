package com.example.intent_filter;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    Button search_button;
    Button camera_button;
    Button format_button;
    EditText search_field;
    ImageView camera_result;

    final int PICTURE_REQUEST = 293658;
    final int FORMAT_REQUEST = 896553;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_button = findViewById(R.id.search_button);
        camera_button = findViewById(R.id.camera_button);
        format_button = findViewById(R.id.format_button);
        camera_result = findViewById(R.id.camera_result);
        search_field = findViewById(R.id.search_field);
        search_button.setOnClickListener(search -> onClickSearch());
        camera_button.setOnClickListener(camera -> onCLickCamera());
        format_button.setOnClickListener(format -> onCLickFormat());

        search_field.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        search_field.setTextColor(Color.BLACK);
    }


    public void onClickSearch(){
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, search_field.getText().toString());
        startActivity(intent);
    }

    public void onCLickCamera () {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, PICTURE_REQUEST);
    }

    public void onCLickFormat () {
        Intent intent = new Intent(MainActivity.this, EditActivity.class);
        intent.putExtra("color", search_field.getCurrentTextColor());
        intent.putExtra("align", search_field.getTextAlignment());
        startActivityForResult(intent, FORMAT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case PICTURE_REQUEST:
                Bitmap picture = (Bitmap)data.getExtras().get("data");
                camera_result.setImageBitmap(picture);
                break;
            case FORMAT_REQUEST:
                if (resultCode == RESULT_OK) {
                    int color = data.getIntExtra("color", 0);
                    int align = data.getIntExtra("align", 0);
                    search_field.setTextColor(color);
                    search_field.setTextAlignment(align);
                }
                break;
            default:
                return;
        }
    }
}