package controller;

import model.Bus;
import model.LuckyTicket;
import static model.Character.*;

import java.util.Random;

public class LuckyTicketAppearance implements Runnable
{
    private Performer performer;
    private Random ticketRandom;

    public LuckyTicketAppearance(Performer performer)
    {
        this.performer = performer;
        ticketRandom = new Random(System.currentTimeMillis());
    }


    @Override
    public void run()
    {
        int ticketX;
        int ticketY;

        while(true)
        {
            try
            {
                Thread.sleep(ticketRandom.nextInt(20000) + 10000);
                do
                {
                    ticketX = ticketRandom.nextInt(MAX_X);
                    ticketY = ticketRandom.nextInt(MAX_Y);
                }
                while (performer.seatCollision(new LuckyTicket(ticketX, ticketY)));

                Bus bus = performer.getBus();
                bus.add(new LuckyTicket(ticketX, ticketY));

                Thread.sleep(ticketRandom.nextInt(5000) + 2000);
                if (bus.getLuckyTicket() != null) {
                    bus.removeLuckyTicket();
                }
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
