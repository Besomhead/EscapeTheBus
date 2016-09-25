package model;

import java.awt.*;

public class Inspector extends PlayingAreaElement implements Character
{
    private static final String IMG_PATH = "img\\inspector.png";

    public Inspector(int x, int y)
    {
        super(IMG_PATH, new Point(x, y));
    }


    public void moveX(int delta)
    {
        if(this.getX() + delta > MIN_X && this.getX() + delta < MAX_X)
            position.setLocation(this.getX() + delta, this.getY());
    }

    public void moveY(int delta)
    {
        if(this.getY() + delta > MIN_Y && this.getY() + delta < MAX_Y)
            position.setLocation(this.getX(), this.getY() + delta);
    }

    public void move(Point playerPosition, int delta)
    {
        Point bestPosition = new Point();
        Point defaultPosition = new Point(position);
        double minDistance = Integer.MAX_VALUE;
        for(int xShift = -1; xShift < 2; xShift++)
            for(int yShift = -1; yShift < 2; yShift++)
                if(!((xShift == 0) && (yShift == 0)))
                {
                    moveX(xShift * delta);
                    moveY(yShift * delta);
                    double curDistance = playerPosition.distance(getX(), getY());
                    if (curDistance < minDistance)
                    {
                        minDistance = curDistance;
                        bestPosition = new Point(position);
                    }
                    position = new Point(defaultPosition);
                }

        position = new Point(bestPosition);
    }
}
