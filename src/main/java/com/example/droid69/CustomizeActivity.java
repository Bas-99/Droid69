package com.example.droid69;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.content.res.ResourcesCompat;

import static com.example.droid69.R.font.font1;

public class CustomizeActivity extends AppCompatActivity {

    public static int package_font;

    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);

        Typeface font1 = ResourcesCompat.getFont(this, R.font.font1);   //1
        Typeface font2 = ResourcesCompat.getFont(this, R.font.font2);   //1
        Typeface font3 = ResourcesCompat.getFont(this, R.font.font3);   //1
        Typeface font4 = ResourcesCompat.getFont(this, R.font.font4);   //1
        Typeface font5 = ResourcesCompat.getFont(this, R.font.font5);   //1
        Typeface font6 = ResourcesCompat.getFont(this, R.font.font6);   //1

        radioGroup = findViewById(R.id.radioGroup);
        textView = findViewById(R.id.text_view_selected);

        Button buttonApply = findViewById(R.id.button_apply);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();

                radioButton = findViewById(radioId);
                textView.setText("Your Selected Font: "+ radioButton.getText());

                if (radioId == R.id.radio_one) {
                    textView.setTypeface(font1);
                    package_font =1;
                } else if (radioId == R.id.radio_two) {
                    textView.setTypeface(font2);
                    package_font =2;
                } else if (radioId == R.id.radio_three) {
                    textView.setTypeface(font3);
                    package_font =3;
                } else if (radioId == R.id.radio_four) {
                    textView.setTypeface(font4);
                    package_font =4;
                } else if (radioId == R.id.radio_five) {
                    textView.setTypeface(font5);
                    package_font = 5;
                } else if (radioId == R.id.radio_six) {
                    textView.setTypeface(font6);
                    package_font =6;
                }
                Intent i = new Intent(CustomizeActivity.this,AgendaActivity.class);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        startActivity(i);
                    }
                }, 2000);
            }
        });

    }
    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        Toast.makeText(this,"Selected Font: " + radioButton.getText(),Toast.LENGTH_SHORT).show();

    }
}