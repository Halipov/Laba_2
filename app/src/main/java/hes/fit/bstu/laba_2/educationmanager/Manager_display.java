package hes.fit.bstu.laba_2.educationmanager;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

import hes.fit.bstu.laba_2.Current_person;
import hes.fit.bstu.laba_2.R;
import hes.fit.bstu.laba_2.addons.DBHelper;
import hes.fit.bstu.laba_2.addons.StudentAdapter;
import hes.fit.bstu.laba_2.units.Student;

public class Manager_display extends AppCompatActivity {
    ListView listView;
    DBHelper dbHelper;
    EditText search;
    ArrayList<Student> people = new ArrayList<>();
    List<Student> userNames = new ArrayList<>();
    FloatingActionMenu fam;
    String str = "";
    FloatingActionButton fab1, fab2, fab3;
    StudentAdapter adapter;
    ImageButton search_btn;
    SQLiteDatabase database;
    Toolbar toolbar;
    int pos;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item)
    {

        final int id = item.getItemId();
        if (id == R.id.action_menu) {
            search.setVisibility(View.VISIBLE);
            search_btn.setVisibility(View.VISIBLE);
        }
        return true;

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Student student = userNames.get(info.position);
        switch (item.getItemId()) {
            case R.id.context_view:
                current_person_view(student.getEmail());
                return true;
            case R.id.delete:
                current_person_delete(student.getEmail());
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        listView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mngr_display);
        init();
        setSupportActionBar(toolbar);
        registerForContextMenu(listView);
        setListView();
//        Cursor query = database.rawQuery("SELECT * FROM "+DBHelper.TABLE_NAME,null);
//        String fin = "";
//        if(query.moveToFirst()){
//            do{
//                Student student = new Student();
//                student.setFirst_name(query.getString(1));
//                student.setLast_name(query.getString(2));
//                userNames.add(student);
//                people.add(student);
//                fin = fin + student.getFirst_name();
//            }
//            while (query.moveToNext());
//        }
//
//          query.close();
//             database.close();

    }
    public void setListView()
    {
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        userNames.clear();
        String text = "";
        Cursor cursor = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {

            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int first_name = cursor.getColumnIndex(DBHelper.KEY_First_name);
            int last_name = cursor.getColumnIndex(DBHelper.KEY_Last_name);
            int date_of_birth = cursor.getColumnIndex(DBHelper.KEY_Date_of_birth);
            int photo = cursor.getColumnIndex(DBHelper.KEY_Photo);
            int email = cursor.getColumnIndex(DBHelper.KEY_Email);
            int phone = cursor.getColumnIndex(DBHelper.KEY_Phone);
            int org = cursor.getColumnIndex(DBHelper.KEY_Organization);
            int status = cursor.getColumnIndex(DBHelper.KEY_Status);
            int course = cursor.getColumnIndex(DBHelper.KEY_Course);

            do {
                Student student = new Student();
                student.setFirst_name(cursor.getString(first_name));
                student.setLast_name(cursor.getString(last_name));
                student.setPhoto(BitmapFactory.decodeByteArray(cursor.getBlob(photo), 0, cursor.getBlob(photo).length));
                student.setDate_of_birth(cursor.getString(date_of_birth));
                student.setEmail(cursor.getString(email));
                student.setPhone_number(cursor.getString(phone));
                student.setOrganization(cursor.getString(org));
                student.setStatus(cursor.getString(status));
                student.setCourse(cursor.getString(course));
                userNames.add(student);
                people.add(student);
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(this, "nothing", Toast.LENGTH_SHORT).show();
        }

        adapter = new StudentAdapter(this, R.layout.list_students, userNames);
        listView.setAdapter(adapter);
        cursor.close();
        database.close();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Student selectedItem = userNames.get(position);
                Toast.makeText(Manager_display.this, "selected:" + selectedItem.getEmail(), Toast.LENGTH_SHORT).show();
                current_person_view(selectedItem.getEmail());
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });
    }
    public void current_person_view(String str) {
        Intent intent = new Intent(this, Current_person.class);
        intent.putExtra("current", str);
        startActivity(intent);
    }

    public void current_person_delete(String str) {
        database = dbHelper.getWritableDatabase();

        database.execSQL("DELETE FROM " + DBHelper.TABLE_NAME+ " WHERE "+DBHelper.KEY_Email+"='"+str+"'");
        database.close();
        Toast.makeText(this, "person: " + str + " deleted", Toast.LENGTH_SHORT).show();
        setListView();
    }

    public void init() {
        fam = findViewById(R.id.fam);
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        fab3 = findViewById(R.id.fab3);
        listView = findViewById(R.id.list_student);
        toolbar = findViewById(R.id.toolbar);
        search = findViewById(R.id.toolbar_search);
        search_btn = findViewById(R.id.search_button);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = false;
                for (Student d : userNames) {
                    if (d.getLast_name().contains("Nester")) {

                        listView.requestFocusFromTouch();
                        listView.setSelection(adapter.getPosition(d));
                        flag = true;
                    }
                }
                if (flag) {
                    Toast.makeText(Manager_display.this, "Finded", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(Manager_display.this, "Not find", Toast.LENGTH_SHORT).show();

            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    public void btn_search(View view) {
        boolean flag = false;
        for (Student d : userNames) {
            if (d.getLast_name().contains(search.getText())) {

                listView.requestFocusFromTouch();
                listView.setSelection(adapter.getPosition(d));
                flag = true;
            }
        }
        if (flag) {
            Toast.makeText(Manager_display.this, "Finded", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(Manager_display.this, "Not find", Toast.LENGTH_SHORT).show();
    }
}
