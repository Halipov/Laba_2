package hes.fit.bstu.laba_2.manual_registration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

import hes.fit.bstu.laba_2.R;
import hes.fit.bstu.laba_2.addons.DBHelper;
import hes.fit.bstu.laba_2.staff.Staff;
import hes.fit.bstu.laba_2.units.Listener;
import hes.fit.bstu.laba_2.units.Person;
import hes.fit.bstu.laba_2.units.Student;

public class Rgstr_3 extends AppCompatActivity {
    TextView Full_Name, Date, Email, Phone, Orga, Status, Course, lol;
    String status, first_name, last_name, city, date, orga, course, phone, email;
    ImageView photo;
    DBHelper dbHelper;
    SQLiteDatabase database;
    Bitmap bitmap;
    byte[] byteArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rgstr_3);
        Bundle arg = getIntent().getExtras();
        init();
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        if (arg != null) {

            first_name = arg.get("First name").toString();
            last_name = arg.get("Last name").toString();
            phone = arg.get("Phone").toString();
            email = arg.get("Email").toString();
            date = arg.get("Date").toString();
            orga = arg.get("Organization").toString();
            course = arg.get("Course").toString();
            status = arg.get("Status").toString();
            bitmap = (Bitmap) arg.get("Photo");

            Full_Name.setText(String.format("%s %s", first_name, last_name));
            Date.setText(date);
            Email.setText(email);
            Phone.setText(phone);
            Orga.setText(orga);
            Status.setText(String.format("Status: %s", status));
            Course.setText(String.format("Course: %s", course));
            photo.setImageBitmap(bitmap);
            photo.setRotation(270);
            byteArray = BitmapToByteArray(bitmap);
        }


    }

    private byte[] BitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }

    public void init() {
        Full_Name = findViewById(R.id.text_fn);
        Date = findViewById(R.id.text_date_of_birth);
        Email = findViewById(R.id.text_email);
        Phone = findViewById(R.id.text_sn);
        Orga = findViewById(R.id.text_organization);
        Status = findViewById(R.id.status);
        Course = findViewById(R.id.course);
        photo = findViewById(R.id.image_photo_end);

    }

    public void btn_sign(View view) {


        Staff staff = new Staff();
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        if (status.equals("Student")) {
            Student student = new Student();
            student.setFirst_name(first_name);
            student.setLast_name(last_name);
            student.setDate_of_birth(date);
            student.setEmail(email);
            student.setPhone_number(phone);
            student.setOrganization(orga);
            student.setStatus(status);
            student.setCourse(course);
            student.setPhoto(bitmap);
            student.setRating(50);
            add_in_db(student);

            //Toast.makeText(context, "Student "+ last_name+" has been registered", duration).show();
            //read_from_db();
        } else {
            Listener listener = new Listener();
            listener.setFirst_name("kek");
        }
    }

    public void add_in_db(Person person) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.KEY_First_name, person.getFirst_name());
        contentValues.put(DBHelper.KEY_Last_name, person.getLast_name());
        contentValues.put(DBHelper.KEY_Photo, byteArray);
        contentValues.put(DBHelper.KEY_Date_of_birth, person.getDate_of_birth());
        contentValues.put(DBHelper.KEY_Email, person.getEmail());
        contentValues.put(DBHelper.KEY_Phone, person.getPhone_number());
        contentValues.put(DBHelper.KEY_Organization, person.getOrganization());
        contentValues.put(DBHelper.KEY_Status, person.getStatus());
        contentValues.put(DBHelper.KEY_Course, person.getCourse());
        database.insert(DBHelper.TABLE_NAME, null, contentValues);
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

    }

    public void read_from_db() {
        String text = "";
        Cursor cursor = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {

            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int first_name = cursor.getColumnIndex(DBHelper.KEY_First_name);
            int last_name = cursor.getColumnIndex(DBHelper.KEY_Last_name);
            do {
                text = cursor.getString(first_name) + cursor.getString(last_name);
                Toast.makeText(this, "Finded", Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(this, "nothing", Toast.LENGTH_SHORT).show();
        }
        lol.setText(text);
        cursor.close();
    }

    public void btn_back(View view) {
        onBackPressed();
    }
}
