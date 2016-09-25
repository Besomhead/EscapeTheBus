package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bus
{
    private Player player;
    private List<Inspector> inspectors;
    private List<Seat> seats;
    private List<Door> doors;

    public Bus() throws IOException
    {
        player = new Player();
        inspectors = new ArrayList<>();
        createInspectors(2);
        seats = new ArrayList<>();
        doors = new ArrayList<>();
    }

    private void createInspectors(int amount) throws IOException
    {
        Random inspectorRand = new Random();

        for (int inspectorIndex = 0; inspectorIndex < amount; inspectorIndex++)
            inspectors.add(new Inspector(inspectorRand.nextInt(900), inspectorRand.nextInt(540)));
    }

    public Player getPlayer()
    {
        return player;
    }

    public List<Inspector> getInspectors()
    {
        return inspectors;
    }
}
