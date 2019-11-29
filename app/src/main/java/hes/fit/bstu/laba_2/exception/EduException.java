package hes.fit.bstu.laba_2.exception;

public class EduException extends Exception {
    private int number;
    public int getNumber() {
        return number;
    }
public EduException(String message, int num){super(message);number=num;}


}
