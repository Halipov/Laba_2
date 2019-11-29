package hes.fit.bstu.laba_2.staff;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hes.fit.bstu.laba_2.addons.DBHelper;
import hes.fit.bstu.laba_2.units.Person;

public class Staff {
    private ArrayList<Person> studlist;

    public Staff(){ studlist = new ArrayList<Person>();}
    public Staff(ArrayList<Person> persons){}

    public List<Person> getStudList(){return studlist;}

    public void setStudlist(ArrayList<Person> studlist){this.studlist = studlist;}

    public void add (Person item, SQLiteDatabase db)
    {

          ContentValues contentValues = new ContentValues();
          contentValues.put(DBHelper.KEY_First_name, item.getFirst_name());
          contentValues.put(DBHelper.KEY_Last_name, item.getLast_name());

          db.insert(DBHelper.TABLE_NAME,null,contentValues);

//        db.execSQL("CREATE TABLE IF NOT EXISTS students (first_name TEXT, last_name TEXT)");//, course TEXT, Date_of_birth TEXT, Email TEXT, Phone TEXT, Organization TEXT, Status TEXT
//        db.execSQL("INSERT INTO Students VALUES ('"+ item.getFirst_name()+"', '"+ item.getLast_name()+")';");//+"','"+ item.getDate_of_birth()+"','"+ item.getEmail()+"','"+ item.getOrganization()+"''"+ item.getStatus()+"'
//        Log.d("log_2_3_person", "person added");
    }
    public boolean remove (Person item){return true;}

    @Override
    public String toString(){return "kek";}

    public boolean compByYear (Person a, Person b){return  true;}

}
