package Components;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Creates crack on bricks
 */
public class Crack {

    private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;

    public static final int LEFT = 10;
    public static final int RIGHT = 20;
    public static final int UP = 30;
    public static final int DOWN = 40;
    public static final int VERTICAL = 100;
    public static final int HORIZONTAL = 200;


    private final Brick brick;
    private GeneralPath crack;
    private static Random rnd = Brick.getRND();

    private int crackDepth;
    private int steps;


    public Crack(Brick brick, int crackDepth, int steps) {
        this.brick = brick;

        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;

    }


    /**
     * Draws the crack
     * @return
     */
    public GeneralPath draw() {

        return crack;
    }

    public void reset() {
        crack.reset();
    }

    /**
     * Calculates the direction the crack will be facing
     * @param point = starting point of crack
     * @param direction = direction it moves towards to
     */
    protected void makeCrack(Point2D point, int direction) {
        Rectangle bounds = brick.brickFace.getBounds();

        Point impact = new Point((int) point.getX(), (int) point.getY());
        Point start = new Point();
        Point end = new Point();


        switch (direction) {
            case LEFT:
                start.setLocation(bounds.x + bounds.width, bounds.y);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                Point tmp = makeRandomPoint(start, end, VERTICAL);
                makeCrack(impact, tmp);

                break;
            case RIGHT:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x, bounds.y + bounds.height);
                tmp = makeRandomPoint(start, end, VERTICAL);
                makeCrack(impact, tmp);

                break;
            case UP:
                start.setLocation(bounds.x, bounds.y + bounds.height);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                tmp = makeRandomPoint(start, end, HORIZONTAL);
                makeCrack(impact, tmp);
                break;
            case DOWN:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x + bounds.width, bounds.y);
                tmp = makeRandomPoint(start, end, HORIZONTAL);
                makeCrack(impact, tmp);

                break;

        }
    }

    /**
     * Creates a crack
     * @param start = starting point of crack
     * @param end = where crack ends
     */
    protected void makeCrack(Point start, Point end) {

        GeneralPath path = new GeneralPath();


        path.moveTo(start.x, start.y);

        double w = (end.x - start.x) / (double) steps;
        double h = (end.y - start.y) / (double) steps;

        int bound = crackDepth;
        int jump = bound * 5;

        double x, y;

        for (int i = 1; i < steps; i++) {

            x = (i * w) + start.x;
            y = (i * h) + start.y + randomInBounds(bound);

            if (inMiddle(i, CRACK_SECTIONS, steps))
                y += jumps(jump, JUMP_PROBABILITY);

            path.lineTo(x, y);

        }

        path.lineTo(end.x, end.y);
        crack.append(path, true);
    }

    private int randomInBounds(int bound) {
        int n = (bound * 2) + 1;
        return rnd.nextInt(n) - bound;
    }

    private boolean inMiddle(int i, int steps, int divisions) {
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return (i > low) && (i < up);
    }

    private int jumps(int bound, double probability) {

        if (rnd.nextDouble() > probability)
            return randomInBounds(bound);
        return 0;

    }

    /**
     * Makes the crack move in a random direction
     * @param from
     * @param to
     * @param direction
     * @return
     */
    private Point makeRandomPoint(Point from, Point to, int direction) {

        Point out = new Point();
        int pos;

        switch (direction) {
            case HORIZONTAL:
                pos = rnd.nextInt(to.x - from.x) + from.x;
                out.setLocation(pos, to.y);
                break;
            case VERTICAL:
                pos = rnd.nextInt(to.y - from.y) + from.y;
                out.setLocation(to.x, pos);
                break;
        }
        return out;
    }

}
