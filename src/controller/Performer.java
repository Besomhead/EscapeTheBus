package controller;

import model.*;
import model.Button;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class Performer
{
    public static final int DELTA = 10;
    private Bus bus;
    private static final String FILE_NAME_BEG = "level";
    private static final String FILE_EXT = ".xml";
    public enum Side {Left, Right, Up, Down}
    private boolean impregnablePlayer = false;
    private int buttonsPushed = 0;

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
        Side noWay = null;

        if(!seatCollision(bus.getPlayer()).isEmpty())
        {
            noWay = whereIsSeat(bus.getPlayer(), seatCollision(bus.getPlayer()));
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT && noWay != Side.Left)
        {
            bus.getPlayer().moveX(-DELTA);
        }
        else
            if(e.getKeyCode() == KeyEvent.VK_RIGHT && noWay != Side.Right)
            {
                bus.getPlayer().moveX(DELTA);
            }
            else
                if(e.getKeyCode() == KeyEvent.VK_UP && noWay != Side.Up)
                {
                    bus.getPlayer().moveY(-DELTA);
                }
                else
                    if(e.getKeyCode() == KeyEvent.VK_DOWN && noWay != Side.Down)
                    {
                        bus.getPlayer().moveY(DELTA);
                    }
    }

    public boolean ifCollision()
    {
        for(Inspector inspector : bus.getInspectors())
            if(bus.getPlayer().getRectangle().intersects(inspector.getRectangle())) return true;

        return false;
    }

    public List<Seat> seatCollision(Player player)
    {
        List<Seat> result = new ArrayList<>();

        for(Seat seat : bus.getSeats())
            if(player.getRectangle().intersects(seat.getRectangle()))
            {
                result.add(seat);
            }

        return result;
    }

    public List<Seat> seatCollision(Inspector inspector)
    {
        List<Seat> result = new ArrayList<>();

        for(Seat seat : bus.getSeats())
            if(inspector.getRectangle().intersects(seat.getRectangle()))
            {
                result.add(seat);
            }

        return result;
    }

    public boolean seatCollision(LuckyTicket ticket)
    {
        for(Seat seat : bus.getSeats())
            if(seat.getRectangle().intersects(ticket.getRectangle())) return true;

        return false;
    }

    public boolean seatCollision(Button button)
    {
        for(Seat seat : bus.getSeats())
            if(seat.getRectangle().intersects(button.getRectangle())) return true;

        return false;
    }

    public Side whereIsSeat(Player player, List<Seat> seats)
    {
        Rectangle commonRect = seats.get(0).getRectangle();
        seats.remove(0);

        for(Seat seat : seats) commonRect.add(seat.getRectangle());
        Rectangle2D intersection = player.getRectangle().createIntersection(commonRect);
        
        if(intersection.getWidth() < intersection.getHeight())
        {
            if (commonRect.getX() <= player.getX()) return Side.Left;
            return Side.Right;
        }
        else
            if(commonRect.getY() < player.getY()) return Side.Up;

        return Side.Down;
    }

    public Side whereIsSeat(Inspector inspector, List<Seat> seats)
    {
        Rectangle commonRect = seats.get(0).getRectangle();
        seats.remove(0);

        for(Seat seat : seats) commonRect.add(seat.getRectangle());
        Rectangle2D intersection = inspector.getRectangle().createIntersection(commonRect);

        if(intersection.getWidth() < intersection.getHeight())
        {
            if (commonRect.getX() <= inspector.getX()) return Side.Left;
            return Side.Right;
        }
        else
        if(commonRect.getY() < inspector.getY()) return Side.Up;

        return Side.Down;
    }

    public boolean ifExit()
    {
        for(Door door : bus.getDoors())
            if(door.getRectangle().intersects(bus.getPlayer().getRectangle())) return true;

        return false;
    }

    public void setImpregnable(boolean isImpregnable)
    {
        this.impregnablePlayer = isImpregnable;
    }

    public boolean isPlayerImpregnable()
    {
        return impregnablePlayer;
    }

    public boolean ifLuckyTicketPicked()
    {
        if(bus.getLuckyTicket() == null) return false;

        if(bus.getPlayer().getRectangle().intersects(bus.getLuckyTicket().getRectangle()))
        {
            return true;
        }

        return false;
    }

    public int getButtonsAmount()
    {
        return bus.getButtonsToExit();
    }

    public boolean ifButtonPushed()
    {
        return bus.getButton() != null && bus.getButton().getRectangle().intersects(bus.getPlayer().getRectangle());
    }

    public void incPushedButtons()
    {
        buttonsPushed++;
    }

    public boolean ifEnoughButtons()
    {
        return buttonsPushed >= bus.getButtonsToExit();
    }

    public int getPushedButtons()
    {
        return buttonsPushed;
    }
}
