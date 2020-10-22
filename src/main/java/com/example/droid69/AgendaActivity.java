package com.example.droid69;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Build;

import android.view.MotionEvent;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;


@RequiresApi(api = Build.VERSION_CODES.O)
public class AgendaActivity extends AppCompatActivity {
    TextView monday, tuesday, wednesday,
            line1monday, line2monday, line3monday, line4monday,
            line1tuesday, line2tuesday, line3tuesday, line4tuesday,
            line1wednesday, line2wednesday, line3wednesday, line4wednesday;

    ConstraintLayout background;

    int packet_font = 4;     //the packet_font number will indicate which layout can be chosen by the user
    int packet_background = 3;

    float x1, x2, y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        monday = (TextView) findViewById(R.id.monday);
        line1monday = (TextView) findViewById(R.id.line1monday);
        line2monday = (TextView) findViewById(R.id.line2monday);
        line3monday = (TextView) findViewById(R.id.line3monday);
        line4monday = (TextView) findViewById(R.id.line4monday);

        tuesday = (TextView) findViewById(R.id.tuesday);
        line1tuesday = (TextView) findViewById(R.id.line1tuesday);
        line2tuesday = (TextView) findViewById(R.id.line2tuesday);
        line3tuesday = (TextView) findViewById(R.id.line3tuesday);
        line4tuesday = (TextView) findViewById(R.id.line4tuesday);

        wednesday = (TextView) findViewById(R.id.wednesday);
        line1wednesday = (TextView) findViewById(R.id.line1wednesday);
        line2wednesday = (TextView) findViewById(R.id.line2wednesday);
        line3wednesday = (TextView) findViewById(R.id.line3wednesday);
        line4wednesday = (TextView) findViewById(R.id.line4wednesday);

        background = (ConstraintLayout) findViewById(R.id.background);

        TextView[] textViews = new TextView[15];

        textViews[0] = monday;
        textViews[1] = tuesday;
        textViews[2] = wednesday;
        textViews[3] = line1monday;
        textViews[4] = line2monday;
        textViews[5] = line3monday;
        textViews[6] = line4monday;
        textViews[7] = line1tuesday;
        textViews[8] = line2tuesday;
        textViews[9] = line3tuesday;
        textViews[10] = line4tuesday;
        textViews[11] = line1wednesday;
        textViews[12] = line2wednesday;
        textViews[13] = line3wednesday;
        textViews[14] = line4wednesday;

        for (int i = 0; i < textViews.length; i++) {
            textViews[i].setTextSize(20);
        }

        for (int i = 0; i < 3; i++) {
            textViews[i].setTextSize(40);
        }

        if (packet_background == 1) {
            background.setBackgroundColor(Color.WHITE);
            for (int i = 0; i < textViews.length; i++) {
                textViews[i].setTextColor(Color.DKGRAY);
                int color = getResources().getColor(R.color.LightGrey);
                textViews[i].setHintTextColor(color);
                ViewCompat.setBackgroundTintList(textViews[i], ColorStateList.valueOf(color));
            }
        } else if (packet_background == 2) {
            background.setBackgroundColor(Color.BLACK);
            for (int i = 0; i < textViews.length; i++) {
                textViews[i].setTextColor(Color.WHITE);
                int color = getResources().getColor(R.color.LightGrey);
                textViews[i].setHintTextColor(color);
                ViewCompat.setBackgroundTintList(textViews[i], ColorStateList.valueOf(color));
            }
        } else if (packet_background == 3) {
            background.setBackgroundColor(Color.GRAY);
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
                if (x1 > x2) {
                    Intent i = new Intent(AgendaActivity.this, Agenda2Activity.class);
                    startActivity(i);
                    break;
                }
                return false;
        }
        return false;
    }
}