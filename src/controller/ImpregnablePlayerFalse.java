package controller;

import java.util.Timer;
import java.util.TimerTask;

public class ImpregnablePlayerFalse implements Runnable
{
    private int influenceTime;
    private Performer performer;

    public ImpregnablePlayerFalse(Performer performer)
    {
        this.performer = performer;
    }

    public void setInfluenceTime(int infTime)
    {
        this.influenceTime = infTime;
    }

    public void run()
    {
       /* try
        {
           if(performer.isPlayerImpregnable())
            {
                Thread.sleep(influenceTime);
                performer.setImpregnable(false);
            }
            else return;
        }
        catch (InterruptedException e)
        {
            return;
        }
*/
        Timer impregnablePlayerTimer = new Timer();
        impregnablePlayerTimer.schedule(new TimerTask()
        {
            public void run()
            {
                performer.setImpregnable(false);
            }
        }, influenceTime);
    }
}
