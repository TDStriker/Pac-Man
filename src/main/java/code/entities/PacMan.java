package code.entities;

import code.Maze;
import code.utilities.ProjectSettings;
import code.utilities.ResettableKeyHandler;

import java.awt.*;

public class PacMan extends Entity{
    private ResettableKeyHandler leftListener;
    private ResettableKeyHandler rightListener;
    private ResettableKeyHandler upListener;
    private ResettableKeyHandler downListener;

    public PacMan(int x, int y) {
        super(x, y);
        setColor(Color.yellow);

        this.leftListener = new ResettableKeyHandler(ProjectSettings.LEFT_KEY);
        this.rightListener = new ResettableKeyHandler(ProjectSettings.RIGHT_KEY);
        this.upListener = new ResettableKeyHandler(ProjectSettings.UP_KEY);
        this.downListener = new ResettableKeyHandler(ProjectSettings.DOWN_KEY);

        ProjectSettings.addKeyHandler(leftListener);
        ProjectSettings.addKeyHandler(rightListener);
        ProjectSettings.addKeyHandler(upListener);
        ProjectSettings.addKeyHandler(downListener);
    }

    @Override
    public void update(int timePassed) {
        super.update(timePassed);

        if(leftListener.checkAndReset()){
            if(Maze.spaceValid(x-1,y)) {
            x--;
            }else if(x-1 < 0){
                x = Maze.map[0].length-1;
            }
        }
        if(rightListener.checkAndReset()) {
            if (Maze.spaceValid(x + 1, y)) {
                x++;
            } else if (x + 1 >= Maze.map[0].length) {
                x = 0;
            }
        }
        if(downListener.checkAndReset()&& Maze.spaceValid(x,y+1)) {
            y++;
        }
        if(upListener.checkAndReset()&& Maze.spaceValid(x,y-1)){
            y--;
        }
    }


}
