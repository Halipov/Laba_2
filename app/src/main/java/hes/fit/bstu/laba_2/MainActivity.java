package hes.fit.bstu.laba_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import hes.fit.bstu.laba_2.educationmanager.ManagerActivity;

public class MainActivity extends AppCompatActivity {
    ShimmerTextView tv;
    Shimmer shimmer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.shimmer);
        if (shimmer != null && shimmer.isAnimating()) {
            shimmer.cancel();
        } else {
            shimmer = new Shimmer();
            shimmer.start(tv);
        }
    }

    public void btn_student(View view) {
        Intent student = new Intent(this, StudentActivity.class);
        startActivity(student);
    }

    public void btn_manager(View view) {
        Intent manager = new Intent(this, ManagerActivity.class);
        startActivity(manager);
    }
}
