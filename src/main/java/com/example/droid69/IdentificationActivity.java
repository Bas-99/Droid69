package com.example.droid69;

import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.w3c.dom.Text;

public class IdentificationActivity extends AppCompatActivity implements View.OnClickListener {



    private static final int RESULT_LOAD_IMAGE = 1;

    ImageView imageToUpload;
    EditText editName, editHobbies;
    TextView addPicture, thingsYouLike, fullNameTitle;
    Button submitButton;
    ColorMatrix matrix;
    ColorMatrixColorFilter filter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);

        editName = (EditText) findViewById(R.id.fullName);
        editHobbies = (EditText) findViewById(R.id.hobbys);
        addPicture = (TextView) findViewById(R.id.addPicture);
        thingsYouLike = (TextView) findViewById(R.id.thingsYouLike);
        fullNameTitle = (TextView) findViewById(R.id.fullNameTitle); 
        imageToUpload = (ImageView) findViewById(R.id.imageToUpload);
        submitButton = (Button) findViewById(R.id.check);

        addPicture.setVisibility(TextView.VISIBLE);

        imageToUpload.setOnClickListener(this);
        submitButton.setOnClickListener(this);

        matrix = new ColorMatrix();
        matrix.setSaturation(0);

        filter = new ColorMatrixColorFilter(matrix);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageToUpload:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                imageToUpload.setColorFilter(filter);
                addPicture.setVisibility(TextView.INVISIBLE);
                break;
            case R.id.check:
                String fullName = editName.getText().toString();
                String hobbies = editHobbies.getText().toString();
                editName.setText(fullName);
                editHobbies.setText(hobbies);
                IdentificationActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        startActivity(new Intent(IdentificationActivity.this, AgendaActivity.class));
                    }
                });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            imageToUpload.setImageURI(selectedImage);
        }
    }
}
