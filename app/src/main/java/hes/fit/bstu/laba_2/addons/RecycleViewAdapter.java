package hes.fit.bstu.laba_2.addons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import hes.fit.bstu.laba_2.R;
import hes.fit.bstu.laba_2.RecycleView;
import hes.fit.bstu.laba_2.units.Student;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private int layout;
    private List<Student> students = new ArrayList<>();

    public RecycleViewAdapter(Context context, List<Student> userNames) {
        this.students = userNames;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_students, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student;
        student = students.get(position);
        holder.imageView.setImageBitmap(student.getPhoto());
        holder.emailView.setText(student.getEmail());
        holder.nameView.setText(String.format("%s %s", student.getFirst_name(), student.getLast_name()));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView nameView;
        private TextView emailView;

        ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.flag);
            nameView = view.findViewById(R.id.Full_name);
            emailView = view.findViewById(R.id.email);
        }
    }
}
