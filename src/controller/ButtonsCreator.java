package controller;

import model.Button;

import java.util.Random;
import static model.Character.*;

public class ButtonsCreator implements Runnable
{
    private Performer performer;

    public ButtonsCreator(Performer performer)
    {
        this.performer = performer;
    }

    public void run()
    {
        Random buttonsRand = new Random(System.currentTimeMillis());
        int buttonX, buttonY;

        while(true)
        {
            try
            {
                Thread.sleep(buttonsRand.nextInt(2000) + 2000);
                do
                {
                    buttonX = buttonsRand.nextInt(MAX_X);
                    buttonY = buttonsRand.nextInt(MAX_Y);
                }
                while (performer.seatCollision(new Button(buttonX, buttonY)));

                performer.getBus().add(new Button(buttonX, buttonY));

                Thread.sleep(buttonsRand.nextInt(5000) + 4000);
                if (performer.getBus().getButton() != null)
                    performer.getBus().removeButton();

            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
