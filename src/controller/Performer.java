package controller;

import model.Bus;
import model.Inspector;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class Performer
{
    public static final int DELTA = 10;
    private Bus bus;
    private static final String FILE_NAME_BEG = "level";
    private static final String FILE_EXT = ".xml";

    public Performer(int level)
    {
        bus = ReadFile.readFile(new File(FILE_NAME_BEG + level + FILE_EXT));
    }

    public Bus getBus()
    {
        return bus;
    }

    public Image getPlayerImg()
    {
        return bus.getPlayer().getImg();
    }

    public int getPlayerX()
    {
        return bus.getPlayer().getX();
    }

    public int getPlayerY()
    {
        return bus.getPlayer().getY();
    }

    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) bus.getPlayer().moveX(-DELTA);
        else
            if(e.getKeyCode() == KeyEvent.VK_RIGHT) bus.getPlayer().moveX(DELTA);
            else
                if(e.getKeyCode() == KeyEvent.VK_UP) bus.getPlayer().moveY(-DELTA);
                else
                    if(e.getKeyCode() == KeyEvent.VK_DOWN) bus.getPlayer().moveY(DELTA);
    }

    public boolean ifCollision()
    {
        for(Inspector inspector : bus.getInspectors())
            if(bus.getPlayer().getRectangle().intersects(inspector.getRectangle())) return true;

        return false;
    }
}
