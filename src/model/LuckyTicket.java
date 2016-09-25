package model;

import java.awt.*;

public class LuckyTicket extends PlayingAreaElement
{
    private static String IMG_PATH = "img\\lucky_ticket.png";

    public LuckyTicket(int x, int y)
    {
        super(IMG_PATH, new Point(x, y));
    }
}
