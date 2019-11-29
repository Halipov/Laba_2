package hes.fit.bstu.laba_2.addons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import hes.fit.bstu.laba_2.R;
import hes.fit.bstu.laba_2.units.Student;

public class StudentAdapter extends ArrayAdapter<Student> {
    private LayoutInflater inflater;
    private int layout;
    private List<Student> students;

    public StudentAdapter(@NonNull Context context, int resource, @NonNull List<Student> students) {
        super(context, resource, students);
        this.students = students;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);
        ImageView photoView = view.findViewById(R.id.flag);
        TextView nameView = view.findViewById(R.id.Full_name);
        TextView emailView = view.findViewById(R.id.email);
        Student student = students.get(position);
        photoView.setImageBitmap(student.getPhoto());
        nameView.setText(String.format("%s %s", student.getFirst_name(), student.getLast_name()));
        emailView.setText(student.getEmail());

        return view;
    }
}
