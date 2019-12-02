package hes.fit.bstu.laba_2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;

import hes.fit.bstu.laba_2.addons.DBHelper;
import hes.fit.bstu.laba_2.addons.RecycleViewAdapter;
import hes.fit.bstu.laba_2.units.Student;

public class RecycleView extends AppCompatActivity {
    private AccountHeader headerResult = null;
    ArrayList<Student> people = new ArrayList<>();
    List<Student> userNames = new ArrayList<>();
    RecyclerView recyclerView;
    DBHelper dbHelper;
    SQLiteDatabase database;
    RecycleViewAdapter adapter;
    private LinearLayoutManager linearLayoutManagerVertical;
    private GridLayoutManager gridLayoutManagerVertical;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        recyclerView = findViewById(R.id.RecyclerView);
        setRecylcerListView();
        linearLayoutManagerVertical = new LinearLayoutManager(RecycleView.this, LinearLayoutManager.VERTICAL,false);
        gridLayoutManagerVertical =
                new GridLayoutManager(RecycleView.this,
                        2, //The number of Columns in the grid
                        LinearLayoutManager.VERTICAL,
                        false);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("RecycleView");
        new DrawerBuilder()
                .withActivity(this)

                .withAccountHeader(headerResult)
                .withDisplayBelowStatusBar(false)
                .withTranslucentStatusBar(false)
                .withDrawerLayout(R.layout.material_drawer_fits_not)
                .withToolbar(toolbar)
                //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_linear_layout).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_grid_layout)
                ).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if(position == 0)
                        {
                            Toast.makeText(RecycleView.this, "LinearLayout", Toast.LENGTH_SHORT).show();
                            recyclerView.setLayoutManager(linearLayoutManagerVertical);
                        }
                        if(position == 1)
                        {
                            Toast.makeText(RecycleView.this, "GridLayout", Toast.LENGTH_SHORT).show();
                            recyclerView.setLayoutManager(gridLayoutManagerVertical);
                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();



    }

    public void setRecylcerListView(){


        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        userNames.clear();
        String text = "";
        Cursor cursor = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {

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
        adapter = new RecycleViewAdapter(this, userNames);
        recyclerView.setAdapter(adapter);

        cursor.close();
        database.close();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }
}
