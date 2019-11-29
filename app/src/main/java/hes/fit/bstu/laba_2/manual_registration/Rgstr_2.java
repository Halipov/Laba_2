package hes.fit.bstu.laba_2.manual_registration;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import hes.fit.bstu.laba_2.R;

public class Rgstr_2 extends AppCompatActivity {
    EditText Date, City, Phone, Orga, Email;
    String first_name, last_name, status, course;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rgstr_2);
        Bundle arg = getIntent().getExtras();
        first_name = arg.get("First name").toString();
        last_name = arg.get("Last name").toString();
        bitmap = (Bitmap) arg.get("Photo");
        init();
    }

    public void init() {
        Date = findViewById(R.id.edit_date);
        Email = findViewById(R.id.edit_email);
        Phone = findViewById(R.id.edit_phone);
        Orga = findViewById(R.id.edit_organization);
    }

    public void btn_back(View view) {
        onBackPressed();
    }

    public void btn_next(View view) {
        Intent intent = new Intent(this, Rgstr_3.class);
        intent.putExtra("Photo", bitmap);
        intent.putExtra("First name", first_name);
        intent.putExtra("Last name", last_name);
        intent.putExtra("Date", Date.getText().toString());
        intent.putExtra("Email", Email.getText().toString());
        intent.putExtra("Phone", Phone.getText().toString());
        intent.putExtra("Organization", Orga.getText().toString());
        intent.putExtra("Status", status);
        intent.putExtra("Course", course);
        startActivity(intent);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rb_student:
                if (checked) {
                    status = "Student";
                }
                break;
            case R.id.rb_listener:
                if (checked) {
                    status = "Listener";
                }
                break;
            case R.id.rb_java:
                if (checked) {
                    course = "Java";
                }
                break;
            case R.id.rb_cs:
                if (checked) {
                    course = "C#";
                }
                break;
            case R.id.rb_ruby:
                if (checked) {
                    course = "Ruby";
                }
                break;
        }
    }
}
