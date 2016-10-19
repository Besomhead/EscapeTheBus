package model;

import java.util.ArrayList;
import java.util.List;

public class Bus
{
    private Player player;
    private List<Inspector> inspectors;
    private List<Seat> seats;
    private List<Door> doors;
    private LuckyTicket luckyTicket;
    private Button button;
    private static final int BUTTONS_TO_EXIT = 10;

    public Bus()
    {
        this.inspectors = new ArrayList<>();
        this.seats = new ArrayList<>();
        this.doors = new ArrayList<>();
        this.luckyTicket = null;
        this.button = null;
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

    public void add(Door door)
    {
        this.doors.add(door);
    }

    public void add(LuckyTicket ticket)
    {
        this.luckyTicket = ticket;
    }

    public void add(Button button)
    {
        this.button = button;
    }

    public void removeLuckyTicket()
    {
        this.luckyTicket = null;
    }

    public void removeButton()
    {
        this.button = null;
    }

    public Button getButton()
    {
        return this.button;
    }

    public LuckyTicket getLuckyTicket()
    {
        return this.luckyTicket;
    }

    public Player getPlayer()
    {
        return player;
    }

    public List<Inspector> getInspectors()
    {
        return new ArrayList<>(inspectors);
    }

    public List<Seat> getSeats()
    {
        return new ArrayList<>(seats);
    }

    public List<Door> getDoors()
    {
        return new ArrayList<>(doors);
    }

    public int getButtonsToExit()
    {
        return BUTTONS_TO_EXIT;
    }
}
