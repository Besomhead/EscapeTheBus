package model;

import java.awt.*;

public class Player implements Character, ShowInformation
{
    private static final String ORIGIN_IMG_PATH = "img\\player.png";
    private static final String WITH_SHIELD_IMG_PATH = "img\\player_with_shield.png";
    private PlayingAreaElement playingAreaElement;

    public Player(int x, int y)
    {
        playingAreaElement = new PlayingAreaElement(ORIGIN_IMG_PATH, new Point(x, y));
    }

    public void moveX(int delta)
    {
        if(playingAreaElement.getX() + delta > MIN_X && playingAreaElement.getX() + delta < MAX_X)
            playingAreaElement.getPosition().setLocation(playingAreaElement.getX() + delta, playingAreaElement.getY());
    }

    public void moveY(int delta)
    {
        if(playingAreaElement.getY() + delta > MIN_Y && playingAreaElement.getY() + delta < MAX_Y)
            playingAreaElement.getPosition().setLocation(playingAreaElement.getX(), playingAreaElement.getY() + delta);
    }

    public void equipShield(Boolean isEquipped)
    {
        if(isEquipped)
        {
            playingAreaElement.loadImg(WITH_SHIELD_IMG_PATH);
        }
        else
        {
            playingAreaElement.loadImg(ORIGIN_IMG_PATH);
        }
    }

    public int getX()
    {
        return playingAreaElement.getX();
    }

    public int getY()
    {
        return playingAreaElement.getY();
    }

    public Rectangle getRectangle()
    {
        return playingAreaElement.getRectangle();
    }

    public Image getImg()
    {
        return playingAreaElement.getImg();
    }
}
