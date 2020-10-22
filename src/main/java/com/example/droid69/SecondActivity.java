package com.example.droid69;

import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageButton;
import pl.droidsonroids.gif.GifImageView;

import java.util.Timer;
import java.util.TimerTask;

public class SecondActivity extends AppCompatActivity {

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final GifImageButton buttonGifImage = new GifImageButton(this);
        buttonGifImage.setBackgroundResource(R.drawable.animated_book);

        mp = MediaPlayer.create(getApplicationContext(), R.raw.book_flick);
        mp.start();

        new Timer().schedule(new TimerTask(){
           public void run() {
                SecondActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        mp.stop();
                        startActivity(new Intent(SecondActivity.this, ThirdActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    }
                });
            }
        }, 1150);
    }
}

