package hes.fit.bstu.laba_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hes.fit.bstu.laba_2.manual_registration.Rgstr_1;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
    }

    public void btn_manual_register(View view) {
        Intent intent = new Intent(this, Rgstr_1.class);
        startActivity(intent);
    }

    public void btn_generate(View view) {
    }

    public void btn_generate_from_json(View view) {
    }

    public void btn_generate_from_sql(View view) {
    }

    public void btn_back(View view) {
        Intent intent = new Intent( this, MainActivity.class );
        startActivity( intent );
    }
}
