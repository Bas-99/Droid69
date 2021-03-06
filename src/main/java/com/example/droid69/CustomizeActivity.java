package com.example.droid69;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

import static com.example.droid69.R.font.font1;

public class CustomizeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static int package_font;
    public static int package_background;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    ImageView theme1preview;

    RadioGroup radioGroup, radioGroupBackground;
    RadioButton radioButton, radioButtonBackground;
    TextView textView, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        theme1preview = findViewById(R.id.theme1preview);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Typeface font1 = ResourcesCompat.getFont(this, R.font.font1);   //1
        Typeface font2 = ResourcesCompat.getFont(this, R.font.font2);   //1
        Typeface font3 = ResourcesCompat.getFont(this, R.font.font3);   //1
        Typeface font4 = ResourcesCompat.getFont(this, R.font.font4);   //1
        Typeface font5 = ResourcesCompat.getFont(this, R.font.font5);   //1
        Typeface font6 = ResourcesCompat.getFont(this, R.font.font6);   //1

        radioGroup = findViewById(R.id.radioGroup);
        textView = findViewById(R.id.text_view_selected);

        textView2 = findViewById(R.id.selected_background);
        radioGroupBackground = findViewById(R.id.radioGroupBackground);

        if (CustomizeActivity.package_font == 1) {
            RadioButton b = (RadioButton)findViewById(R.id.radio_one);
            b.setChecked(true);
        } else if (CustomizeActivity.package_font == 2) {
            RadioButton b = (RadioButton)findViewById(R.id.radio_two);
            b.setChecked(true);
        }else if (CustomizeActivity.package_font == 3) {
            RadioButton b = (RadioButton)findViewById(R.id.radio_three);
            b.setChecked(true);
        }else if (CustomizeActivity.package_font == 4) {
            RadioButton b = (RadioButton)findViewById(R.id.radio_four);
            b.setChecked(true);
        }else if (CustomizeActivity.package_font == 5) {
            RadioButton b = (RadioButton)findViewById(R.id.radio_five);
            b.setChecked(true);
        }else if (CustomizeActivity.package_font == 6) {
            RadioButton b = (RadioButton)findViewById(R.id.radio_six);
            b.setChecked(true);
        }

        if (CustomizeActivity.package_background == 1) {
            RadioButton b = (RadioButton)findViewById(R.id.radio_b_one);
            b.setChecked(true);
        }else if (CustomizeActivity.package_background == 2) {
            RadioButton b = (RadioButton)findViewById(R.id.radio_b_two);
            b.setChecked(true);
        }else if (CustomizeActivity.package_background == 3) {
            RadioButton b = (RadioButton)findViewById(R.id.radio_b_three);
            b.setChecked(true);
        }

        Button buttonApply = findViewById(R.id.button_apply);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                int radioId2 = radioGroupBackground.getCheckedRadioButtonId();

                radioButton = findViewById(radioId);
                radioButtonBackground = findViewById(radioId2);

                textView.setText("Your Selected Font: " + radioButton.getText());
                textView2.setText("Your Selected Theme: " + radioButtonBackground.getText());

                if (radioId == R.id.radio_one) {
                    textView.setTypeface(font1);
                    package_font = 1;
                } else if (radioId == R.id.radio_two) {
                    textView.setTypeface(font2);
                    package_font = 2;
                } else if (radioId == R.id.radio_three) {
                    textView.setTypeface(font3);
                    package_font = 3;
                } else if (radioId == R.id.radio_four) {
                    textView.setTypeface(font4);
                    package_font = 4;
                } else if (radioId == R.id.radio_five) {
                    textView.setTypeface(font5);
                    package_font = 5;
                } else if (radioId == R.id.radio_six) {
                    textView.setTypeface(font6);
                    package_font = 6;
                }

                if (radioId2 == R.id.radio_b_one) {
                    package_background = 1;
                } else if (radioId2 == R.id.radio_b_two) {
                    package_background = 2;
                } else if (radioId2 == R.id.radio_b_three) {
                    package_background = 3;
                }

                Intent i = new Intent(CustomizeActivity.this, AgendaActivity.class);
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

        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_logout).setVisible(false);
        menu.findItem(R.id.nav_login).setVisible(false);

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_customize);

    }


    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        Toast.makeText(this, "Selected Font: " + radioButton.getText(), Toast.LENGTH_SHORT).show();

    }

    public void checkButtonBackground(View v) {
        int radioId = radioGroupBackground.getCheckedRadioButtonId();

        radioButtonBackground = findViewById(radioId);

        Toast.makeText(this, "Selected Theme: " + radioButtonBackground.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_customize:
                break;
            case R.id.nav_home:
                Intent intent = new Intent(CustomizeActivity.this,AgendaActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_share:
                Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_achievements:
                Intent intent2 = new Intent(CustomizeActivity.this,AchievementsActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_tasks:
                Intent intent3 = new Intent(CustomizeActivity.this,ToDoActivity.class);
                startActivity(intent3);
                break;
            case R.id.nav_profile:
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}