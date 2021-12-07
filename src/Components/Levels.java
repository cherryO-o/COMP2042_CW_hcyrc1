package Components;

import java.awt.*;

/**
 * This class is to set and draw each level in the game
 * Separated from Wall class
 */
public class Levels {

    private static final int LEVELS_COUNT = 6;

    private MakeBrickFactory makeBrickFactory = new MakeBrickFactory();

    private final int CLAY = makeBrickFactory.CLAY;
    private final int STEEL = makeBrickFactory.STEEL;
    private final int CEMENT = makeBrickFactory.CEMENT;
    private final int SPEED = makeBrickFactory.SPEED;

    public Brick[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio){
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,SPEED, CLAY);
        tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,SPEED,CEMENT);
        tmp[2] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,SPEED, STEEL);
        tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CEMENT);
        tmp[4] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,STEEL);
        tmp[5] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,CEMENT);
        return tmp;
    }

    /**
     * Draw out 2 types of bricks in chessboard style
     * @param drawArea = draw the area where the bricks should be
     * @param brickCnt = number of bricks
     * @param lineCnt = number of lines made between bricks
     * @param brickSizeRatio = size of brick
     * @param typeA = 1st type of brick
     * @param typeB = 2nd type of brick
     * @return tmp, type Brick[]
     */
    private Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  makeBrickFactory.makeBrick(p,brickSize,typeA) : makeBrickFactory.makeBrick(p,brickSize,typeB);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = makeBrickFactory.makeBrick(p,brickSize,typeA);
        }
        return tmp;
    }
}
