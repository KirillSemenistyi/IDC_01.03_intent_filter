package com.example.intent_filter;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.sql.ResultSet;

public class EditActivity extends AppCompatActivity {

    int align, color;
    Button accept_button;
    Button cancel_button;
    RadioButton RBBlack, RBRed, RBGreen;
    RadioButton RBLeft, RBRight, RBCenter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        align = intent.getIntExtra("align", 0);
        color = intent.getIntExtra("color", 0);

        accept_button = findViewById(R.id.accept_button);
        cancel_button = findViewById(R.id.cancel_button);
        accept_button.setOnClickListener(accept -> onClickAccept());
        cancel_button.setOnClickListener(cancel -> onClickCancel());

        RBBlack = findViewById(R.id.radioButtonBlack);
        RBBlack.setOnClickListener(black -> {color = Color.BLACK;});
        RBRed = findViewById(R.id.radioButtonRed);
        RBRed.setOnClickListener(red -> {color = Color.RED;});
        RBGreen = findViewById(R.id.radioButtonGreen);
        RBGreen.setOnClickListener(green -> {color = Color.GREEN;});

        RBLeft = findViewById(R.id.radioButtonLeft);
        RBLeft.setOnClickListener(left -> {align = View.TEXT_ALIGNMENT_TEXT_START;});
        RBRight = findViewById(R.id.radioButtonRight);
        RBRight.setOnClickListener(left -> {align = View.TEXT_ALIGNMENT_TEXT_END;});
        RBCenter = findViewById(R.id.radioButtonCenter);
        RBCenter.setOnClickListener(left -> {align = View.TEXT_ALIGNMENT_CENTER;});

        switch(color) {
            case Color.BLACK:
                RBBlack.setChecked(true);
                break;
            case Color.RED:
                RBRed.setChecked(true);
                break;
            case Color.GREEN:
                RBGreen.setChecked(true);
                break;
        }

        switch(align) {
            case 2:
                RBLeft.setChecked(true);
                break;
            case 3:
                RBRight.setChecked(true);
                break;
            case 4:
                RBCenter.setChecked(true);
                break;
        }
    }

    private void onClickAccept() {
        Intent data = new Intent();
        data.putExtra("color", color);
        data.putExtra("align", align);
        setResult(RESULT_OK, data);
        finish();
    }

    private void onClickCancel() {
        finish();
    }
}