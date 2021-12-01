package Components;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountDown implements ActionListener{
    public CountDown(){}

    Timer timer = new Timer(1000, this);
    int seconds = 60;


    public void countDownStart() {
        timer.start();
    }

    public void resetTimer() {
        seconds = 60;
    }

    public void stopTimer() {
        timer.stop();
    }

    public int getSeconds() {return seconds;}

    public void actionPerformed(ActionEvent e) {

        if (seconds == 0)
            timer.stop();
        else{
            seconds--;
            System.out.println(seconds);
        }
    }
}
