package model;

import java.awt.*;
import static controller.Performer.Side;

public class Inspector implements Character, ShowInformation
{
    private static final String IMG_PATH = "img\\inspector.png";
    private PlayingAreaElement playingAreaElement;

    public Inspector(int x, int y)
    {
        playingAreaElement = new PlayingAreaElement(IMG_PATH, new Point(x, y));
    }


    public void moveX(int delta)
    {
        if(playingAreaElement.getX() + delta > MIN_X && playingAreaElement.getX() + delta < MAX_X)
            playingAreaElement.getPosition().setLocation(playingAreaElement.getX() + delta, playingAreaElement.getY());
        else
            playingAreaElement.getPosition().setLocation(playingAreaElement.getX() - delta, playingAreaElement.getY());
    }

    public void moveY(int delta)
    {
        if(playingAreaElement.getY() + delta > MIN_Y && playingAreaElement.getY() + delta < MAX_Y)
            playingAreaElement.getPosition().setLocation(playingAreaElement.getX(), playingAreaElement.getY() + delta);
        else
            playingAreaElement.getPosition().setLocation(playingAreaElement.getX(), playingAreaElement.getY() - delta);
    }

    public void move(Point playerPosition, int delta, Side noWay)
    {
        int[] xShift = makeXShift(noWay);
        int[] yShift = makeYShift(noWay);

        Point bestPosition = new Point();
        Point defaultPosition = new Point(playingAreaElement.getPosition());
        double minDistance = Integer.MAX_VALUE;
        for(int xShiftIndex = 0; xShiftIndex < xShift.length; xShiftIndex++)
            for(int yShiftIndex = 0; yShiftIndex < yShift.length; yShiftIndex++)
                if(!((xShift[xShiftIndex] == 0) && (yShift[yShiftIndex] == 0)))
                {
                    moveX(xShift[xShiftIndex] * delta);
                    moveY(yShift[yShiftIndex] * delta);
                    double curDistance = playerPosition.distance(playingAreaElement.getX(), playingAreaElement.getY());
                    if (curDistance < minDistance)
                    {
                        minDistance = curDistance;
                        bestPosition = new Point(playingAreaElement.getPosition());
                    }
                    playingAreaElement.getPosition().setLocation(new Point(defaultPosition));
                }

        playingAreaElement.getPosition().setLocation(new Point(bestPosition));
        /*System.out.print(playingAreaElement.getPosition());
        if(noWay != null) System.out.println(" " + noWay.toString());
        else System.out.println(" ");*/
    }

    private int[] makeXShift(Side noWay)
    {
        if(noWay != null)
            switch(noWay)
            {
                case Left: return new int[]{0, 1};
                case Right: return new int[]{-1, 0};
                case Down: return new int[]{-1};
                case Up: return new int[]{1};
            }

        return new int[]{-1, 0, 1};
    }

    private int[] makeYShift(Side noWay)
    {
        if(noWay != null)
            switch(noWay)
            {
                case Up: return new int[]{0, 1};
                case Down: return new int[]{-1, 0};
                case Left: return new int[]{-1};
                case Right: return new int[]{1};
            }

        return new int[]{-1, 0, 1};
    }

    public int getX()
    {
        return playingAreaElement.getX();
    }

    public int getY()
    {
        return playingAreaElement.getY();
    }

    public Rectangle getRectangle()
    {
        return playingAreaElement.getRectangle();
    }

    public Image getImg()
    {
        return playingAreaElement.getImg();
    }
}
