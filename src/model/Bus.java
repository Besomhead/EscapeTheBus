package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static model.Character.*;

public class Bus
{
    private static final int MAX_SEATS_IN_LINE = 15;
    private static final int INSPECTORS_AMOUNT = 2;
    private static final int SEATS_LINES_AMOUNT = 6;
    private static final int DOORS_AMOUNT = 3;
    private static final int STEP = 28;

    private Player player;
    private List<Inspector> inspectors;
    private List<Seat> seats;
    private List<Door> doors;

    public Bus()
    {
        inspectors = new ArrayList<>();
        seats = new ArrayList<>();
        doors = new ArrayList<>();
    }

    public void add(Player player)
    {
        this.player = player;
    }

    public void add(Inspector inspector)
    {
        this.inspectors.add(inspector);
    }

    public void add(Seat seat)
    {
        this.seats.add(seat);
    }

    private void createInspectors(int amount)
    {
        Random inspectorRand = new Random();

        for (int inspectorIndex = 0; inspectorIndex < amount; inspectorIndex++)
            inspectors.add(new Inspector(inspectorRand.nextInt(MAX_X), inspectorRand.nextInt(MAX_Y)));
    }

    private void createSeats(int amount)
    {
        Random seatsRand = new Random();

        int tempX;
        int tempY;
        int seatsCount;
        int seatsLineIndex = 0;

        while(seatsLineIndex < amount)
        {
            tempX = seatsRand.nextInt(MAX_X - 50);
            tempY = seatsRand.nextInt(MAX_Y - 50);
            seatsCount = seatsRand.nextInt(MAX_SEATS_IN_LINE);

            if(findSeat(new Point(tempX, tempY)))
            {
                tempX += STEP;
                tempY += STEP;
            }

            if (tempX + seatsCount * STEP < MAX_X - 50)
            {
                for (int seatIndex = 0; seatIndex < seatsCount; seatIndex++)
                {
                    seats.add(new Seat(tempX, tempY));
                    tempX += STEP;
                }
                seatsLineIndex++;
            }
            else
                if (tempY + seatsCount * STEP < MAX_Y - 50)
                {
                    for (int seatIndex = 0; seatIndex < seatsCount; seatIndex++)
                    {
                        seats.add(new Seat(tempX, tempY));
                        tempY += STEP;
                    }
                    seatsLineIndex++;
                }
        }
    }

    public Player getPlayer()
    {
        return player;
    }

    public List<Inspector> getInspectors()
    {
        return inspectors;
    }

    public List<Seat> getSeats()
    {
        return seats;
    }

    public boolean findSeat(Point point)
    {
        Seat toCheck = new Seat((int)point.getX(), (int)point.getY());

        for(Seat seat : seats)
            if (seat.getRectangle().intersects(toCheck.getRectangle())) return true;

        return false;
    }
}
