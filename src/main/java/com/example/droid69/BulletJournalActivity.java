package com.example.droid69;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class BulletJournalActivity extends Activity {

    ImageButton androidImageButton;
    TextView androidTextView;
    ImageView homeTitle, book;
    Animation title_slide_in, book_slide_in, shake;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidTextView = (TextView) findViewById(R.id.appBy);
        androidImageButton = (ImageButton) findViewById(R.id.imageButton);
        homeTitle = (ImageView) findViewById(R.id.homeTitle);
        book = (ImageView) findViewById(R.id.imageBook);

        title_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.title_slide_in);
        book_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.book_slide_in);
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);

        homeTitle.startAnimation(title_slide_in);
        book.startAnimation(book_slide_in);
        androidImageButton.startAnimation(title_slide_in);

        title_slide_in.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation title_slide_in) {

            }

            @Override
            public void onAnimationEnd(Animation title_slide_in) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        androidImageButton.startAnimation(shake);
                    }
                }, 500);
            }

            @Override
            public void onAnimationRepeat(Animation title_slide_in) {

            }
        });

        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation shake) {

            }

            @Override
            public void onAnimationEnd(final Animation shake) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        androidImageButton.startAnimation(shake);
                    }
                }, 2000);
            }

            @Override
            public void onAnimationRepeat(Animation shake) {

            }
        });

        androidImageButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openNewActivity();
            }
        });
    }
    public void openNewActivity(){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}