package hes.fit.bstu.laba_2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hes.fit.bstu.laba_2.addons.DBHelper;

public class Current_person extends AppCompatActivity {
    TextView Full_Name,Date,City,Phone,Orga,Status,Course,lol,Email;
    String status,first_name,last_name,city,date,orga,course,phone, str;
    DBHelper dbHelper;
    ImageView Photo;
    SQLiteDatabase database;
    byte[] byteArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_person);
        init();
        Bundle arg = getIntent().getExtras();
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        if (arg != null) {
        str = arg.get("current").toString();
        }

        Cursor cursor = database.rawQuery("SELECT * FROM "+ DBHelper.TABLE_NAME +" where email = '"+str+"';", null);
        if (cursor.moveToFirst()){

            int first_name = cursor.getColumnIndex(DBHelper.KEY_First_name);
            int last_name = cursor. getColumnIndex(DBHelper.KEY_Last_name);
            int date_of_birth = cursor.getColumnIndex(DBHelper.KEY_Date_of_birth);
            int photo = cursor.getColumnIndex(DBHelper.KEY_Photo);
            int email = cursor.getColumnIndex(DBHelper.KEY_Email);
            int phone = cursor.getColumnIndex(DBHelper.KEY_Phone);
            int org = cursor.getColumnIndex(DBHelper.KEY_Organization);
            int status = cursor.getColumnIndex(DBHelper.KEY_Status);
            int course = cursor.getColumnIndex(DBHelper.KEY_Course);

            do{
                Full_Name.setText(String.format("%s %s", cursor.getString(first_name), cursor.getString(last_name)));
                Date.setText(cursor.getString(date_of_birth));
                Email.setText(cursor.getString(email));
                Phone.setText(cursor.getString(phone));
                Orga.setText(cursor.getString(org));
                Status.setText(cursor.getString(status));
                Course.setText(cursor.getString(course));
                Photo.setImageBitmap(BitmapFactory.decodeByteArray(cursor.getBlob(photo),0,cursor.getBlob(photo).length));
                Photo.setRotation(270);
                Toast.makeText(this, "Finded", Toast.LENGTH_SHORT).show();
            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(this, "nothing", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

    public void init(){
        Full_Name = findViewById(R.id.text_fn);
        Date = findViewById(R.id.text_date_of_birth);
        Email = findViewById(R.id.text_email);
        Phone = findViewById(R.id.text_sn);
        Orga = findViewById(R.id.text_organization);
        Status = findViewById(R.id.status);
        Course = findViewById(R.id.course);
        Photo = findViewById(R.id.image_photo);
    }
}
