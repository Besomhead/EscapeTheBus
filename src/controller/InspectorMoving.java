package controller;

import model.Inspector;
import static controller.Performer.Side;
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
        Side noWay = null;

        for(Inspector inspector : performer.getBus().getInspectors())
        {
            if(!performer.seatCollision(inspector).isEmpty())
                noWay = performer.whereIsSeat(inspector, performer.seatCollision(inspector));
            inspector.move(new Point(performer.getPlayerX(), performer.getPlayerY()), Performer.DELTA, noWay);
            noWay = null;
        }
    }
}
