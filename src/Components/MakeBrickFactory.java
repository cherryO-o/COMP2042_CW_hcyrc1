package Components;

import java.awt.*;

/**
 * The brick factory where different brick types are referenced
 */
public class MakeBrickFactory {
    public final int CLAY = 1;
    public final int STEEL = 2;
    public final int CEMENT = 3;
    public final int SPEED = 4;

    private Dimension size;

    /**
     * MakeBrickFactory constructor
     * @param size = gets size of brick
     */
    public MakeBrickFactory(Dimension size) {
        this.size = size;
    }

    /**
     * The brick factory where different brick types objects are made
     * @param point = location of brick
     * @param type = type of brick
     * @return
     */
    public Brick makeBrick(Point point, int type){
        Brick out = switch (type) {
            case CLAY -> new ClayBrick(point, size);
            case STEEL -> new SteelBrick(point, size);
            case CEMENT -> new CementBrick(point, size);
            case SPEED -> new SpeedBrick(point, size);
            default -> throw new IllegalArgumentException(String.format("Unknown Type:%d\n", type));
        };
        return out;
    }
}
