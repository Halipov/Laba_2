package hes.fit.bstu.laba_2.units;

import hes.fit.bstu.laba_2.exception.EduException;

public class Student extends Person {
    private void Show(){
    if(getRating() < 0)
        try {
            throw new EduException("This number less then 0",getRating());
        } catch (EduException e) {
            e.printStackTrace();
        }
    }
}
