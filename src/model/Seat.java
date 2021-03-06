package model;


import java.awt.*;

public class Seat implements ShowInformation
{
    private static final String IMG_PATH = "img\\seat.png";
    private PlayingAreaElement playingAreaElement;

    public Seat(int x, int y)
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
