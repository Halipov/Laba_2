package hes.fit.bstu.laba_2.manual_registration;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.InputStream;

import hes.fit.bstu.laba_2.R;
import hes.fit.bstu.laba_2.units.Person;

public class Rgstr_1 extends AppCompatActivity {
    EditText First_name, Last_name;
    private static final int CAMERA_REQUEST = 0;
    private static final int GALLERY_REQUEST = 1;
    Bitmap selectedImage;
    ImageView ivPhoto, camera, gallery;
    Person person;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rgstr_1);
        First_name = findViewById(R.id.edit_fn);
        Last_name = findViewById(R.id.edit_sn);
        ivPhoto = findViewById(R.id.image_photo);
        camera = findViewById(R.id.image_camera);
        gallery = findViewById(R.id.image_gallery);
    }

    public void btn_next(View view) {
        Intent intent = new Intent(this, Rgstr_2.class);
        ivPhoto.buildDrawingCache();
        Bitmap bitmap = ivPhoto.getDrawingCache();
        intent.putExtra("Photo", bitmap);
        intent.putExtra("First name", First_name.getText());
        intent.putExtra("Last name", Last_name.getText());
        startActivity(intent);
    }

    public void btn_back(View view) {
        onBackPressed();
//        Intent intent = new Intent(this, StudentActivity.class);
//        startActivity(intent);
    }

    public void btn_photo(final View view) {
        if (flag) {
            animate_out(camera);
            animate_out(gallery);
            flag = false;
        } else {
            animate(camera);
            animate(gallery);
            flag = true;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ivPhoto.setImageBitmap(bitmap);
            ivPhoto.setRotation(270);
        }
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
            final Uri imageUri = data.getData();
            InputStream imageStream = null;
            try {
                imageStream = getContentResolver().openInputStream(imageUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            selectedImage = BitmapFactory.decodeStream(imageStream);
            ivPhoto.setImageBitmap(selectedImage);
        }

    }

    public void btn_camera(View view) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    public void btn_gallery(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST);
    }

    public void animate(final ImageView obj) {
        Animation animation;
        animation = AnimationUtils.loadAnimation(this, R.anim.fade_show);
        obj.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                obj.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public void animate_out(final ImageView obj) {
        Animation animation;
        animation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        obj.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                obj.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}
