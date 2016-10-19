package model;

import javax.swing.*;
import java.awt.*;

public class PlayingAreaElement
{
    private Image img;
    private Point position;

    public PlayingAreaElement(String img_path, Point position)
    {
        loadImg(img_path);
        this.position = position;
    }

    void loadImg(String img_path)
    {
        img = new ImageIcon(img_path).getImage();
    }

    public Rectangle getRectangle()
    {
        return new Rectangle((int)position.getX(), (int)position.getY(), img.getWidth(null), img.getHeight(null));
    }

    public Image getImg()
    {
        return img;
    }

    public int getX()
    {
        return (int)position.getX();
    }

    public int getY()
    {
        return (int)position.getY();
    }

    public Point getPosition()
    {
        return position;
    }
}
