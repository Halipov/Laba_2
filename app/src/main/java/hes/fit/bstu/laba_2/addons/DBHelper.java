package hes.fit.bstu.laba_2.addons;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Persons.db";
    public static final String TABLE_NAME = "Persons";

    public static final String KEY_ID = "_id";
    public static final String KEY_First_name = "first_name";
    public static final String KEY_Last_name = "last_name";
    public static final String KEY_Date_of_birth = "date_of_birth";
    public static final String KEY_Email = "email";
    public static final String KEY_Phone = "phone";
    public static final String KEY_Organization = "organization";
    public static final String KEY_Status = "status";
    public static final String KEY_Course = "course";
    public static final String KEY_Photo = "photo";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ("
                + KEY_ID + " integer primary key,"
                + KEY_Photo + " blob, "
                + KEY_First_name + " text, "
                + KEY_Last_name + " text, "
                + KEY_Date_of_birth + " text, "
                + KEY_Email + " text, "
                + KEY_Phone + " text, "
                + KEY_Organization + " text, "
                + KEY_Status + " text, "
                + KEY_Course + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);


    }

}
