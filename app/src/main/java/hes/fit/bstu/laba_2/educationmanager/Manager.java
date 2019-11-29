package hes.fit.bstu.laba_2.educationmanager;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.Comparator;

import hes.fit.bstu.laba_2.exception.EduException;
import hes.fit.bstu.laba_2.staff.Staff;
import hes.fit.bstu.laba_2.units.Person;

public class Manager implements IAction {
    public Staff createGroup(Staff someCourse, int maxStudent, int maxListener, String filename){
        return someCourse;
    }

    public Staff generateCourse(Staff someCourse, int maxstudCount, int maxlistCount){
        return someCourse;
    };

    public int sumRanges (Staff anyCourse){return 1;}
    public int countListener (Staff anyCourse){return 1;}
  /*  Comparator<Person> compare = (o1,o2) -> {
        return Integer.compare(o1.getYear())
    }
*/
   // @TargetApi(Build.VERSION_CODES.N)
   // public Staff sortByYear (Staff anyCourse){return}
}

