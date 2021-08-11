package code.entities;

import code.utilities.GameObject;
import code.utilities.ProjectSettings;

import java.awt.*;

public class Entity implements GameObject {
    static final int NORTH = 0;
    static final int EAST = 1;
    static final int SOUTH = 2;
    static final int WEST = 3;

    static final int[] dirX = {0,1,0,-1};
    static final int[] dirY = {-1,0,1,0};

    int x;
    int y;
    Color color;

    int dir;

    public Entity(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setColor(Color color){
        this.color = color;
    }

    @Override
    public void update(int timePassed) {

    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(color);
        g.fillOval(x* ProjectSettings.SQUARE_LENGTH,y*ProjectSettings.SQUARE_LENGTH,ProjectSettings.SQUARE_LENGTH,ProjectSettings.SQUARE_LENGTH);
    }
}
