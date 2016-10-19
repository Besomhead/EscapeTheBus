package model;


public interface Character
{
    int MIN_X = 0;
    int MAX_X = 950;
    int MIN_Y = -15;
    int MAX_Y = 560;

    void moveX(int delta);
    void moveY(int delta);
}
