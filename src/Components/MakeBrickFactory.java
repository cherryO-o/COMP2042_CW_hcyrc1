package Components;

import java.awt.*;

public class MakeBrickFactory {
    public final int CLAY = 1;
    public final int STEEL = 2;
    public final int CEMENT = 3;
    public final int SPEED = 4;

    public Brick makeBrick(Point point, Dimension size, int type){
        Brick out = switch (type) {
            case CLAY -> new ClayBrick(point, size);
            case STEEL -> new SteelBrick(point, size);
            case CEMENT -> new CementBrick(point, size);
            case SPEED -> new SpeedBrick(point, size);
            default -> throw new IllegalArgumentException(String.format("Unknown Type:%d\n", type));
        };
        return  out;
    }
}
