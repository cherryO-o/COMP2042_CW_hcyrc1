package Components;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Countdown timer for the game
 */
public class CountDown implements ActionListener{
    public CountDown(){}

    Timer timer = new Timer(1000, this);
    int seconds = 120;


    public void countDownStart() {
        timer.start();
    }

    public void resetTimer() {
        seconds = 120;
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
