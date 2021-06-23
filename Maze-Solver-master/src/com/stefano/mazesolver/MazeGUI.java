package com.stefano.mazesolver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MazeGUI extends JPanel {

    private int [][] maze;
    private final List<Integer> path;
    private final List<Integer> traversal;
    private int pathIndex;
    private int traversalIndex;

    public MazeGUI(int[][] maze, List<Integer> path, List<Integer> traversal, int pathIndex, int traversalIndex) {
        this.maze = maze;
        this.path = path;
        this.traversal = traversal;
        this.pathIndex = pathIndex;
        this.traversalIndex = traversalIndex;

        // game loop each 0.3 seconds
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                update();
                repaint();
            }
        }, 100, 200);
    }

    public void update() {
        if(traversalIndex < traversal.size()) {
            traversalIndex += 1;
        } else {
            pathIndex -= 2;
            if (pathIndex < 0) {
                pathIndex = 0;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // draw the maze
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                Color color;
                switch (maze[row][col]) {
                    case 1 : color = Color.BLACK; break;
                    case 9 : color = Color.RED; break;
                    default : color = Color.WHITE;
                }
                g.setColor(color);
                g.fillRect(30 * col, 30 * row, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(30 * col, 30 * row, 30, 30);
            }
        }

        // draw the traversal list
        for(int i=0; i<traversalIndex; i+=2) {
            int traversalX = traversal.get(i);
            int traversalY = traversal.get(i + 1);
            if(traversalIndex == traversal.size() && path.contains(traversalX) && path.contains(traversalY)) {
                continue;
            }
            if(traversalX == 1 && traversalY == 1) {
                g.setColor(Color.BLUE);
            } else if(traversalX == 11 && traversalY == 8) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.YELLOW);
            }
            g.fillRect(traversalX * 30, traversalY * 30, 30, 30);
        }

        if(traversalIndex == traversal.size()) {
            // draw the path list
            for(int i=path.size()-1; i>=pathIndex; i-=2) {
                int pathX = path.get(i - 1);
                int pathY = path.get(i);
                if(pathX == 1 && pathY == 1) {
                    g.setColor(Color.BLUE);
                } else if(pathX == 11 && pathY == 8) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.GREEN);
                }
                g.fillRect(pathX * 30, pathY * 30, 30, 30);
            }

            // draw the ball on path
            int pathX = path.get(pathIndex);
            int pathY = path.get(pathIndex + 1);
            g.setColor(Color.RED);
            g.fillOval(pathX * 30, pathY * 30, 30, 30);
        }
    }
}
