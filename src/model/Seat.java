package model;


import java.awt.*;

public class Seat extends PlayingAreaElement
{
    private static final String IMG_PATH = "img\\seat.png";

    public Seat(int x, int y)
    {
        super(IMG_PATH, new Point(x, y));
    }
}
