package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import java.awt.Image;


public class Instructions extends JComponent implements MouseListener, MouseMotionListener {
    private static final String TITLE = "HOW TO PLAY";
    private static final String INSTRUCT1 = "1) Press SPACEBAR to START the game.";
    private static final String INSTRUCT2 = "2) Press A or D to move LEFT and RIGHT";
    private static final String INSTRUCT3 = "3) Press ESC to go to the MENU";
    private static final String INSTRUCT4 = "4) Press RESTART to restart LEVEL";
    private static final String INSTRUCT5 = "5) Press EXIT to exit the game";
    private static final String INSTRUCT6 = "6) DESTROY all bricks to WIN";

    private static final String BACK_TEXT = "BACK";

    private Font textFont;
    private Font titleFont;
    private Font backFont;

    private static final Color BG_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = Color.BLACK;//black
    private static final Color CLICKED_BUTTON_COLOR = new Color(255,255,255);
    private static final Color CLICKED_TEXT = new Color(255,255,255);

    private Rectangle menuFace;
    private Rectangle backButton;

    private GameFrame owner;

    private boolean backClicked;

    public Instructions(GameFrame owner, Dimension area) {
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;

        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btn = new Dimension(area.width / 3, area.height / 12);
        backButton = new Rectangle(btn);

        textFont = new Font("Noto Mono",Font.PLAIN,25);;
        titleFont = new Font("Noto Mono",Font.BOLD,35);
        backFont = new Font("Arial",Font.BOLD,25);
    }

    public void paint(Graphics g){
        drawInstruct((Graphics2D)g);
    }

    private void drawInstruct(Graphics2D g) {
        drawContainer(g);

        Color prevColor = g.getColor();
        Font prevFont = g.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g.translate(x,y);

        drawText(g);
        drawButton(g);

        g.translate(-x,-y);
        g.setFont(prevFont);
        g.setColor(prevColor);
    }

    private void drawContainer(Graphics2D g){

        g.setColor(BG_COLOR);
        g.fill(menuFace);

        Image picture = Toolkit.getDefaultToolkit().getImage("brownBrick.gif");
        Image newPic = picture.getScaledInstance(550,432, java.awt.Image.SCALE_SMOOTH);
        g.drawImage(picture, 0, 0, this);

    }

    private void drawText(Graphics2D g){

        g.setColor(TEXT_COLOR);
        FontRenderContext frc = g.getFontRenderContext();

        Rectangle2D titleRect = titleFont.getStringBounds(TITLE,frc);

        Rectangle2D xtxtRect1 = textFont.getStringBounds(INSTRUCT1,frc);
        Rectangle2D xtxtRect2 = textFont.getStringBounds(INSTRUCT2,frc);
        Rectangle2D xtxtRect3 = textFont.getStringBounds(INSTRUCT3,frc);
        Rectangle2D xtxtRect4 = textFont.getStringBounds(INSTRUCT4,frc);
        Rectangle2D xtxtRect5 = textFont.getStringBounds(INSTRUCT5,frc);
        Rectangle2D xtxtRect6 = textFont.getStringBounds(INSTRUCT6,frc);

        int sX, sY;

        sX = (int)(menuFace.getWidth() - titleRect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 6);
        g.setFont(titleFont);
        g.drawString(TITLE,sX,sY);

        sX = (int)(menuFace.getWidth() - xtxtRect1.getWidth()) / 4;
        sY += (int) xtxtRect1.getHeight() * 1.5;//add 10% of String height between the two strings
        g.setFont(textFont);
        g.drawString(INSTRUCT1,sX,sY);

        sX = (int)(menuFace.getWidth() - xtxtRect1.getWidth()) / 4;
        sY += (int) xtxtRect2.getHeight() * 1.1;//add 10% of String height between the two strings
        g.setFont(textFont);
        g.drawString(INSTRUCT2,sX,sY);

        sX = (int)(menuFace.getWidth() - xtxtRect1.getWidth()) / 4;
        sY += (int) xtxtRect3.getHeight() * 1.1;//add 10% of String height between the two strings
        g.setFont(textFont);
        g.drawString(INSTRUCT3,sX,sY);

        sX = (int)(menuFace.getWidth() - xtxtRect1.getWidth()) / 4;
        sY += (int) xtxtRect4.getHeight() * 1.1;//add 10% of String height between the two strings
        g.setFont(textFont);
        g.drawString(INSTRUCT4,sX,sY);

        sX = (int)(menuFace.getWidth() - xtxtRect1.getWidth()) / 4;
        sY += (int) xtxtRect5.getHeight() * 1.1;//add 10% of String height between the two strings
        g.setFont(textFont);
        g.drawString(INSTRUCT5,sX,sY);

        sX = (int)(menuFace.getWidth() - xtxtRect1.getWidth()) / 4;
        sY += (int) xtxtRect6.getHeight() * 1.1;//add 10% of String height between the two strings
        g.setFont(textFont);
        g.drawString(INSTRUCT6,sX,sY);
    }

    private void drawButton(Graphics2D g){

        FontRenderContext frc = g.getFontRenderContext();

        Rectangle2D btnRect = backFont.getStringBounds(BACK_TEXT,frc);
        g.setFont(backFont);

        int x = (menuFace.width - backButton.width) / 2;
        int y =(int) ((menuFace.height - backButton.height) * 0.8);

        backButton.setLocation(x,y);

        x = (int)(backButton.getWidth() - btnRect.getWidth()) / 2;
        y = (int)(backButton.getHeight() - btnRect.getHeight()) / 2;

        x += backButton.x;
        y += backButton.y + (backButton.height * 0.9);
        y-= 6;

        if(backClicked){
            Color tmp = g.getColor();
            g.setColor(CLICKED_BUTTON_COLOR);
            g.draw(backButton);
            g.setColor(CLICKED_TEXT);
            g.drawString(BACK_TEXT,x,y);
            g.setColor(tmp);
        }
        else{
            g.draw(backButton);
            g.drawString(BACK_TEXT,x,y);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)){
            owner.enableHomeMenu();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)){
            backClicked = true;
            repaint(backButton.x,backButton.y,backButton.width+1,backButton.height+1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(backClicked){
            backClicked = false;
            repaint(backButton.x,backButton.y,backButton.width+1,backButton.height+1);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());
    }
}