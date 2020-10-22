package com.example.droid69;

import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        ImageView img = (ImageView)findViewById(R.id.lastPage);
        Animation aniSlide = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);

        img.startAnimation(aniSlide);

        aniSlide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ThirdActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        startActivity(new Intent(ThirdActivity.this, IdentificationActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}