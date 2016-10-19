package model;

import java.awt.*;

public class Door implements ShowInformation
{
    private static final String IMG_PATH = "img\\door.png";
    private PlayingAreaElement playingAreaElement;

    public Door(int x, int y)
    {
        playingAreaElement = new PlayingAreaElement(IMG_PATH, new Point(x, y));
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
