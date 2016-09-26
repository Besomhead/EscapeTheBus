package model;

import java.awt.*;
import java.io.IOException;

public class Player extends PlayingAreaElement implements Character
{
    private static final String IMG_PATH = "img\\player.png";

    public Player(int x, int y)
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
}
