package model;

import java.awt.*;

public class Button implements ShowInformation
{
    private static final String ORIGIN_IMG_PATH = "img\\button.png";
    private static final String PUSHED_IMG_PATH = "img\\pushed_button.png";
    private PlayingAreaElement playingAreaElement;

    public Button(int x, int y)
    {
        playingAreaElement = new PlayingAreaElement(ORIGIN_IMG_PATH, new Point(x, y));
    }

    public void pushButton()
    {
        playingAreaElement.loadImg(PUSHED_IMG_PATH);
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
