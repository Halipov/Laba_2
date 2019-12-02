package hes.fit.bstu.laba_2.educationmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hes.fit.bstu.laba_2.R;
import hes.fit.bstu.laba_2.RecycleView;

public class ManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
    }

    public void btn_display(View view) {
        Intent intent = new Intent(this, Manager_display.class);
        startActivity(intent);
    }

    public void btn_manager(View view) {
        switch (view.getId()){
            case R.id.btn_generate:
            {
                break;
            }
        }
    }


    public void btn_back(View view) {
    }

    public void btn_recycle_view(View view) {
       Intent intent = new Intent(this, RecycleView.class);
       startActivity(intent);
    }
}
