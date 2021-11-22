package View;

import javax.swing.*;
import java.awt.*;

public class Instructions extends JComponent {
    private static final String TITLE = "HOW TO PLAY";
    private static final String INSTRUCT1 = "1) Press SPACEBAR to START the game.";
    private static final String INSTRUCT2 = "2) Press A or D to move the bar to the LEFT and RIGHT";
    private static final String INSTRUCT3 = "3) Press ESC to go to the MENU";
    private static final String INSTRUCT4 = "4) Press RESTART to restart your CURRENT LEVEL";
    private static final String INSTRUCT5 = "5) Press EXIT to exit the game";
    private static final String INSTRUCT6 = "6) DESTROY all bricks to WIN";

    private Font textFont;
    private Font titleFont;

    private Color textColor;

    private Rectangle backButton;

    private GameFrame owner;
}
