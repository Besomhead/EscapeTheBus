package model;

import java.awt.*;

public class LuckyTicket implements ShowInformation
{
    private static String IMG_PATH = "img\\lucky_ticket.png";
    private PlayingAreaElement playingAreaElement;
    private static final int INFLUENCE_TIME = 10000;

    public LuckyTicket(int x, int y)
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

    public int getInfluenceTime()
    {
        return INFLUENCE_TIME;
    }
}
