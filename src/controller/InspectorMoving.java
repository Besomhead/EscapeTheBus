package controller;

import model.Inspector;

import javax.swing.*;
import java.awt.*;

public class InspectorMoving implements Runnable
{
    private Performer performer;

    public InspectorMoving(Performer performer)
    {
        this.performer = performer;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                Thread.sleep(150);
                moveInspectors();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void moveInspectors()
    {
        for(Inspector inspector : performer.getBus().getInspectors())
            inspector.move(new Point(performer.getPlayerX(), performer.getPlayerY()), Performer.DELTA);
    }
}
