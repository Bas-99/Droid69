package com.example.droid69;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;

public class Agenda2Activity extends AppCompatActivity {

    TextView thursday, line1thursday, line2thursday, line3thursday, line4thursday,
             friday, line1friday, line2friday, line3friday, line4friday,
             saturday, line1saturday, line2saturday,
             sunday, line1sunday, line2sunday;

    float x1,x2,y1,y2;

    int packet_background = 3;
    int packet_font = 4;

    ConstraintLayout background2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda2);
        thursday = (TextView) findViewById(R.id.thursday);
        line1thursday = (TextView) findViewById(R.id.line1thursday);
        line2thursday = (TextView) findViewById(R.id.line2thursday);
        line3thursday = (TextView) findViewById(R.id.line3thursday);
        line4thursday = (TextView) findViewById(R.id.line4thursday);

        friday = (TextView) findViewById(R.id.friday);
        line1friday = (TextView) findViewById(R.id.line1friday);
        line2friday = (TextView) findViewById(R.id.line2friday);
        line3friday = (TextView) findViewById(R.id.friday);
        line4friday = (TextView) findViewById(R.id.friday);

        saturday = (TextView) findViewById(R.id.saturday);
        line1saturday = (TextView) findViewById(R.id.line1saturday);
        line2saturday = (TextView) findViewById(R.id.line2saturday);

        sunday = (TextView) findViewById(R.id.sunday);
        line1sunday = (TextView) findViewById(R.id.line1sunday);
        line2sunday = (TextView) findViewById(R.id.line2sunday);

        background2 = (ConstraintLayout) findViewById(R.id.background2);

        TextView[] textViews = new TextView[16];

        textViews[0] = thursday;
        textViews[1] = friday;
        textViews[2] = saturday;
        textViews[3] = sunday;
        textViews[4] = line1thursday;
        textViews[5] = line2thursday;
        textViews[6] = line3thursday;
        textViews[7] = line4thursday;
        textViews[8] = line1friday;
        textViews[9] = line2friday;
        textViews[10] = line3friday;
        textViews[11] = line4friday;
        textViews[12] = line1saturday;
        textViews[13] = line2saturday;
        textViews[14] = line1sunday;
        textViews[15] = line2sunday;

        for (int i = 0; i < textViews.length; i++) {
            textViews[i].setTextSize(20);
        }

        for (int i = 0; i < 4; i++) {
            textViews[i].setTextSize(40);
        }

        if (packet_background == 1) {
            background2.setBackgroundColor(Color.WHITE);
            for (int i = 0; i < textViews.length; i++) {
                textViews[i].setTextColor(Color.DKGRAY);
                int color = getResources().getColor(R.color.LightGrey);
                textViews[i].setHintTextColor(color);
                ViewCompat.setBackgroundTintList(textViews[i], ColorStateList.valueOf(color));
            }
        } else if (packet_background == 2) {
            background2.setBackgroundColor(Color.BLACK);
            for (int i = 0; i < textViews.length; i++) {
                textViews[i].setTextColor(Color.WHITE);
                int color = getResources().getColor(R.color.LightGrey);
                textViews[i].setHintTextColor(color);
                ViewCompat.setBackgroundTintList(textViews[i], ColorStateList.valueOf(color));
            }
        } else if (packet_background == 3) {
            background2.setBackgroundColor(Color.GRAY);
            for (int i = 0; i < textViews.length; i++) {
                textViews[i].setTextColor(Color.WHITE);
                int color = getResources().getColor(R.color.LightGrey);
                textViews[i].setHintTextColor(color);
                ViewCompat.setBackgroundTintList(textViews[i], ColorStateList.valueOf(color));
            }
        }

        if (packet_font == 1) {
            Typeface font1 = ResourcesCompat.getFont(this, R.font.font1);   //1
            for (int i = 0; i < textViews.length; i++) {
                textViews[i].setTypeface(font1);
            }
        } else if (packet_font == 2) {
            Typeface font2 = ResourcesCompat.getFont(this, R.font.font2);   //2
            for (int i = 0; i < textViews.length; i++) {
                textViews[i].setTypeface(font2);
            }
        } else if (packet_font == 3) {
            Typeface font3 = ResourcesCompat.getFont(this, R.font.font3);   //3
            for (int i = 0; i < textViews.length; i++) {
                textViews[i].setTypeface(font3);
            }
        } else if (packet_font == 4) {
            Typeface font4 = ResourcesCompat.getFont(this, R.font.font4);   //4
            for (int i = 0; i < textViews.length; i++) {
                textViews[i].setTypeface(font4);
            }
        } else if (packet_font == 5) {
            Typeface font5 = ResourcesCompat.getFont(this, R.font.font5);   //5
            for (int i = 0; i < textViews.length; i++) {
                textViews[i].setTypeface(font5);
            }
        } else if (packet_font == 6) {
            Typeface font6 = ResourcesCompat.getFont(this, R.font.font6);   //6
            for (int i = 0; i < textViews.length; i++) {
                textViews[i].setTypeface(font6);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent touchEvent) {
        switch (touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if (x1 < x2) {
                    Intent i = new Intent(Agenda2Activity.this, AgendaActivity.class);
                    startActivity(i);
                    break;
                }
                return false;
        }
        return false;
    }
}