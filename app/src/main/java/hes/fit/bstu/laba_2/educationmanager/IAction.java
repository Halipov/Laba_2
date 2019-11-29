package hes.fit.bstu.laba_2.educationmanager;

import hes.fit.bstu.laba_2.exception.EduException;
import hes.fit.bstu.laba_2.staff.Staff;

public interface IAction {
    Staff createGroup(Staff someCourse, int maxStudent, int maxListener, String filename);
    Staff generateCourse(Staff someCourse, int maxstudCount, int maxlistCount);
    int sumRanges (Staff anyCourse);
    int countListener (Staff anyCourse);

    //Staff sortByYear (Staff anyCourse);

}

