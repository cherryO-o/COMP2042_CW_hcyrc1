package Controller;

import Components.CountDown;
import Components.Wall;
import GUI.GameBoardView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class is the Controller of GameBoard
 */
public class GameBoardController extends JComponent implements KeyListener, MouseListener, MouseMotionListener {

    private Timer gameTimer;
    private GameFrame owner;
    private GameBoardView view;

    private Wall wall;
    private CountDown countDown;


    public GameBoardController(GameFrame owner, GameBoardView view) {
        this.view = view;
        this.owner = owner;
        wall = new Wall(new Rectangle(0,0,view.DEF_WIDTH,view.DEF_HEIGHT),30,3,3,new Point(300,430));
        countDown = new CountDown();

//        exitButtonRect  = view.exitButtonRect;
//        restartButtonRect =  view.restartButtonRect;
//        continueButtonRect = view.continueButtonRect;

        this.initialize();
        this.gameTimer();
    }

    private void gameTimer() {
        gameTimer = new Timer(10, e ->{
            view.wall.move();
            view.wall.findImpacts();

            view.message2 = String.format("%d",countDown.getSeconds());
            view.message = String.format("Bricks: %d %s",wall.getBrickCount(),view.Lives());
            view.scoreCount = String.format("Score: %d", wall.getScore());

            if(view.wall.isBallLost()){
                if(wall.ballEnd()){
                    view.wall.resetScore();
                    countDown.resetTimer();
                    view.wall.wallReset();
                    view.message = "Game over";
                }
                view.wall.ballReset();
                gameTimer.stop();
            }
            else if(view.wall.isDone() || countDown.getSeconds() == 0){
                if(view.wall.hasLevel()){
                    view.message = "Go to Next Level";
                    gameTimer.stop();
                    countDown.resetTimer();
                    view.wall.highScore();
                    view.wall.resetScore();
                    view.wall.ballReset();
                    view.wall.wallReset();
                    view.wall.nextLevel();
                }
                else{
                    gameTimer.stop();
                    view.wall.highScore();
                    view.printHighScore();
                    view.message = "YOU HAVE REACHED THE END";
                }
            }

            view.repaint();
        });
    }
    private void initialize(){
        view.setPreferredSize(new Dimension(view.DEF_WIDTH, view.DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        view.addMouseListener(this);
        view.addMouseMotionListener(this);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    /**
     * Keys A & D to change player movement, Space to start/stop game,
     * @param keyEvent
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                view.wall.player.moveLeft();
                break;
            case KeyEvent.VK_D:
                view.wall.player.movRight();
                break;
            case KeyEvent.VK_ESCAPE:
                view.showPauseMenu = !view.showPauseMenu;
                view.repaint();
                gameTimer.stop();
                countDown.stopTimer();
                break;
            case KeyEvent.VK_SPACE:
                if(!view.showPauseMenu)
                    if(gameTimer.isRunning()) {
                        gameTimer.stop();
                        countDown.stopTimer();
                    }
                    else {
                        gameTimer.start();
                        countDown.countDownStart();
                    }
                break;
            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                    view.debugConsole.setVisible(true);
            default:
                view.wall.player.stop();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        view.wall.player.stop();
    }

    /**
     * Click Continue to continue game
     * Click Restart to restart level
     * Click Exit to exit game
     * @param mouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!view.showPauseMenu)
            return;
        if(view.continueButtonRect.contains(p)){
            view.showPauseMenu = false;
            view.repaint();
        }
        else if(view.restartButtonRect.contains(p)){
            view.message = "Restarting Game...";
            wall.ballReset();
            view.wall.wallReset();
            view.showPauseMenu = false;
            countDown.resetTimer();
            wall.resetScore();
            view.repaint();
        }
        else if(view.exitButtonRect.contains(p)){
            System.exit(0);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(view.exitButtonRect != null && view.showPauseMenu) {
            if (view.exitButtonRect.contains(p) || view.continueButtonRect.contains(p) || view.restartButtonRect.contains(p))
                view.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                view.setCursor(Cursor.getDefaultCursor());
        }
        else{
            view.setCursor(Cursor.getDefaultCursor());
        }
    }

    public void onLostFocus(){
        gameTimer.stop();
        countDown.stopTimer();
        view.message = "Focus Lost";
        view.repaint();
    }

}
