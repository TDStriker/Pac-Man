package code;

import code.entities.PacMan;
import code.utilities.GameObject;
import code.utilities.ProjectSettings;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Maze implements GameObject {
    public final static boolean[][] map = { //false represents walls
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true , true , true , true , true , true , true , true , false, true , true , true , true , true , true , true , true , false},
            {false, true , false, false, true , false, false, false, true , false, true , false, false, false, true , false, false, true , false},
            {false, true , true , true , true , true , true , true , true , true , true , true , true , true , true , true , true , true , false},
            {false, true , false, false, true , false, true , false, false, false, false, false, true , false, true , false, false, true , false},
            {false, true , true , true , true , false, true , true , true , false, true , true , true , false, true , true , true , true , false},
            {false, false, false, false, true , false, false, false, true , false, true , false, false, false, true , false, false, false, false},
            {false, false, false, false, true , false, true , true , true , true , true , true , true , false, true , false, false, false, false},
            {false, false, false, false, true , false, true , false, false, true , false, false, true , false, true , false, false, false, false},
            {true , true , true , true , true , true , true , false, true , true , true , false, true , true , true , true , true , true , true },
            {false, false, false, false, true , false, true , false, false, false, false, false, true , false, true , false, false, false, false},
            {false, false, false, false, true , false, true , true , true , true , true , true , true , false, true , false, false, false, false},
            {false, false, false, false, true , false, true , false, false, false, false, false, true , false, true , false, false, false, false},
            {false, true , true , true , true , true , true , true , true , false, true , true , true , true , true , true , true , true , false},
            {false, true , false, false, true , false, false, false, true , false, true , false, false, false, true , false, false, true , false},
            {false, true , true , false, true , true , true , true , true , true , true , true , true , true , true , false, true , true , false},
            {false, false, true , false, true , false, true , false, false, false, false, false, true , false, true , false, true , false, false},
            {false, true , true , true , true , false, true , true , true , false, true , true , true , false, true , true , true , true , false},
            {false, true , false, false, false, false, false, false, true , false, true , false, false, false, false, false, false, true , false},
            {false, true , true , true , true , true , true , true , true , true , true , true , true , true , true , true , true , true , false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}
    };

    private final int border = 4;

    PacMan pac;

    public Maze(){
        pac = new PacMan(10,1);
    }

    public static boolean spaceValid(int x, int y){
        return (x>=0 && x < map[0].length) && (y>=0 && y < map.length) && map[y][x];
    }

    @Override
    public void update(int timePassed) {
        pac.update(timePassed);
    }

    @Override
    public void render(Graphics2D g) {
        AffineTransform old = g.getTransform();

        g.translate(border*2 + (ProjectSettings.DIM.width - (map[0].length*ProjectSettings.SQUARE_LENGTH))/2, (ProjectSettings.DIM.height - (map.length*ProjectSettings.SQUARE_LENGTH))/2);
        g.setStroke(new BasicStroke (border));
        g.setColor(Color.white);
        g.drawRect(0, 0, ((ProjectSettings.SQUARE_LENGTH* map[0].length) + 2 * border), ((ProjectSettings.SQUARE_LENGTH*map.length)+ 2 * border));
        g.translate(border,border);

        g.setStroke(new BasicStroke (border/4));

        for(int i = 0; i < map[0].length; i++){
            for(int j = 0; j < map.length; j++){
                if(!map[j][i]){
                    g.setColor(Color.blue);
                    g.drawRect(i*ProjectSettings.SQUARE_LENGTH,j*ProjectSettings.SQUARE_LENGTH,ProjectSettings.SQUARE_LENGTH,ProjectSettings.SQUARE_LENGTH);
                }
            }
        }

        g.translate(border/4,border/4);
        pac.render(g);

        g.setTransform(old);
    }
}
