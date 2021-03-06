package Components;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Countdown timer for the game, limit is 120 seconds
 */
public class CountDown implements ActionListener{
    public CountDown(){}

    Timer timer = new Timer(1000, this);
    int seconds = 120;

    /**
     * Starts countdown
     */
    public void countDownStart() {
        timer.start();
    }

    /**
     * Resets timer
     */
    public void resetTimer() {
        seconds = 120;
    }

    /**
     * Stops timer
     */
    public void stopTimer() {
        timer.stop();
    }

    public int getSeconds() {return seconds;}

    /**
     * Decreases timer at every second, if seconds = 0 then stop timer
     * @param e
     */
    public void actionPerformed(ActionEvent e) {

        if (seconds == 0)
            timer.stop();
        else
            seconds--;
    }
}
