package Components;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountDown implements ActionListener{
    public CountDown(){}

    Timer timer = new Timer(1000, this);
    int minutes = 2;
    int seconds = 60;


    public void countDownStart() {
        timer.start();
    }

    public void resetTimer() {
        minutes = 2;
        seconds = 60;
    }

    public void stopTimer() {
        timer.stop();
    }

    public void actionPerformed(ActionEvent e) {

        if (seconds == 0)
            timer.stop();
        else{
            seconds--;
            System.out.println(seconds);
        }
    }
}
