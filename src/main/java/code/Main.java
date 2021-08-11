package code;

import code.utilities.KeyboardListener;
import code.utilities.ProjectSettings;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    final Canvas renderySpot;

    Color backColor;

    int refreshRate = 10;

    Maze maze;

    final KeyboardListener keyInput = new KeyboardListener();

    private Main(){
        renderySpot = new Canvas();
        renderySpot.setMinimumSize(ProjectSettings.DIM);
        renderySpot.setMaximumSize(ProjectSettings.DIM);
        renderySpot.setPreferredSize(ProjectSettings.DIM);



        this.add(renderySpot);
        this.setResizable(false);
        this.pack();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        SwingUtilities.invokeLater(this::start);
    }

    private void start(){
        // set the look and feel to the system look and feel

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        renderySpot.setVisible(true);
        // don't allow AWT to paint the canvas since we are
        this.renderySpot.setIgnoreRepaint(true);
        // enable double buffering (the JFrame has to be
        // visible before this can be done)
        this.renderySpot.createBufferStrategy(2);
        this.renderySpot.requestFocus();
        this.renderySpot.addKeyListener(keyInput);

        ProjectSettings.setCanvas(this.renderySpot);

        maze = new Maze();

        final Thread gameThread = new Thread(this::gameLoop);
        gameThread.setDaemon(true);
        gameThread.start();

        backColor = Color.black;
    }

    public void gameLoop(){
        while(!false){
            long startTime = System.currentTimeMillis();

            update();

            try {
                Thread.sleep(Math.max(0, refreshRate - (System.currentTimeMillis() - startTime)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            render();
        }
    }

    public void update(){
        maze.update(refreshRate);
    }

    public void render(){
        final Graphics2D g = (Graphics2D) renderySpot.getBufferStrategy().getDrawGraphics();
        g.setColor(backColor);
        g.fillRect(0,0,ProjectSettings.DIM.width,ProjectSettings.DIM.height);

        maze.render(g);

        renderySpot.getBufferStrategy().show();
    }

    public static void main(String[] args){
        try {
            Main tets = new Main();
            tets.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
